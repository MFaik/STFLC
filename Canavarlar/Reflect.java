package Canavarlar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.regex.PatternSyntaxException;

import javax.imageio.ImageIO;

import Main.Entity;
import Main.ID;

public class Reflect extends Entity{
	private Shoot s;
	private BufferedImage Sp;
	private URL f = this.getClass().getClassLoader().getResource("Reflect/1.png");
	private int a;
	private int Health = 10;
	public Reflect(float x, float y, ID id,Shoot s,int Way) {
		super(x, y, id);
		try{
			Sp = ImageIO.read(f);   
		}catch(IOException e){
			System.out.println(e);
		}
		this.s = s;
	}

	@Override
	public void tick() {
		a = s.Do("ref", "");
		x = s.getX();
		y = s.getY();
		if(a == 2){
			x = s.getX()-20;
		}else if(a == -2){
			x = s.getX()+40;
		}else if(a == 1){
			y = s.getY()-20;
		}else{
			y = s.getY()+40;
		}
		if(Health < 1)s.handler.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		if(Math.abs(a) == 1)g.drawImage(Sp,(int)x,(int)y,40,20,null);
		else g.drawImage(Sp,(int)x,(int)y,20,40,null);
	}

	@Override
	public Rectangle getBounds() {
		if(Math.abs(a) == 1){
			return new Rectangle((int)x,(int)y,15,40);
		}else return new Rectangle((int)x,(int)y,40,15);
	}

	@Override
	public int Do(String a, String b) {
		if(a == "hit"){
			String[] k = {"0","0","0"};
			try {
			    k = b.split("\\s+");
			} catch (PatternSyntaxException ex) {
			    // 
			}
			Health -= Integer.valueOf(k[2]);
		}
		return 0;
	}
}
