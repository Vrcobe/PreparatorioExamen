
public class Patinete extends Vehiculo implements Movible {
 
	private int caballos;
	private String nombreDuenyo;
	protected boolean enMovimiento = false;
	
	public Patinete(int caballos, String nombreDuenyo, boolean enMovimiento) {
		super();
		this.caballos = caballos;
		this.nombreDuenyo = nombreDuenyo;
		this.enMovimiento = enMovimiento;
	}
	
	public Patinete(int caballos,String nombreDuenyo) {
		super();
		this.caballos=caballos;
		this.nombreDuenyo =nombreDuenyo;
	}
	public Patinete() {
		super();
		this.caballos=0;
		this.nombreDuenyo="Desconocido";
	}
	
	public String getNombreDuenyo() {
		return nombreDuenyo;
	}

	public void setNombreDuenyo(String nombreDuenyo) {
		if(nombreDuenyo.equals("Desconocido")) {
			this.nombreDuenyo=nombreDuenyo;
		}else {
			System.out.println("No es posible cambiar el nombre del patinete sin su permiso y credenciales");
		}
	}

	public int getCaballos() {
		return caballos;
	}

	@Override
	public void moviendose() {
		if(enMovimiento == false) enMovimiento = true;
		else enMovimiento = false;
	}
	@Override
	public String toString() {
		return "Nunca he tenido un patinete";
	}
	
}
