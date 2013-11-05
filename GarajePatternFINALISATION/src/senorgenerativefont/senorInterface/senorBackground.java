package senorgenerativefont.senorInterface;

import processing.core.PApplet;
import processing.core.PImage;
import controlP5.*;

public class senorBackground {
	
	PApplet parent;
	PImage myBackgroundPNG;
	
	public senorBackground(PApplet p, ControlP5 _cp5, PImage _myBackgroundPNG)
	{
		parent = p;
		myBackgroundPNG = _myBackgroundPNG;
       	
	}
	
	public void draw()
	{
		parent.image(myBackgroundPNG, 0 , 0); 
				
	}
	

}
