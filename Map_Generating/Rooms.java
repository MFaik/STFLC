package Map_Generating;

import java.util.LinkedList;

import Main.Handler;

public class Rooms {
	public LinkedList<Rom> r = new LinkedList<Rom>();
	public Rooms(Handler h,Tiles t) {
		while(r.size() < 15) {
			new Rom(h,t,r);
		}
		for(Rom a : r) {
			System.out.println(a.x+" "+a.y+" "+a.width+" "+a.height);
		}
	}
}
