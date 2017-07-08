import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	public int level;
	
	public Entity(float x, float y, ID id,int level){
		
		this.x = x;
		this.y = y;
		this.id = id;
		this.level = level;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract void Do(String a,String b);
	
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setId(ID id){
		this.id = id;
	}
	public ID getId(){
		return id;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
}

