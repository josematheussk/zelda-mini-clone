package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	public static int WIDTH = 640, HEIGHT = 480;
	public static int SCALE = 3;
	public Player player;
	
	public List<Enemy> enemies = new ArrayList<Enemy>();
	
	public World world;
	
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		world = new World();
		
		player = new Player(32,32);	
		
		enemies.add(new Enemy(32,32));
		
	}
	
	public void tick() {
		player.tick();
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0, 135, 13));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		new Spritesheet();
		
		player.render(g);
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g);
		}
		
		world.render(g);
		
		bs.show();
		
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Mini Zelda");
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		new Thread(game).start();
		
	}
	
	@Override
	public void run() {
		
		 while(true) {
			 tick();
			 render();
			 try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right=true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left=true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up=true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down=true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.shoot = true;
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right=false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left=false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up=false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down=false;
		}
		
	}

}
