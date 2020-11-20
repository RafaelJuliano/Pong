package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Enemy {
	
	public double x, y;
	public int width, height;
	public boolean right, left;
	private int score;
	
	
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
		this.score = 0;
		
	}
	
	public void upScore() {
		this.score += 1;
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
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString(Integer.toString(score), 5, 40);
	}
}
