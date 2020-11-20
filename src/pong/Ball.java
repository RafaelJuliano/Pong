package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x, y;
	public int width, height;
	
	public double dx, dy;
	public double speed = 2.3;
	
	private boolean ps, es;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		this.ps = false;
		this.es = false;
		
		int angle = new Random().nextInt(75)+45;		
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}

	public void tick() {	
		if(x+(dx*speed) + width >= Game.WIDTH) {
			dx*= -1;
		}else if(x+(dx*speed) <0) {
			dx*= -1;
		}
		
		if(y >= Game.HEIGHT) {			
			if (es) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Game.enemy.upScore();
				Game.ball = new Ball(100, 5);
			}
			es =true;			
			return;
		}else if(y < -5) {
			if (ps) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Game.player.upScore();
				Game.ball = new Ball(100, 5);
			}
			ps = true;
			
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		
		Rectangle boundsPlayer = new Rectangle((int)Game.player.x, (int)Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(75)+45;		
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy > 0)
			dy*=-1;
		}else if(bounds.intersects(boundsEnemy)){
			int angle = new Random().nextInt(75)+45;		
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy<0)
			dy*=-1;
		}
		
		x+=dx*speed;
		y+=dy*speed;		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE); //Escolhe a cor
		g.fillRect((int)x, (int)y, width, height);//cria retangulo (x, y, w, h)
	}
}
