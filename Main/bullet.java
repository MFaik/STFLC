package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import Map_Generating.*;

public class bullet extends Entity{
	public int size,range,power,fRange,refd = 1;
	public Handler handler;
	public boolean inv,refed;
	public String type;
	private Player player;
	private Color c;
	private float fx,fy;
	private LinkedList<Entity> a = new LinkedList<Entity>();
	public bullet(float x, float y, ID id,int speedx,int speedy,int range,int size,int power
			,String type,Handler handler,Color c) {
		super(x, y, id);
		fx = x;
		fy = y;
		this.handler = handler;
		this.size = size;
		this.range = range*40;
		this.fRange = range*40;
		this.velY = speedy;
		this.velX = speedx;
		this.power = power;
		this.type = type;
		if(type == "boom"){
			inv = true;
		}
		this.c = c;
	}

	public void tick() {
		x += velX;
		y += velY;
		if((velX == 0 || velY == 0)|| id == ID.Bullet){
			if(velX > 0)range -= velX;
			if(velX < 0)range += velX;
			if(velY > 0)range -= velY;
			if(velY < 0)range += velY;
		}else{
			if(velX > 0)range -= velX/2;
			if(velX < 0)range += velX/2;
			if(velY > 0)range -= velY/2;
			if(velY < 0)range += velY/2;
		}
		collusion();
		if(range < 1){
			if(type == "boom"){
				range = fRange;
				velX *= -1;
				velY *= -1;
				type = "a";
				refd = 1;
				fx = x;
				fy = y;
				a = new LinkedList<Entity>();
				refed = false;
			}else handler.removeObject(this);
		} 
	}
	private void collusion(){
		for(int i = ((int)x/80)-2;i < ((int)x/80)+2;i++){
			for(int s = ((int)y/80)-2;s < ((int)y/80)+2;s++){
				if(handler.game.tile.GetTile(new Point(i,s)) == 0){
					if(getBounds().intersects(new Rectangle(i*80,s*80,80,80)))range = 0;
				}
			}
		}
		for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
			if(type == "a"){
				if(tempObject.id == ID.Player){
					if(getBounds().intersects(tempObject.getBounds())){
						player = (Player) tempObject;
						if(player != null){
							for(int a = 0;a < player.silah.size();a++){
								if(player.silah.get(a).type == "boom"){
									if(player.silah.get(a).power == power && /*player.silah.get(i).bulletRange == fRange
											&& */player.silah.get(a).bulletSize == size){
										handler.removeObject(this);
										player.silah.get(a).bulletIH = 1;
									}
								}
							}
						}
					}
				}
			}
			if(tempObject.id == ID.Player && c != Color.BLUE) {
				if(getBounds().intersects(tempObject.getBounds())){
					tempObject.Do("hit", ""+power);
					if(!inv)handler.removeObject(this);
				}
			}
			if(tempObject.id == ID.Enemy && c == Color.BLUE) {
				if(getBounds().intersects(tempObject.getBounds())){
					if((type != "boom" && type != "a") || ((type == "boom" || type == "a") && !a.contains(tempObject)))
						tempObject.Do("hit", (int)fx+" "+(int)fy+" "+power);
					if(type == "boom" || type == "a")a.add(tempObject);
					if(!inv)handler.removeObject(this);
				}
			}
			if(tempObject.id == ID.Reflect && !refed){
				if(getBounds().intersects(tempObject.getBounds())){
					if(tempObject.Do("way", "") == 1){
						float v = velX;
						velX = velY;
						velY = v;
						refed = true;
					}else {
						float v = velX;
						velX = -velY;
						velY = -v;
						refed = true;
					}
					tempObject.Do("hit", (int)fx+" "+(int)fy+" "+power);
				}
			}
		}
	}
	public void render(Graphics g) {
			g.setColor(c);
			g.fillArc((int)(x),(int)(y),(int)(size*5),(int)(size*5), 0, 360);
			g.setColor(Color.black);
			g.drawArc((int)(x),(int)(y),(int)(size*5),(int)(size*5), 0, 360);
		
		//g.drawRect((int)x,(int)y,5*size,5*size);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,5*size,5*size);
	}

	@Override
	public int Do(String a, String b) {
		if(a == "power"){
			return power;
		}else if(a == "inv"){
			if(inv)return 1;
		}else if(a == "refd"){
			if(b == "?"){
				return refd;
			}
			if(b == "!"){
				refd = 0;
			}
		}
		return 0;
	}

}
