/**
 * Player.java
 * @author Greg (Prime) Galligan
 */
package gp.galligan.game;

public class Player {

	private Player player;
	private int    x,      y;
	
	public Player() {
		x=y=100;
	}
	
	/**
	 * Moves the player's X-Coordinate
	 * @param int
	 */
	public void moveX(int x) {
		this.x+=x;
	}
	
	/**
	 * Moves the player's Y-Coordinate
	 * @param int
	 */
	public void moveY(int y) {
		this.y+=y;
	}
	
	/**
	 * Accessor to the X-Coordinate
	 * @return int
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Accessor to the Y-Coordinate
	 * @return int
	 */
	public int getY() {
		return y;
	}
	
}