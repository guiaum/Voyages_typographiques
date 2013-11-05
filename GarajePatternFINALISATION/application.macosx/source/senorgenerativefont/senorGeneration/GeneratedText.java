package senorgenerativefont.senorGeneration;

import java.util.List;

import processing.core.PApplet;
import senorgenerativefont.senorInterface.Params;
import processing.core.PGraphics;

/*
 * Embedded in a page, a paragraph that store and display the characters
 */

public class GeneratedText {

	PApplet parent;
	//int offsetX;

	List<GenCharacter> arrayChar;

	public GeneratedText(PApplet p/*, int _offsetX*/)
	{
		parent = p;
		//offsetX = _offsetX;

	}


	public void render()
	{
		//Center the patterns in the page, depending of the format
		parent.pushMatrix();

		/*
		if (Params.Format == 1.0f)
		{
			parent.translate(42+offsetX, 45);
		}else{*/
			parent.translate(42/*+offsetX*/, 45);
		//}

		parent.scale((float)(1.0f/(Params.NumberLines/4.0f)));

		if (arrayChar != null)
		{
			for (int i=0; i<arrayChar.size(); i++)
			{
				// Position Lines/Rows
				int xOffset = i% Params.NumberLines;
				int yOffset = PApplet.round(i/ Params.NumberLines);
				GenCharacter myCharacter = (GenCharacter) arrayChar.get(i);
				myCharacter.render(xOffset, yOffset);

			}
		}
		parent.popMatrix();
	}
	
	public void PDFrender(PGraphics myPDF)
	{
		//Center the patterns in the page, depending of the format
		myPDF.pushMatrix();

		if (Params.Format == 1.0f)
		{
			myPDF.translate(50, 57);
		}else{
			myPDF.translate(50, 31);
		}

		myPDF.scale((float)(1.0f/(Params.NumberLines/4.0f)));

		if (arrayChar != null)
		{
			for (int i=0; i<arrayChar.size(); i++)
			{
				// Position Lines/Rows
				int xOffset = i% Params.NumberLines;
				int yOffset = PApplet.round(i/ Params.NumberLines);
				GenCharacter myCharacter = (GenCharacter) arrayChar.get(i);
				myCharacter.renderPDF(xOffset, yOffset, myPDF);

			}
		}
		myPDF.popMatrix();
	}
	
	public void update(List<GenCharacter> _arrayChar) 
	{
		arrayChar = _arrayChar;

	}


	public void regenerate() {
		if (arrayChar != null)
		{
			for (int i=0; i<arrayChar.size(); i++)
			{
				GenCharacter myCharacter = (GenCharacter) arrayChar.get(i);
				myCharacter.shuffle();

			}
		}
		
	}

}