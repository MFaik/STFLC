import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
	LinkedList<Entity> object = new LinkedList<Entity>();
	public int level;
	public Handler(int level){
		this.level = level;
	}
	public void tick(){
		for(int i = 0;i < object.size();i++){
			Entity tempObject = object.get(i);
			if(tempObject.level == level){
			tempObject.tick();
			}
		}
	}
	public void render (Graphics g){
		if(object.size() != 0){
		for(int i = 0;i < object.size();i++){
			
			Entity tempObject = object.get(i);
			if(tempObject.level == level){
			tempObject.render(g);
			}
			}
		}
	}
	public void addObject(Entity object){
		this.object.add(object);
	}
	public void removeObject(Entity object){
		this.object.remove(object);
	}
	
}
