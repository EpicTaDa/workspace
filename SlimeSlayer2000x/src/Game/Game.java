package Game;

import java.applet.Applet;
import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Anim.Arrow_Spell;
import Anim.Attack;
import Anim.WalkDown;
import Anim.WalkLeft;
import Anim.WalkRight;
import Anim.WalkUp;
import Game.Sprite;
import Game.SlimeSprite;
import Inherit.ScreenManager;
import java.awt.event.KeyAdapter;

import javax.swing.SwingUtilities;


public class Game extends Applet implements Runnable, ActionListener {

	private Image bg, slimeImg, slimeDeadImg, HUD;
	private Anim.WalkUp wUp;
	private Anim.WalkLeft wLeft;
	private WalkRight wRight;
	private WalkDown wDown;
	private Anim.Attack a;
	private Sprite sprite;
	private Font big;
	private Font medium;
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
	private boolean ingame;
	//private ScreenManager s;
	
	private static final DisplayMode modes1[] = {
		new DisplayMode(800,600,32,59),
		new DisplayMode(800,600,32,60),
		new DisplayMode(960,540,32,59),
		new DisplayMode(960,540,32,60)
	};

	public void init(){
		
		setSize(800,640);
		setFocusable(true);
		setVisible(true);
		setBackground(Color.black);
		Frame f = (Frame) this.getParent().getParent();
		f.setTitle("Zeldur");
		
		bg = new ImageIcon(".\\src\\Images\\BG.jpg").getImage();
		bg = getImage(getDatabase(), ".\\src\\Images\\BG.jpg");
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
        
	}

	
	public void initSlimes(){
		
		slimes = new ArrayList();
		
		for(int i = 0; i < pos.length; i++){
			
			slimes.add(new SlimeSprite(slimeImg, pos[i][0], pos[i][1]));
			
		}
		
	}
	
	public void start(){
		
		//s = new ScreenManager();
		
		//DisplayMode dm = s.findFirstCompatibleMode(modes1);
		//s.setFullScreen(dm);		
		
		//ingame = true;
		
		try{
			movieLoop();
		}finally{
			destroy();
		}
	}
	
	public void run() {}
	
	public void paint(Graphics g){
		
		
		super.paint(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		System.out.println("BY THE GODS");
		g.drawImage(bg, 0, 0, 500, 500, this);
		
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
		
		g.drawImage(sprite.getImage(), (int)sprite.getX(), (int)sprite.getY(), 
				    sprite.getWidth()*2, sprite.getHeight()*2, null);
		
		g.setColor(Color.WHITE);
		g.setFont(medium);
		g.drawImage(HUD, 0, 0, 500, 500, null);
	    g.drawString("X "+(int)sprite.arrows_left, 500/2, 500-20);
	    g.setFont(big);
	    g.setColor(Color.RED);
	    g.drawString("X", 300, 500-35);
	    g.drawString("Z", 200, 500-35);
		
	}
	
public void movieLoop(){
		
		
		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(ingame){
			
			long timePassed = System.currentTimeMillis() - startingTime;
			cumTime += timePassed;
			update();
			
	//DRAW SCREEN
			repaint();
			
			ArrayList arrows = sprite.getArrows();
			
			for(int i = 0; i < arrows.size(); i++){
				Arrow_Spell m = (Arrow_Spell)arrows.get(i);
	            m.move();
			}//ForEND

			for(int i = 0; i < pos.length; i++){
				
				SlimeSprite ss = (SlimeSprite)slimes.get(i);
				ss.update();
			}
			//s.update();
			
		}
		//s.restoreScreen();
		
	}//Method

	public void update(){
	
 	if(sprite.getX() < 10){	
		sprite.setX(10);	
	}else if((sprite.getX() + sprite.getWidth()) > 500*0.97){
		sprite.setX((500-sprite.getWidth())*0.97);
	}
	
	if(sprite.getY() < -10){	
		sprite.setY(-9);
	}else if((sprite.getY() + sprite.getHeight()) > 500*0.95){
		sprite.setY((500-sprite.getHeight())*0.95);
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
	
	public void stop(){	
	}

 	public void Destroy(){
 	}
	
	public void actionPerformed(ActionEvent e) {	
		repaint();
	}
	
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

}
