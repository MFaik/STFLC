package Map_Generating;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import Main.Handler;
import Main.ID;
import Main.Player;

public class Rom {
	public boolean linked = false;
	 int width,height,x,y;
	public Rom(Handler h,Tiles t,LinkedList<Rom> r) {
		width = ((int)(Math.random()*4)*2)+7;
		height = ((int)(Math.random()*4)*2)+7;
		x = (int)(Math.random()*87);
		y = (int)(Math.random()*87);
		boolean poss = true;
		for (Rom temp : r) {
            if(temp.getBounds().intersects(getBounds()))poss = false;
        }
		if(poss) {
			if(r.isEmpty())h.addObject(new Player((float)(x+width/2)*80,(float)(y+height/2)*80,ID.Player,h,h.game);
			r.add(this);
			for(int w = 0;w < width;w++)
			for(int he = 0;he < height;he++)
			t.SetTile(new Point(x+w,y+he),1);
			
		}
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
}
