package Canavarlar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import Main.Entity;
import Main.Handler;
import Main.ID;
import Main.bullet;

public class Shoot_X extends Entity{
	public float timer = 50,shape = 1,Health = 50;
	public Handler handler;
	private BufferedImage Sp1,Sp2;
	public Shoot_X(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		URL f1 = this.getClass().getClassLoader().getResource("Mushroom/1.png"); 
		URL f2 = this.getClass().getClassLoader().getResource("Mushroom/2.png");
		this.handler = handler;
			try{
				Sp1 = ImageIO.read(f1);   
				Sp2 = ImageIO.read(f2); 
			}catch(IOException e){
				System.out.println(e);
			}
	}

	public void tick() {
		if(timer == 0){
			if(shape > 0){
			handler.addObject(new bullet(x,y,ID.EBullet,5,0,5,5,1,"",handler,Color.gray));
			handler.addObject(new bullet(x,y,ID.EBullet,-5,0,5,5,1,"",handler,Color.gray));
			handler.addObject(new bullet(x,y,ID.EBullet,0,5,5,5,1,"",handler,Color.gray));
			handler.addObject(new bullet(x,y,ID.EBullet,0,-5,5,5,1,"",handler,Color.gray));
			}else {
				handler.addObject(new bullet(x,y,ID.EBullet,5,5,3,5,1,"",handler,Color.gray));
				handler.addObject(new bullet(x,y,ID.EBullet,5,-5,3,5,1,"",handler,Color.gray));
				handler.addObject(new bullet(x,y,ID.EBullet,-5,5,3,5,1,"",handler,Color.gray));
				handler.addObject(new bullet(x,y,ID.EBullet,-5,-5,3,5,1,"",handler,Color.gray));
			}
			timer = 100;
			shape *= -1;
		}else timer--;
			for(int i = 0;i < handler.object.size();i++){
				Entity tempObject = handler.object.get(i);
				if((tempObject.getId() == ID.Bullet) || tempObject.getId() == ID.Player){
					if(getBounds().intersects(tempObject.getBounds())){
						Health -= tempObject.Do("power", null);
						if(tempObject.Do("inv","") != 1){
							if(tempObject.getId() == ID.Bullet)handler.removeObject(tempObject);
						}
					}
				}
			}
		if(Health < 1)handler.removeObject(this);
	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		if(timer < 15)
		g.drawImage(Sp2, (int)x, (int)y, 40, 40,null);
		else g.drawImage(Sp1, (int)x, (int)y, 40, 40,null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,40,40);
	}

	@Override
	public int Do(String a, String b) {
		if(a == "xp")return 2;
		else return 0;
	}
}
