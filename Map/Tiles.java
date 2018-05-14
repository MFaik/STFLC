package Map;

import java.awt.Point;
import java.util.HashMap;
//0 duvar || 1 oda || 2 kapý || 3 koridor || 4 can be kapý
public class Tiles {
	HashMap<Point, Integer> hmap = new HashMap<Point, Integer>();
	public Tiles(){
		
	}
	public void SetTile(Point a,int b){
		hmap.put(a, b);
	}
	public int GetTile(Point a){
		if(hmap.get(a) == null){
			return 0;
		}else{
			return hmap.get(a);
		}
	}
	public void clear() {
		 hmap.forEach((k, v) -> {
	            if(v == 4) {
	            	hmap.put(k, 0);
	            }
	     });
	}
}
