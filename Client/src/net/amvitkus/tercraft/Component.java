package net.amvitkus.drugcraft;

//Applet is for the web
import java.applet.*;
import java.awt.*;
//Opening up a window
import javax.swing.*;
import java.util.*;

//Component is working area for drawing things
public class Component extends Applet implements Runnable {// Runnable use
															// methods from
															// Runnable class

	private static final long serialVersionUID = 1L; //Compilation things, not
														// important

	public static int pixelSize = 2;// 2 by 2 pixels or 3 by 3. 3x3 zooms in closer

	public static int moveFromBorder = 0;//Prevent character lag tick
	public static double sX = moveFromBorder, sY = moveFromBorder; // the
																	// sidescrolling
																	// coordinates
																	// so we can
																	// move
	public static double dir = 0;

	//Public is other classes can see it
	//Static other classes can use it
	//Dimension is a method that takes parameters, holds width and heigth.
	public static Dimension realSize = new Dimension (0,0); //Get rid of white line on side
	public static Dimension size = new Dimension(700, 560);
	public static Dimension pixel = new Dimension(size.width / pixelSize,
			size.height / pixelSize);// For the pixel size
	
	public static int height = 700;
	public static int width = 560;

	public static Point mse = new Point(0, 0);

	public static String name = "2D Drugcraft";
	public static String deathText = "YOU ARE DEAD!";

	public static boolean isRunning = false;
	public static boolean isMoving = false;
	public static boolean isJumping = false;
	public static boolean isMouseLeft = false;
	public static boolean isMouseRight = false;

	private Image screen; //shown on actual screen
	
	public int fps;
	public int totalTime;

	// Creating class objects
	public static Level level;
	public static Character character;
	public static Inventory inventory;
	public static Sky sky;
	public static Spawner spawner;
	public static Checker checker;
	public static ArrayList<Mob> mob = new ArrayList<Mob>();

	// Constructor
	// Component is Setting sizes and stuff
	public Component() {
		// Componenets own size
		setPreferredSize(size);

		// adding key listener
		addKeyListener(new Listening());

		// add mouse listeners
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
		addMouseWheelListener(new Listening());

	}

	// void start and stop are applet methods
	public void start() {
		
		frame = new JFrame();						
		realSize = new Dimension(frame.getWidth(), frame.getHeight());
		
		frame.setTitle(name);
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.setVisible(true);		
		frame.setIconImage(new ImageIcon("res/tile_icon.png").getImage());		
		//requestFocus();
 
		
		// Defining objects etc.		
		new Tile(); // Loading images
		level = new Level();// level is defined and will be used before loop
		character = new Character(Tile.tileSize, Tile.tileSize * 2);// width and
																	// heigth
		inventory = new Inventory();
		sky = new Sky();
		spawner = new Spawner();
		checker = new Checker();

		// Starting game loop
		isRunning = true;
		new Thread(this).start();// create a thread, then starting it
		// thread is something that you can run multiple threads at one time

	}

	public void stop() {
		isRunning = false;

	}
	
	private static JFrame frame;

	// game loop runs all the time. all character movements, clicks, timing,
	// etc. all games need it duh
	public static void main(String args[]) {

//		// New object of component method
//		// Game reads Component first
//		Component component = new Component();
//		// New frame
//		frame = new JFrame();

//		// When created, jumps to public Component()
//		frame.add(component);
		
		Component component = new Component();
		
		new Menu(component);
	}

	public void tick() {// tick moves monsters, character movement
		
		if(frame.getWidth() != realSize.width || frame.getHeight() != realSize.height){
			frame.pack();
		}
		
		character.tick();
		level.tick((int) sX, (int) sY, (pixel.width / Tile.tileSize) + 2,
				(pixel.height / Tile.tileSize) + 2);
		sky.tick();
		checker.tick();
		
		for(int i = 0; i < mob.toArray().length; i++){
			mob.get(i).tick();
		}

	}

	public void render() {// drawing things on screen basically
		Graphics g = screen.getGraphics();// object used to draw rectangles,
											// whatever on screen

		// Drawing things.
		// Everything is drawn on top of screen image
		sky.render(g);
		level.render(g, (int) sX, (int) sY, (pixel.width / Tile.tileSize) + 2,
				(pixel.height / Tile.tileSize) + 2);
		character.render(g);
		inventory.render(g);
		
		for(int i = 0; i < mob.toArray().length; i++){
			mob.get(i).render(g);
		}

		g.setColor(Color.GREEN);
		g.drawString(fps + " fps", 1, 10);
		g.drawString("X: " + Math.round(sX / 18), 315, 10);
		g.drawString("Y: " + Math.round(sY / 18), 315, 20);
		
		if(character.isDead){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.RED);
			g.setFont(new Font("Courier New", Font.BOLD, 24));
			g.drawString(deathText, 80, 135);
		}

		g = getGraphics();

		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width,
				pixel.height, null);	
		g.dispose();//throw away object to prevent lag
				
		
	}

	public void run() {//run used to make threads

		screen = createVolatileImage(pixel.width, pixel.height);// createVolatileImage
																// creates game
																// ontop of user
																// graphics card
//Show fps and x,y coordinate in game
		int frames = 0;
		double nonProcessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean hasTicked = false;
		int totalTime = 0;
		
		while (isRunning) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			nonProcessedSeconds += passedTime / 1000000000.0;
			
			while (nonProcessedSeconds > secondsPerTick){
				tick();
				nonProcessedSeconds -= secondsPerTick;
				hasTicked = true;
				tickCount++;
					if (tickCount % 60 == 0){
						previousTime += 1000;
						fps = frames;
						frames = 0;
					}
			}
			if(hasTicked){
			frames++;
			}
			
			frames++;		
			requestFocus();
			tick();
			render();
			
			try {
				Thread.sleep(5);// stop game 5 miliseconds, before try drawing
								// another time
			} catch (Exception e) {

			}

		}

	}

}
