import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class Exercise16KalenderClass {

	public static void main(String[] args) throws ParseException {

//Ausgabe Banner
		System.out.println(" _  __     _                _           ");
		System.out.println("| |/ /    | |              | |          ");
		System.out.println("| ' / __ _| | ___ _ __   __| | ___ _ __ ");
		System.out.println("|  < / _` | |/ _ \\ '_ \\ / _` |/ _ \\ '__|");
		System.out.println("| . \\ (_| | |  __/ | | | (_| |  __/ |");
		System.out.println("|_|\\_\\__,_|_|\\___|_| |_|\\__,_|\\___|_| \n");
		  	   
//Usereingabe samt Prüfung V2
		Scanner UserInput = new Scanner(System.in);
		
		String[] StorageInputText = {"Jahr", "Monat", "Start-Tag"};
		boolean wantToContinue = true;
		
		do {
			int year = 0, month = 0, startingDay = 0;
			
			for(int i = 0; i < 3;i++) {
				boolean userInputCheck = false;
				do {
					System.out.print("Bitte " + StorageInputText[i] + " eingeben : ");
					if(UserInput.hasNextInt()) {
						int tempMemory = UserInput.nextInt();
						switch(i) {
						case 0: year = tempMemory;
								userInputCheck = true;
								break;
						case 1: 
							if(tempMemory >= 1 && tempMemory <= 12) {
								userInputCheck = true;
								month = tempMemory;
							}else{
								System.out.println("\nUngültige Eingabe. Es gibt nur 12 Monate!\n");
							}
							break;
						case 2:				
							if(tempMemory >= 1 && tempMemory <= 7) {
								userInputCheck = true;
								startingDay = tempMemory;
							}else{
								System.out.println("\nUngültige Eingabe. Bitte Starttag zwischen 1 & 7 eingeben!\n");
							}
							break;
						}
					}else{
						System.out.println("\nUngültige Eingabe\n");
						UserInput.next();
						UserInput.nextLine();
					}
				}while(!userInputCheck);
			}
			
//Bestimmung wieviele Tage das Jahr hat
			int daysOfMonth = 0;
			
			switch(month) {
				case 2: 
					daysOfMonth = 28;
					if(year%4 == 0 && (year%100 != 0) || year%400 == 0) {
						System.out.println("Der Februar hat 29 Tage, da dieses Jahr ein Schaltjahr ist!");
						daysOfMonth++;
					}break;
				case 4: case 6: case 9: case 11: daysOfMonth = 30;break;
				default: daysOfMonth = 31;
			}

//Prüfung auf echtes Datum. Wenn nicht, dann wird 
			LocalDate wholeDate = LocalDate.of(year, month, 1); //.of = Bestimmtes Datum
			DayOfWeek day = wholeDate.getDayOfWeek(); 
			int DayOfWeekInt = day.getValue();
			if(startingDay != (DayOfWeekInt)) {
				System.out.println("\n!!! Der Kalender ist falsch. Der StartTag müsste "+DayOfWeekInt + " sein!!!");
				startingDay = DayOfWeekInt;
			}
			
//Ausgabe			
			String[] months = {"Jänner","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
			
			System.out.print("\nDatum: "+ months[month-1] + " " + year+"\n");
			System.out.format("\n| %2s | %2s | %2s | %2s | %2s | %2s | %2s |\n------------------------------------\n", "Mo","Di","Mi","Do","Fr","Sa","So");
		
			//Spacer = Leerräume/ daysDisplayed = Anzahl der Zeichen, die schon ausgegeben wurden/ placesInRow = max. 7 / Reihe
			for(int daysDisplayed = 0 , spacer = 0 , placesInRow = 1; daysDisplayed < daysOfMonth;placesInRow++) {
				if(spacer < startingDay-1) {													//-1, da am Starttag der Kalender anfängt und die leeren Teile 1 davor aushören! 
					System.out.format("| %2s ", "");
					spacer++;
				}else{
					System.out.format("| %2s ", daysDisplayed+1);
					daysDisplayed++;
				}
				if(placesInRow % 7 == 0 || daysDisplayed == daysOfMonth) {
					System.out.print("|\n");
				}
			}
			
//Prüfung ob nochmal
			String tempInput ="";
			
			System.out.print("\nWollen Sie einen anderen Monat anzeigen lassen? (\"Y\" für Ja / andere Taste zum beenden) : ");
			UserInput.nextLine();
			tempInput = UserInput.nextLine();
			if(tempInput.equalsIgnoreCase("Y")){
				wantToContinue = true;
				System.out.println("");
			}else {
				wantToContinue = false;				
			}
		}while(wantToContinue);
		UserInput.close();
	}
}