import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	Game game;
	Handler handler;
	boolean r,l,u,d,u1,d1,l1,r1;
	KeyInput(Game game,Handler handler){
		this.game = game;
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
			if(key == KeyEvent.VK_W){
				tempObject.setVelY(-5);
				u = true;
			}
			if(key == KeyEvent.VK_S){
				tempObject.setVelY(5);
				d = true;
			}
			if(key == KeyEvent.VK_A){
				tempObject.setVelX(-5);
				l = true;
			}
			if(key == KeyEvent.VK_D){
				tempObject.setVelX(5);
				r = true;
			}
			}
		}
		if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_WINDOWS){
			game.paused = true;
		}
		/*
		if(key == KeyEvent.VK_UP){
			u1 = true;
		}else if(key == KeyEvent.VK_DOWN){
			d1 = true;
		}if(key == KeyEvent.VK_LEFT){
			l1 = true;
		}else if(key == KeyEvent.VK_RIGHT){
			r1 = true;
		}
		
		for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
		if(u1){
			if(l1){
				tempObject.Do("yon", "ul");
			}else if(r1){
				tempObject.Do("yon", "ur");
			}else{
				tempObject.Do("yon", "u");
			}
		}else
		if(d1){
			if(l1){
				tempObject.Do("yon", "dl");
			}else if(r1){
				tempObject.Do("yon", "dr");
			}else{
				tempObject.Do("yon", "d");
			}
		}else if(l1){
			tempObject.Do("yon", "l");
		}else if(r1){
			tempObject.Do("yon", "r");
		}
			}
		}*/
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
			if(key == KeyEvent.VK_W){
				u = false;
			}
			if(key == KeyEvent.VK_S){
				d = false;
			}
			if(key == KeyEvent.VK_A){
				l = false;
			}
			if(key == KeyEvent.VK_D){
				r = false;
			}
			for(int i = 0;i < handler.object.size();i++){
				Entity tempObject = handler.object.get(i);
				if(tempObject.id == ID.Player){
						if(!l&&!r)tempObject.setVelX(0);
						if(!u&&!d)tempObject.setVelY(0);
			}
		}
	}
}
