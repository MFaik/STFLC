package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Fl_Num extends Entity {

	private Handler handler;
	private float alpha = 1;
	private float life;
	private String number;
	private Color color;
	//life = 0.01 ile 0.1 arasi
	
	public Fl_Num(float x, float y, ID id,Color color,String number,int width,int height,float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.number = number;
		this.x = (int)(Math.random()*width)+x;
		this.y = (int)(Math.random()*height)+y;
		this.life = life;
		this.color = color;
	}

	
	public void tick() {
		if(alpha > life){
			alpha -= (life - 0.001f); 
		} else handler.removeObject(this);
	}

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g2d.setColor(color);
		g2d.drawString(number+"",x, y);
		g2d.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	
	public Rectangle getBounds() {
		return null;
	}


	@Override
	public int Do(String a, String b) {
		// TODO Auto-generated method stub
		return 0;
	}

}
