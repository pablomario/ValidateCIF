
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if ( validateDocument("A0000000F") ) {
			System.out.println( "Valido" );
		} else {
			System.out.println( "No valido" );
		}
		
		/**
		 * CASOS BASE
		 * N3936181A
		 * V3066110B
		 * D07841729
		 * S1973418E 	
		 */
		
		/**
		 * CASOS ESPECIALES LETRAS A, B, E, H
		 * A79429411
		 * B66360918
		 * E25346677
		 * H93550333
		 */		
		
		/**
		 * CASOS ESPECIALES, LETRAS P, Q, S, W
		 * W1386253G
		 * S7142683G
		 * Q0781729I
		 * P9185911F
		 */		
	}
	
	
	/**
	 * Validacion documento CIF
	 * {@link https://es.wikipedia.org/wiki/C%C3%B3digo_de_identificaci%C3%B3n_fiscal}
	 * @param cif
	 * @author Pablo Mario
	 * @return
	 */
	public static boolean validateDocument(String cif) {
		// TODO: Este método se puede optimizar.
		
		System.out.println(cif);
		
		// CIF tamanio 9 cifras
		if ( cif.isEmpty() || cif.length() != 9 ){
			System.out.println("Formato Cif incorrecto");
			return false;
		}
		
		// Relación dígito de control <-> letra 
		// J = 0, A = 1, B = 2, C= 3, D = 4, E = 5, F = 6, G = 7, H = 8, I = 9
		String letraControl = "JABCDEFGHI";	
		
		try {
			
			int sumaA = 0; // Suma de los valores pares
			int sumaB = 0; // Suma de los valores impares
			int sumaC = 0; // Suma parcial, posteriormente sumaA + sumaB
			
			// Primer dígito (Posicion 0) es la calve de entidad
			String entidad = cif.substring( 0,1 );	
					
			// Último dígito (posición 9) es un código de control que puede ser un número o una letra:	
			String ultimoDigito = cif.substring( 8 ); 
					
			
			// Cálculo SumaA - Valores pares
			for ( int i = 2; i < 7; i = i +2 ) {				
				int actual = 0;						
				actual = Integer.parseInt( cif.substring(i, i+1) );			
				sumaA = sumaA + actual;						
			}						
			
			// Cálculo SumaB - Valores impares
			for ( int i = 1; i <= 7; i = i + 2 ) {				
				int actual = 0;						
				actual = Integer.parseInt( cif.substring(i, i+1) );			
				if ( 2 * actual != 0 ) {
					int doble = 2 * actual;				
					int parte_entera = doble % 10;				
					doble = doble / 10;				
					sumaB += doble + parte_entera;
				}						
			}		
			
			// SumaC parcial sumaA + sumaB y obtención de las unidades
			sumaC = sumaA + sumaB;		
			int unidades = sumaC % 10;	
			
			// Obtención dígito de Control
			int digitoControl = 10 - unidades;		
			if ( digitoControl == 10 ) digitoControl = 0;
			System.out.println("Digito de Control: " + digitoControl );

			// CASO ESPECIAL 
			// Si Entidad es igual a P, Q, S o W el último dígito SIEMPRE será LETRA.
			if ( entidad.equals("P") ) {
				System.out.println("Validado P");
				if ( ultimoDigito.equals( letraControl.substring(digitoControl,digitoControl+1) ) ){
					return true;
				} else {
					return false;
				}
			} else if ( entidad.equals("Q") ) {
				System.out.println("Validado Q");
				if ( ultimoDigito.equals( letraControl.substring(digitoControl,digitoControl+1) ) ){
					return true;
				} else {
					return false;
				}
			} else if ( entidad.equals("S") ) {
				System.out.println("Validado S");
				if ( ultimoDigito.equals( letraControl.substring(digitoControl,digitoControl+1) ) ){
					return true;
				} else {
					return false;
				}
			} else if ( entidad.equals("W") ) {
				System.out.println("Validado W");
				if ( ultimoDigito.equals( letraControl.substring(digitoControl,digitoControl+1) ) ){
					return true;
				} else {
					return false;
				}
			}
			
			// CASO ESPECIAL 
			// Si Entidad es igual a A, B, E o H el último digito SIEMPRE será NUMERO
			if ( entidad.equals("A") ) {
				System.out.println("Validado A");
				if ( digitoControl == Integer.parseInt(ultimoDigito) ) {
					return true;
				} else {
					return false;
				}
			} else if ( entidad.equals("B") ) {
				System.out.println("Validado B");
				if ( digitoControl == Integer.parseInt(ultimoDigito) ) {
					return true;
				} else {
					return false;
				}
			} else if ( entidad.equals("E") ) {
				System.out.println("Validado E");
				if ( digitoControl == Integer.parseInt(ultimoDigito) ) {
					return true;
				} else {
					return false;
				}
			} else if ( entidad.equals("H") ) {	
				System.out.println("Validado H");
				if ( digitoControl == Integer.parseInt(ultimoDigito) ) {
					return true;
				} else {
					return false;
				}
			}		
			
			// CASOS GENERICOS 
			// Para otras claves de entidad: el dígito podrá ser tanto número como letra. 		
			if ( isNumeric(ultimoDigito) ) {
				// Si el último dígito es numerico validamos contra el dígito de control
				if ( digitoControl == Integer.parseInt(ultimoDigito) ) {
					System.out.println("Validado ULTIMO NUMERICO");
					return true;
				} else {
					System.out.println("Validado ULTIMO NUMERICO - MAL");
					return false;
				}
			} else {
				// Si el último dígito es letra validamos contra el dígito de control
				if ( ultimoDigito.equals( letraControl.substring(digitoControl, digitoControl+1)) ) {
					System.out.println("Validado ULTIMO LETRA");
					return true;
				} else {
					System.out.println("Validado ULTIMO LETRA - MAL");
					return false;
				}
			}		
						
		}catch(NumberFormatException err){
			return false;
		}		
	
	}
	
    private static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
	

}
