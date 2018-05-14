package Map;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Canavarlar.Shoot;
import Main.Game;
import Main.ID;
import Main.Player;
import Map_Generating.Tiles;

public class Rom {
	int x,y,w,h;
	public boolean delete,done,linked;
	public Rom(Tiles t,Rooms r,Game g,int x,int y,int w,int h) {
		this.w =w;
		this.h = h;
		this.x = x;
		this.y = y;
		if(r.rA.size() != 1) {
			for(int i = 0;i < r.rA.size();i++) {
				if(r.rA.get(i).getBounds().intersects(getBounds())) {
					delete = true;
					break;
				}
			}
			/*for(int i = 0;i < r.room.size();i++) {
				if(r.room.get(i).getBounds().intersects(getBounds())) {
					delete = true;
					break;
				}
			}*/
		}else {
			g.handler.addObject(new Player((x+w/2)*80,(y+h/2)*80,ID.Player,g.handler,g,r));
			g.handler.addObject(new Shoot((x+w/2+3)*80,(y+h/2+3)*80,ID.Enemy,g.handler,g,"still","P"));
		}
		if(!delete) {
			for(int a = x-1;a < x+w+1;a++) {
				for(int b = y-1;b < y+h+1;b++) {
					if((a == x-1 || a == x+w)&&(b == y-1 || b == y+h)) {
						continue;
					}
					t.SetTile(new Point(a,b), 4);
				}		
			}
			for(int a = x;a < x+w;a++) {
				for(int b = y;b < y+h;b++) {
					t.SetTile(new Point(a,b), 1);
				}		
			}
		}
	}
	public Rectangle getBounds() {
		return new Rectangle(x-1,y-1,w+2,h+2);
	}
	public Rectangle getRealBounds() {
		return new Rectangle(x*80-80,y*80-80,w*80+160,h*80+160);
	}
	public void Str() {
		if(!done) {
			;
			;
			done = true;
		}else {
			
		}
	}
	public void EntityCreator() {
		try {
			FileReader fr = new FileReader("ali");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
