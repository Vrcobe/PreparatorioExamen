import java.util.ArrayList;

public class HolaMundo {
	public static void main(String[] args) {
		Coche coche1 = new Coche(4, 4);
		Patinete patin1 = new Patinete(30,"Víctor");
		ArrayList<Vehiculo> vehiculos = new ArrayList<>();
		ArrayList<Movible> vehiculos1 = new ArrayList<>();
		vehiculos.add(coche1);
		vehiculos.add(patin1);
		vehiculos1.add(patin1);
		vehiculos1.add(coche1);
		for(Vehiculo nuevo : vehiculos) {
			System.out.println(nuevo);
			if(nuevo instanceof Movible) {
				System.out.println(nuevo);
			}
		}
//		for(Movible nuevo :vehiculos1) {
//			System.out.println(nuevo);
//		}
	}
}
