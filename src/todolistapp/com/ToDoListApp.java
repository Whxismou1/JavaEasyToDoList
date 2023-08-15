package todolistapp.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				System.out.println("Choose an option: ");
				int choice = sc.nextInt();
				
				switch (choice) {
				case 1: 
					userLogin(sc, conexion);
					break;
				case 2:
					userRegister(sc, conexion);
					break;
				case 0:
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
		// TODO Auto-generated method stub
		
	}

	private static void userLogin(Scanner sc, Connection connection) throws SQLException {
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
				if(rs.next()) {
					int id = rs.getInt("id");
					String usernameBD = rs.getString("username");
					String passwordBD = rs.getString("password");
					
					User user = new User(id, usernameBD, passwordBD);
					System.out.println("Login successful. Welcome, " + user.getUsername());					
				}else {
					System.out.println("Invalid username or password!");
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		
		
		
		
		
		
		
	}

}
