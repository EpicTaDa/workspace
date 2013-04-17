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
	
//CONSTRUCTOR
	public Sprite(Animation a){
		this.a = a;
		
	}//Method
	
//CHANGE POSITION
	public void update(long timePassed){
		
		x += vx * 500;
		y += vy * 500;
		
		a.update(timePassed);
		
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
	public void setY(float x){
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
	public void setVelocityX(float vx){
		this.vx = vx;
	}
		
//SET Y VELOCITY
	public void setVelocityY(float vy){
		this.vy = vy;
	}
	
//GET SPRITE/IMAGE
	public Image getImage(){
		return a.getImage();
	}

//KEY PRESSED
	public void keyPressed(KeyEvent e){
		
			int key= e.getKeyCode();
		
			if(key == KeyEvent.VK_UP){
				vy = 0.001f;
			}
			if(key == KeyEvent.VK_DOWN){
				vy = 0.001f;
			}
			if(key == KeyEvent.VK_RIGHT){
				vx = 0.001f;
			}
			if(key == KeyEvent.VK_LEFT){
				vx = -0.001f;
			}
		
			e.consume();
	}//Method
	
//KEY PRESSED
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
		
			e.consume();
	}//Method
	
	public void keyTyped(KeyEvent e){
		e.consume();
	}
		
}//CLASS
