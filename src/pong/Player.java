package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Player {
	
	public boolean right, left;
	public double x, y, speed;
	public int width, height;
	private int score;
	
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
		this.score = 0;
		this.speed = 1.5;
	}
	
	public void upScore() {
		this.score += 1;
	}
	
	public void tick() {
		if(right) {
			x+=speed;
		}else if (left) {
			x-=speed;
		}
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}
	}
	
	public void render(Graphics g){ //Graphics manipula imagens
		g.setColor(Color.WHITE); //Escolhe a cor
		g.fillRect((int)x, (int)y, width, height);//cria retangulo (x, y, w, h)
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString(Integer.toString(score), 5, 80);
		
	}
}
