import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Entity{
	double width,height;
	public Wall(float x, float y,double width,double height, ID id, int level) {
		super(x, y, id, level);
		this.height = height;
		this.width = width;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x,(int)y,(int)width,(int)height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}

	public void Do(String a, String b) {
		
	}

}
