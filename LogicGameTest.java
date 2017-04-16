import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class LogicGameTest extends GraphicsProgram {

	/* How many ms to pause between "heartbeats" */
	private static final int DELAY = 2;
	/* How much to reduce velocity after a collision */
	private static final double DAMPING = 0.85;
	/* Initial speed in the x direction */
	private static final double INITIAL_VY = -1;
	/* Initial speed in the y direction */
	private static final double INITIAL_VX = 1;
	/* The amount of acceleration in the y direction */
	private static final double DELTA_VY = 0.05;
	/* The size of the ball (in pixels) */
	private static final int BALL_RADIUS = 15;
	/* The color of the ball */
	private static final Color BALL_COLOR = Color.BLUE;
	/* The damping from the force of static friction */
	private static final double STATIC_FRICTION = 0.05;
	/* The damping from the force of friction */
	private static final double FRICTION = 0.999;

	private RandomGenerator rgen = RandomGenerator.getInstance();

	private double vx = rgen.nextDouble(0.0, 3.0);
	private double vy = rgen.nextDouble(0.0, 1.0);
	private double vx2 = rgen.nextDouble(0.0, 3.0);
	private double vy2 = rgen.nextDouble(0.0, 1.0);


	private GRect bucket = new GRect (50,10);

	public void run() {	
		Ball ball = new Ball(BALL_COLOR, 200, 200);
		Ball ball2 = new Ball(BALL_COLOR, 100, 200);
		add(ball.getBall());
		add(ball2.getBall());

		waitForClick();
		
		while(true) {
			
		
			// update visualization
			move(ball, vx, vy);
			move(ball2, vx2, vy2);
			vy += DELTA_VY;
			// update parameters
			if(hitLeftWall(ball, vx) || hitRightWall(ball, vx)) {
				vx = -(vx * DAMPING);
			}
			if(hitTopWall(ball, vy)) {
				vy = -(vy * DAMPING);
			}
			if(hitBottomWall(ball, vy)) {
				vy = -(vy * DAMPING);
				// if the ball is rolling on the ground.
				if(Math.abs(vy) < 0.5) {
					vy = 0.0;
					vx = vx * FRICTION;
					if(Math.abs(vx) < STATIC_FRICTION) {
						vx = 0.0;
					}
				}
			}
			/*	if(ball.fillBucket()){

				}*/
			// pause
			pause(DELAY);
		}

		
	}


/* public boolean fillBucket() {
		if(getCollidingObject(ballImg.getX(), ballImg.getY())== bucket){


		}

		private GObject getCollidingObject(double x, double y){
			if (getElementAt (x,y) !=null){
				GObject collider = getElementAt (x,y);
				return collider;
			}else if (getElementAt (x+2*BALL_RADIUS,y) !=null){
				GObject collider = getElementAt (x+2*BALL_RADIUS,y);
				return collider;
			}else if (getElementAt (x,y+2*BALL_RADIUS) !=null){
				GObject collider = getElementAt (x,y+2*BALL_RADIUS);
				return collider;
			}else if (getElementAt (x+2*BALL_RADIUS,y+2*BALL_RADIUS) !=null){
				GObject collider = getElementAt (x+2*BALL_RADIUS,y+2*BALL_RADIUS);
				return collider;
			}else{
				return null;
			}
		}
 */

public void move(Ball ball, double vx, double vy){
	ball.getBall().move(vx, vy);
}

/**
 * Method: Hit Bottom Wall
 * -----------------------
 * Returns whether or not the given ball should bounce off
 * of the bottom wall of the window.
 */
public boolean hitBottomWall(Ball ball, double vy) {
	if(vy < 0) return false;
	return ball.getBall().getY() > getHeight() - ball.getBall().getHeight();
}

/**
 * Method: Hit Top Wall
 * -----------------------
 * Returns whether or not the given ball should bounce off
 * of the top wall of the window.
 */
public boolean hitTopWall(Ball ball, double vy) {
	if(vy > 0) return false;
	return ball.getBall().getY() <= 0;
}

/**
 * Method: Hit Right Wall
 * -----------------------
 * Returns whether or not the given ball should bounce off
 * of the right wall of the window.
 */
public boolean hitRightWall(Ball ball, double vx) {
	if(vx < 0) return false;
	return ball.getBall().getX() >= getWidth() - ball.getBall().getWidth();
}

/**
 * Method: Hit Left Wall
 * -----------------------
 * Returns whether or not the given ball should bounce off
 * of the left wall of the window.
 */
public boolean hitLeftWall(Ball ball, double vx) {
	if(vx > 0) return false;
	return ball.getBall().getX() <= 0;
}




}


