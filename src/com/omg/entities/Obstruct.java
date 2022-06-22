package com.omg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.omg.main.Game;
import com.omg.main.Sound;
import com.omg.world.World;

public class Obstruct extends Entity {
	
	private int sideCane;

	public Obstruct(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	private boolean passed = false;
	
	public void tick() {
		setX(getX()-2);
		if(getX()+Game.WIDTH <= 0) {
			Game.entities.remove(this);
			return;
		}
		if(getX()< Game.player.getX() && !passed) {
			Game.score+= 0.5;
			passed = true;
		}
		if(collided()) {
			//Game over
			World.worldRestart(null);
		}
		if(getY() == 0) {
			sideCane = 1;
		}else {
			sideCane = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0xFF02B333));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(0xFF6EB280));
		g.fillRect(getX()+1, getY()+1, getWidth()-55  , getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(getX(),getY(),getWidth(),getHeight());
		if(sideCane == 0) {
			g.setColor(new Color(0xFF02B333));
			g.fillRect(getX()-10,getY(),getWidth()+20,30);
			g.setColor(new Color(0xFF6EB280));
			g.fillRect(getX()-10, getY(), getWidth()+20, 10);
			g.setColor(Color.BLACK);
			g.drawRect(getX()-10,getY(),getWidth()+20,30);
		}else {
			g.setColor(new Color(0xFF02B333));
			g.fillRect(getX()-10,getY()+getHeight(),getWidth()+20,30);
			g.setColor(new Color(0xFF6EB280));
			g.fillRect(getX()-10, getY()+getHeight()+20, getWidth()+20, 10);
			g.setColor(Color.BLACK);
			g.drawRect(getX()-10,getY()+getHeight(),getWidth()+20,30);
		}
		
	}

}
