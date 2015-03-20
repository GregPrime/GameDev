/**
 * InputHandler.java
 * @author Greg (Prime) Galligan
 */
package gp.galligan.game;

import java.awt.Component;
import java.awt.event.*;

public class InputHandler implements KeyListener
{
	
	private int keyDown;
	
	public InputHandler(Component c) {
		//keys = new boolean[256];
		//for(int i=0; i<256; i++) {
		//	keys[i]=false;
		//}
		c.addKeyListener(this);
	}
	
	/**
	 * Checks if a key is marked as pressed
	 * @param int
	 * @return boolean
	 */
	public boolean isKeyDown(int keyCode) {
		if(keyCode>0 && keyCode<256)
			return (keyDown==keyCode);
		else
			return false;
	}

	/**
	 * Marks a key as pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()>0 && e.getKeyCode()<256) {
			keyDown=e.getKeyCode();
		}	
	}

	/**
	 * Marks a key as released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()>0 && e.getKeyCode()<256) {
			keyDown=-1;
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
}