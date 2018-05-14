package Main;
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
					tempObject.Do("Way","u");
				}
				if(key == KeyEvent.VK_A){
					tempObject.Do("Way","l");
				}
				if(key == KeyEvent.VK_S){
					tempObject.Do("Way","d");
				}
				if(key == KeyEvent.VK_D){
					tempObject.Do("Way","r");
				}
				if(key == KeyEvent.VK_TAB){
					game.ns = 1000000000 / 20.0;
				}
				if(key == KeyEvent.VK_Q){
					tempObject.Do("chan", "-");
				}
				if(key == KeyEvent.VK_E){
					tempObject.Do("chan", "+");
				}
				if(key == KeyEvent.VK_R){
					tempObject.Do("Rld", null);
				}
			}
		}
		if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_WINDOWS){
			game.paused = !game.paused;
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
			for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
			if(tempObject.id == ID.Player){
			if(key == KeyEvent.VK_W){
				tempObject.Do("Way","!u");
			}
			if(key == KeyEvent.VK_A){
				tempObject.Do("Way","!l");
			}
			if(key == KeyEvent.VK_S){
				tempObject.Do("Way","!d");
			}
			if(key == KeyEvent.VK_D){
				tempObject.Do("Way","!r");
			}
			if(key == KeyEvent.VK_UP){
				tempObject.Do("Shoot","!Su");
			}
			if(key == KeyEvent.VK_LEFT){
				tempObject.Do("Shoot","!Sl");
			}
			if(key == KeyEvent.VK_DOWN){
				tempObject.Do("Shoot","!Sd");
			}
			if(key == KeyEvent.VK_RIGHT){
				tempObject.Do("Shoot","!Sr");
			}
			if(key == KeyEvent.VK_TAB){
				game.ns = 1000000000 / 60.0;
			}
			}
			}
	}
}
