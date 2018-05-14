package Main;
import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
	public LinkedList<Entity> object = new LinkedList<Entity>();
	Player p;
	public Game game;
	public Handler(Game game){
		this.game = game;
	}
	public void tick(){
		for(int i = 0;i < object.size();i++){
			Entity tempObject = object.get(i);
			tempObject.tick();
		}
	}
	public void render (Graphics g){
		if(object.size() != 0){
			for(int i = 0;i < object.size();i++){

				Entity tempObject = object.get(i);
				tempObject.render(g);
			}
		}
		for(int i = 0;i < object.size();i++){
			Entity tempObject = object.get(i);
			if(tempObject.id == ID.Player){
				tempObject.render(g);
			}
		}
	}
	public void addObject(Entity object){
		this.object.add(object);
	}
	public void removeObject(Entity Object){
		if(Object.id == ID.Enemy){
			for(int i = 0;i < object.size();i++){
				Entity tempObject = object.get(i);
				if(tempObject.id == ID.Player){p = (Player)tempObject;
				p.xp += Object.Do("xp", "");
				}
			}
		}
		this.object.remove(Object);
	}
}
