import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Entity{
	public float lastX,lastY;
	public boolean r,l,u,d;
	Handler handler;
	public Player(float x, float y, ID id,Handler handler,int level) {
		super(x, y, id, level);
		this.handler = handler;
		this.level = 5;
	}
	public void tick() {
		lastX = x;
		lastY = y;
		collusion();
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y, 40, 40);
		g.setColor(Color.yellow);
	}
	
	private void collusion(){
		for(int i = 0;i < handler.object.size();i++){
			
			Entity tempObject = handler.object.get(i);
			if(tempObject.level == level){
				if(tempObject.getId() == ID.Enemy){
					if(getBounds().intersects(tempObject.getBounds())){
							
					}	
				}else if(tempObject.getId() == ID.Wall){
					if(nextBoundsX().intersects(tempObject.getBounds())){
						velX = 0;
					}
					if(nextBoundsY().intersects(tempObject.getBounds())){
						velY = 0;
					}
				}
			}
		}
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 40, 40);
	}
	public Rectangle nextBoundsX(){
		return new Rectangle((int) ((int)x+velX),(int)y,40,40);
	}
	public Rectangle nextBoundsY(){
		return new Rectangle((int)x,(int)(y+velY),40,40);
	}
	public void Do(String a, String b) {
		if(a == "way"){
			if(b == "r")r = true;
		}
	}
}
