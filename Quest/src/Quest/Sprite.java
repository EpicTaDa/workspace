package Quest;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Anim.WalkDown;
import Anim.WalkUp;
import Anim.WalkLeft;
import Anim.WalkRight;

public class Sprite implements KeyListener{

	private WalkUp wUp;
	private WalkLeft wLeft;
	private WalkRight wRight;
	private WalkDown wDown;
	private int x;
	private int y;
	private float vx;
	private float vy;
	private boolean walk_up;
	private boolean walk_left;
	private boolean walk_down;
	private boolean walk_right;
	
	Quest_Main Quest = new Quest_Main();
	
//CONSTRUCTOR
	public Sprite(WalkUp wUp, WalkLeft wLeft, WalkRight wRight, WalkDown wDown){
		
		this.wUp = wUp;
		this.wLeft = wLeft;
		this.wRight = wRight;
		this.wDown = wDown;
		
		x = 50;
		y = 40;
		
	}//Method
	
//CHANGE POSITION
	public void update(){
		
		x += vx;
		y += vy;
		
		if(walk_up == true)
			wUp.update();
		else
			wUp.setStanding();
		
		if(walk_left == true && walk_up != true)
			wLeft.update();
		else
			wLeft.setStanding();
		
		if(walk_right == true && walk_up != true)
			wRight.update();
		else
			wRight.setStanding();
		
		if(walk_down == true && walk_up != true)
			wDown.update();
		else
			wDown.setStanding();
		
		
	}//Method
	
//GET X POSITION
	public float getX(){
		return x;
	}//Method
	
//GET Y POSITION
	public float getY(){
		return y;
	}//Method
	
//SET SPRITE X POSITION
	public void setX(int x){
		x = this.x;
		
	}//Method
	
//SET SPRITE Y POSITION
	public void setY(int y){
		this.y = y;
	}//Method
	
//GET SPRITE WIDTH
	public int getWidth(){
		return wUp.getImage().getWidth(null);
			
	}//Method
	
//GET SPRITE WIDTH
	public int getHeight(){
		return wUp.getImage().getHeight(null);
	}//Method
	
//GET X VELOCITY
	public float getVelocityX(){
		return vx;
	}
	
//GET Y VELOCITY
	public float getVelocityY(){
		return vy;
	}
	
//SET X VELOCITY
	public void setVelocityX(float vx_1){
		this.vx = vx_1;
		vx = vx_1;
	}
		
//SET Y VELOCITY
	public void setVelocityY(float vy_1){
		this.vy = vy_1;
		vy = vy_1;
	}
	
//GET SPRITE/IMAGE
	public Image getImage(){
		
		if(walk_left == true)
			return wLeft.getImage();
		
		if(walk_up == true)
			return wUp.getImage();
		
		if(walk_right == true)
			return wRight.getImage();
		
		if(walk_down == true)
			return wDown.getImage();
		
		return wDown.getImage();
	}

	//KEY Released
	public void keyReleased(KeyEvent e){
				
			int key= e.getKeyCode();
		
			if(key == KeyEvent.VK_UP){
				vy = 0;
				walk_up = false;
			}
			if(key == KeyEvent.VK_DOWN){
				vy = 0;
				walk_down = false;
			}
			if(key == KeyEvent.VK_RIGHT){
				vx = 0;
				walk_right = false;
			}
			if(key == KeyEvent.VK_LEFT){
				vx = 0;
				walk_left = false;
			}
			if(key == KeyEvent.VK_ESCAPE){
				Quest.setIngameFalse();
			}
			
			update();
			e.consume();
	}//Method
	
//KEY PRESSED
	public void keyPressed(KeyEvent e){
		
			int key= e.getKeyCode();
		
			if(key == KeyEvent.VK_UP){
				vy = -1f;
				walk_up = true;
			}
			if(key == KeyEvent.VK_DOWN){
				vy = 5f;
				walk_down = true;
			}
			if(key == KeyEvent.VK_RIGHT){
				vx = 5f;
				walk_right = true;
			}
			if(key == KeyEvent.VK_LEFT){
				vx = -1f;
				walk_left = true;
			}
			if(key == KeyEvent.VK_ESCAPE){
				Quest.setIngameFalse();
			}
		
			e.consume();
	}//Method
	
	
	public void keyTyped(KeyEvent e){
		e.consume();
	}
		
}//CLASS
