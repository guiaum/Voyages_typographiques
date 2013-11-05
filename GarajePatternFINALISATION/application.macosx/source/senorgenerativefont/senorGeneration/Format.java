package senorgenerativefont.senorGeneration;

import java.util.List;

import processing.core.PApplet;
//import processing.core.PImage;
import senorgenerativefont.senorGeneration.GeneratedText;
import senorgenerativefont.senorInterface.Params;
import processing.core.PGraphics;


/*
 * Just the background of the page.
 * Store an object called GeneratedTex, 
 * to make the placement of the characters easier.
 */


public class Format {

	PApplet parent;
	float rectType;
	int Id;
	//int offsetX;
	//int widthPage;
	//float heightPageA4;
	//float heightPageLetter;
	//PImage myFormatImage;

	//The Text generated on the current page
	GeneratedText myGeneratedText;

	public Format(PApplet p, int _origineX, int _Id)
	{
		parent = p;
		rectType = Params.Format;
		Id = _Id;
		//widthPage = 403;
		//heightPageA4 = 570;
		//heightPageLetter = 520;
		//offsetX = Id*(widthPage+50);
		//myFormatImage = _myFormatImage;
		myGeneratedText = new GeneratedText(parent/*, offsetX*/);
	}

	public void draw() {

		// Drawing the page
		parent.noFill();
		parent.stroke(200,0,0);
		
		// Drawing the patterns on the page
		myGeneratedText.render();


	}

	public void PDFdraw(PGraphics myPDF) {

		// Drawing the patterns on the page
		myGeneratedText.PDFrender(myPDF);


	}

	public void changeFormat() {
		// Type of format, Letter or A4
		rectType = Params.Format;
	}

	public void updateText(List<GenCharacter> _updatedArray)
	{
		// Updating the generated text on the page by sending the Array of chars splitted.
		myGeneratedText.update(_updatedArray);
	}

	public void regenerate() {
		myGeneratedText.regenerate();

	}



}
