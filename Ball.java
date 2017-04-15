import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Ball extends GraphicsProgram{
	private GOval ballImg;
	private Color ballColor;
	
	public Ball (Color color) {
		ballColor=color;
		ballImg = new GOval(20,20);
		ballImg.setColor(ballColor);
	}
	public void addBall(){
		add(ballImg, 20, 20);
	}

	public void move(double vx, double vy){
		ballImg.move(vx, vy);
	}
	/**
	 * Method: Hit Bottom Wall
	 * -----------------------
	 * Returns whether or not the given ball should bounce off
	 * of the bottom wall of the window.
	 */
	public boolean hitBottomWall(double vy) {
		if(vy < 0) return false;
		return ballImg.getY() > getHeight() - ballImg.getHeight();
	}

	/**
	 * Method: Hit Top Wall
	 * -----------------------
	 * Returns whether or not the given ball should bounce off
	 * of the top wall of the window.
	 */
	public boolean hitTopWall(double vy) {
		if(vy > 0) return false;
		return ballImg.getY() <= 0;
	}

	/**
	 * Method: Hit Right Wall
	 * -----------------------
	 * Returns whether or not the given ball should bounce off
	 * of the right wall of the window.
	 */
	public boolean hitRightWall(double vx) {
		if(vx < 0) return false;
		return ballImg.getX() >= getWidth() - ballImg.getWidth();
	}

	/**
	 * Method: Hit Left Wall
	 * -----------------------
	 * Returns whether or not the given ball should bounce off
	 * of the left wall of the window.
	 */
	public boolean hitLeftWall(double vx) {
		if(vx > 0) return false;
		return ballImg.getX() <= 0;
	}
	


}
