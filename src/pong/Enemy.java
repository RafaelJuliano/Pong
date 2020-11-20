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
		
		if (Game.ball.dy > 0) {
			x+= ((Game.WIDTH/2)-(width/2) - x)* 0.04;
		}else {
			x+= (Game.ball.x - x - 20) * 0.04;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE); //Escolhe a cor
		g.fillRect((int)x, (int)y, width, height);//cria retangulo (x, y, w, h)
	}
}
