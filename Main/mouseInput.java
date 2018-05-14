package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseInput implements MouseListener {
	Game a;
	public mouseInput(Game game){
		a = game;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
				a.mP = true;
		/*if(esilah.uReloadS < 1 && esilah.bulletIH > 0 && !shooted && !esilah.reloading){
			esilah.uReloadS = esilah.reloadS;
			esilah.bulletIH--;
			shooted = true;
			bullet++;
			esilah.shoot(e.getX(),e.getY(), (int)x, (int)y,xSize,ySize);
		}*/
	}

	@Override
	public void mouseReleased(MouseEvent e) {
				a.mP = false;
	}
	
}
