package senorgenerativefont.senorGeneration;

import java.util.Arrays;
import java.util.Collections;


import processing.core.PApplet;
import senorgenerativefont.senorInterface.Params;
import processing.core.PGraphics;


/*
 * The generated character, drawn by pou7
 */

public class GenCharacter {

	PApplet parent;
	int OffsetX;
	int OffsetY;
	float myColor;
	char theKey;

	//How many shapes for each layer
	int howManyShapesLayer1;
	int howManyShapesLayer2;
	int howManyShapesLayer3;
	int howManyShapesLayer4;

	//Wich Shape? Array of IDs
	Integer[] arrayLayer1 = new Integer[]{1,2,3};
	Integer[] arrayLayer2 = new Integer[]{1,2};
	Integer[] arrayLayer3 = new Integer[]{1,2,3,4};
	Integer[] arrayLayer4 = new Integer[]{1,2,3,4,5,6,7,8};

	public GenCharacter(PApplet p, char _theKey)
	{
		parent = p;
		myColor = parent.random(255);
		theKey = _theKey;

		System.out.println("Char:"+theKey);

		//How Many Shapes for each Layer
		howManyShapesLayer1 = Params.myCharHashTable.get(theKey)[0];
		howManyShapesLayer2 = Params.myCharHashTable.get(theKey)[1];
		howManyShapesLayer3 = Params.myCharHashTable.get(theKey)[2];
		howManyShapesLayer4 = Params.myCharHashTable.get(theKey)[3];

		//Shuffling the arrays of IDs
		shuffle();


	}

	public void shuffle() {
		Collections.shuffle(Arrays.asList(arrayLayer1));
		Collections.shuffle(Arrays.asList(arrayLayer2));
		Collections.shuffle(Arrays.asList(arrayLayer3));
		Collections.shuffle(Arrays.asList(arrayLayer4));
	}

	public void render(int _offsetX, int _offsetY)
	{

		parent.noStroke();

		//Position
		OffsetX = _offsetX*80;
		OffsetY = _offsetY*80;

		parent.pushMatrix();
		parent.translate(OffsetX, OffsetY);

		// Generate Layer 1
		for (int i =0; i<howManyShapesLayer1; i++)
		{
			generateLayer1 (arrayLayer1[i]);
		}

		// Generate Layer 2

		for (int i =0; i<howManyShapesLayer2; i++)
		{
			generateLayer2 (arrayLayer2[i]);
		}

		// Generate Layer 4

		for (int i =0; i<howManyShapesLayer4; i++)
		{
			generateLayer4 (arrayLayer4[i]);
		}

		// Generate Layer 3

		for (int i =0; i<howManyShapesLayer3; i++)
		{
			generateLayer3 (arrayLayer3[i]);
		}




		parent.popMatrix();

	}
	
	

	private void generateLayer1 (int ID)
	{
		parent.fill(Params.colorArray.get(0)[0], Params.colorArray.get(0)[1], Params.colorArray.get(0)[2], 90 + (55*ID));
		switch(ID)
		{
		case 1: parent.triangle (0, 0, 40, 0, 0, 80);
		break;
		case 2: parent.triangle (0, 80, 40, 0, 80, 80);
		break;
		case 3: parent.triangle (80, 0, 80, 80, 40, 0);
		break;

		}
	}

	private void generateLayer2 (int ID)
	{
		parent.fill(Params.colorArray.get(1)[0], Params.colorArray.get(1)[1], Params.colorArray.get(1)[2], 25+(50*ID));
		switch(ID)
		{
		case 1: parent.triangle (0, 40, 40, 0, 40, 80);
		break;
		case 2: parent.triangle (80, 40, 40, 0, 40, 80);
		break;
		}
	}

	private void generateLayer4 (int ID)
	{
		switch(ID)
		{
		case 1: createArc( 20, 20,  Params.colorArray.get(2), "TOP");
		break;
		case 2: createArc( 20, 20, Params.colorArray.get(2), "BOTTOM");
		break;
		case 3: createArc( 20, 60,  Params.colorArray.get(2), "TOP");
		break;
		case 4: createArc( 20, 60, Params.colorArray.get(2), "BOTTOM");
		break;
		case 5: createArc( 60, 20,  Params.colorArray.get(2), "TOP");
		break;
		case 6: createArc( 60, 20, Params.colorArray.get(2), "BOTTOM");
		break;
		case 7: createArc( 60, 60,  Params.colorArray.get(2), "TOP");
		break;
		case 8: createArc( 60, 60, Params.colorArray.get(2), "BOTTOM");
		break;
		}
	}

	private void generateLayer3 (int ID)
	{
		parent.fill (Params.colorArray.get(3)[0], Params.colorArray.get(3)[1],  Params.colorArray.get(3)[2], 125);
		switch(ID)
		{
		case 1: parent.quad (0, 20, 20, 0, 40, 20, 20, 40); 
		break;
		case 2: parent.quad (0, 60, 20, 40, 40, 60, 20, 80);
		break;
		case 3: parent.quad (40, 20, 60, 0, 80, 20, 60, 40);
		break;
		case 4:parent.quad (40, 60, 60, 40, 80, 60, 60, 80);
		break;	
		}
	}

	private void createArc (int arcX, int arcY, int[] _arcColor, String theHalf)
	{
		if (theHalf == "BOTTOM")
		{
			parent.fill(_arcColor[0], _arcColor[1],  _arcColor[2], 125 );
			parent.arc(arcX, arcY, 40, 40, PApplet.HALF_PI-PApplet.QUARTER_PI, PApplet.PI+PApplet.QUARTER_PI);
		}
		else {
			parent.fill(_arcColor[0], _arcColor[1],  _arcColor[2], 255 );
			parent.arc(arcX, arcY, 40, 40, PApplet.PI+PApplet.QUARTER_PI, PApplet.TWO_PI+PApplet.QUARTER_PI);
		}
	}
	
	/////////////////////PDF
	
	public void renderPDF(int _offsetX, int _offsetY, PGraphics myPDF)
	{

		myPDF.noStroke();

		//Position
		OffsetX = _offsetX*80;
		OffsetY = _offsetY*80;

		myPDF.pushMatrix();
		myPDF.translate(OffsetX, OffsetY);

		// Generate Layer 1
		for (int i =0; i<howManyShapesLayer1; i++)
		{
			generateLayer1PDF (arrayLayer1[i], myPDF);
		}

		// Generate Layer 2

		for (int i =0; i<howManyShapesLayer2; i++)
		{
			generateLayer2PDF (arrayLayer2[i], myPDF);
		}

		// Generate Layer 4

		for (int i =0; i<howManyShapesLayer4; i++)
		{
			generateLayer4PDF (arrayLayer4[i], myPDF);
		}

		// Generate Layer 3

		for (int i =0; i<howManyShapesLayer3; i++)
		{
			generateLayer3PDF (arrayLayer3[i], myPDF);
		}




		myPDF.popMatrix();

	}
	
	

	private void generateLayer1PDF (int ID, PGraphics myPDF)
	{
		myPDF.fill(Params.colorArray.get(0)[0], Params.colorArray.get(0)[1], Params.colorArray.get(0)[2], 90 + (55*ID));
		switch(ID)
		{
		case 1: myPDF.triangle (0, 0, 40, 0, 0, 80);
		break;
		case 2: myPDF.triangle (0, 80, 40, 0, 80, 80);
		break;
		case 3: myPDF.triangle (80, 0, 80, 80, 40, 0);
		break;

		}
	}

	private void generateLayer2PDF (int ID, PGraphics myPDF)
	{
		myPDF.fill(Params.colorArray.get(1)[0], Params.colorArray.get(1)[1], Params.colorArray.get(1)[2], 25+(50*ID));
		switch(ID)
		{
		case 1: myPDF.triangle (0, 40, 40, 0, 40, 80);
		break;
		case 2: myPDF.triangle (80, 40, 40, 0, 40, 80);
		break;
		}
	}

	private void generateLayer4PDF (int ID, PGraphics myPDF)
	{
		switch(ID)
		{
		case 1: createArcPDF( 20, 20,  Params.colorArray.get(2), "TOP", myPDF);
		break;
		case 2: createArcPDF( 20, 20, Params.colorArray.get(2), "BOTTOM", myPDF);
		break;
		case 3: createArcPDF( 20, 60,  Params.colorArray.get(2), "TOP", myPDF);
		break;
		case 4: createArcPDF( 20, 60, Params.colorArray.get(2), "BOTTOM", myPDF);
		break;
		case 5: createArcPDF( 60, 20,  Params.colorArray.get(2), "TOP", myPDF);
		break;
		case 6: createArcPDF( 60, 20, Params.colorArray.get(2), "BOTTOM", myPDF);
		break;
		case 7: createArcPDF( 60, 60,  Params.colorArray.get(2), "TOP", myPDF);
		break;
		case 8: createArcPDF( 60, 60, Params.colorArray.get(2), "BOTTOM", myPDF);
		break;
		}
	}

	private void generateLayer3PDF (int ID, PGraphics myPDF)
	{
		myPDF.fill (Params.colorArray.get(3)[0], Params.colorArray.get(3)[1],  Params.colorArray.get(3)[2], 125);
		switch(ID)
		{
		case 1: myPDF.quad (0, 20, 20, 0, 40, 20, 20, 40); 
		break;
		case 2: myPDF.quad (0, 60, 20, 40, 40, 60, 20, 80);
		break;
		case 3: myPDF.quad (40, 20, 60, 0, 80, 20, 60, 40);
		break;
		case 4: myPDF.quad (40, 60, 60, 40, 80, 60, 60, 80);
		break;	
		}
	}

	private void createArcPDF (int arcX, int arcY, int[] _arcColor, String theHalf, PGraphics myPDF)
	{
		if (theHalf == "BOTTOM")
		{
			myPDF.fill(_arcColor[0], _arcColor[1],  _arcColor[2], 125 );
			myPDF.arc(arcX, arcY, 40, 40, PApplet.HALF_PI-PApplet.QUARTER_PI, PApplet.PI+PApplet.QUARTER_PI);
		}
		else {
			myPDF.fill(_arcColor[0], _arcColor[1],  _arcColor[2], 255 );
			myPDF.arc(arcX, arcY, 40, 40, PApplet.PI+PApplet.QUARTER_PI, PApplet.TWO_PI+PApplet.QUARTER_PI);
		}
	}

}
