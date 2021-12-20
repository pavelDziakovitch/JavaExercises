import java.util.Random;

public class Exercise14BruteForceClass {

	public static void main(String[] args){
		
		Random randomGenerator = new Random();
		String password = "";
		boolean hasSpecialChar = false;
		char addChar;
		char[] validChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'h', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X','Y','Z','1','2','3','4','5','6','7','8','9','!','?','%','&','/','*'};

		//Passwort generieren
		for(int i = 1; i <= 5; i++) {
			addChar = validChars[randomGenerator.nextInt(66)];
			
			if(addChar == validChars[61] || addChar == validChars[62] || addChar == validChars[63] || addChar == validChars[64] || addChar == validChars[65]|| addChar == validChars[66]) {
				hasSpecialChar = true;
			}
			if(i == 5 && !hasSpecialChar ) {
				addChar = validChars[61+randomGenerator.nextInt(5)];
			}
			password += addChar;
		}
		System.out.println("Das generierte Passwort lautet: " + password);
		System.out.println("Bitte warten, das Passwort wird gesucht!");
		String s = generate(validChars,password.length(), "",validChars.length,password);
		System.out.println("Passwort gefunden: "+s);
	}

    public static String generate(char[] validChars, int posInPassword, String s, int validLenght, String password){
    	
        String bruteforcedPW = "";
    	//Sobald posInPassword auf die letzte Position(0 von rechts) gekommen ist, soll das Passwort ausgegeben werden     	
        if (posInPassword == 0) { 
            return s;
        }
        
        //Erstellung der verschachtelten Ebenen --> pro ebene kommt 1 Buchstabe dazu.
        //Wenn eine Ebene "durchgelaufen" ist, dann schließt sich diese und erhöht den Char davor auf den nächsten Char im Array
        for (int j = 0; j < validLenght && !bruteforcedPW.equals(password); j++){
            String appended = s + validChars[j];
            bruteforcedPW = generate(validChars, posInPassword - 1, appended, validLenght, password); //mehrfach verschachtelte Rekursion (1 "Ebene" pro Stelle)
        }
        return bruteforcedPW;
    }
}
