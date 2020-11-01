package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public double x, y;
	public int width, height;
	public boolean right, left;
	
	
	
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
				
		
	}

	public void tick() {
		
		x+= (Game.ball.x - x - 20) * 0.03;
		if(right) {
			x++;
		}else if (left) {
			x--;
		}
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE); //Escolhe a cor
		g.fillRect((int)x, (int)y, width, height);//cria retangulo (x, y, w, h)
	}
}
