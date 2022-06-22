package com.omg.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

import com.omg.entities.Buildings;
import com.omg.entities.Entity;
import com.omg.entities.Player;
import com.omg.entities.Star;
import com.omg.graph.Spritesheet;
import com.omg.graph.UI;
import com.omg.world.BackGround;
import com.omg.world.ObstructGenerator;
import com.omg.world.World;
import com.omg.world.StarGenerator;

public class Game extends Canvas implements Runnable, KeyListener{
	
	
	private static final long serialVersionUID = 1227254042505466843L;
	
	///Definindo parametros para janela
	public static JFrame frame;
	public static int WIDTH = 400;
	public static int HEIGHT = 650;
	public static int SCALE = 1;
	///Fim parametros para janela
	
	public static BufferedImage image;
	public static List<Entity> entities;
	public static List<Entity> buildings;
	public static List<Star> star;
	private StarGenerator starGen;
	public static Spritesheet spritesheet;
	public static Player player;
	private BackGround bg;
	public static Random rand;
	public static ObstructGenerator oB;
	public static UI ui;
	public static World world;
	private Thread thread;
	private boolean isRunning = true;
	public boolean restartGame = false;
	public boolean saveGame = false;
	public static double score = 0;
	public int pscore = 0;

	public Game() {
		
		///Criação da Janela
		setPreferredSize(new Dimension(SCALE*WIDTH,SCALE*HEIGHT));
		inicia_frame();
		///
		addKeyListener(this);
		entities = new ArrayList<Entity>();
		buildings = new ArrayList<Entity>();
		star = new ArrayList<Star>();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		bg = new BackGround();
		oB = new ObstructGenerator();
		starGen = new StarGenerator();
		spritesheet = new Spritesheet("/text.png");
		player = new Player(Game.WIDTH/2-100,Game.HEIGHT/2,32,32, spritesheet.getSprite(32, 0, 16, 16));
		entities.add(player);
		rand = new Random();
		world = new World("/map1.png");
		ui = new UI();
		
	}
	
	public void inicia_frame() {///Inicializa janela
		frame = new JFrame("FlappyBird");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
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
		
	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}
		
	public void tick() {
		oB.tick();
		starGen.tick();
		bg.tick();
		player.tick();
		for(int i = 0; i<star.size();i++) {
			star.get(i).tick();
		}
		for(int i = 0; i<entities.size();i++) {
			entities.get(i).tick();
		}
		for(int i = 0; i<buildings.size();i++) {
			buildings.get(i).tick();
		}
		if(score == pscore+1) {
			Sound.pointSound.play();
			pscore++;
		}
		
	}
	
	public void  render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0xFF2897FF));
		g.fillRect(0, 0, WIDTH, HEIGHT);	
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		for(int i = 0; i<star.size();i++) {
			star.get(i).render(g);
		}
		for(Entity e:buildings) {
			e.render(g);
		}
		for(Entity e:entities) {
			e.render(g);
		}
		ui.render(g);
		bs.show();
	}
	
	public void run() {
		
		//Implementação game looping
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		int frames = 0;
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+=(now - lastTime)/ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				frames = 0;
				timer += 1000;
			}
		}//Fim implementação game looping
		
		stop();
}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			player.clicked = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
