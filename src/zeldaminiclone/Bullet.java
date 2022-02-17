package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle{
	
	public int dir = 2;
	public int speed = 8;
	public int frames = 0;
	
	public Bullet(int x, int y, int dir) {
		super(x+16-4,y+16-4,8,8);
		this.dir = dir;
	}
	
	public void tick() {
		x+=speed*dir;
		frames++;
		if(frames == 50) {
			Player.bullets.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x,y,width,height);
	}
	
}
