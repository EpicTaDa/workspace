package Quest;

import java.awt.Image;
import java.util.ArrayList;

public class Animation{

	private ArrayList scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;
	

//CONSTRUCTOR
	public Animation(){
		
		scenes = new ArrayList();
		totalTime = 0;
		start();
		
	}//Method

//ADD SCENE
	public synchronized void addScene(Image i, long t){
		
		totalTime += t;
		scenes.add(new OneScene(i, totalTime));
		
	}//Method
	
//START ANIMATION
	public synchronized void start(){
		
		movieTime = 0;
		sceneIndex = 0;
		
	}//Method
	
//CHANGE SCENES
	public synchronized void update(long timePassed){
		
		if(scenes.size()>1){
			
			movieTime += 5000;
			
			if(movieTime >= totalTime){
				movieTime = 0;
				sceneIndex = 0;
			}
			
			while(movieTime > getScene(sceneIndex).endTime){
				
				sceneIndex++;
			}
			
		}
		
		
	}//Method
	
//GET CURRENT ANIMATION SCENE
	public synchronized Image getImage(){
		
		if(scenes.size() == 0)
			return null;
		else
			return getScene(sceneIndex).pic;
			
	}//Method
	
//GET SCENE
	private OneScene getScene(int x){
		
		return (OneScene)scenes.get(x);
		
	}//Method
	
////// PRIVATE INNER CLASS \\\\\\
	private class OneScene{
		
		Image pic;
		long endTime;
		
		public OneScene(Image pic, long endTime){
			
			this.pic = pic;
			this.endTime = endTime;
			
		}
	}
	
}//Class
