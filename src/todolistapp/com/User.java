/***
 * 
 * @author Whxismou
 * 
 */
package todolistapp.com;

/***
 * 
 * Clase Usuario que almacena los atributos 
 * y metodos que tiene cada usuario de la app
 *
 */


public class User {

	private int id;
	private String Username;
	private String Password;

	/***
	 * Constructor de la clase Usuario
	 * @param id: Asigna el id del usuario que obtuvo de la BBDD
	 * @param Username: Asigna el usuario introducido por el user
	 * @param Password: Asigna el usuario introducido por el user
	 */
	public User(int id, String Username, String Password) {
		this.id = id;
		this.Username = Username;
		this.Password = Password;
	}
	

	/***
	 * Metodo que obtiene el id
	 * @return id 
	 */
	public int getId() {
		return id;
	}

	/***
	 * Metodo que obtiene el usuario
	 * @return Username 
	 */
	public String getUsername() {
		return Username;
	}

	/***
	 * Metodo que obtiene el password
	 * @return Password
	 */
	public String getPassword() {
		return Password;
	}

}
