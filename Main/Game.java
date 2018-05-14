package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import Canavarlar.*;
//import Map.Rooms;
import Map_Generating.*;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1550691097823471818L;

	public float WIDTH = 800,HEIGHT = 600;
	//	public int offsetMaxX,offsetMaxY,offsetMinX,offsetMinY;
	public float camX,camY;
	public Thread thread;
	private Window wind;
	private boolean running = false;
	public Handler handler;
	public boolean paused = false,inBattle = false,mP,fightStr = false;
	public int c1,c2 = 0,c3,s = 0;
	double ns;
	public Tiles tile;
	public Game(){

		wind = new Window((int)WIDTH, (int)HEIGHT, this);
		wind.addLocation(0, 0);
		handler = new Handler(this);
		tile = new Tiles();
		new Rooms(handler,tile);
		//handler.addObject(new Player(100,100,ID.Player,handler,this));
		/*map = new Map(1,handler);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Chosen = map.Rooms.get(0);
		handler.addObject(new Player((float)Chosen.getBounds().getCenterX(),(float)Chosen.getBounds().getCenterY()
				, ID.Player, handler, handler.game));
				*/
//		handler.addObject(new Player(-100,-100,ID.Player,handler,this));
		setFocusTraversalKeysEnabled(false);
		this.addKeyListener(new KeyInput(this,handler));
		this.addMouseListener(new mouseInput(this));
		//handler.addObject(new Shoot_Nxp(600,600,ID.Enemy,handler));
		/*handler.addObject(new Wall(-5,0,260,10,ID.Wall));
		handler.addObject(new Wall(345,0,260,10,ID.Wall));
		handler.addObject(new Wall(-5,0,10,400,ID.Wall));
		handler.addObject(new Wall(-5,400,250,10,ID.Wall));
		handler.addObject(new Wall(345,400,260,10,ID.Wall));
		handler.addObject(new Wall(595,0,10,400,ID.Wall));

		handler.addObject(new Wall(-5,-400,600,10,ID.Wall));
		handler.addObject(new Wall(-5,-400,10,400,ID.Wall));
		handler.addObject(new Wall(-5,0,250,10,ID.Wall));
		handler.addObject(new Wall(345,0,260,10,ID.Wall));
		handler.addObject(new Wall(595,-400,10,400,ID.Wall));

		handler.addObject(new Shoot_X(150,105,ID.Enemy,handler));
		handler.addObject(new Shoot_X(400,105,ID.Enemy,handler));
		handler.addObject(new Shoot_X(150,305,ID.Enemy,handler));
		handler.addObject(new Shoot_X(400,305,ID.Enemy,handler));



		handler.addObject(new item((float)300,(float)-200,ID.item,handler,new Silah("Hand Canon",7,50,10,20,5,15,80,150,"normal",handler),""));

		handler.addObject(new item((float)300,(float)-200,ID.item,handler,null,"bullet"));

		handler.addObject(new Shoot_P(600,600,ID.Enemy,handler));
		
		handler.addObject(new Reflect_Enemy(600,600,ID.Enemy,handler,"down"));
		*/
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
		ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			try{
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
					//System.out.println("FPS: " + frames);
					frames = 0;
					camX += frames;	
				}
			}catch(Exception e){
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
					//System.out.println("FPS: " + frames);
					frames = 0;
					camX += frames;	
				}
			}
		}
		stop();	

	}

	private void tick(){
		HEIGHT = (float) wind.frame.getSize().getHeight();
		WIDTH = (float) wind.frame.getSize().getWidth();
		/*offsetMaxX = WIDTH - 800;
		offsetMaxY = HEIGHT - 600;
		offsetMinX = 0;
		offsetMinY = 0;*/
		handler.tick();
		for(int i = 0;i < handler.object.size();i++){
			if(handler.object.get(i) != null){
				Entity tempObject = handler.object.get(i);
				if(tempObject.id == ID.Player){
					camX = (int)(tempObject.getX() /*-4000 */- (WIDTH - /*tempObject.Do("boyut", "x")*/40) / 2);
					camY = (int)(tempObject.getY() /*-4000 */- (HEIGHT - /*tempObject.Do("boyut","y")*/40) / 2);
				}
			}
		}
		/*if(camX < offsetMinX)camX = offsetMinX;
		else if(camX > offsetMaxX)camX = offsetMaxX;
		if(camY < offsetMinY)camY = offsetMinY;
		else if(camY > offsetMaxY)camY = offsetMaxY;*/
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){

			this.createBufferStrategy(2);
			return;

		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
//		g.scale(0.1,0.1);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 10000, 10000);
		g.translate((int)-camX, (int)-camY);
		Font fnt = new Font("Comic Sans ms",1,15);
		g.setFont(fnt);
		drawTiles(g);
		handler.render(g);
		g.dispose();
		bs.show();
	}
	public void drawTiles(Graphics2D g){
			for(int a = 0;a < handler.object.size();a++){
				if(handler.object.get(a) != null){
					Entity tempObject = handler.object.get(a);
					if(tempObject.id == ID.Player){
						for(int i = ((int)tempObject.getX()/80)-13;i < ((int)tempObject.getX()/80)+13;i++){
							for(int s = ((int)tempObject.getY()/80)-10;s < ((int)tempObject.getY()/80)+10;s++){
								if(tile.GetTile(new Point(i,s)) == 0){
									g.setColor(Color.blue);
									g.fillRect(i*80, s*80, 80,80);
								}else if(tile.GetTile(new Point(i,s)) == 1){
									g.setColor(Color.white);
									g.fillRect(i*80, s*80, 80,80);
								}else if(tile.GetTile(new Point(i,s)) == 2){
									g.setColor(Color.orange);
									g.fillRect(i*80, s*80, 80,80);
								}else if(tile.GetTile(new Point(i,s)) == 3){
									g.setColor(Color.gray);
									g.fillRect(i*80, s*80, 80,80);
								}
							}
						}
					}
				}
			}
			
		}
	public static void main(String[] args) {
		new Game();
	}
}
