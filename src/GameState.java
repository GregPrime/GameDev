/**
 * GameState.java
 * @author Greg (Prime) Galligan
 */

public class GameState {
	
	private static final GameState gs = new GameState();
	private Player player;
	
	private GameState() {
		player = new Player();
	}
	
	/**
	 * Accessor to the singleton GameState
	 * @return GameState
	 */
	public static GameState getGameState() {
		return gs;
	}
	
	/**
	 * Moves the player's X-Coordinate
	 * @param int
	 */
	public void movePlayerX(int x) {
		player.moveX(x);
	}
	
	/**
	 * Moves the player's Y-Coordinate
	 * @param int
	 */
	public void movePlayerY(int y) {
		player.moveY(y);
	}
	
	/**
	 * Accessor to the game's Player
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}
}