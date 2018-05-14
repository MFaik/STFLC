package Map;

import java.awt.*;

import Map_Generating.Tiles;

public class Wall_Eater {
	int x,y,d = 0,tx,ty;
	Tiles t;
	Rooms r;
	boolean done = false;
	public Wall_Eater(Tiles t,Rooms r,int x,int y,int tx,int ty) {
		this.x = x;
		this.y = y;
		this.t = t;
		this.r = r;
		this.ty = ty;
		this.tx = tx;
	}
	public Rom str() {
		if((int)Math.random() == 1) {
			X(tx);
			Y(ty);
		}else {
			Y(tx);
			X(ty);
		}
		for(int i = 0;i < r.rA.size();i++) {
			if(r.rA.get(i).getBounds().intersects(new Rectangle(x,y,1,1))) {
				return r.rA.get(i);
			}
		}
		for(int i = 0;i < r.room.size();i++) {
			if(r.room.get(i) != null)
			if(r.room.get(i).getBounds().intersects(new Rectangle(x,y,1,1))) {
				return r.room.get(i);
			 }
		}
		done = true;
		return null;
	}
	public void X(int tx) {
		while(tx != x && d < 2) {
			if(tx > x)x++;
			if(tx < x)x--;
			System.out.println(x);
			if(t.GetTile(new Point(x,y)) == 4){
				t.SetTile(new Point(x,y),2);
				d++;
				if(d > 1)break;
			}
			if(t.GetTile(new Point(x,y)) == 0) {
				t.SetTile(new Point(x,y),3);
			}
		}
	}
	public void Y(int ty) {
		while(ty != y && d < 2) {
			if(ty > y)y++;
			if(ty < y)y--;
			System.out.println(y);
			if(t.GetTile(new Point(x,y)) == 4){
				t.SetTile(new Point(x,y),2);
				d++;
				if(d > 1)break;
			}
			if(t.GetTile(new Point(x,y)) == 0) {
				t.SetTile(new Point(x,y),3);
			}
		}
	}
}
