package senorgenerativefont.senorGeneration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import senorgenerativefont.senorInterface.Params;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.pdf.PGraphicsPDF;
import processing.core.PFont;
import senorgenerativefont.senorGeneration.Format;

public class Generation {

	PApplet parent;
	int origineX;

	// Array of Pages
	ArrayList<Format> arrayPages = new ArrayList<Format>();
	//Array of characters
	List<GenCharacter> arrayChar = new ArrayList<GenCharacter>();

	public int totalPagesNeeded;
	float charByPages;

	PFont f;

	public Generation(PApplet p, PImage _myFormatImage)
	{
		parent = p;
		origineX = parent.width/2-120;

		// Creation of the 1st Page
		arrayPages.add(new Format(parent, origineX, 0));

		f = parent.createFont("Garaje53Unicase-Black", 20);
		parent.textFont(f);
		parent.textAlign(PConstants.LEFT, PConstants.BOTTOM);


	}

	public void draw()
	{


		parent.pushMatrix();

		parent.translate(353, 150);

		//Drawing first page
		Format myPage = (Format) arrayPages.get(0);
		myPage.draw();

		parent.popMatrix();

		//Displaying the numbers of pages.
		parent.fill(63, 169, 245);
		String myDisplayedPagesNeeded = String.format( "%03d" , totalPagesNeeded );
		parent.text(myDisplayedPagesNeeded, 789, 253);

	}



	// Updating the format of the Pages
	public void setFormat()
	{

		for (int i = 0; i<arrayPages.size(); i++)
		{
			Format myPage = (Format) arrayPages.get(i);
			myPage.changeFormat();

		}



	}

	public void add(char _theKey)
	{
		arrayChar.add(new GenCharacter(parent, _theKey));
		thePagesUpdate();
		theCharsSplit();
	}

	public void remove()
	{
		arrayChar.remove(arrayChar.size()-1);
		thePagesUpdate();
		theCharsSplit();
	}


	public void thePagesUpdate() 
	{
		// How many characters in the string
		int totalOfChar = arrayChar.size();

		if (totalOfChar == 0)
		{
			totalPagesNeeded = 1;
		}else{
			// How many characters by page
			charByPages = (Params.NumberLines*(Params.NumberLines*1.5f));

			// How many Pages needed
			totalPagesNeeded = PApplet.ceil(totalOfChar/charByPages);
			System.out.println("character by Pages "+ charByPages+ " - Pages Needed "+ totalPagesNeeded);

			// Updating the pages displayed
			if (arrayPages.size()<totalPagesNeeded)
			{
				while (arrayPages.size()<totalPagesNeeded)
				{
					arrayPages.add(new Format(parent, origineX, arrayPages.size()));
				}
			}else if (arrayPages.size()>totalPagesNeeded && arrayPages.size()>1)
			{
				while (arrayPages.size()>totalPagesNeeded)
				{
					arrayPages.remove(arrayPages.size()-1);
				}
			}

		}
	}

	public void theCharsSplit() {

		//if only 1 page is needed...
		if (totalPagesNeeded == 1)
		{
			//we send the entire array of char to the page
			arrayPages.get(0).updateText(arrayChar);

		}else{

			//if multiple pages are needed
			for (int i=0; i<totalPagesNeeded ; i++)
			{
				int start = i*(int) charByPages;
				int end;
				// if it's the last page
				if (i == totalPagesNeeded -1)
				{
					end = arrayChar.size();
				}else{
					end = start + (int) charByPages;
				}

				List<GenCharacter> splitListofChar = arrayChar.subList(start, end);
				arrayPages.get(i).updateText(splitListofChar);
			}
		}



	}

	public void regenerate() {
		for (int i = 0; i<arrayPages.size(); i++)
		{
			Format myPage = (Format) arrayPages.get(i);
			myPage.regenerate();

		}

	}

	public void eraseAll()
	{
		for (int i = arrayChar.size(); i > 0; i--)
		{
			arrayChar.remove(arrayChar.size()-1);
			thePagesUpdate();
			theCharsSplit();
		}
	}

	public void generatePDF()
	{
		parent.selectOutput("Select a file to export:", "fileExport", null , this);
	}

	public void fileExport(File selection)
	{
		if (selection == null) {
			System.out.println("Window was closed or the user hit cancel.");
		} else {
			System.out.println(selection);

			int myHeight;
			if (Params.Format == 1.0)
			{
				myHeight = 594;
			} else {
				myHeight = 542;
			}

			String exportPath = selection.getAbsolutePath();
			
			
			PGraphicsPDF pdf = (PGraphicsPDF) parent.createGraphics(420, myHeight, PConstants.PDF, exportPath+".pdf");

			pdf.beginDraw();
			//int count = 0;
			for (int i = 0; i<arrayPages.size(); i++)
			{

				Format myPage = (Format) arrayPages.get(i);
				myPage.PDFdraw(pdf);
				if (i != arrayPages.size()-1)
				{
					pdf.nextPage();
				}
			}
			pdf.dispose();
			pdf.endDraw();
			System.out.println("PDF Done");
		}


	}
}