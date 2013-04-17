package Quest;
import java.awt.Image;

public class Sprite {

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
		
		x += vx * timePassed;
		y += vy * timePassed;
		
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
		return a.getImage().getHeight(null);
	}//Method
	
}//CLASS
