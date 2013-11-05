package senorgenerativefont.senorInterface;

import processing.core.PApplet;
import processing.core.PImage;
import controlP5.*;

public class SenorControlP5 {

	PApplet parent;
	ControlP5 cp5;
	RadioButton interfaceFormat;
	RadioButton numberLines;
	RadioButton interfaceColor;
	int myOrigine = 75;

	public SenorControlP5(PApplet p, ControlP5 _cp5)
	{
		parent = p;
		cp5 = _cp5;
		createInterfaceFORMAT();
		createSliderNBERINLINES();
		createInterfaceCOLORS();
		createInterfaceRANDOMCOL();
		createInterfacePDF();
		createImportBtn();
		createClearBtn();
		createConvertBtn();
		Params.UIcreated = true;
	}
	
	private void createImportBtn() {
		PImage[] imgBtnImport = {parent.loadImage("type-pattern-import.png"),parent.loadImage("type-pattern-import-alt.png"),parent.loadImage("type-pattern-import-bleu.png")};
		cp5.addButton("ImportTXT")
		.setValue(0)
		.setPosition(126,675)
		.setImages(imgBtnImport)
		.setSize(65,45)
		;
	}
	
	
	
	private void createClearBtn() {
		PImage[] imgBtnClear = {parent.loadImage("type-pattern-clear.png"),parent.loadImage("type-pattern-clear-alt.png"),parent.loadImage("type-pattern-clear-click.png")};
		cp5.addButton("Clear")
		.setValue(0)
		.setPosition(201,675)
		.setImages(imgBtnClear)
		.setSize(65,45)
		;
	}
	
	private void createConvertBtn() {
		PImage[] imgBtnConvert = {parent.loadImage("type-pattern-convert-click.png"),parent.loadImage("type-pattern-convert-alt.png"),parent.loadImage("type-pattern-convert.png")};
		cp5.addButton("Convert")
		.setValue(0)
		.setPosition(276,675)
		.setImages(imgBtnConvert)
		.setSize(65,45)
		;
	}
	
	private void createInterfaceFORMAT() {
		//Toggle to choose the size of the Pages
		interfaceFormat = cp5.addRadioButton("FORMAT")
				.setPosition(352,myOrigine)
				.setSize(15,15)
				.setNoneSelectedAllowed(false)
				.setItemsPerRow(1)
				.setSpacingRow(17)
				.addItem("A4", 1)
				.addItem("Letter", 2)
				.activate(0)
				;
		interfaceFormat.hideLabels();
	}

	private void createSliderNBERINLINES()
	{
		//Toggle to choose the number of char by lines
		numberLines = cp5.addRadioButton("NUMBERLINES")
				.setPosition(437,myOrigine)
				.setSize(15,15)
				.setNoneSelectedAllowed(false)
				.addItem("4",4)
				.addItem("8",8)
				.addItem("16",16)
				.addItem("24",24)
				.setSpacingRow(17)
				.setSpacingColumn(70)
				.setItemsPerRow(2)
				.activate(0)
				;
		numberLines.hideLabels();
	}

	private void createInterfaceCOLORS() {
		//Toggle to choose colour or black & white
		interfaceColor = cp5.addRadioButton("COLORS")
				.setPosition(607,myOrigine)
				.setSize(15,15)
				.setItemsPerRow(1)
				.setSpacingRow(17)
				.setNoneSelectedAllowed(false)
				.addItem("Color", 1)
				.addItem("BW", 2)
				.activate(0)
				;
		interfaceColor.hideLabels();

	}

	private void createInterfaceRANDOMCOL() {

		PImage[] imgBtnC1 = {parent.loadImage("type-pattern-color-1.png"),parent.loadImage("type-pattern-color-1-alt.png"),parent.loadImage("type-pattern-color-1-click.png")};
		cp5.addButton("Color1")
		.setPosition(692,myOrigine)
		.setSize(30,30)
		.setImages(imgBtnC1)
		.setValue(0)
		;

		PImage[] imgBtnC2 = {parent.loadImage("type-pattern-color-2.png"),parent.loadImage("type-pattern-color-2-alt.png"),parent.loadImage("type-pattern-color-2-click.png")};
		cp5.addButton("Color2")
		.setPosition(732,myOrigine)
		.setSize(30,30)
		.setImages(imgBtnC2)
		.setValue(0)
		;

		PImage[] imgBtnC3 = {parent.loadImage("type-pattern-color-3.png"),parent.loadImage("type-pattern-color-3-alt.png"),parent.loadImage("type-pattern-color-3-click.png")};
		cp5.addButton("Color3")
		.setPosition(772,myOrigine)
		.setSize(30,30)
		.setImages(imgBtnC3)
		.setValue(0)
		;

		PImage[] imgBtnC4 = {parent.loadImage("type-pattern-color-4.png"),parent.loadImage("type-pattern-color-4-alt.png"),parent.loadImage("type-pattern-color-4-click.png")};
		cp5.addButton("Color4")
		.setPosition(812,myOrigine)
		.setSize(30,30)
		.setImages(imgBtnC4)
		.setValue(0)
		;




	}

	private void createInterfacePDF() {

		PImage[] imgRegenerate = {parent.loadImage("type-pattern-random.png"),parent.loadImage("type-pattern-random-alt.png"),parent.loadImage("type-pattern-random-click.png")};
		cp5.addButton("Regenerate")
		.setValue(0)
		.setPosition(862,myOrigine)
		.setSize(65,45)
		.setImages(imgRegenerate)
		;

		PImage[] imgPDF = {parent.loadImage("type-pattern-pdf-click.png"),parent.loadImage("type-pattern-pdf-alt.png"),parent.loadImage("type-pattern-pdf.png")};
		cp5.addButton("PDF")
		.setValue(0)
		.setPosition(947,myOrigine)
		.setSize(65,45)
		.setImages(imgPDF)
		;
		
	}
	






}
