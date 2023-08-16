package todolistapp.com;

public class User {
	
	private int id;
	private String Username;
	private String Password;
	
	public User(int id, String Username, String Password) {
		this.id = id;
		this.Username = Username;
		this.Password = Password;
	}
	
	
	public int getId() {
		return id;
	}
	
	
	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}



}
