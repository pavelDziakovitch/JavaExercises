import java.util.Scanner;
import java.util.Random;

public class Exercise11NightOut {
	
	public static void main(String[] args) {
		Scanner UserIn = new Scanner(System.in);
		Random randomGenerator = new Random();
		
		int[] checkDifference;
		int[] volumesDrink = new int[6]; 
		volumesDrink[0] = 250;
		volumesDrink[1] = 200;
		volumesDrink[2] = 150;
		volumesDrink[3] = 100;
		volumesDrink[4] = 50;
		volumesDrink[5] = 7;	
		
		int quantityDrinks = 0;
		int pinsFallen = 0;
		int counterPullUpsLoops = 1;
		int counterBowling = 0;
		int gamesAmount = 0;
		int cookieQuantity = 1 + randomGenerator.nextInt(10);
		
		boolean inputIsRight = true;
		
//Getränke 
		do {
			System.out.print("Wieviel Bier (250ml) trinkst du heute? : ");	
			if(UserIn.hasNextInt()) {
				quantityDrinks = UserIn.nextInt(); 
				for(int i = 1; i <= quantityDrinks; i++){
					for(int j =  0; j < volumesDrink.length; j++) {
						if(volumesDrink[j] == 250) {
							System.out.println("Das Glas ist voll");
						}
						if(volumesDrink[j] < 250 && volumesDrink[j] > 100) {
							System.out.println("Das Glas ist nicht mehr voll");
						}
						if(volumesDrink[j] > 20 && volumesDrink[j] <= 100) {
							System.out.println("Das Glas ist fast leer!");
						}
						if(volumesDrink[j] <= 20 && i < quantityDrinks) {
							System.out.println("Kellner, neues Bier!\n");
						}
						if(volumesDrink[j] <= 20 && i == quantityDrinks) {
							System.out.println("Gehen wir zur Bowlingbahn!\n");
						}
					}
				}
				inputIsRight = true;
			}
			else{
				System.out.println("Ungültige Eingabe! Bitte erneut probieren!");
				inputIsRight = false;
				UserIn.nextLine();
			}
		}while(inputIsRight == false);
		
//Bowling
		System.out.println("*gehen zur Bowlingbahn*\n");	
		if(quantityDrinks < 4) {
			do {
				UserIn.nextLine();
				System.out.print("Wieviele Spiele wollt Ihr spielen? (min. 10) : ");
				if(UserIn.hasNextInt()) {
					gamesAmount = UserIn.nextInt(); 
					if(gamesAmount >= 10) {
						checkDifference = new int[gamesAmount];
						while(counterBowling < checkDifference.length) {
							if(counterBowling > 0) {
								do {
									pinsFallen = 1 + randomGenerator.nextInt(10);
								}while(checkDifference[counterBowling-1] == pinsFallen && counterBowling > 0);
							}
							System.out.println("\nDu hast " + pinsFallen + " Kegel getroffen!");
							switch(pinsFallen) {
								case 10: System.out.println("X - Strike");break;
								case 9: case 8: case 7: System.out.println("Guter Treffer"); break;
								case 6: case 5: case 4: System.out.println("Normaler Treffer"); break;
								case 3: case 2: case 1: System.out.println("Schwacher Treffer"); break;
								case 0: System.out.println("- Miss"); break;
							}
							checkDifference[counterBowling] = pinsFallen;
							counterBowling++;		
						}
						inputIsRight = true;
					}
					else {
							System.out.println("Mindestens 10 Spiele! Bitte erneut probieren!");
							inputIsRight = false;
						}
					}
				else{
					System.out.println("Ungültige Eingabe! Bitte erneut probieren!");
					inputIsRight = false;
					UserIn.next();

				}
			}while(inputIsRight == false);
		}
		else {
			System.out.println("Leider bist du zu betrunken zum Spielen!\n");
		}
		UserIn.close();
		
//Klimmzüge in 3 verschiedene Schleifen
		System.out.println("\n*jetzt fängt ihr an, Klimmzüge im Türrahmen zu machen!*\n\nDeine Freunde zählen mit dir:");
//do-while-Schleife
		do {
			switch(counterPullUpsLoops) {
				case 10: System.out.println(counterPullUpsLoops +"\nPhu ich hoffe das ist bald vorbei"); break;
				case 15: System.out.println(counterPullUpsLoops +"\nIch glaub ich schaff das nicht"); break;		
				case 19: System.out.println(counterPullUpsLoops +"\nJA ICH SCHAFFE ES!!!!!"); break;
				case 20: System.out.println(counterPullUpsLoops +"\nOLEOLEOLEEEEE\n"); break;
				default:System.out.println(counterPullUpsLoops);		
			}
			counterPullUpsLoops++;
		}while(counterPullUpsLoops <= 20);
		
		counterPullUpsLoops = 1;	//Reset auf Originalwert
		
//while-Schleife		
		while(counterPullUpsLoops <= 20) {		
			switch(counterPullUpsLoops) {			
				case 10: System.out.println(counterPullUpsLoops +"\nPhu ich hoffe das ist bald vorbei"); break;
				case 15: System.out.println(counterPullUpsLoops +"\nIch glaub ich schaff das nicht"); break;		
				case 19: System.out.println(counterPullUpsLoops +"\nJA ICH SCHAFFE ES!!!!!"); break;
				case 20: System.out.println(counterPullUpsLoops +"\nOLEOLEOLEEEEE\n"); break;
				default:System.out.println(counterPullUpsLoops);		
			}
			counterPullUpsLoops++;
		}
//for-Schleife		
		for(int counterPullUps = 1; counterPullUps <= 20; counterPullUps++){	
			switch(counterPullUps) {
				case 10: System.out.println(counterPullUps +"\nPhu ich hoffe das ist bald vorbei"); break;
				case 15: System.out.println(counterPullUps +"\nIch glaub ich schaff das nicht"); break;		
				case 19: System.out.println(counterPullUps +"\nJA ICH SCHAFFE ES!!!!!"); break;
				case 20: System.out.println(counterPullUps +"\nOLEOLEOLEEEEE\n"); break;
				default:System.out.println(counterPullUps);		
			}
		}
		
//Bestimmung, was zu Hause gemacht wird
		System.out.println("*du machst dich auf den Weg nach Hause*\n");	
		switch(quantityDrinks) {
			case 2: 
				System.out.println("Als du nach Hause kommst, errinerst du dich,\ndass du noch Kekse im Kühlschrank hast.");
				for(int i = 1; i <= cookieQuantity; i++) {
					System.out.format("Keks " + "Nr.%d" + " gegessen!\n", i);
				}
			case 0: case 1: System.out.println("Vor dem Schlafen gehst du noch Duschen, Zähne putzen und ziehst dir deine Schlafkleidung an.\n");break;
			default: 
				if(quantityDrinks <= 14) {
					for(int i = 1;i <= 15-quantityDrinks; i++) {
						System.out.println("Stufe " + i);
					}
					System.out.println("Jetzt muss ich schlafen\n");
				}
				else {
					System.out.println("Irgendwie zu Hause gelandet erleidest\ndu eine Alkoholvergiftung und wirst im Vorraum unmächtig.\n");
				}
		}
	}
}
