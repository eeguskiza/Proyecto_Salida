package app;

public class User {

	private String nombre;
	private String apellido;
	private int telefono;
	private int edad;
	private String username;
	private String password;
	
	private static int num =0;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String nombre, String apellido, int telefono, int edad, String username, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.edad = edad;
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password) {
		super();
		this.nombre = "UserWithoutProperties_"+num;
		this.apellido = "";
		this.telefono = 0;
		this.edad = 18;
		this.username = username;
		this.password = password;
		num++;
	}
	@Override
	public String toString() {
		return "User [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", edad=" + edad
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
