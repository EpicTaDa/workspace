package Anim;

import java.awt.Image;
import java.util.ArrayList;

@SuppressWarnings("all")

public class Attack{
	
	private ArrayList scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;
	private int timePassed;

//CONSTRUCTOR
	public Attack(){
		
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
	
	public void attackLeft(){
		sceneIndex = 0;
	}
	
	public void attackRight(){
		sceneIndex = 1;
	}

	public void attackUp(){
		sceneIndex = 2;
	}
	
	public void attackDown(){
		sceneIndex = 3;
	}
	
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


