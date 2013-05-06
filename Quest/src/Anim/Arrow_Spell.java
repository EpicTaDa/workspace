package Anim;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Arrow_Spell {
	
	private double x;
	private double y;
	private int vx, vy;
    private Image arrow;
    private boolean visible;
    private int height, width;

	public Arrow_Spell(double x, double y, int keyIndex){
        
	       this.x = x;
	       this.y = y;
		
        if(keyIndex == 1){
        	vx = 0;
        	vy = -10;
        	arrow = new ImageIcon(".\\src\\Images\\arrowUp.png").getImage();
        } else if(keyIndex == 2){
        	vx = 0;
        	vy = 10;
        	arrow = new ImageIcon(".\\src\\Images\\arrowDown.png").getImage();
        } else if(keyIndex == 3){
        	vx = 10;
        	vy = 0;
        	arrow = new ImageIcon(".\\src\\Images\\arrowRight.png").getImage();
        } else if(keyIndex == 4){
        	vx = -10;
        	vy = 0;
        	arrow = new ImageIcon(".\\src\\Images\\arrowLeft.png").getImage();
        }
        
        width = arrow.getWidth(null);
        height = arrow.getHeight(null);
        
	}
	
	public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public int getWidth(){
    	return arrow.getWidth(null);
    }
    
    public int getHeight(){
    	return arrow.getHeight(null);
    }
    
    public Image getImage(){
        return arrow;
    }
    
    public boolean isVisible(){
        return visible;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, width, height);    
    }
    
    public void move(){
        x += vx;
        y += vy;
    }
    
}//PC_Missile