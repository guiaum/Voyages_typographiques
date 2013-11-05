package senorgenerativefont.senorInput;

import processing.core.PApplet;
import controlP5.*;

import java.awt.Font;
import java.io.*;

import g4p_controls.G4P;
import g4p_controls.GTextArea;


/*
 * Text area in Garaje
 */

public class GarajeInput {

	PApplet parent;

	String textValue = "";
	String totalst = "";

	GTextArea myGarajeInput;


	public GarajeInput(PApplet p, ControlP5 _cp5)
	{
		parent = p;
		Font font = new Font("Garaje53Unicase-Black", Font.ROMAN_BASELINE, 14);
		
		myGarajeInput = new GTextArea(parent, 12, 150, 320, 500, G4P.SCROLLBAR_VERTICAL | G4P.SCROLLBARS_AUTOHIDE);
		myGarajeInput.setText(textValue, 290);
		myGarajeInput.setFont(font);
		// Set some default text
		myGarajeInput.setDefaultText("TYPE SOME TEXT");
	}


	public String getTextFromArea(){
		return myGarajeInput.getText();
	}


	public void clearText()
	{
		myGarajeInput.setText("");
	}
	


	// IMPORTATION Fichier TXT

	public void importTXT(){
		parent.selectInput("Select a file to process:", "fileSelected", null , this);
	}

	public void fileSelected(File selection) {
		if (selection == null) {
			System.out.println("Window was closed or the user hit cancel.");
		} else {
			System.out.println("User selected " + selection.getAbsolutePath());			
			String[] myLines = PApplet.loadStrings(selection);
			pushFromTxtToInput(myLines);
			
		}
	}
	
	private void pushFromTxtToInput(String[] myImport)
	{
		String myImportedTextString = "";
		for (int i = 0; i<myImport.length; i++)
		{
			myImportedTextString += myImport[i].toUpperCase();
		}
		myGarajeInput.setText(myImportedTextString);
	}

}
