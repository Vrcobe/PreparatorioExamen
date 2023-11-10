import java.util.StringTokenizer;

public class ClaseMemoriza {
	
		
	public static void main(String[] args) {
		System.out.println(palabraMasLarga("La palabra más larga de esta frasea es "));
	}
	
	public static String palabraMasLarga(String cadena) {
		StringTokenizer st = new StringTokenizer(cadena," ,.");
		String ml = "";
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if(ml.length()<token.length()) {
				ml=token;
			}
		}
		return ml;
	}
		
}
