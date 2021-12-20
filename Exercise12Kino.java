//Tools für Zeit
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

//Tools für Eingabe und Zufallszahlen
import java.util.Random;
import java.util.Scanner;

public class Exercise12Kino {
	
	public static void main(String[] args) {
		
//Initialisierung Tools	
		Scanner UserInput = new Scanner(System.in);
		Random randomGenerator = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar calendarTime = Calendar.getInstance();

//Initialisierung Variablen
		String userMovie = "";
		String userChoiceFood = "";
		int movieIndex = 0;
		int counterLoopFood = 0;
		int RandomForPrimeChecker = randomGenerator.nextInt(1000);
		long diffHours = 0, diffMin = 0, diffInMilSec = 0;
		double walletValue =  Math.round((randomGenerator.nextDouble()*18.5+1.50)*100.0)/100.0; 
		double foodValue = 0;
		boolean inputIsRight = false;
		boolean randomIsPrim = false;
		Date timeNow, timeMovie;
	
//Initialisierung Zeit
	//aktuelle Zeit
		String timeWatch = sdf.format(calendarTime.getTime());
	//Film 1 (+9Min)
		calendarTime.add(Calendar.MINUTE,9);
		String timeMovie1 = sdf.format(calendarTime.getTime());
	//Film 2 (+24Min)
		calendarTime.add(Calendar.MINUTE,15);
		String timeMovie2 = sdf.format(calendarTime.getTime());
	//Film 3 (+59Min)
		calendarTime.add(Calendar.MINUTE,35);
		String timeMovie3 = sdf.format(calendarTime.getTime());
		
//Array für Anzeigetafel Kinofilme
		String timeTable[][] = new String[3][6];
	//Film 1	
		timeTable[0][0] = "1";
		timeTable[0][1] = "Don't Breathe 2";
		timeTable[0][2] = "3D";
		timeTable[0][3]	= timeMovie1;	
		timeTable[0][4] = "Saal 4";
		timeTable[0][5]	= "ausgebucht";		
	//Film 2			
		timeTable[1][0] = "2";
		timeTable[1][1] = "Tom & Jerry";
		timeTable[1][2] = "2D";
		timeTable[1][3]	= timeMovie2;	
		timeTable[1][4] = "Saal 2";
		timeTable[1][5]	= "verfügbar";	
	//Film 3		
		timeTable[2][0] = "3";
		timeTable[2][1] = "Free Guy";
		timeTable[2][2] = "3D";
		timeTable[2][3]	= timeMovie3;	
		timeTable[2][4] = "Saal 6";
		timeTable[2][5]	= "ausgebucht";
		
//Array für Anzeige Kiosk
	//Namen
		String priceboardNames[] = new String[3];
		priceboardNames[0] = "M&Ms";
		priceboardNames[1] = "Popcorn"; 
		priceboardNames[2] = "Nachos"; 
	//Preise
		double priceboardPrices[] = new double[3];
		priceboardPrices[0] = 2.40;
		priceboardPrices[1] = 2.00; 
		priceboardPrices[2] = 1.50; 
	//Bestimmung des niedrigsten Preises im Array für Schleifenwiederholung 
		double lowestPrice = 100;
		for(int i = 0; i < priceboardPrices.length; i++) {
			if(priceboardPrices[i] < lowestPrice) {
				lowestPrice = priceboardPrices[i];	
			}
		}

//Ausgabe Kinotafel		
		for(int i = 0; i < timeTable.length; i++) {
			for(int j = 0; j < timeTable[0].length; j++) {
				switch(j) {
					case 0: System.out.format("%2s ", timeTable[i][j]); break;
					case 1: System.out.format("| %-20s ", timeTable[i][j]); break;
					case 2:	System.out.format("| %-2s ", timeTable[i][j]); break;
					case 3:	System.out.format("| %-5s ", timeTable[i][j]); break;
					case 4: System.out.format("| %-6s ", timeTable[i][j]); break;
					case 5: System.out.format("| %-14s\n", timeTable[i][j]); break;
				}
			}		
		}
		
//Eingabe User Film
		do {
			System.out.print("\nWelchen Film möchtest du sehen? (Nummer oder Name) : ");
			userMovie = UserInput.nextLine();
			for(int i = 0; i < timeTable.length; i++) {
				if((userMovie.equalsIgnoreCase(timeTable[i][0]) ^ userMovie.equalsIgnoreCase(timeTable[i][1])) && timeTable[i][5].equalsIgnoreCase("verfügbar")) {
					System.out.print("Auswahl mit bestätigen: "+ timeTable[i][1] + " (Y/andere Taste: Auswahl) ");
					if(UserInput.nextLine().equalsIgnoreCase("Y")){
						inputIsRight = true;
						movieIndex = i;
					}			
					else{
					}
					break;
				}
				else if(userMovie.equalsIgnoreCase(timeTable[i][0]) ^ userMovie.equalsIgnoreCase(timeTable[i][1]) && timeTable[i][5].equalsIgnoreCase("ausgebucht")){
					System.out.println("Der Film " + timeTable[i][1] + " ist ausgebucht");
					break;
				}
				else if(userMovie.equalsIgnoreCase("abbruch")){
					System.out.println("Kauf abgebrochen!");
					break;
				}
				else if(i == timeTable.length-1 && !(userMovie.equalsIgnoreCase("abbruch"))) {
						System.out.println("Eingabe ungültig, Film nicht gefunden.\nGeben Sie \"Abbruch\" ein um den Kauf abzubrechen!" );
				}
			}
		}while(inputIsRight == false && !(userMovie.equalsIgnoreCase("abbruch")));	
		
//Ausgabe Ticket
		if(inputIsRight == true) {
			System.out.println("\n* geht zum Ticketschalter und holt sich das Ticket *\n");			
			System.out.format("*************************************\n* %18s %-14d *\n", "Ticket Nr.", randomGenerator.nextInt(99998)+1);
			System.out.format("*%35s*\n","");
			System.out.format("* %-20s %12s *\n", timeTable[movieIndex][1], timeTable[movieIndex][3]);		
			System.out.format("* %-33s *\n",("Sitz " + (randomGenerator.nextInt(15) + 1) + " Reihe " + ((char)(randomGenerator.nextInt(12) + 'A'))));	
			System.out.println("*************************************\n");
			
//Vergleich Uhrzeiten			
			System.out.println("Es ist " + timeWatch + " Uhr");
	//String Uhrzeiten werden zu "echten" Uhrzeiten in Date
			try {
				timeNow = sdf.parse(timeWatch);
				timeMovie = sdf.parse(timeTable[movieIndex][3]);
	//MilSecunden *1000 => SEC *60 => MIN / 60 = übrige STUNDEN %60 übrige MIN	
			    diffInMilSec = timeMovie.getTime() - timeNow.getTime();
			      
			    diffHours = diffInMilSec / (1000*60) / 60; 
			    diffMin = diffInMilSec / (1000*60) % 60;
				
			}catch (ParseException e) {
				e.printStackTrace();
			}
		    System.out.print("Übrige Zeit: "); 
		    if(diffHours >= 1) {
		    	System.out.print(diffHours + " Std. ");
		    }
		    System.out.print(diffMin + " Min.\n");
		    
//Wenn genügend Zeit --> Kiosk Besuch    
		    if(diffHours > 0 || diffMin > 10) {
			    System.out.println("\nDu gehst zum Kiosk\nAuswahl: ");
			    
			    for(int i = 0;i < priceboardNames.length;i++) {
			    	System.out.format("%-8s | %4.2f€\n",priceboardNames[i], priceboardPrices[i]);
			    }
			    System.out.format("\nIn der Geldtasche hast du %.2f€\n",walletValue);
			    
		//Einkauf beim Kiosk bis kein Geld oder X			    
			    while(walletValue >= foodValue+lowestPrice && !(userChoiceFood.equalsIgnoreCase("X")) && walletValue >= lowestPrice){
				    if(counterLoopFood == 0) {
				    	System.out.print("\nWillst du was kaufen? (Name oder \"X\" zum Abbrechen) : ");
				    }
				    else {
				    	System.out.print("\nWillst du noch etwas kaufen? (Name oder \"X\" zum Bezahlen): ");
				    }
				    userChoiceFood = UserInput.next();
				    for(int i = 0; i < priceboardNames.length;i++ ) {
				    	if(userChoiceFood.equalsIgnoreCase(priceboardNames[i])) {
				    		if(foodValue+priceboardPrices[i] > walletValue) {
				    			System.out.println("Geld reicht nicht, Artikel zu teuer");
	
				    		}
				    		else {
				    			foodValue += priceboardPrices[i];
				    			System.out.format("Wert Einklaufswagen: %.2f€\n",foodValue);
				    			break;
				    		}
				    	}
				    	else if(i == priceboardNames.length-1 && !(userChoiceFood.equalsIgnoreCase("X"))) {
				    		System.out.println("Ungültige Eingabe. Bitte Artikelnamen eingeben!");
				    	}
				    }
				    counterLoopFood++;
			    }
				UserInput.close();	
				if(walletValue-foodValue < lowestPrice ) {
				    if(counterLoopFood == 0) {
				    	System.out.println("Du hast nicht genügend Geld um dir etwas zu kaufen");
				    }
				    else {
				    	System.out.println("Du hast nicht genügend Geld um dir noch etwas zu kaufen");
				    }
				}
		//Wenn was gekauft wurde, wird geprüft, ob zufällige Zahl bis 1000 = Primzahl. Wenn Primzahl, dann ist der Einkauf gratis
				if(foodValue > 0) {
					System.out.println("\nAKTION: ZIEHE EINE ZAHL UND VERSUCHE DEIN GLÜCK\nDeine Zahl lautet: " + RandomForPrimeChecker);
					randomIsPrim = isPrimeOrRoundChecker(RandomForPrimeChecker);
					if(randomIsPrim == true) {
						System.out.println("DU MUSST NIX BEZAHLEN");
					}
					else if(foodValue > 0){
						System.out.format("\nzu Bezahlen : %.2f€", foodValue);
						walletValue -= foodValue;
						System.out.format("\nGeld übrig  : %.2f€\n", walletValue);
					}
				}

		    }
//Wenn nicht genügend Zeit, dann nur noch aufs Klo und dann in den Kinosaal
			System.out.println("\nDu gehst noch schnell aufs Klo!");
			System.out.println("Beim Einlass zeigst du dein Ticket für " + timeTable[movieIndex][1] + " und darfst eintretten.");
		}
	}

//Methode zur Überprüfung, ob zufällige Zahl bis 1000 eine Primzahl ist. Rückgabewert im Boolean entweder true oder false;
	public static boolean isPrimeOrRoundChecker (int randomNumber) {
		if(randomNumber <= 1) {
			return false;
		}
		if(randomNumber%10==0) {
			System.out.println("\nGlückwunsch! Deine Zahl ist Rund.");
			return true;
		}
		else if(randomNumber%10!=0){
			for(int i  = 2; i < randomNumber/2; i++)
				if(randomNumber%i == 0) {
					System.out.println("Leider keine Primzahl bzw keine runde Zahl!");
					return false;
				}			
		}
		System.out.println("\nGlückwunsch! Deine Zahl ist eine Primzahl!");
		return true;
	}
}