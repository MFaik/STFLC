package Main;

import java.awt.Color;

public class Silah {
	//bullet range ne kadar mesafe sonra mermi yok olacak(40px/birim)
	//bullet speed mermi hýzý(px/tick)
	//bullet lazer oldugunda bullet speed lazerin durationu olacak
	//maxBullet cepde toplamda kaç mermi olabilir
	//maxBulletIH elimizde kaç mermi olabilir
	//power ne kadar vurduðu
	//bulletIH elimizde kaç mermi var
	//bulletIP cepte kaç mermi var
	//reloadS iki merminin ateþ edilmesi arasýndaki süre(tick)
	//reloadG cepten silaha mermi koyarkenki süre(tick)
	//bulletSize 5px/birim
	public String name,type;
	public int bulletSize,bulletRange,bulletSpeed,maxBulletIP,maxBulletIH,power,bulletIH,bulletIP,reloadS,reloadG,velX,velY;
	public float uReloadS,uReloadG;
	private double deltaX,deltaY,direction;
	public boolean reloading;
	private Handler handler;
	public Silah(String name,int bR,int bSpeed,int bSize,int mBIP,int mBIH,int pow,int rS,int rG,String type,Handler handler){
		this.bulletRange = bR;
		this.bulletSpeed = bSpeed;
		this.bulletSize = bSize;
		this.maxBulletIP = mBIP;
		this.maxBulletIH = mBIH;
		this.power = pow;
		this.reloadS = rS;
		this.reloadG = rG;
		this.bulletIH = mBIH;
		this.bulletIP = mBIP;
		this.name = name;
		this.type = type;
		this.handler = handler;
	}
	public void shoot(int Tx,int Ty,int x,int y,int xSize,int ySize,Game game){
		deltaX = Tx - (game.WIDTH/2)-20;
		deltaY = Ty - (game.HEIGHT/2)-20;
		direction = Math.atan(deltaY / deltaX);
		if(deltaX > -1){
			velX = (int)((double)Math.cos(direction)*bulletSpeed);
			velY = (int)((double)Math.sin(direction)*bulletSpeed);
		}else{
			velX = -(int)((double)Math.cos(direction)*bulletSpeed);
			velY = -(int)((double)Math.sin(direction)*bulletSpeed);
		}
		if(type == "normal" || type == "infinite"){
			handler.addObject(new bullet((int)x+(int)(xSize / 2-(bulletSize/2)*5),(int)y+(int)(ySize / 2 -(bulletSize/2)*5),ID.Bullet,velX,velY,bulletRange,bulletSize,power,"",handler,Color.BLUE));
		}
		if(type == "shotgun"){
			handler.addObject(new bullet((int)x+(int)(xSize / 2-(bulletSize/2)*5),(int)y+(int)(ySize / 2 -(bulletSize/2)*5),ID.Bullet,velX,velY,bulletRange,bulletSize,power,"",handler,Color.BLUE));
			if(deltaX < -1){
				velX = (int)((double)Math.cos(direction-20*180/Math.PI)*bulletSpeed);
				velY = (int)((double)Math.sin(direction-20*180/Math.PI)*bulletSpeed);
			}else{
				velX = -(int)((double)Math.cos(direction-20*180/Math.PI)*bulletSpeed);
				velY = -(int)((double)Math.sin(direction-20*180/Math.PI)*bulletSpeed);
			}
			handler.addObject(new bullet((int)x+(int)(xSize / 2-(bulletSize/2)*5),(int)y+(int)(ySize / 2 -(bulletSize/2)*5),ID.Bullet,velX,velY,bulletRange,bulletSize,power,"",handler,Color.BLUE));
			if(deltaX < -1){
				velX = (int)((double)Math.cos(direction+20*180/Math.PI)*bulletSpeed);
				velY = (int)((double)Math.sin(direction+20*180/Math.PI)*bulletSpeed);
			}else{
				velX = -(int)((double)Math.cos(direction+20*180/Math.PI)*bulletSpeed);
				velY = -(int)((double)Math.sin(direction+20*180/Math.PI)*bulletSpeed);
			}
			handler.addObject(new bullet((int)x+(int)(xSize / 2-(bulletSize/2)*5),(int)y+(int)(ySize / 2 -(bulletSize/2)*5),ID.Bullet,velX,velY,bulletRange,bulletSize,power,"",handler,Color.BLUE));
		}
		if(type == "boom"){
			handler.addObject(new bullet((int)x+(int)(xSize / 2-(bulletSize/2)*5),(int)y+(int)(ySize / 2 -(bulletSize/2)*5),ID.Bullet,velX,velY,bulletRange,bulletSize,power,"boom",handler,Color.BLUE));
		}
	}
}
