package Main;


import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -240840600533728354L;

	JFrame frame = new JFrame("ICBTU");
	public Window(int width, int height, Game game){
		BufferedImage icon = null;
		BufferedImage cursorImg = null;
		URL f1 = this.getClass().getClassLoader().getResource("stmlc_001_46x46.jpg"); 
		URL f2 = this.getClass().getClassLoader().getResource("Cursor1.png");
		if(f1 != null){
			try{
				icon = ImageIO.read(f1);
				cursorImg = ImageIO.read(f2);
			}catch(IOException e){
				System.out.println(e);
			}
		}
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(16,16), "name");
		frame.setCursor(cursor);
		//frame.setPreferredSize(new Dimension(width,height));
		/*frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(icon);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		//frame.setOpacity(0.5f);
		frame.add(game);
		frame.setVisible(true);

		game.start();
	}
	public void addLocation(int x,int y){
		Point a = frame.getLocation();
		frame.setLocation(a.x + x, a.y + y);
	}
	public void setLocation(int x,int y){
		frame.setLocation(x,y);
	}
}
