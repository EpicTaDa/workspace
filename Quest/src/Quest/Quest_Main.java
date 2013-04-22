package Quest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;

import javax.swing.ImageIcon;
import Inherit.Core;
import javax.swing.JPanel;

public class Quest_Main extends JPanel implements ActionListener{
	
	public static void main(String[] args){
		
		Quest_Main main = new Quest_Main();
		main.run();
		
	}//Main
	
//VARIABLE INITZ
	private Sprite sprite;
	private Animation a;
	private ScreenManager s;
	private Image bg;
	private long timePassed;
	private static final DisplayMode modes1[] = {
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0)
	};
	
	private boolean ingame;

//IMAGE LOADER
	public void loadImages(){
		
		bg = new ImageIcon(".\\src\\Images\\BG.jpg").getImage();
		Image step1 = new ImageIcon(".\\src\\Images\\step1.png").getImage();
		Image step2 = new ImageIcon(".\\src\\Images\\step2.png").getImage();
		
		a = new Animation();
		a.addScene(step1, 5000);
		a.addScene(step2, 5000);
		
		sprite = new Sprite(a);
		
	}//Method

//MAIN METHOD CALLED FROM MAIN
	public void run(){
		
		s = new ScreenManager();
		ingame = true;
		
		try{
			
			DisplayMode dm = s.findFirstCompatibleMode(modes1);
			s.setFullScreen(dm);
			loadImages();
			movieLoop();
			
		}finally{
			
			s.restoreScreen();
		}
		
	}//Method
	
//PLAY MOVIE
	public void movieLoop(){
		
		Window w = s.getFullScreenWindow();
		
		w.addKeyListener(new TAdapter());
		w.setFocusable(true);
		
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(ingame == true){
			
			long timePassed = System.currentTimeMillis() - startingTime;
			cumTime += timePassed;
			update();
			
	//DRAW SCREEN
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			sprite.update();
			s.update();
			
			try{
				
				Thread.sleep(20);
			}catch(Exception ex){}
			
		}
		
		
	}//Method
	
//DRAW METHOD
	public void draw(Graphics g){
		
		g.drawImage(bg, 0, 0, s.getWidth(), s.getHeight(), null);
		g.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
	}
	
	public void setIngameFalse(){
		this.ingame = false;
	}
	
//MAIN SPRITE POS
	public void update(){
		
		Graphics2D g = s.getGraphics();
		
		if(sprite.getX() < 0){	
			sprite.setX(1);	
		}else if((sprite.getX() + sprite.getWidth()) > s.getWidth()){
			sprite.setX(s.getWidth());
			
		}
		
		if(sprite.getY() < 0){
			
			sprite.setY(1);
		
		}else if((sprite.getY() + sprite.getHeight()) > s.getHeight()){
			
			sprite.setY(s.getHeight());
			
		}
		
		sprite.update();
		draw(g);
		
	}//Method
	
	public void actionPerformed(ActionEvent e) {
		
		Graphics2D g = s.getGraphics();

		draw(g);
		g.dispose();
		
	}
	
//INNER ADAPTER CLASS
	
	private class TAdapter extends KeyAdapter {
		
		public TAdapter(){
			setFocusable(true);
		}
		
        public void keyReleased(KeyEvent e){
        	System.out.println("stas");
            sprite.keyReleased(e);
            e.consume();
        }
        
        public void keyPressed(KeyEvent e){
        	System.out.println("stas");
            sprite.keyPressed(e);
            e.consume();
        }
        
        public void keyTyped(KeyEvent e) {
        	e.consume();
        } 
	}
	
}//Class
