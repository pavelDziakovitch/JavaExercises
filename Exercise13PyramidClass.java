import java.util.Scanner;

public class Exercise13PyramidClass {
	
	public static void main(String[] args) {
		
		Scanner UserInput = new Scanner(System.in);
		
		String xString ="X";
		String spaceString="";
		int linesAmount = 0;
		boolean checkInput = false;
		
		
		System.out.println("**********************************");
		System.out.println("*    Ausgabe folgender Formen    *");
		System.out.println("*  Pyramide, Pyramide ungekehrt  *");
		System.out.println("*         Raute seitlich         *");
		System.out.println("*     Raute von oben - unten     *");
		System.out.println("**********************************\n");

		
		System.out.print("Bitte geben Sie die Anzahl der Zeilen ein (ganze Zahlen) : ");
		do {
			
			if(UserInput.hasNextInt()){
				linesAmount = UserInput.nextInt();
				checkInput = true;
					
		}
			else {
				System.out.print("Ungütige Eingabe! Bitte ganze Zahl eingeben: ");
				UserInput.nextLine();
			}
		}while(checkInput == false);
		
		UserInput.close();
		System.out.println("");

		//Füllen der scapeString mit Abständen
		for(int i = 0; i < ((linesAmount-1));i++) {
			spaceString = spaceString + "  ";
		}
//Pyramide
		//Ausgabe von xString und spaceString, nach jeder Ausgabe sollen 2x " X" bei xString addiert und 2x " " von spaceString entfernt werden!
		for(int i = 1; i <= linesAmount; i++) {
			System.out.print(spaceString + xString+"\n");
			xString = xString+" X X";
			if(spaceString.length() > 0) {
				spaceString = spaceString.substring(0, spaceString.length() - 2);
			}
		}
		System.out.println("");

		
		//Valuereset
		xString ="X";
		spaceString="";

//Kopfüber
		for(int i = 1; i < (linesAmount);i++) {
			xString = xString + " X X";
		}

		//Ausgabe von xString und spaceString, nach jeder Ausgabe sollen 2x " X" bei xString entfernt und 2x " " von spaceString addiert werden!
		for(int i = 1; i <= linesAmount; i++) {
			System.out.print(spaceString + xString+"\n");
			spaceString = spaceString+"  ";
			if(xString.length() > 2) {
				xString = xString.substring(0, xString.length() - 4);
			}
		}
		System.out.println("");	
		
		
		//Valuereset
		xString ="";
		spaceString="";
		
//Raute
		//Füllen der scapeString mit Abständen und xString mit X soviel wie Zeilen
		for(int i = 0; i < (linesAmount);i++) {
			if(i >= 1) {
				spaceString = spaceString + "  ";
			}
		}
		for(int i = 0; i < (linesAmount*1.5);i++) {		
			if(i <= (linesAmount*1.5)) {
				if(i == (linesAmount*1.5)-1) {
					xString = xString + "X";
				}
				else {
					xString = xString + "X ";
				}
			}
		}
		for(int i = 1; i <= linesAmount; i++) {
			System.out.println(spaceString + xString);
			if(i < linesAmount)
			spaceString = spaceString.substring(0, spaceString.length() - 2);
		}
		System.out.println("");	
		
		if(linesAmount%2 != 0) {
			
			//Valuereset
			xString ="X";
			spaceString="";
			
//Raute von oben nach unten ungerade Zahlen
			for(int i = 0; i < ((linesAmount-1)/2);i++) {
				spaceString = spaceString + "  ";
			}
			for(int i = 1; i <= linesAmount; i++) {
			//Raute oben
				if(i <= linesAmount/2) {
					System.out.print(spaceString + xString+"\n");
					xString = xString+" X X";
					if(spaceString.length() > 0) {
						spaceString = spaceString.substring(0, spaceString.length() - 2);
					}
				}
			//Raute unten	
				else {
					System.out.print(spaceString + xString+"\n");
					spaceString = spaceString+"  ";
					if(xString.length() > 2) {
						xString = xString.substring(0, xString.length() - 4);
					
					}
				}
			}
		}
		else {
			//Valuereset
			xString ="X";
			spaceString="";
			
//Raute von oben nach unten mit geraden Zahlen
			for(int i = 0; i < ((linesAmount-1)/2);i++) {
				spaceString = spaceString + "  ";
			}
			for(int i = 1; i <= linesAmount; i++) {
			//Raute oben
				if(i <= linesAmount/2) {
					System.out.print(spaceString + xString+"\n");
					if(spaceString.length() > 0) {
						spaceString = spaceString.substring(0, spaceString.length() - 2);
					}
					if(i < linesAmount/2) {
					xString = xString+" X X";
					}
				}
			//Raute unten	
				else if(i <= linesAmount) {
					System.out.print(spaceString + xString+"\n");
					spaceString = spaceString+"  ";
					if(xString.length() > 2) {
						xString = xString.substring(0, xString.length() - 4);
					}
				}
			}
		}
	}
}