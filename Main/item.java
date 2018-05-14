package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class item extends Entity{
	Handler handler;
	Player player;
	Silah silah;
	String type;
	public item(float x, float y, ID id,Handler handler,Silah silah,String type) {
		super(x, y, id);
		this.silah = silah;
		this.handler = handler;
		for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player){
					player = (Player) tempObject;
				}
		}
		this.type = type;
	}

	public void tick() {
		/*for(int i = 0;i < handler.object.size();i++){
			Entity tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player){
					if(getBounds().intersects(tempObject.getBounds())){
							if(silah != null){
								player.silah.add(silah);
								player.ssilah = player.silah.size() - 1;
								handler.removeObject(this);
							}if(type=="heart" && player.MaxHealth != player.Health){
								player.Health += 1;
								handler.removeObject(this);
							}if(type=="bullet" && player.esilah.maxBulletIP != player.esilah.bulletIP){
								player.esilah.bulletIP = player.esilah.maxBulletIP;
								handler.removeObject(this);
							}
						}
				}
		}*/
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)x,(int)y,40,40);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,40,40);
	}

	@Override
	public int Do(String a, String b) {
		return 0;
	}

}
