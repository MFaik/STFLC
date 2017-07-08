
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static int WIDTH = 800,HEIGHT = WIDTH/12 * 9;
	public int offsetMaxX,offsetMaxY,offsetMinX,offsetMinY;
	public float camX,camY;
	private Thread thread;
	private boolean running = false;
	protected int save[] = new int[2];
	private Handler handler;
	public boolean paused = false;
	public int c1,c2 = 0,c3;
	public Game(){
		try{
		//	File x = new File("C:\\Windows\\fa.ik");
			File x = new File("C:\\Users\\Hp\\Desktop\\fa.ik");
			Scanner sc = new Scanner(x);
			while(sc.hasNext()){
				c2++;
				if(c1 == 0){
					c1 = sc.nextInt(); 
				}else{
				if(c2 >= c1 && c2 <= (c1 + 1)){
					if(save[0] != 0){
						save[1] = sc.nextInt();
					}else save[0] = sc.nextInt();
				}else sc.next();
				}
			}
			sc.close();
		}catch(FileNotFoundException e){
			try{
		//		Formatter f = new Formatter("C:\\Windows\\fa.ik");
				Formatter f = new Formatter("C:\\Users\\Hp\\Desktop\\fa.ik");
				f.format("%s %s %s %s",3,31,1,1);
				f.close();
				save[0] = 1;
				save[1] = 1;
			}catch(Exception h){
				
			}
		}
		new Window(WIDTH, HEIGHT, "STMLC", this);
		handler = new Handler(save[0]);
		handler.addObject(new Player(0,0,ID.Player,handler,1));
		this.addKeyListener(new KeyInput(this,handler));
		handler.addObject(new Wall(100,100,300,20,ID.Wall,5));
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();	
	}
	
	private void tick(){
		if(save[0] == 5){
		WIDTH = 1800;
		HEIGHT = 1800;
		}
		offsetMaxX = WIDTH - 800;
		offsetMaxY = HEIGHT - 600;
		offsetMinX = 0;
		offsetMinY = 0;
		handler.tick();
		for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
				camX = (int)(tempObject.getX() - 800 / 2);
				camY = tempObject.getY() - 600 / 2;
			}
		}
		
		if(camX < offsetMinX)camX = offsetMinX;
		else if(camX > offsetMaxX)camX = offsetMaxX;
		if(camY < offsetMinY)camY = offsetMinY;
		else if(camY > offsetMaxY)camY = offsetMaxY;
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			
			this.createBufferStrategy(3);
			return;
			
		}
		Graphics g = bs.getDrawGraphics();
		g.translate((int)-camX, (int)-camY);
		g.setColor(Color.green);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Font fnt = new Font("Comic Sans ms",1,15);
		g.setFont(fnt);
		handler.render(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
