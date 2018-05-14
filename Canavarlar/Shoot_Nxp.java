package Canavarlar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.regex.PatternSyntaxException;

import Main.Entity;
import Main.Handler;
import Main.ID;
import Main.bullet;
public class Shoot_Nxp extends Entity {
	private int timer = 100,velx,vely,Health = 50;
	private double deltaX,deltaY,direction;
	private Handler handler;
	private Entity YOLO;
	public Shoot_Nxp(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		if(velX < 0 && velX > 1)velX -= velX/2;else if(velX < 0)velX = 0;
		if(velY < 0 && velY > 1)velY -= velY/2;else if(velY < 0)velY = 0;
		System.out.println(velX +" "+velY);
		if(YOLO == null)
		for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player){
					YOLO = tempObject;
				}
		}
		if(YOLO != null){
		if(timer > 0){
			timer--;
		}else{
			
			deltaX = (YOLO.getX() + YOLO.getVelX() * 30) - x;
			deltaY = (YOLO.getY() + YOLO.getVelY() * 30) - y;
			direction = Math.atan(deltaY / deltaX);
			if(deltaX > -1){
				velx = (int)((double)5*Math.cos(direction));
				vely = (int)((double)5*Math.sin(direction));
			}else{
				velx = -(int)((double)5*Math.cos(direction));
				vely = -(int)((double)5*Math.sin(direction));
			}
			handler.addObject(new bullet(x,y, ID.EBullet, velx,vely,10, 5, 1,"", handler,Color.red));
			timer = 100;
		}
		/*for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
			if((tempObject.getId() == ID.Bullet) || tempObject.getId() == ID.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					Health -= tempObject.Do("power", null);
					if(tempObject.Do("inv","") != 1){
						if(tempObject.getId() == ID.Bullet)handler.removeObject(tempObject);
					}
				}
			}
		}*/
	}
	if(Health < 1)handler.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 40, 40);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,40,40);
	}

	@Override
	public int Do(String a, String b) {
		if(a == "hit") {
			String[] k = {"0","0","0"};
			try {
			    k = b.split("\\s+");
			} catch (PatternSyntaxException ex) {
			    // 
			}
			Health -= Integer.valueOf(k[2]);
			deltaX = (Integer.valueOf(k[0])) - x;
			deltaY = (Integer.valueOf(k[1])) - y;
			direction = Math.atan(deltaY / deltaX);
			if(Integer.valueOf(k[0]) < -1){
				velX = (int)((double)-5*Math.cos(direction));
				velY = (int)((double)-5*Math.sin(direction));
			}else{
				velX = -(int)((double)-5*Math.cos(direction));
				velY = -(int)((double)-5*Math.sin(direction));
			}
			return 0;
		}
		else return 0;
	}
	public void getHit(int pow,Point p) {
		
	}
}
