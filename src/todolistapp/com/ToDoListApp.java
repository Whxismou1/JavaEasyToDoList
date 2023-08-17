package todolistapp.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ToDoListApp {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/todo_list";
	private static final String JDBC_USER = "root2";
	private static final String JDBC_PASSWORD = "";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu();

		try {
			Connection conexion = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			while (true) {
				menu.printMenu();
				System.out.print("Choose an option: ");
				int choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
				case 1:
					userLogin(sc, conexion);
					break;
				case 2:
					userRegister(sc, conexion);
					break;
				case 0:
					System.out.println("Saliendo....");
					System.exit(0);
				default:
					System.out.println("Invalid choice.");
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void userRegister(Scanner sc, Connection connection) {
		System.out.println("\n\n******Register Menu******");
		System.out.print("Username: ");
		String userIntroduced = sc.nextLine();
		
		String query = "SELECT id, username FROM users WHERE username = ?";
		
		try {
			PreparedStatement state = connection.prepareStatement(query);
			state.setString(1, userIntroduced);
			ResultSet rs = state.executeQuery();
			
			if(rs.next()) {
				System.out.println("ERROR: El usuario ya existe!");
			}else {
				System.out.print("Password: ");
				String passwordIntroduced = sc.nextLine();
				
				String sql = "INSERT INTO todo_list.users (username, password) VALUES (?, ?);";
				
				
				state = connection.prepareStatement(sql);
				state.setString(1, userIntroduced);
				state.setString(2, passwordIntroduced);
				state.executeUpdate();
				state.close();
				
				System.out.println("Usuario registrado correctamente!");
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void userLogin(Scanner sc, Connection connection) throws InterruptedException {

		System.out.println("\n\n******Login Menu******");
		System.out.print("Username: ");
		String userIntroduced = sc.nextLine();
		System.out.print("Password: ");
		String passwordIntroduced = sc.nextLine();

		String query = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
		try {
			PreparedStatement preparedState = connection.prepareStatement(query);
			preparedState.setString(1, userIntroduced);
			preparedState.setString(2, passwordIntroduced);

			try {
				ResultSet rs = preparedState.executeQuery();
				if (rs.next()) {
					int id = rs.getInt("id");
					String usernameBD = rs.getString("username");
					String passwordBD = rs.getString("password");

					User user = new User(id, usernameBD, passwordBD);
					System.out.println("Login successful. Welcome, " + user.getUsername());

					
					TaskMenu task = new TaskMenu();
					task.start(user);
					
					
				} else {
					System.out.println("Invalid username or password!\n\n");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
