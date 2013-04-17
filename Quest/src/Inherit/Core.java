package Inherit;

import java.awt.*;
import javax.swing.*;

import Quest.ScreenManager;

public abstract class Core {

	private static DisplayMode modes[] = {
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0)
	};
	
	private boolean running;
	protected ScreenManager s;
	
//STOP METHOD
	public void stop(){
		
		running = false;
		
	}//Method
	
//CALL INIT + GAMELOOP
	public void run(){
		
		try{
			init();
			gameLoop();
		}finally{
			s.restoreScreen();
		}
		
	}//Method
	
//SET FULLSCREEN
	public void init(){
		
		s = new ScreenManager();
		DisplayMode dm = s.findFirstCompatibleMode(modes);
		s.setFullScreen(dm);
		
		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.GREEN);
		w.setForeground(Color.WHITE);
		running = true;
		
	}
	
//GAMELOOP
	public void gameLoop(){
		
		long startTime = System.currentTimeMillis();
		long cumlTime = startTime;
		
		while(running){
			
			long timePassed = System.currentTimeMillis() - startTime;
			cumlTime += timePassed;
			
			update(timePassed);
			
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
			
		}
		
	}//Method
	
//UPDATE
	public void update(long timePassed){
		
	}
	
//DRAW
	public void draw(Graphics2D g){
		
	}
	
}//Class

