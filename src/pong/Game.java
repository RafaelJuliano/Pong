package pong;

/*Teste de comit 2 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable, KeyListener{	
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Thread thread;
	public boolean isRunning;
	


	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));		
		this.addKeyListener(this);
		player = new Player(100, HEIGHT-5);
		enemy = new Enemy(100, 0);
		ball = new Ball(100, 5);
		startFrame();
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();		
		game.start();
	}
	
	public void startFrame(){
		JFrame frame = new JFrame("Pong"); //Objeto Java para criar janela
		frame.setResizable(false); // Bloqueia ajuste de janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fechar a janela para o processo		
		frame.add(this); //busca construtor
		frame.pack();	
		frame.setLocationRelativeTo(null); //Deixa janela no centro(Após criação para não seta pela ponta
		frame.setVisible(true); // Deixa janela visivel		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();		
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy(); //metodo renderiza graficos
		if(bs == null) { // se buffer não existe 
			this.createBufferStrategy(3); //crie um buffer = 3
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		g = layer.getGraphics();
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.WHITE);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);		
				
		bs.show();		
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double frameRate = 60.0;
		double nanosPerFrame = 1000000000 / frameRate;
		while(isRunning) {
			long now = System.nanoTime();
			if ((now - lastTime)/nanosPerFrame>=1) {
				lastTime = now;
				tick();
				render();
			}

		}
		
		stop();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		player.right |= code == KeyEvent.VK_RIGHT;
		player.left |= code == KeyEvent.VK_LEFT;				
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		player.right = code == KeyEvent.VK_RIGHT? false:player.right;
		player.left = code == KeyEvent.VK_LEFT? false:player.left;				
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

	
}
