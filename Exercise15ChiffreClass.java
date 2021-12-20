import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class Exercise15ChiffreClass {

	public static void main(String[] args) throws IOException {

		Scanner UserInput = new Scanner(System.in);
		Random randomGen = new Random();
		String text = "";
		String textEncoded = "";
		boolean userRating = false;
		int ratingShift = 1;
		int charFromText;
		int encodeShift =  1+randomGen.nextInt(25);

		FileReader fr = new FileReader("stringText.txt",StandardCharsets.UTF_8);

		
		System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("+   Textverschl�sselung aus Datei   +");
        System.out.println("+++++++++++++++++++++++++++++++++++++\n");
        System.out.print("Hallo Benutzer, ich habe soeben den Text aus deiner Datei\n\"stringText.txt\" ausgelesen und verschl�sselt!\n\nVerschl�sselter Text: ");

		while((charFromText = fr.read()) != -1){
			switch(charFromText) {
				case '�': text += "Ae";break;
				case '�': text += "Oe";break;
				case '�': text += "Ue";break;
				case '�': text += "ss";break;
				case '�': text += "ae";break;
				case '�': text += "oe";break;
				case '�': text += "ue";break;
				default: text += (char)charFromText;
			}
		}
		fr.close();        
        
//Text wird durch .toCharArray in char-Array umgewandelt
		char[] textToCharArray = text.toCharArray();
        
//Verschl�sselung
        for(int i = 0; i < textToCharArray.length;i++) {
        	char textTemp = Character.toUpperCase(textToCharArray[i]);
        	if(textTemp >= 'A' && textTemp <= ('Z'-encodeShift)){
        		textToCharArray[i] += encodeShift;
        	}
        	else if(textTemp > ('Z'-encodeShift)){
        		textToCharArray[i] = (char) (textToCharArray[i]-26+encodeShift);
        	}
        }
		FileWriter fwEncoded = new FileWriter("encoded.txt");
		for(int i = 0;i < textToCharArray.length;i++){
            System.out.print(textToCharArray[i]);
            fwEncoded.write(textToCharArray[i]);
        }
		fwEncoded.close();	
		System.out.println("");
		
//Profiaufgabe, das erste Wort bis zum ersten Space wird ausgegeben und der User sagt, ob er dieses Wort kennt
        while(userRating == false) { 
    		System.out.print("\nWort: ");
	        for(int i = 0;i < textToCharArray.length && textToCharArray[i] != ' ';i++) {
	        	char textTemp = Character.toUpperCase(textToCharArray[i]);
	        	if(textTemp >= 'A'+ratingShift && textTemp <= ('Z')){
	        		System.out.print((char) (textToCharArray[i] - ratingShift));
	        	}
	        	else if(textTemp < ('A'+ratingShift) && textTemp >= 'A'){
	        		System.out.print((char) (textToCharArray[i]+26-ratingShift));
	        	}
	        }
	        System.out.println("\nChiffre: "+ ratingShift);
	        System.out.print("Kennen Sie das Wort? (Y f�r \"Ja\" / andere Taste f�r \"Nein\") ");
	        if((UserInput.nextLine()).equalsIgnoreCase("Y")){
	        	userRating = true;
	        }
	        else {
	        	ratingShift++;
	        }
        }
        UserInput.close();
 
//Entschl�sselung		
        
        FileReader frEncoded = new FileReader("encoded.txt");
		while((charFromText = frEncoded.read()) != -1){
			textEncoded += (char)charFromText;
		}
		frEncoded.close();
		
		char[] textToCharArrayEncoded = textEncoded.toCharArray();
		
        for(int i = 0; i < textToCharArrayEncoded.length;i++) {
        	char textTemp = Character.toUpperCase(textToCharArrayEncoded[i]);
        	if(textTemp >= 'A'+ratingShift && textTemp <= ('Z')){
        		textToCharArrayEncoded[i] -= ratingShift;
        	}
        	else if(textTemp < ('A'+ratingShift) && textTemp >= 'A'){
        		textToCharArrayEncoded[i] = (char) (textToCharArrayEncoded[i]+26-ratingShift);
        	}
        }
//Ausgabe entschl�sselt in Text und Datei  
		FileWriter fw = new FileWriter("uncoded.txt");
        System.out.print("\nEntschl�sselt: ");
		for(int i = 0;i < textToCharArrayEncoded.length;i++){
			System.out.print(textToCharArrayEncoded[i]);
            fw.write(textToCharArrayEncoded[i]);
        }
		fw.close();	
	}
}
