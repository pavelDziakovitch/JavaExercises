import java.util.Random;
import java.util.Scanner;

public class Exercise17ZahlenratenClass6 {

    public static void main(String[] args) {

//Tools
        Random RandomGen = new Random();
        Scanner UserInput = new Scanner(System.in);
        
//Valiablen für mehrere Level
        int numberToGuess;
        int UserGuess = 0;
        int rangeRandomByUser = 0;
        String userWantstoConitnue = "";
        boolean guessed = false;
        int diffNumbers = 0;
        int[] ranges = new int[8];
        
        
        String hints[] = {
                "Fast erraten!", 
                "Du bist auf dem richtigen Weg!",
                "Du kommst etwas näher!",
                "Weit daneben!",
                "Ganz weit daneben!",
                "So kommst du nie drauf!",
                "Astronomisch weit weg!",
                "Der Mond ist näher!"
             };  
        
//Ausgabe Logo        
        printAsciiArt();
        
//LEVEL1 - ready
        System.out.println("----------------- LEVEL 1 -----------------\n");
        System.out.print("Errate die zufallsgenerierte Zahl zwischen 0 und 100!\nDu hast 9 Versuche!\n\n");
        do {
            guessed = false;
            numberToGuess = 1;//RandomGen.nextInt(101);
            System.out.println("Die Zufallszahl wurde generiert!");
            for (int i = 9; i > 0 && !guessed; i--) {
                System.out.println("\nVersuche übrig: " + i);
                UserGuess = UserInputChecker(UserInput, 101, false);
                if (UserGuess == numberToGuess) {
                    System.out.println("\n*** Glückwunsch! Zahl erraten! ***");
                    guessed = true;
                } else if (UserGuess > numberToGuess) {
                    System.out.println("Deine Zahl ist größer als die gesuchte Zahl");
                } else {
                    System.out.println("Deine Zahl ist kleiner als die gesuchte Zahl");
                }
            }
            if(!guessed) {
            	System.out.println("\nLeider hast du die Zahl nicht erraten! Es wäre " + numberToGuess + " gewesen!");
            }
        }while ((userWantstoConitnue= wantToContinue(UserInput, 1)).equalsIgnoreCase("Y"));

//LEVEL2 - ready	
        if (userWantstoConitnue.equalsIgnoreCase("N")) {
        	
        	//Variablen für LEVEL 2
	        int guessesMemory[] = new int[18];
	        String guessesMemoryText[][] = new String[18][2];
        
            System.out.println("\n----------------- LEVEL 2 -----------------\n");
	        System.out.print("Jetzt werden die Tipps weniger hilfreich.\nDen Zahlenbereich darfst du selber bestimmen!\nDu hast 18 Versuche!\n");

            do {
                guessed = false;
                System.out.print("\nFordere dich selber heraus!\n");
                rangeRandomByUser = UserInputChecker(UserInput, 0, true)+1;
                ranges = rangeArrayValueSetter(rangeRandomByUser);
                numberToGuess = 1;//RandomGen.nextInt(rangeRandomByUser);
                System.out.println("Die Zufallszahl wurde generiert!");
                
                for (int i = 18; i > 0 && !guessed; i--) {
                    for (int j = 0; j < guessesMemory.length && j < (18 - i); j++) {
                        System.out.println((j + 1) + ". Eingabe : " + guessesMemory[j] + " " + guessesMemoryText[j][0]);
                    }
                    System.out.println("\nVersuche übrig: " + i);
                    UserGuess = UserInputChecker(UserInput, rangeRandomByUser, false);
                    if (UserGuess == numberToGuess) {
                        System.out.println("\n*** Glückwunsch! Zahl erraten! ***");
                        guessed = true;
                    }
                    else {
                        diffNumbers = getDiffNumbers(numberToGuess, UserGuess);
                        guessesMemoryText = hintGiver(diffNumbers, guessesMemoryText, i, false, hints, ranges);
                        guessesMemory[18 - i] = UserGuess;
                    }
                }
                if(!guessed) {
                	System.out.println("\n\nLeider hast du die Zahl nicht erraten! Es wäre " + numberToGuess + " gewesen!");
                }
            }while ((userWantstoConitnue= wantToContinue(UserInput, 2)).equalsIgnoreCase("Y"));
        }

//LEVEL3
        if (userWantstoConitnue.equalsIgnoreCase("N")) {
        	System.out.println("\n----------------- LEVEL 3 -----------------\n");
        	System.out.print("Jetzt spielst du gegen eine schlaue AI.\nIhr spielt solange bis einer die Zahl errät.\nRegel: Ihr könnt eure beiden Eingaben und Tipps sehen!\nDu darfst den Zahlenraum selber bestimmen!\n");
     
            do {
            	
            	//Variablen für LEVEL 3
                int guessesMemoryVersusAI[][] = new int[100][2];
                String[][] guessesMemoryVersusAIText = new String[100][2];
                int AIreturn[] = new int[3];
               
                boolean isGuessSmart;
                guessed = false;
                
                int minValue = 0;
                int guessByAI = 0;
                int rangeRandomforAI = 0;

                int position = 0;
                
                //Münzwurf, wer starten soll
                int whoStarts = 0;
                int whoStartsRandom = RandomGen.nextInt(10) + 1; //%2 = 0 ich else AI
                if (whoStartsRandom % 2 == 0) {
                    whoStarts += 1;
                }
                //
                
                rangeRandomByUser = UserInputChecker(UserInput, 0, true)+1;
                ranges = rangeArrayValueSetter(rangeRandomByUser);
                rangeRandomforAI = rangeRandomByUser;
                int userPorgress = ranges.length-1;
                int progress = ranges.length-1;
                numberToGuess = RandomGen.nextInt(rangeRandomByUser);
                System.out.println("Die Zufallszahl wurde generiert!");

                for (int i = 0; !guessed; i++, position++) {
                	//AI
                    if (i + whoStarts >= 1) {
                        do {
                            isGuessSmart = true;
                            if(i > 0) {
                            	userPorgress = Integer.parseInt(guessesMemoryVersusAIText[position-1][1]); //-1 da position von vorherigen durchgang geprüft wird
	                        	if(userPorgress < progress) {   
		                            progress = userPorgress;
		                    		minValue = UserGuess - ranges[progress];
		                            rangeRandomforAI = rangeRandomforAICalculator(minValue, rangeRandomByUser, ranges[progress]);
		                            minValue = minValueChecker(minValue);
	                        	}

	                        }
                            guessByAI = RandomGen.nextInt(rangeRandomforAI) + minValue;
                            //Prüfung der RandomInt von AI auf von mir oder auf von der AI bereits geraten
                            for (int k = i; k >= 0; k--) {
                                if (guessesMemoryVersusAI[k][0] == guessByAI || guessesMemoryVersusAI[k][1] == guessByAI) {
                                    isGuessSmart = false;
                                    break;
                                }
                            }
                        } while (!isGuessSmart);
                        System.out.println("\nDer PC hat " + guessByAI + " geraten!");

                        if (guessByAI == numberToGuess) {
                            System.out.println("Der PC hat gewonnen!");
                            guessed = true;
                        }
                        else{
                        	diffNumbers = getDiffNumbers(numberToGuess, guessByAI);
                        	AIreturn = aiRangeSetter(diffNumbers, minValue, guessByAI, rangeRandomforAI,rangeRandomByUser,progress,userPorgress, 0, hints, ranges);
                        	rangeRandomforAI = AIreturn[0];
                        	minValue = AIreturn[1];
                        	progress = AIreturn[2];
                            //Abspeichern der Eingabe in den Speicher von User und AI ([i][0] für User / [i][1] für AI
                            guessesMemoryVersusAI[i][1] = guessByAI;
                        }
                    }

                    //USER	
                    if (!guessed) {
                        for (int j = 0; j < guessesMemoryVersusAI.length && j < (i); j++) {
                            System.out.println((j + 1) + ". Eingabe : " + guessesMemoryVersusAI[j][0] + " " + guessesMemoryVersusAIText[j][0]);
                        }

                        UserGuess = UserInputChecker(UserInput, rangeRandomByUser, false);
        				
                        if (UserGuess == numberToGuess) {
                            System.out.println("Zahl erraten!");
                            guessed = true;
                        }
                        else{
                        	diffNumbers = getDiffNumbers(numberToGuess, UserGuess);
                            guessesMemoryVersusAIText = hintGiver(diffNumbers, guessesMemoryVersusAIText, i, true, hints, ranges);
                            //Abspeichern der Eingabe in den Speicher von User und AI ([i][0] für User / [i][1] für AI
                            guessesMemoryVersusAI[i][0] = UserGuess;
                        }
                    }
                }
            }while ((userWantstoConitnue= wantToContinue(UserInput, 3)).equalsIgnoreCase("Y"));
            UserInput.close();
        }
    }
    
//METHODEN 
    //Ausgabe Ascii-Logo
    public static void printAsciiArt(){
        System.out.println(" ______      _     _                      _             ");
        System.out.println("|___  /     | |   | |                    | |            ");
        System.out.println("   / /  __ _| |__ | | ___ _ __  _ __ __ _| |_ ___ _ __  ");
        System.out.println("  / /  / _` | '_ \\| |/ _ \\ '_ \\| '__/ _` | __/ _ \\ '_ \\ ");
        System.out.println(" / /__| (_| | | | | |  __/ | | | | | (_| | ||  __/ | | |");
        System.out.println("\\_____/\\__,_|_| |_|_|\\___|_| |_|_|  \\__,_|\\__\\___|_| |_|\n");
    }

    //Prüft, ob Eingabe gültig ist
    public static int UserInputChecker(Scanner UserInput, int displayRange, boolean isForRange) {

    	boolean inputRight = false;
		do {
			if(isForRange) {
				System.out.print("\nGib den maximalen Wert der zufallsgenerierten Zahl ein: ");
			}
			else {
	            System.out.print("Bitte geben Sie eine Zahl zwischen 0 und "+(displayRange-1)+" ein: ");
			}
        	if(UserInput.hasNextInt()) {
        		int intToReturn = UserInput.nextInt();
        		if(!isForRange) {
                	return intToReturn;
        		}
        		if(isForRange && intToReturn >= 100) {
                	return intToReturn;
        		}
        		else {
                	System.out.println("Ungültige Eingabe! Fordere dich selber heraus und gib 100 oder mehr ein!");
        		}
            }
            else {
            	System.out.println("Ungültige Eingabe!");
            	UserInput.next();
            	UserInput.nextLine();
            }
        }while(!inputRight);
		return 0;
    }
    
    //Prüfung der Eingabe, ob User nochmal spielen, das nächste Level spielen oder aufhören wollte.
    public static String wantToContinue(Scanner UserInput, int level) {
    	if(level == 3) {
            System.out.print("Willst du erneut Spielen? (\"Y\" für Ja / andere Taste zum Beenden) ");
    	}
    	else{
    		System.out.print("\nWillst du erneut Spielen? (\"Y\" für Ja / \"N\" für das nächste Level / \"X\" zum Beenden) ");
    	}
        UserInput.nextLine();
        String userWantstoConitnue = UserInput.nextLine();
        return userWantstoConitnue;
    }
    
    //Berechnung Zahlendifferenz für Begrenzung
    public static int getDiffNumbers(int numberToGuess, int Guess){
    	if(numberToGuess - Guess >= 0) {
        	return numberToGuess - Guess;	
    	}
    		return -1* (numberToGuess - Guess);
    }
    
    //Speicherung der Tipps und Rückgabe von Array
    public static String[][] hintGiver(int diffNumbers, String[][] guessesMemoryText , int xPositioninArray, boolean forLevel3, String[] hints, int[] ranges){  
        if(!forLevel3) {
        	xPositioninArray = 18-xPositioninArray;
        }
        for(int k = 0; k <= hints.length-1; k++) {
        	if (diffNumbers <= ranges[k] || k == hints.length-1) {
        		guessesMemoryText[xPositioninArray][0] = hints[k];
        		guessesMemoryText[xPositioninArray][1] = "" + k;
        		break;
        	}
        }
        return guessesMemoryText;
    }
    
    //Setzung der Grenzen für die AI (randomGen)
    public static int[] aiRangeSetter(int diffNumbers, int minValue, int guess, int rangeRandomforAI, int rangeRandomByUser,int progress, int userPorgress, int progressTemp, String[] hints, int[] ranges) {
    	int[] AIreturn = new int[3];
        if (diffNumbers <= ranges[progressTemp]) {
        	if(progressTemp < progress) {
        		int minValueTemp = guess - ranges[progressTemp];
        		rangeRandomforAI = rangeRandomforAICalculator(minValueTemp, rangeRandomByUser, ranges[progressTemp]);
        		minValue = minValueChecker(minValueTemp);
        		AIreturn[2] = progressTemp;
        	}
	        else {
		        AIreturn[2] = progress;
	        }
	        AIreturn[0] = rangeRandomforAI;
	        AIreturn[1] = minValue;
	        System.out.println("TIPP: " +  hints[progressTemp] +"\n");
        }
        else if(progressTemp <= hints.length-2 ) {
        	return aiRangeSetter(diffNumbers, minValue, guess, rangeRandomforAI,rangeRandomByUser, progress, userPorgress, progressTemp+1, hints, ranges);
    	}
        return AIreturn;
     }
    
    //Prüft für aiRangeSetter ob minValue >= 0, bzw. wenn < dann wird minValue angepasst
    public static int minValueChecker(int minValue) {
        if (minValue < 0) {
            return 0;
        }
        return minValue;
	}
    
    //Prüft für aiRangeSetter ob rangeRandomforAI <= rangeRandomByUser, bzw. wenn > dann wird rangeRandomforAI angepasst    
    public static int rangeRandomforAICalculator (int minValue, int rangeRandomByUser, int add) {
    	int rangeRandomforAITemp = 0;
    	if(add < rangeRandomByUser) {
        	rangeRandomforAITemp =  add*2+1;    		
    	}
    	else {
    		rangeRandomforAITemp = add;
    	}
    	
        if (rangeRandomforAITemp + minValue >= rangeRandomByUser) {
        	return rangeRandomByUser-minValue;
        }
        else if(minValue < 0) {
        	return rangeRandomforAITemp + minValue;
        }
    	return rangeRandomforAITemp;
	}
    
    public static int[] rangeArrayValueSetter(int rangeRandomByUser) {
        int rangesTemp[] = {5, (rangeRandomByUser/14), (rangeRandomByUser/12), (rangeRandomByUser/10), (rangeRandomByUser/8), (rangeRandomByUser/6), (rangeRandomByUser/4), (rangeRandomByUser)};
    	return rangesTemp;
    }
}