package Map;

import java.util.LinkedList;

import Main.*;
import Map_Generating.Tiles;

public class Rooms {
	public LinkedList<Rom>rA = new LinkedList<Rom>();
	//public LinkedList<Rom>room = new LinkedList<Rom>();
	public Rooms(Tiles t,Game game) {
		while(rA.size() < 15) {
			Rom a = new Rom(t,this,game,((int)(Math.random()*30))*2,((int)(Math.random()*30))*2,((int)(Math.random()*3))*2+7,((int)(Math.random()*3))*2+7);
			if(!a.delete)rA.add(a);
		}
		/*Rom a;
		Rom b;
		while(!rA.isEmpty()) {
		b = rA.getFirst();
		/*if(room.isEmpty())*//*a = rA.getLast();
//		else a = room.getFirst();
		b = new Wall_Eater(t,this,a.x+a.w/2,a.y+a.h/2,b.x+b.w/2,b.y+b.h/2).str();
		rA.remove(b);
		if(!room.contains(b))room.add(b);
		}
	*/	boolean done = false;
		int c = 0;
		rA.getFirst().linked = true;
		while(!done) {
			Rom b;
			//b = new Wall_Eater(t,this,a.x+a.w/2,a.y+a.h/2,b.x+b.w/2,b.y+b.h/2).str();
			b = new Wall_Eater2(t,this,rA.get(c).x,rA.get(c).y).str();
			b.linked = true;
			done = true;
			for(int i = 0;i < rA.size();i++) {
				if(!rA.get(i).linked)done = false;
				else c = i;
			}
		}
		t.clear();
	}
	public void rAll() {
		
	}
}
