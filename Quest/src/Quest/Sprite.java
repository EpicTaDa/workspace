package Quest;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Sprite implements KeyListener{

	private Animation a;
	private float x;
	private float y;
	private float vx;
	private float vy;
	
	Quest_Main Quest = new Quest_Main();
	
//CONSTRUCTOR
	public Sprite(Animation a){
		this.a = a;
		
		x = 50;
		y = 40;
		
	}//Method
	
//CHANGE POSITION
	public void update(){
		
		x += vx;
		y += vy;
		
		a.update();
		
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
	public void setX(float x){
		this.x = x;
	}//Method
	
//SET SPRITE Y POSITION
	public void setY(float y){
		this.y = y;
	}//Method
	
//GET SPRITE WIDTH
	public int getWidth(){
		return a.getImage().getWidth(null);
	}//Method
	
//GET SPRITE WIDTH
	public int getHeight(){
		return a.getImage().getHeight(null);
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
		return a.getImage();
	}

	//KEY Released
	public void keyReleased(KeyEvent e){
				
			int key= e.getKeyCode();
		
			if(key == KeyEvent.VK_UP){
				vy = 0;
			}
			if(key == KeyEvent.VK_DOWN){
				vy = 0;
			}
			if(key == KeyEvent.VK_RIGHT){
				vx = 0;
			}
			if(key == KeyEvent.VK_LEFT){
				vx = 0;
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
			}
			if(key == KeyEvent.VK_DOWN){
				vy = 1f;
			}
			if(key == KeyEvent.VK_RIGHT){
				vx = 1f;
			}
			if(key == KeyEvent.VK_LEFT){
				vx = -1f;
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
