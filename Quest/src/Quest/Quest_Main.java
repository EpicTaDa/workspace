package Quest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Anim.Arrow_Spell;
import Anim.Attack;
import Anim.WalkUp;
import Anim.WalkDown;
import Anim.WalkLeft;
import Anim.WalkRight;
import Inherit.Core;
import Inherit.ScreenManager;

import javax.swing.JPanel;

@SuppressWarnings("all")

public class Quest_Main extends JPanel implements ActionListener{
	
	public static void main(String[] args){
		
		Quest_Main main = new Quest_Main();
		main.run();
		
	}//Main
	
//VARIABLE INITZ
	private Sprite sprite;
	private WalkUp wUp;
	private WalkLeft wLeft;
	private WalkRight wRight;
	private WalkDown wDown;
	private Attack a;
	private Arrow_Spell arrow;
	private ScreenManager s;
	private Image bg, HUD, slimeDeadImg, slimeImg;
	private SlimeSprite slimeSprite;
	private long timePassed;
	private Font medium;
	private Font big;
	private ArrayList slimes, arrows;
	private int[][] pos =
	    {
	        {500, 100}, {300, 500}, {400, 400},
	        {800, 109}, {700, 700}, {300, 500}, 
	        {40, 759}, {700, 500}, {800, 150}, 
	        {100, 209},{400, 700}, {300, 800}, 
	        {40, 759}, {700, 500}, {800, 650}, 
	        {200, 309}
	    };
	
	private static final DisplayMode modes1[] = {
		new DisplayMode(800,600,32,59),
		new DisplayMode(800,600,32,60),
		new DisplayMode(960,540,32,59),
		new DisplayMode(960,540,32,60)
	};
	
	private boolean ingame;

//IMAGE LOADER
	public void loadImages(){
		
		bg = new ImageIcon(".\\src\\Images\\BG.jpg").getImage();
		HUD = new ImageIcon(".\\src\\Images\\HUD.png").getImage();
		
		Image standUp = new ImageIcon(".\\src\\Images\\stand.png").getImage();
		Image step1Up = new ImageIcon(".\\src\\Images\\step1.png").getImage();
		Image step2Up = new ImageIcon(".\\src\\Images\\step2.png").getImage();
		
		Image standleft = new ImageIcon(".\\src\\Images\\standLeft.png").getImage();
		Image step1Left = new ImageIcon(".\\src\\Images\\step1Left.png").getImage();
		Image step2Left = new ImageIcon(".\\src\\Images\\step2Left.png").getImage();
		
		Image standRight = new ImageIcon(".\\src\\Images\\standRight.png").getImage();
		Image step1Right = new ImageIcon(".\\src\\Images\\step1Right.png").getImage();
		Image step2Right = new ImageIcon(".\\src\\Images\\step2Right.png").getImage();
		
		Image standDown = new ImageIcon(".\\src\\Images\\standDown.png").getImage();
		Image step1Down = new ImageIcon(".\\src\\Images\\step1Down.png").getImage();
		Image step2Down = new ImageIcon(".\\src\\Images\\step2Down.png").getImage();
		
		Image attackLeft = new ImageIcon(".\\src\\Images\\attackLeft.png").getImage();
		Image attackRight = new ImageIcon(".\\src\\Images\\attackRight.png").getImage();
		Image attackDown = new ImageIcon(".\\src\\Images\\attackDown.png").getImage();
		Image attackUp = new ImageIcon(".\\src\\Images\\attackUp.png").getImage();
		
		Image bowLeft = new ImageIcon(".\\src\\Images\\bowLeft.png").getImage();
		Image bowRight = new ImageIcon(".\\src\\Images\\bowRight.png").getImage();
		Image bowDown = new ImageIcon(".\\src\\Images\\bowDown.png").getImage();
		Image bowUp = new ImageIcon(".\\src\\Images\\bowUp.png").getImage();
		
		slimeImg = new ImageIcon(".\\src\\Images\\slime.png").getImage();
		slimeDeadImg = new ImageIcon(".\\src\\Images\\slimeDead.png").getImage();
		
		wUp = new WalkUp();
		wUp.addScene(step1Up, 5000);
		wUp.addScene(standUp, 5000);
		wUp.addScene(step2Up, 5000);
		
		a = new Attack();
		a.addScene(attackLeft, 5000);
		a.addScene(attackRight, 5000);
		a.addScene(attackUp, 5000);
		a.addScene(attackDown, 5000);
		a.addScene(bowLeft, 5000);
		a.addScene(bowRight, 5000);
		a.addScene(bowUp, 5000);
		a.addScene(bowDown, 5000);
		
		wLeft = new WalkLeft();
		wLeft.addScene(step1Left, 5000);
		wLeft.addScene(standleft, 5000);
		wLeft.addScene(step2Left, 5000);
		
		wRight = new WalkRight();
		wRight.addScene(step1Right, 5000);
		wRight.addScene(standRight, 5000);
		wRight.addScene(step2Right, 5000);
		
		wDown = new WalkDown();
		wDown.addScene(step1Down, 5000);
		wDown.addScene(standDown, 5000);
		wDown.addScene(step2Down, 5000);
		
		wDown.setStanding();
		wLeft.setStanding();
		wRight.setStanding();
		wUp.setStanding();
		
		sprite = new Sprite(wUp, wLeft, wRight, wDown, a);
		
		medium = new Font("Impact", Font.BOLD, 24);
		big = new Font("Impact", Font.BOLD, 84);
        FontMetrics mtr = this.getFontMetrics(medium);
		
        initSlimes();
        
	}//Method

//INIT SLIMES
	public void initSlimes(){
		
		slimes = new ArrayList();
		
		for(int i = 0; i < pos.length; i++){
			
			slimes.add(new SlimeSprite(slimeImg, pos[i][0], pos[i][1]));
			
		}
		
	}
	
//MAIN METHOD CALLED FROM MAIN
	public void run(){
		
		s = new ScreenManager();
		ingame = true;
		
		try{
			
			DisplayMode dm = s.findFirstCompatibleMode(modes1);
			s.setFullScreen(dm);
			loadImages();
			movieLoop();
			
		}finally{
			
			s.restoreScreen();
		}
		
	}//Method
	
//PLAY MOVIE
	public void movieLoop(){
		
		Window w = s.getFullScreenWindow();
		
		w.addKeyListener(new TAdapter());
		w.setFocusable(true);
		
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(ingame){
			
			long timePassed = System.currentTimeMillis() - startingTime;
			cumTime += timePassed;
			update();
			
	//DRAW SCREEN
			Graphics2D g = s.getGraphics();
			draw(g);
			
			ArrayList arrows = sprite.getArrows();
			
			for(int i = 0; i < arrows.size(); i++){
				Arrow_Spell m = (Arrow_Spell)arrows.get(i);
	            m.move();
			}//ForEND

			g.dispose();
			for(int i = 0; i < pos.length; i++){
				
				SlimeSprite ss = (SlimeSprite)slimes.get(i);
				ss.update();
			}
			s.update();
			
		}
		s.restoreScreen();
		
	}//Method
	
//DRAW METHOD
	public void draw(Graphics g){
		
		g.drawImage(bg, 0, 0, s.getWidth(), s.getHeight(), null);
		
		for(int i = 0; i < pos.length; i++){
			
			SlimeSprite ss = (SlimeSprite)slimes.get(i);
			
			if(ss.getIfDead() == false){
				g.drawImage(ss.getImage(), (int)ss.getX(), (int)ss.getY(), ss.getWidth()*2, ss.getHeight()*2, null);
			}else if(ss.getIfBlink())
				g.drawImage(slimeDeadImg, (int)ss.getX(), (int)ss.getY(), ss.getWidth()*2, ss.getHeight()*2, null);
			else
				g.drawImage(null, (int)ss.getX(), (int)ss.getY(), ss.getWidth()*2, ss.getHeight()*2, null);
		}
		ArrayList arrows = sprite.getArrows();
		
		for(int i = 0; i < arrows.size(); i++){
			Arrow_Spell m = (Arrow_Spell)arrows.get(i);
			g.drawImage(m.getImage(), (int)m.getX()+5, (int)m.getY()+22, m.getWidth()*2, m.getHeight()*2, null); 
		}//ForEND
		
		g.drawImage(sprite.getImage(), (int)sprite.getX(), (int)sprite.getY(), sprite.getWidth()*2, sprite.getHeight()*2, null);
		
		g.setColor(Color.WHITE);
		g.setFont(medium);
		g.drawImage(HUD, 0, 0, s.getWidth(), s.getHeight(), null);
        g.drawString("X "+(int)sprite.arrows_left, s.getWidth()/2, s.getHeight()-20);
        g.setFont(big);
        g.setColor(Color.RED);
        g.drawString("X", s.getWidth()-920, s.getHeight()-35);
        g.drawString("Z", s.getWidth()-1220, s.getHeight()-35);
		
	}
	
	public void setIngameFalse(){
		this.ingame = false;
	}
	
//MAIN SPRITE POS
	public void update(){
		
		Graphics2D g = s.getGraphics();	
		
	 	if(sprite.getX() < 10){	
			sprite.setX(10);	
		}else if((sprite.getX() + sprite.getWidth()) > s.getWidth()*0.97){
			sprite.setX((s.getWidth()-sprite.getWidth())*0.97);
		}
		
		if(sprite.getY() < -10){	
			sprite.setY(-9);
		}else if((sprite.getY() + sprite.getHeight()) > s.getHeight()*0.95){
			sprite.setY((s.getHeight()-sprite.getHeight())*0.95);
		}
		
		ArrayList arrows = sprite.getArrows();
		
		for(int i = 0; i < arrows.size(); i++){
			
            Arrow_Spell m = (Arrow_Spell)arrows.get(i);
            Rectangle arrow_bounds = m.getBounds();
            
            for(int p = 0; p < slimes.size(); p++){
            	
            	SlimeSprite ss = (SlimeSprite)slimes.get(p);
            	
            	Rectangle slime_bounds = ss.getBounds();
            	
            	if(slime_bounds.intersects(arrow_bounds)){
            	
            		ss.kill();
        	   
        		}
            }
        }//ForEND
		
		for(int p = 0; p < slimes.size(); p++){
        	
        	SlimeSprite ss = (SlimeSprite)slimes.get(p);
        	
        	Rectangle slime_bounds = ss.getBounds();
        
        	if(sprite.getBounds().intersects(slime_bounds)){
            	
        		sprite.kill();
    	   
    		}
		}
		
		for(int i = 0; i < pos.length; i++){
			
			SlimeSprite ss = (SlimeSprite)slimes.get(i);
			ss.update();	
		}
		
		sprite.update();

		
	}//Method
	
	public void actionPerformed(ActionEvent e) {
		
		Graphics2D g = s.getGraphics();
		draw(g);
		g.dispose();
		
	}
	
//INNER ADAPTER CLASS
	
	private class TAdapter extends KeyAdapter {
		
		public TAdapter(){
			setFocusable(true);
		}
		
        public void keyReleased(KeyEvent e){
            sprite.keyReleased(e);
            e.consume();
        }
        
        public void keyPressed(KeyEvent e){
            sprite.keyPressed(e);
            e.consume();
        }
        
        public void keyTyped(KeyEvent e) {
        	e.consume();
        } 
	}
	
}//Class
