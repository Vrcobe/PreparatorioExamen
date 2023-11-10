
public class Coche extends Vehiculo implements Movible{
	
	private String marca;
	private String matricula;
	private int caballos;
	private boolean enMovimiento;
	public Coche(int pasajeros, int ruedas, String marca, String matricula, int caballos) {
		this.pasajeros= pasajeros;
		this.ruedas = ruedas;
		this.marca = marca;
		this.matricula = matricula;
		this.caballos = caballos;
	}
	public Coche(int pasajeros,int ruedas) {
		this.pasajeros = pasajeros;
		this.ruedas=ruedas;
	}
	
	@Override
	public void moviendose() {
		if(enMovimiento == false) enMovimiento = true;
		else enMovimiento = false;
	}
	
	@Override
	public String toString() {
		return "Si el coche se mueve es un coñazo"; 
	}
}
