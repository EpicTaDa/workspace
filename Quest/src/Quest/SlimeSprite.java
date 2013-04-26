package Quest;

import java.awt.Image;
import java.util.Random;

import Inherit.ScreenManager;

public class SlimeSprite {

//VARIABLE INITZ
	private double x;
	private double y;
	private float vx;
	private float vy;
	private Image img;
	private double timer;
	private double timerStart;
	
	private ScreenManager s = new ScreenManager();
	
	public SlimeSprite(Image img){
		
		timerStart = System.currentTimeMillis();
		x = s.getWidth()/2;
		y = s.getHeight()/2;
		this.img = img;
		timer = System.currentTimeMillis() - timerStart;
		
	}
	
	public void update(){
		
		movement();
		
		y += vy;
		x += vx;

	}
	
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
			return img.getWidth(null);
				
		}//Method
		
	//GET SPRITE WIDTH
		public int getHeight(){
			return img.getHeight(null);
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
		
	//GET IMAGE
		public Image getImage(){
			return img;
		}
		
		public void movement(){
			
			Random rnd = new Random();
			
			if(timer <= 1 && timer >= 0){
				
				vx = rnd.nextFloat() - rnd.nextFloat();
				vy = rnd.nextFloat() - rnd.nextFloat();
				
			}else if(timer <= 250 && timer >= 50){
				
				vx = 0;
				vy = 0;
				
			}else if(timer > 251)
				timer = -1;

			timer ++;
			
			
		}
		
	
}