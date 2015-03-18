/**
 * GameEngine.java
 * @author Greg (Prime) Galligan
 */

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameEngine extends Canvas implements Runnable {

	private static final String     TITLE       = "Olimu Engine Alpha";
	private static final int        WIDTH       = 650;
	private static final int        HEIGHT      = WIDTH * 3/4;
	private static final Dimension  DIMENSION   = new Dimension(WIDTH, HEIGHT);
	private static final int        UPDATE_RATE = 30;
	private static final int        RENDER_RATE = 60;
	
	private JFrame _frame;
	private Thread _thread;
	private int    _tps        = 0;
	private int    _fps        = 0;
	private int    _totalTicks = 0;
	
	private BufferedImage _image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)_image.getRaster().getDataBuffer()).getData();
	
	private boolean _running   = false;
	
	/**
	 * Singleton instantiation of GameEngine
	 */	
	private static final GameEngine _engine     = new GameEngine();

	/**
	 * Private constructor for GameEngine
	 */
	private GameEngine() {		
		setMinimumSize(DIMENSION);
		setMaximumSize(DIMENSION);
		setPreferredSize(DIMENSION);
		
		_frame = new JFrame(TITLE);
		_frame.setLayout(new BorderLayout());
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.add(this, BorderLayout.CENTER);
		_frame.pack();
		
		_frame.setLocationRelativeTo(null);
		_frame.setResizable(false);
		_frame.setVisible(true);
	}

	/**
	 * Main running loop of the engine
	 */
	@Override
	public void run() {
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = lastUpdateTime;

		final int    ns = 1000000000;
		final double nsPerUpdate = (double)(ns)/UPDATE_RATE;
		final double nsPerRender = (double)(ns)/RENDER_RATE;
		final int    maxUpdatePerRender = 5;
		
		int lastSecond  = (int)(lastUpdateTime/ns);
		int tickCount   = 0;
		int renderCount = 0;
		
		System.out.println("Game Engine started!");
		while(_running) {
			long currentTime = System.nanoTime();
			int tps     = 0;
			
			while((currentTime-lastUpdateTime)>nsPerUpdate && tps<maxUpdatePerRender) {
				update(tickCount);
				++_totalTicks;
				++tickCount;
				++tps;
				
				lastUpdateTime += nsPerUpdate;
			}
			
			if((currentTime-lastUpdateTime)>nsPerUpdate) {
				lastUpdateTime = currentTime-nsPerUpdate;
			}
			
			float interp = Math.min(1.0F, (float)((currentTime-lastUpdateTime)/nsPerUpdate));
			render(interp);
			++renderCount;
			lastRenderTime = currentTime;
			
			int currentSecond = (int)(lastUpdateTime/ns);
			if(currentSecond>lastSecond) {
				_tps = tickCount;
				_fps = renderCount;
				tickCount   = 0;
				renderCount = 0;
				lastSecond  = currentSecond;
				System.out.println("TPS: " + _tps + "    FPS: " + _fps);
			}
			
			while((currentTime-lastRenderTime)<nsPerRender && (currentTime-lastUpdateTime)<nsPerUpdate) {
				Thread.yield();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				currentTime = System.nanoTime();
			}
			
		}

	}
	
	/**
	 * Starts the dedicated engine thread
	 */
	public synchronized void start() {
		_running = true;
		_thread  = new Thread(this, TITLE + "_main");
		_thread.start();
	}
	
	/**
	 * Stops the dedicated engine thread
	 */
	public synchronized void stop() {
		_running = false;
		if(_thread!=null)
			try {
				_thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Called once per frame, checks for input and updates the screen
	 */
	public void update(int tickCount) {
		for(int i=0; i<pixels.length; i++) {
			pixels[i] = i+tickCount;
		}
	}
	
	/**
	 * Renders screen imagery
	 */
	public void render(float interp) {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(_image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	}

	/**
	 * Checks for input with every call to update
	 */
	/*public void checkInput() {
		if(_input.isKeyDown(KeyEvent.VK_RIGHT)) {
			_gs.getPlayer().moveX(1);
		}

		if(_input.isKeyDown(KeyEvent.VK_LEFT)) {
			_gs.getPlayer().moveX(-1);
		}

		if(_input.isKeyDown(KeyEvent.VK_UP)) {
			_gs.getPlayer().moveY(-1);
		}

		if(_input.isKeyDown(KeyEvent.VK_DOWN)) {
			_gs.getPlayer().moveY(1);
		}
	}*/	

	/**
	 * Starts the game engine and loads to the menu
	 * @param String[] args, unused.
	 */
	public static void main(String[] args) {
		System.out.println("Starting Game Engine...");
		_engine.start();
	}

}