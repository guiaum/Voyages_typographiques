package senorgenerativefont;



import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import senorgenerativefont.senorGeneration.Generation;
import senorgenerativefont.senorInterface.*;
import senorgenerativefont.senorInput.*;
import controlP5.*;

public class SenorGenerativeFont extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2476044399613648969L;

	//INTERFACE
	senorBackground myBackground;
	PImage myFormatImage;
	PImage myBackgroundPNG;
	SenorControlP5 myControls;
	ControlP5 cp5;

	Params myParams;

	//INPUT
	GarajeInput myInput;

	//GENERATIVE FONT
	Generation generationArea;

	ArrayList<int[]> myStoredColors = new ArrayList<int[]>();

	public void setup() {
		size (1024,768);
		frame.setBackground(new java.awt.Color(217,213,194));
		smooth();

		myParams = new Params();

		for (int i =0; i<4 ; i++)
		{
			myStoredColors.add(new int[]{0,0,0});
		}

		// Interface
		cp5 = new ControlP5(this);
		paramCP5();
		//Background
		myBackgroundPNG = loadImage("type-pattern-background.png");
		myBackground = new senorBackground(this, cp5, myBackgroundPNG);

		//Core of the program
		myFormatImage = loadImage("type-pattern-page.png");
		generationArea = new Generation(this, myFormatImage);

		// Input
		myInput = new GarajeInput(this, cp5);
		//Interface
		myControls = new SenorControlP5 (this, cp5);




	}

	public void draw() {
		myBackground.draw();
		generationArea.draw();
	}


	
	public void controlEvent(ControlEvent theEvent) 
	{
		if (Params.UIcreated == true){

			System.out.println(theEvent);
			

			//IMPORT TXT
			if(theEvent.getName() == "ImportTXT") 
			{
				myInput.importTXT();
			}

			//CLEAR
			if(theEvent.getName() == "Clear") 
			{
				myInput.clearText();
			}
			
			//CONVERT
			if(theEvent.getName() == "Convert") 
			{
				String textTyped = myInput.getTextFromArea();
				
				// On enlve ponctuation et accents
				String st="";
				String totalst="";
				st = StringOperation.sansAccent(textTyped);
				st = StringOperation.sansPonctuation(st);
				totalst += st;
				System.out.println("Texte converti:" + totalst);
				generateNewArrayOfChar(totalst);
			}

			// FORMAT CHANGE
			if(theEvent.getName() == "FORMAT") 
			{
				Params.Format = theEvent.getValue();
				generationArea.setFormat();

			}else if (theEvent.getName() == "NUMBERLINES") 
			{
				// NUMBER OF CHAR PER LINE
				Params.NumberLines = (int) theEvent.getValue();
				generationArea.thePagesUpdate();
				generationArea.theCharsSplit();
			}	
			else if (theEvent.getName() == "COLORS") 
			{
				System.out.println(theEvent.getValue() + "   "+ Params.BW);
				//Transition Color/BW
				if (theEvent.getValue()==1.0)
				{
					Params.BW = false;
					restoreColors();

				}else if (theEvent.getValue()==2.0 && Params.BW == false){
					Params.BW = true;
					backupColors();

				}
			}	
			//Random Generation of the colors
			else if (theEvent.getName() == "Color1") 
			{
				randomCol(0);
			}	
			else if (theEvent.getName() == "Color2") 
			{
				randomCol(1);
			}	
			else if (theEvent.getName() == "Color3") 
			{
				randomCol(2);
			}	
			else if (theEvent.getName() == "Color4") 
			{
				randomCol(3);
			}	
			else if (theEvent.getName() == "Regenerate") 
			{
				generationArea.regenerate();
			}
			else if (theEvent.getName() == "PDF") 
			{
				System.out.println("Generate PDF");
				generationArea.generatePDF();


			}

		}
	}
	
	
	private void generateNewArrayOfChar(String textTyped) {

		// Erasing all the existing generated characters and Pages
		generationArea.eraseAll();
		// On ajoute un par un les caractres de la chaine de caractres
		for (int i = 0; i< textTyped.length(); i++)
		{
			char myChar = textTyped.charAt(i);
			System.out.println(myChar);
			generationArea.add(myChar);
		}
	}
	
	
	
	private void backupColors() {
		//Storing each color in an array,  
		myStoredColors.clear();
		for (int i = 0; i<4; i++)
		{
			int[] myTempStoredColor = {Params.colorArray.get(i)[0], Params.colorArray.get(i)[1],Params.colorArray.get(i)[2]} ;
			myStoredColors.add(myTempStoredColor);
		}
		convertColors();
	}

	
	
	private void convertColors() {
		//Then we convert the actual colors in BW
		for (int i = 0; i<4; i++)
		{
			// Gray is an average between RGB values
			int myGrayValue = (Params.colorArray.get(i)[0]+Params.colorArray.get(i)[1]+Params.colorArray.get(i)[2])/3;
			// Sending the Gray values to the main array of colors
			Params.colorArray.get(i)[0] =  myGrayValue;
			Params.colorArray.get(i)[1] =  myGrayValue;
			Params.colorArray.get(i)[2] =  myGrayValue;
		}
	}

	private void restoreColors() {
		// Transition from BW to color
		Params.colorArray.clear();
		for (int i = 0; i<4; i++)
		{
			// Restoring the values stored in the local Array
			int[] myTempRestoredColor = {myStoredColors.get(i)[0], myStoredColors.get(i)[1], myStoredColors.get(i)[2]};
			Params.colorArray.add(myTempRestoredColor);
		}

	}

	private void randomCol(int id) {

		// Create a random color from 3 random values

		int myColorComposant1 = (int) random(256);
		int myColorComposant2 = (int) random(256);
		int myColorComposant3 = (int) random(256);

		int[] myNewCol = {myColorComposant1, myColorComposant2, myColorComposant3};

		ColorCompar(id , myNewCol);


	}

	private void ColorCompar(int id, int[] _myNewColor) {

		//Check if the color don't already exist

		int testCount = 0;
		for (int i = 0; i<3; i++)
		{
			if (_myNewColor[i] != Params.colorArray.get(0)[i] && _myNewColor[i] != Params.colorArray.get(1)[i] && _myNewColor[i] != Params.colorArray.get(2)[i] && _myNewColor[i] != Params.colorArray.get(3)[i])
			{
				testCount++;	
			}
		}

		// if ok, we affect the new color

		if (testCount == 3)
		{
			if (Params.BW == true)
			{
				// if BW, we store the RGB new value in the local array and then send the Gray value to the main array
				int myNewBWColor = (_myNewColor[0]+_myNewColor[1]+_myNewColor[2])/3;
				for (int i =0; i<3; i++)
				{
					myStoredColors.get(id)[i] = _myNewColor[i];
					Params.colorArray.get(id)[i] = myNewBWColor;
				}

			}else{
				for (int i =0; i<3; i++)
				{
					Params.colorArray.get(id)[i] = _myNewColor[i];
				}
			}
		}else{
			randomCol(id);
		}
	}



	private void paramCP5()
	{
		cp5.setColorActive(0xff3fa9f5);
		cp5.setColorBackground(0xff666666);
		cp5.setColorForeground(0xff000000);
	}



	public static void main(String _args[]) {
		PApplet.main(new String[] {"--present", "--hide-stop", senorgenerativefont.SenorGenerativeFont.class.getName() });
		//PApplet.main(new String[] {senorgenerativefont.SenorGenerativeFont.class.getName() });

	}
}
