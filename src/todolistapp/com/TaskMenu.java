package todolistapp.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TaskMenu {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/todo_list";
	private static final String JDBC_USER = "root2";
	private static final String JDBC_PASSWORD = "";
	private String description;
	private UITaskMenu taskMenu = new UITaskMenu();
	private Scanner sc = new Scanner(System.in);

	public void showTasks(Connection cn, User user) {
		String SQL = "SELECT id, description FROM tasks WHERE user_id = ?";
		try {
			PreparedStatement state = cn.prepareStatement(SQL);
			state.setInt(1, user.getId());

			ResultSet rs = state.executeQuery();
			boolean taskfound = false;

			while (rs.next()) {
				taskfound = true;
				System.out.println("id: " + rs.getInt(1) + " Task: " + rs.getString(2));
			}

			if (!taskfound) {
				System.out.println("Vaya parece que no tienes ninguna tarea!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addTasks(Connection cn, User user) {
		System.out.print("Introduce the description of the task: ");
		description = sc.nextLine();
		String SQL = "INSERT INTO tasks (description, user_id) VALUES (?, ?)";
		try {

			PreparedStatement state = cn.prepareStatement(SQL);

			state.setString(1, description);
			state.setInt(2, user.getId());

			state.executeUpdate();

			System.out.println("La tarea se ha guardado correctamente!!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void removeTasks(Connection cn, User user) {
		String query = "DELETE FROM tasks WHERE id=?";
		System.out.print("Introduce el ID de la tarea que quieres borrar: ");
		int id = sc.nextInt(); 
		
		
		try {
			PreparedStatement state = cn.prepareStatement(query);
			state.setInt(1, id);
			int numBorrados = state.executeUpdate();
			
			if(numBorrados == 0) {
				System.out.println("ERROR: No hay ninguna tarea!");				
			}else {				
				System.out.println("Borrado correcto!");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start(User user) {
		try {
			Connection cn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			while (true) {
				taskMenu.printTaskMenu();
				System.out.print("Enter a choice: ");
				int option = sc.nextInt();
				sc.nextLine();

				switch (option) {
				case 1:
					showTasks(cn, user);
					break;
				case 2:
					addTasks(cn, user);
					break;
				case 3:
					removeTasks(cn, user);
					break;
				case 0:
					System.out.println("Saliendo al menu principal....");
					return;

				default:
					System.out.println("Invalid choice.");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
