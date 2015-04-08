/**
 * InputHandler.java
 * @author Greg (Prime) Galligan
 */
package gp.galligan.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputHandler implements KeyListener
{
	public class Key {
		private boolean pressed  = false;
		private int timesPressed = 0;
		
		public boolean isPressed() { return pressed; }
		
		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if(isPressed) { timesPressed++; }
		}
		
		public int getTimesPressed() { return timesPressed; }
	}
	
	public ArrayList<Key> keys = new ArrayList<Key>();
	public Key keyUp = new Key();
	public Key keyDown = new Key();
	public Key keyLeft = new Key();
	public Key keyRight = new Key();
	
	public InputHandler(GameEngine Engine) {
		Engine.addKeyListener(this);
	}

	/**
	 * Marks a key as pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	/**
	 * Marks a key as released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);	
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	public void toggleKey(int keyCode, boolean isPressed) {
		if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP)    { keyUp.toggle(isPressed); }
		if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN)  { keyDown.toggle(isPressed); }
		if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)  { keyLeft.toggle(isPressed); }
		if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) { keyRight.toggle(isPressed); }
	}
	
}