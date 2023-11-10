import java.time.LocalDate;

public class Persona {
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String estadoCivil = "Soltero";
	private int goles;
	private int asistencias;
	
	
	public Persona(String nombre, String apellidos, LocalDate fechaNacimiento, int goles, int asistencias) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.goles = goles;
		this.asistencias = asistencias;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setEstadoCivil(String nuevoEstado) {
		this.estadoCivil = nuevoEstado;
	}
	public String getEStadoCivil() {
		return this.estadoCivil;
	}
	
	
	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public int getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}

	@Override
	public String toString() {
	
		return "" + nombre + " " + apellidos + " " + fechaNacimiento + " Estado Civil: " + estadoCivil + " " + goles + " " + asistencias;
	
	}
	
}
