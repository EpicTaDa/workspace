package Game;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import Anim.Arrow_Spell;
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
	private int keyIndex, width, height;
	private Rectangle bounds;
	private double timer, timerStart;
	private boolean walk_up;
	private boolean walk_left;
	private boolean walk_down;
	private boolean walk_right;
	private boolean attack;
	private boolean attackBow;
	public double arrows_left;
	private ArrayList arrows;
	private KeyEvent re;
	
	private Game main;
	private ScreenManager s = new ScreenManager();
	
//CONSTRUCTOR
	public Sprite(WalkUp wUp, WalkLeft wLeft, WalkRight wRight, WalkDown wDown, Attack a){
		
		this.wUp = wUp;
		this.wLeft = wLeft;
		this.wRight = wRight;
		this.wDown = wDown;
		this.a = a;
		
		arrows_left = 10;
		
		arrows = new ArrayList();
		
		timerStart = System.currentTimeMillis();
		timer = System.currentTimeMillis() - timerStart;
		
		x = 50;
		y = 40;
		
		width = wUp.getImage().getWidth(null);
		height = wUp.getImage().getHeight(null);
	}//Method
	
//CHANGE POSITION
	public void update(){
		
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
		
        
		timer++;
			
		x += vx*2;
		y += vy*2;
		
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
	
//GET BOUNDS
	public Rectangle getBounds(){
		return new Rectangle((int)x+5, (int)y+35, width-15, height-40);
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
	
	public ArrayList getArrows(){
		return arrows;
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
			
		}else if(attackBow){
			
			vx = 0;
			vy = 0;
			
			fire();
			
			if(keyIndex == 4){
				a.bowLeft();
				return a.getImage();
			}
			if(keyIndex == 3){
				a.bowRight();
				return a.getImage();
			}
			if(keyIndex == 1){
				a.bowUp();
				return a.getImage();
			}
			if(keyIndex == 2){
				a.bowDown();
				return a.getImage();
			}
		}
			
		if(walk_left){
			vx = -1;
			keyIndex = 4;
			return wLeft.getImage();
		}
		if(walk_up){
			vy = -1;
			keyIndex = 1;
			return wUp.getImage();
		}
		if(walk_right){
			vx = 1;
			keyIndex = 3;
			return wRight.getImage();
		}
		if(walk_down){
			vy = 1;
			keyIndex = 2;
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
		
		
		return wDown.getImage();
	}
	
	public void fire() {
		
		if(arrows_left > 0){
			if(timer <= 1 && timer >= 0){
			
				arrows_left -= 0.5;
				arrows.add(new Arrow_Spell(this.x, this.y, keyIndex));
			
			}else if(timer > 40)
				timer = -1;
			
		}

		
    }//PV_FIRE
	
//KILL
	public void kill(){
		x = 1000;
		y = 1000;
	}
	
//KEY PRESSED
	public void keyPressed(KeyEvent e){
			
			int key = e.getKeyCode();
				
				if(key == KeyEvent.VK_UP){
					walk_up = true;
					vy = -1f;
					keyIndex = 1;
				}
				if(key == KeyEvent.VK_DOWN){
					walk_down = true;
					vy = 1f;
					keyIndex = 2;
				}
				if(key == KeyEvent.VK_RIGHT){
					walk_right = true;
					vx = 1f;
					keyIndex = 3;
				}
				if(key == KeyEvent.VK_LEFT){
					walk_left = true;
					vx = -1f;
					keyIndex = 4;
				}
				
				if(key == KeyEvent.VK_Z){
					attack = true;
				}
				
				if(key == KeyEvent.VK_X){
					attackBow = true;
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
			if(key == KeyEvent.VK_Z){
				attack = false;
			}
			if(key == KeyEvent.VK_X){
				attackBow = false;
			}
			
	}//Method
	
	public void keyTyped(KeyEvent e){
	}
		
}//CLASS
