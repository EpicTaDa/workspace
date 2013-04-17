package Quest;

import java.awt.*;

import javax.swing.ImageIcon;

public class Quest_Main {

	public static void main(String[] args){
		
		Quest_Main main = new Quest_Main();
		main.run();
	
	}//Main

//VARIABLE INITZ
	
	private Animation a;
	private ScreenManager s;
	private Image bg;
	private static final DisplayMode modes1[] = {
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0)
	};
	
//IMAGE LOADER
	public void loadImages(){
		
		bg = new ImageIcon(".\\src\\Images\\BG.jpg").getImage();
		Image step1 = new ImageIcon(".\\src\\Images\\step1.png").getImage();
		Image step2 = new ImageIcon(".\\src\\Images\\step2.png").getImage();
		
		a = new Animation();
		a.addScene(step1, 5000);
		a.addScene(step2, 5000);
		
	}//Method

//MAIN METHOD CALLED FROM MAIN
	public void run(){
		
		s = new ScreenManager();
		
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
		
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(cumTime - startingTime < 100000){
			
			long timePassed = System.currentTimeMillis() - startingTime;
			cumTime += timePassed;
			
			a.update(timePassed);
			
		//DRAW SCREEN
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			
			try{
				
				Thread.sleep(20);
			}catch(Exception ex){}
			
		}
		
		
	}//Method
	
//DRAW METHOD
	public void draw(Graphics g){
		
		g.drawImage(bg, 0, 0, s.getWidth(), s.getHeight(), null);
		g.drawImage(a.getImage(), s.getWidth()/2, s.getHeight()/2, null);
		
	}
	
}//Class
