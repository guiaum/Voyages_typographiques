package senorgenerativefont.senorInterface;

import processing.core.PApplet;

public class ScrollBar {
	
	PApplet parent;
	int swidth, sheight;    // width and height of bar
	float xpos, ypos;       // x and y position of bar
	float spos, newspos;    // x position of slider
	float sposMin, sposMax; // max and min values of slider
	int loose;              // how loose/heavy
	boolean over;           // is the mouse over the slider?
	boolean locked;
	float ratio;

	public ScrollBar(PApplet p, float xp, float yp, int sw, int sh, int l) {
		parent = p;
		swidth = sw;
		sheight = sh;
		int widthtoheight = sw - sh;
		ratio = (float)sw / (float)widthtoheight;
		xpos = xp;
		ypos = yp-sheight/2;
		//spos = xpos + swidth/2 - sheight/2;
		spos = xpos + sheight/2;
		newspos = spos;
		sposMin = xpos;
		sposMax = xpos + swidth - sheight;
		loose = l;
	}

	public void update() {
		if(overEvent()) {
			over = true;
		} else {
			over = false;
		}
		if(parent.mousePressed && over) {
			locked = true;
		}
		if(!parent.mousePressed) {
			locked = false;
		}
		if(locked) {
			newspos = constrain(parent.mouseX-sheight/2, sposMin, sposMax);
		}
		if(PApplet.abs(newspos - spos) > 1) {
			spos = spos + (newspos-spos)/loose;
		}
	}

	float constrain(float val, float minv, float maxv) {
		return PApplet.min(PApplet.max(val, minv), maxv);
	}

	boolean overEvent() {
		if(parent.mouseX > xpos && parent.mouseX < xpos+swidth &&
				parent.mouseY > ypos && parent.mouseY < ypos+sheight) {
			return true;
		} else {
			return false;
		}
	}

	public void display() {
		parent.noStroke();
		parent.fill(51);
		parent.rect(xpos, ypos, swidth, sheight);
		if(over || locked) {
			parent.fill(255, 0, 0);
		} else {
			parent.fill(102);
		}
		parent.rect(spos, ypos, sheight, sheight);
	}

	public float getPos() {
		// Convert spos to be values between
		// 0 and the total width of the scrollbar
		return spos * ratio;
	}
}
