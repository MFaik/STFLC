package Canavarlar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.regex.PatternSyntaxException;

import javax.imageio.ImageIO;

import Main.*;

public class Shoot extends Entity{
	private int timer = 0,velx,vely,Health = 50,shape = 1,ref = 0;
	private double deltaX,deltaY,direction;
	protected Handler handler;
	private Entity YOLO;
	private String Mtype,Stype;
	private Game game;
	private BufferedImage Sp1,Sp2;
	public Shoot(float x, float y, ID id,Handler handler,Game g,String Mtype,String Stype) {
		super(x, y, id);
		URL f1 = this.getClass().getClassLoader().getResource("Mushroom/1.png"); 
		URL f2 = this.getClass().getClassLoader().getResource("Mushroom/2.png");
			try{
				Sp1 = ImageIO.read(f1);   
				Sp2 = ImageIO.read(f2); 
			}catch(IOException e){
				System.out.println(e);
			}
		this.handler = handler;
		game = g;
	    this.Mtype = Mtype;
	    this.Stype = Stype;
		if(Stype == "X")timer = 50;
		if(Stype == "Np" || Stype == "P" || Stype == "3")timer = 100;
		if(Stype == "ref")handler.addObject(new Reflect(x,y,ID.Reflect,this,(int)Math.random()*2));
	}
	@Override
	public void tick() {
		if(YOLO == null)
			for(int i = 0;i < handler.object.size();i++){
				Entity tempObject = handler.object.get(i);
					if(tempObject.getId() == ID.Player){
						YOLO = tempObject;
					}
			}
		deltaX = (YOLO.getX()) - x;
		deltaY = (YOLO.getY()) - y;
		//-2 sað  / 2 sol / 1 yukarý / -1 aþaðý
		ref = 0;
		if(Math.abs(deltaX) < Math.abs(deltaY)){
			if(deltaY < 0)ref++;
			else ref--;
		}else{
			if(deltaX < 0)ref+= 2;
			else ref-= 2;
		}
		direction = Math.atan2(deltaY , deltaX);
		if(Mtype == "jump"){
			direction = Math.atan(deltaY / deltaX);
			if(deltaX > -1){
				if(velX < (int)((double)3*Math.cos(direction)))velX += (int)((double)3*Math.cos(direction));
				else velX = (int)((double)3*Math.cos(direction));
				if(velY < (int)((double)3*Math.sin(direction)))velY += (int)((double)3*Math.sin(direction));
				else velY = (int)((double)3*Math.sin(direction));
			}else{
				if(velX > -(int)((double)3*Math.cos(direction)))velX += -(int)((double)3*Math.cos(direction));
				else velX = -(int)((double)3*Math.cos(direction));
				if(velY > -(int)((double)3*Math.sin(direction)))velY += -(int)((double)3*Math.sin(direction));
				else velY = -(int)((double)3*Math.sin(direction));
			}
		}
		if(Mtype == "spinround"){
			if(velX < 0){
				if(velX < (int)((double)Math.cos(direction)))velX += (int)((double)2*Math.cos(direction));
				else velX = (int)((double)3*Math.cos(direction));
			}else {
				if(velX > (int)((double)Math.cos(direction)))velX += (int)((double)2*Math.cos(direction));
				else velX = (int)((double)3*Math.cos(direction));
			}
			if(velY < 0){
				if(velY < (int)((double)Math.sin(direction)))velY += (int)((double)2*Math.sin(direction));
				else velY = (int)((double)3*Math.sin(direction));
			}else {
				if(velY > (int)((double)Math.sin(direction)))velY += (int)((double)2*Math.sin(direction));
				else velY = (int)((double)3*Math.sin(direction));
			}
		}
		if(Mtype == "sekme"){
			if(velX < 0){
				if(velX < (int)((double)Math.cos(direction)))velX += (int)((double)Math.cos(direction));
				else velX = (int)((double)3*Math.cos(direction));
			}else {
				if(velX > (int)((double)Math.cos(direction)))velX += (int)((double)Math.cos(direction));
				else velX = (int)((double)3*Math.cos(direction));
			}
			if(velY < 0){
				if(velY < (int)((double)Math.sin(direction)))velY += (int)((double)Math.sin(direction));
				else velY = (int)((double)3*Math.sin(direction));
			}else {
				if(velY > (int)((double)Math.sin(direction)))velY += (int)((double)Math.sin(direction));
				else velY = (int)((double)3*Math.sin(direction));
			}
		}
		if(Mtype == "tp"){
			if(velX > 0){
				if(velX < (int)((double)3*Math.cos(direction)))velX += (int)((double)Math.cos(direction));
				else velX = (int)((double)3*Math.cos(direction));
			}else {
				if(velX > (int)((double)Math.cos(direction)))velX += (int)((double)Math.cos(direction));
				else velX = (int)((double)3*Math.cos(direction));
			}
			if(velY > 0){
				if(velY < (int)((double)Math.sin(direction)))velY += (int)((double)Math.sin(direction));
				else velY = (int)((double)3*Math.sin(direction));
			}else {
				if(velY > (int)((double)Math.sin(direction)))velY += (int)((double)Math.sin(direction));
				else velY = (int)((double)3*Math.sin(direction));
			}
		}
		collison();
		x += velX;
		y += velY;
		if(Mtype == "still"){
			if(velX > 0)velX--;else if(velX < 0)velX++;
			if(velY > 0)velY--;else if(velY < 0)velY++;
		}
		if(Stype == "ref"){
			
		}
		if(YOLO != null){
			if(timer > 0){
				timer--;
			}else{
				
				if(Stype == "Np" || Stype == "P" || Stype == "3"){
					if(Stype == "Np"){
						deltaX = (YOLO.getX() + YOLO.getVelX() * 30) - x;
						deltaY = (YOLO.getY() + YOLO.getVelY() * 30) - y;
					}
					if(Stype == "P" || Stype == "3"){
						deltaX = (YOLO.getX()) - x;
						deltaY = (YOLO.getY()) - y;
					}
					direction = Math.atan(deltaY / deltaX);
					if(deltaX > -1){
						velx = (int)((double)5*Math.cos(direction));
						vely = (int)((double)5*Math.sin(direction));
					}else{
						velx = -(int)((double)5*Math.cos(direction));
						vely = -(int)((double)5*Math.sin(direction));
					}
					handler.addObject(new bullet(x,y, ID.EBullet, velx,vely,10, 5, 1,"", handler,Color.red));
					if(Stype == "3"){
						handler.addObject(new bullet(x,y, ID.EBullet, velx*3,vely*3,10, 5, 1,"", handler,Color.red));
						handler.addObject(new bullet(x,y, ID.EBullet, velx*2,vely*2,10, 5, 1,"", handler,Color.red));
					}
					timer = 100;
				}
				if(Stype == "X"){
					System.out.println(Stype);
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
						timer = 50;
						shape *= -1;
				}
			}
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
		}
		if(Health < 1)handler.removeObject(this);
	}
	@Override
	public void render(Graphics g) {
		if(Stype == "X"){
			g.setColor(Color.CYAN);
			if(timer < 15)
			g.drawImage(Sp2, (int)x, (int)y, 40, 40,null);
			else g.drawImage(Sp1, (int)x, (int)y, 40, 40,null);
		}else{
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, 40, 40);
		}
		
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,40,40);
	}
	public void collison(){
		for(int i = ((int)x/80)-2;i < ((int)x/80)+2;i++){
			for(int s = ((int)y/80)-2;s < ((int)y/80)+2;s++){
				if(game.tile.GetTile(new Point(i,s)) == 0){
					if(nextBoundsX().intersects(new Rectangle(i*80,s*80,80,80).getBounds())){
						velX = 0;
					}
					if(nextBoundsY().intersects(new Rectangle(i*80,s*80,80,80).getBounds())){
						velY = 0;
					}
				}
			}
		}
		if(handler != null){
			for(int i = 0;i < handler.object.size();i++){
				Entity tempObject = handler.object.get(i);

				if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Enemy){
					if(this != tempObject && Mtype != "spinround"){
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
	}
	public Rectangle nextBoundsX(){
		return new Rectangle((int) ((int)x+velX),(int)y, 40, 40);
	}
	public Rectangle nextBoundsY(){
		return new Rectangle((int)x,(int)(y+velY), 40, 40);
	}
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
			if(deltaX > -1){
				velX = -(int)(((double)Integer.valueOf(k[2])*2)*Math.cos(direction));
				velY = -(int)(((double)Integer.valueOf(k[2])*2)*Math.sin(direction));
			}else{
				velX = (int)(((double)Integer.valueOf(k[2])*2)*Math.cos(direction));
				velY = (int)(((double)Integer.valueOf(k[2])*2)*Math.sin(direction));
			}
			return 0;
		}
		if(a == "bounce") {
			String[] k = {"0","0"};
			try {
			    k = b.split("\\s+");
			} catch (PatternSyntaxException ex) {
			    // 
			}
			velX += Integer.valueOf(k[0]);
			velY += Integer.valueOf(k[1]);
		}
		if(a == "ref" && Stype == "ref"){
			return ref;
		}
		return 0;
	}
}
