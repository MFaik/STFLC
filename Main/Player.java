package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import Map.*;

public class Player extends Entity{
	public float lastX,lastY;
	//Td = somebody toucha my door
	public boolean r,l,u,d,reloadable,shooted,invBool,SM;
	Point TD;
	public int xSize = 40,ySize = 40
			,bullet,ssilah
			,Health = 3,MaxHealth = 3,Shield = 3,MaxShield = 3
			,invTimer,lv = 1,gunT = 5;
	public double xp = 0,Nxp;
	Handler handler;
	public Silah esilah;
	public bullet Bullet;
	public LinkedList<Silah> silah = new LinkedList<Silah>();
	private Game game;
	private Rooms roms;
	public Player(float x, float y, ID id,Handler handler,Game game,Rooms r) {
		super(x, y, id);
		silah.add(new Silah("Infinite",15,10,5,0,10,3,10,100,"infinite",handler));
		silah.add(new Silah("Boomerang",15,6,5,0,1,3,15,100,"boom",handler));
		silah.add(new Silah("Shotgun",15,8,5,30,10,5,30,100,"shotgun",handler));
		silah.add(new Silah("Machine Gun",15,8,5,200,50,2,5,200,"normal",handler));
		silah.add(new Silah("Mini Gun",15,8,2,400,50,2,5,200,"normal",handler));
		esilah = silah.get(ssilah);
		this.handler = handler;
		this.game = game;
		roms = r;
	}
	public void tick() {
		gunT--;
		if(game.ns == 1000000000 / 20.0)SM = true;
		else SM = false;
		if(Health < MaxHealth)Shield = 0;
		if(Shield > MaxShield)Shield = MaxShield;
		if(Health > MaxHealth)Health = MaxHealth;
		if(Health < 1)Health = 1;
		xSize = 25 + Health*5;
		ySize = 25 + Health*5;
		esilah = silah.get(ssilah);
		lastX = x;
		lastY = y;
		Nxp = (int) Math.pow(2, lv);
		if(xp >= Nxp)lv++;
		if(SM){
			gunC();
		}
		if(!SM){
			shoot();
			move();
		}
		collusion();
		if(!SM){
			x += velX;
			y += velY;
		}
		invinsible();
		if(Health < 1){
			//game.ScreenShake();
			//Health = 999999999;
		}
	}
	private void gunC(){
		if(gunT < 1){
			if(!r && l){
				ssilah--;
				gunT = 5;
			}
			if(r && !l){
				ssilah++;
				gunT = 5;
			}
		}
		if(ssilah-1 < -1)ssilah = silah.size()-1;
		if(ssilah+1 > silah.size())ssilah = 0;
	}
	private void shoot(){
		esilah = silah.get(ssilah);
		//ateş etme
		if(game.mP && esilah.uReloadS < 1 && esilah.bulletIH > 0 && !shooted && !esilah.reloading){
			esilah.uReloadS = esilah.reloadS;
			esilah.bulletIH--;
			shooted = true;
			bullet++;
			esilah.shoot((int)game.getMousePosition().getX(),(int)game.getMousePosition().getY(), (int)x, (int)y,xSize,ySize,game);
		}else if(game.mP && esilah.bulletIH == 0 && reloadable && !esilah.reloading && !shooted){
			esilah.uReloadG = esilah.reloadG;
			shooted = true;
		}
		if(!game.mP){
			reloadable = true;
		}else reloadable = false;
		//reloading bullets
		for(int i = 0;i < silah.size();i++){
			Silah ali = silah.get(i);
			//reload süresi azaltma
			if(ali.uReloadG < 1){
				ali.uReloadS--;
				ali.reloading = false;
			}
			//reload etme süresi azaltımı
			else if((ali.bulletIP > 0 || ali.bulletIH > 0) && ali.bulletIH < ali.maxBulletIH && esilah.type != "infinite"){
				ali.uReloadG--;
				ali.reloading = true;
				if(ali.uReloadG < 1){
					if(ali.bulletIH > 0 && ali.bulletIH != ali.maxBulletIH){
						ali.bulletIP += ali.bulletIH;
						ali.bulletIH = 0;
						if(ali.bulletIP >= ali.maxBulletIH){
							ali.bulletIP -= ali.maxBulletIH;
							ali.bulletIH = ali.maxBulletIH;
						}else{
							ali.bulletIH += ali.bulletIP;
							ali.bulletIP = 0;
						}
					}else if(ali.bulletIP >= ali.maxBulletIH){
						ali.bulletIP -= ali.maxBulletIH;
						ali.bulletIH = ali.maxBulletIH;
					}else{
						ali.bulletIH += ali.bulletIP;
						ali.bulletIP = 0;
					}
				}
			}else if(esilah.type == "infinite"  && ali.bulletIH < ali.maxBulletIH){
				ali.uReloadG--;
				ali.reloading = true;
				if(ali.uReloadG < 1){
					ali.bulletIH = ali.maxBulletIH;
				}
			}else if(!(ali.bulletIH < ali.maxBulletIH)){
				ali.uReloadG = 0;
			}
		}
		shooted = false;
	}
	private void move(){
		if(!l && !r)velX = 0;
		if(!u && !d)velY = 0;
		if(l)velX = -5;
		if(r)velX = 5;
		if(u)velY = -5;
		if(d)velY = 5;
	}
	public void render(Graphics g) {
		//Player
		g.setColor(Color.red);
		//if(invTimer % 5 == 0)g.setColor(new Color(255,0,0,20));
		if(invBool)g.setColor(new Color(255,0,0,20));
		g.fillRect((int)x,(int)y, xSize, ySize);
		g.setColor(Color.yellow);
		//reloadlama
		if(esilah.reloading){ 
			g.drawRect((int)x - (10 + ((MaxHealth-Health)*2)),(int) y - 12, 60, 10);
			g.fillRect((int)x - (10 + ((MaxHealth-Health)*2)), (int) y - 12,60 - ((int)((esilah.uReloadG/esilah.reloadG)*60)), 10);
		}
		g.setColor(Color.BLACK);
		//silah
		if(SM){
			g.drawRect((int) ((int)x-25), (int) ((int)y-15), 90, 70);
			g.drawString(esilah.name,(int) ((int)x-20), (int) ((int)y-40));
			if(esilah.type != "infinite"){
			if(esilah.maxBulletIP != 0)g.drawString(esilah.bulletIP+"/"+esilah.maxBulletIP,(int)x+6,(int)y-25);
			}else{
				g.drawString("O",(int) ((int)x+40),(int) ((int)y-25));
				g.drawString("O",(int) ((int)x+30),(int) ((int)y-25));
			}
		}else{
			g.drawRect((int) ((int)x+game.WIDTH/2-120), (int) ((int)y+game.HEIGHT/2-115), 90, 70);
			g.drawString(esilah.name,(int) ((int)x+game.WIDTH/2-120), (int) ((int)y+game.HEIGHT/2-30));
			if(esilah.type != "infinite"){
				if(esilah.maxBulletIP != 0)g.drawString(esilah.bulletIP+"/"+esilah.maxBulletIP,(int)x+ (int)(game.WIDTH/2-90),(int)y+ (int)(game.HEIGHT/2-120));
			}else{
				g.drawString("O",(int) ((int)x+ game.WIDTH/2-50),(int) ((int)y+ game.HEIGHT/2-120));
				g.drawString("O",(int) ((int)x+ game.WIDTH/2-40),(int) ((int)y+ game.HEIGHT/2-120));
			}
			for(int i = 0;i < esilah.maxBulletIH;i++){
				g.setColor(Color.BLACK);
				g.drawRect((int)x+ (int)(game.WIDTH/2)-20, (int)y + (int)(game.HEIGHT/2-50)-(i*10), 15, 6);
			}
			for(int i = 0;i < esilah.bulletIH;i++){
				g.setColor(Color.orange);
				g.fillRect((int)x+ (int)(game.WIDTH/2)-19, (int)y + (int)((game.HEIGHT/2)-49)-(i*10), 14, 5);
			}
		}
		//can
		for(int i = 0; i < Health;i++){
			g.setColor(Color.red);
			g.fillRect((int)x-((int)(game.WIDTH/2)-40) + (i * 40),(int)y - ((int)(game.HEIGHT/2)-60), 20, 20);
		}
		for(int i = 0; i < MaxHealth;i++){
			g.setColor(Color.red);
			g.drawRect((int)x-((int)(game.WIDTH/2)-40) + (i * 40),(int)y - ((int)(game.HEIGHT/2)-60), 20, 20);
		}
		//kalkan
		for(int i = 0; i < Shield;i++){
			g.setColor(Color.white);
			g.fillRect((int)x-((int)(game.WIDTH/2)-40) + (i * 40),(int)y - ((int)(game.HEIGHT/2)-90), 20, 20);
		}
		for(int i = 0; i < MaxShield;i++){
			g.setColor(Color.white);
			g.drawRect((int)x-((int)(game.WIDTH/2)-40) + (i * 40),(int)y - ((int)(game.HEIGHT/2)-90), 20, 20);
		}
		//xp
		g.setColor(Color.YELLOW);
		g.fillRect((int)x - ((int)(game.WIDTH/2)-40),(int)y + (int)(game.HEIGHT/2)-80, (int)((xp*180)/Nxp), 20);
		g.setColor(Color.black);
		g.drawRect((int)x - ((int)(game.WIDTH/2)-40),(int)y + ((int)(game.HEIGHT/2)-80),180,20);
		g.drawString("Level " + lv, (int)x - ((int)(game.WIDTH/2)-100), (int)y + (int)(game.HEIGHT/2)-65);
		//map
	}
	private void collusion(){
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
				if(game.tile.GetTile(new Point(i,s)) == 2){
					if(TD == null) {
						if(getBounds().intersects(new Rectangle(i*80,s*80,80,80).getBounds())){
							TD = new Point(i,s);
						}
					}else if(!getBounds().intersects(new Rectangle((int)(TD.getX()*80),(int)(TD.getY()*80),80,80).getBounds())){
						for(int i1 = 0;i1 < roms.rA.size();i1++) {
							if(roms.rA.get(i1).getRealBounds().intersects(getBounds())) {
								roms.rA.get(i1).Str();
								TD = null;
							}
						}
						/*for(int i1 = 0;i1 < roms.room.size();i1++) {
							if(roms.room.get(i1).getRealBounds().intersects(getBounds())) {
								roms.room.get(i1).Str();
								TD = null;
							}
						}*/
					}
				}
			}
		}
		if(handler != null){
			for(int i = 0;i < handler.object.size();i++){
				Entity tempObject = handler.object.get(i);

				if(tempObject.getId() == ID.Enemy){
					if(nextBoundsX().intersects(tempObject.getBounds())){
						velX = 0;
						if(!invBool){
							if(Shield == 0){
								Health--;
								invBool = true;
							}else{
								Shield--;
								invBool = true;
							}
						}
						tempObject.Do("bounce", (int)velX +" " + 0);
					}
					if(nextBoundsY().intersects(tempObject.getBounds())){
						velY = 0;
						if(!invBool){
							if(Shield == 0){
								Health--;
								invBool = true;
							}else{
								Shield--;
								invBool = true;
							}
						}
						tempObject.Do("bounce", 0 +" " + (int)velY);
					}

				}
				if(tempObject.getId() == ID.Door){
					if(getBounds().intersects(tempObject.getBounds()))
					tempObject.Do("str", "door");
				}
			}
		}
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, xSize, ySize);
	}
	public Rectangle nextBoundsX(){
		return new Rectangle((int) ((int)x+velX),(int)y, xSize, ySize);
	}
	public Rectangle nextBoundsY(){
		return new Rectangle((int)x,(int)(y+velY), xSize, ySize);
	}
	public int Do(String a, String b) {
		if(a == "Way"){
			if(b == "r")r = true;
			if(b == "l")l = true;
			if(b == "u")u = true;
			if(b == "d")d = true;
			if(b == "!r")r = false;
			if(b == "!l")l = false;
			if(b == "!u")u = false;
			if(b == "!d")d = false;
			return 0;
		}if(a == "boyut"){
			if(b== "x")return xSize;
			else return ySize;
		}else if(a == "chan" && !game.mP){
			if(gunT < 1){
				if(b == "-"){
					ssilah--;
					gunT = 5;
				}
				if(b == "+"){
					ssilah++;
					gunT = 5;
				}
			}
			if(ssilah-1 < -1){ssilah = silah.size()-1;}
			if(ssilah+1 > silah.size()){ssilah = 0;}
			return 0;
		}if(a == "hit"){
			if(!invBool){
				if(Shield == 0){
					Health -= Integer.valueOf(b);
					invBool = true;
				}else if(Integer.valueOf(b)<Shield){
					Shield -= Integer.valueOf(b);;
					invBool = true;
				}else{
					Health -= Integer.valueOf(b)-Shield;
					Shield = 0;
					invBool = true;
				}
			}
		}else if(a == "Rld" && esilah.bulletIH != esilah.maxBulletIH &&!( esilah.uReloadG > 0) && (esilah.bulletIP > 0 || esilah.type == "infinite")){
			esilah.uReloadG = esilah.reloadG;
		}
		return 0;
	}
	public void invinsible(){
		if(!invBool){
			invTimer = 50;
		}
		if(invTimer <= 0){
			invBool = false;
			invTimer = 50;
		}else invTimer--;
	}
	public void shot(){
		if(esilah.uReloadS < 1 && esilah.bulletIH > 0 && !shooted && !esilah.reloading){
			esilah.uReloadS = esilah.reloadS;
			esilah.bulletIH--;
			shooted = true;
			bullet++;
			esilah.shoot((int)game.getMousePosition().getX(),(int)game.getMousePosition().getY(), (int)x, (int)y,xSize,ySize,game);
		}
	}
}
