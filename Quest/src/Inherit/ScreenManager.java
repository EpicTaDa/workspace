package Inherit;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;

public class ScreenManager {
	
	private GraphicsDevice vc;
	
//VIDEOCARD ACCESS
	public ScreenManager(){
		
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice();
		
	}//Constructor

//GET ALL COMPATIBLE DISPLAYMODES
	public DisplayMode[] getCompatibleDisplayMode(){
		
		return vc.getDisplayModes();
	}//Method
	
//COMPARE DISPLAYMODES WITH VIDEOCARD MODES
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]){
		
		DisplayMode goodModes[] = vc.getDisplayModes();
		
		for(int x = 0; x<modes.length; x++){
			for(int y = 0; y <modes.length; y++){
				if(displayModesMatch(modes[x],goodModes[y])){
					return modes[x];
				}
			}
		}
		return null;
	}//Method
	
//GET CURRENT DISPLAYMODE
	public DisplayMode getCurrentDisplayMode(){
		
		return vc.getDisplayMode();
		
	}//Method

//CHECK MODE MATCHES
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
		
		if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
			return false;
		}
		
		if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()){
			return false;
		}
		
		if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()){
			return false;
		}
		
		return true;
	}//Method
	
//SET FULLSCREEN
	public void setFullScreen(DisplayMode dm){
		
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setResizable(false);
		f.setIgnoreRepaint(true);
		
		vc.setFullScreenWindow(f);
		
		if(dm != null && vc.isDisplayChangeSupported()){
			
			try{
				
				vc.setDisplayMode(dm);
				
			}catch(Exception ex){}
		}
		
		f.createBufferStrategy(2);
		
	}//Method
	
//Graphics Object = This method
	public Graphics2D getGraphics(){
		
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			BufferStrategy s = w.getBufferStrategy();
			
			return (Graphics2D)s.getDrawGraphics();
		}else
			return null;
		
	}//Method
	
//UPDATE DISPLAY
	public void update(){
		
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			
			BufferStrategy s = w.getBufferStrategy();
			
			if(!s.contentsLost()){
				s.show();
			}//if
			
		}//if
		
	}//Method
	
//RETURN FULL SCREEN WINDOW
	public Window getFullScreenWindow(){
		
		return vc.getFullScreenWindow();	
	}//Method
	
	public int getHeight(){
		
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			return w.getHeight();
		}else
			return 0;
		
	}//Method
	
	public int getWidth(){
		
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			return w.getWidth();
		}else
			return 0;
		
	}//Method
	
//GET OUT OF FULL SCREEN
	public void restoreScreen(){
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			
			w.dispose();
			
		}
		
		vc.setFullScreenWindow(null);
	}//Method
	
//CREATE IMAGE COMPATIBLE TO MONITOR
	public BufferedImage createCompatibleImage(int w, int h, int t){
		
		Window win = vc.getFullScreenWindow();
		
		if(win != null){
			
			GraphicsConfiguration gc = win.getGraphicsConfiguration();
			
			return gc.createCompatibleImage(w, h, t);
			
		}
		return null;
		
	}//Method
	
	
}//CLASS
