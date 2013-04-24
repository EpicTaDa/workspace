package Quest;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Anim.Attack;
import Anim.WalkDown;
import Anim.WalkUp;
import Anim.WalkLeft;
import Anim.WalkRight;
import Inherit.ScreenManager;

@SuppressWarnings("all")

public class Sprite implements KeyListener{

	private WalkUp wUp;
	private WalkLeft wLeft;
	private WalkRight wRight;
	private WalkDown wDown;
	private Attack a;
	private double x;
	private double y;
	private float vx;
	private float vy;
	private int keyIndex;
	private boolean walk_up;
	private boolean walk_left;
	private boolean walk_down;
	private boolean walk_right;
	private boolean attack;
	private boolean can_attack;
	private KeyEvent re;
	
	private ScreenManager s = new ScreenManager();
	
	Quest_Main Quest = new Quest_Main();
	
//CONSTRUCTOR
	public Sprite(WalkUp wUp, WalkLeft wLeft, WalkRight wRight, WalkDown wDown, Attack a){
		
		this.wUp = wUp;
		this.wLeft = wLeft;
		this.wRight = wRight;
		this.wDown = wDown;
		this.a = a;
		
		x = 50;
		y = 40;

		
	}//Method
	
//CHANGE POSITION
	public void update(){
		
		if(attack)
			a.update();
		
		if(walk_up)
			wUp.update();
		else
			wUp.setStanding();
		
		if(walk_left)
			wLeft.update();
		else
			wLeft.setStanding();
		
		if(walk_right)
			wRight.update();
		else
			wRight.setStanding();
		
		if(walk_down)
			wDown.update();
		else
			wDown.setStanding();
		
		x += vx;
		y += vy;
		
	}//Method
	
//GET X POSITION
	public double getX(){
		return x;
	}//Method
	
//GET Y POSITION
	public double getY(){
		return y;
	}//Method
	
//SET SPRITE X POSITION
	public void setX(double x){
		this.x = x;
		
	}//Method
	
//SET SPRITE Y POSITION
	public void setY(double y){
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
		
		if(attack){
			
			vx = 0;
			vy = 0;
			
			if(keyIndex == 4){
				a.attackLeft();
				return a.getImage();
			}
			if(keyIndex == 3){
				a.attackRight();
				return a.getImage();
			}
			if(keyIndex == 1){
				a.attackUp();
				return a.getImage();
			}
			if(keyIndex == 2){
				a.attackDown();
				return a.getImage();
			}
			
		}else if(attack != true){
			
		if(walk_left){
			vx = -2;
			return wLeft.getImage();
		}
		if(walk_up){
			vy = -2;
			return wUp.getImage();
		}
		if(walk_right){
			vx = 2;
			return wRight.getImage();
		}
		if(walk_down){
			vy = 2;
			return wDown.getImage();
		}
		
		
		if(keyIndex == 4){
			vx = 0;
			return wLeft.getImage();
		}
		if(keyIndex == 1){
			vy = 0;
			return wUp.getImage();
		}
		if(keyIndex == 3){
			vx = 0;
			return wRight.getImage();
		}
		if(keyIndex == 2){
			vy = 0;
			return wDown.getImage();
		}
		
		}
		return wDown.getImage();
	}
	
//KEY PRESSED
	public void keyPressed(KeyEvent e){
			
			int key = e.getKeyCode();
				
				if(key == KeyEvent.VK_UP){
					walk_up = true;
					vy = -2f;
					keyIndex = 1;
				}
				if(key == KeyEvent.VK_DOWN){
					walk_down = true;
					vy = 2f;
					keyIndex = 2;
				}
				if(key == KeyEvent.VK_RIGHT){
					walk_right = true;
					vx = 2f;
					keyIndex = 3;
				}
				if(key == KeyEvent.VK_LEFT){
					walk_left = true;
					vx = -2f;
					keyIndex = 4;
				}
				
				if(key == KeyEvent.VK_SPACE){
					attack = true;
				}
				
				if(key == KeyEvent.VK_ESCAPE){
						s.restoreScreen();
				}
		
	}//Method
	
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
			if(key == KeyEvent.VK_SPACE){
				attack = false;
			}
			
			update();
	}//Method
	
	public void keyTyped(KeyEvent e){
	}
		
}//CLASS
