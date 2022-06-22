package com.omg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.omg.main.Game;

public class Buildings extends Entity {

	public Buildings(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		setX(getX()-2);
		if(getX()+Game.WIDTH <= 0) {
			Game.entities.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0xFF006EFF));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(0xFFFFC300));
		for(int i = 0; i < 10;i++) {
			g.fillRect(getX(), getY()+(10*i)+2, 5, 5);
			g.fillRect(getX()+15, getY()+(10*i)+2, 5, 5);
			g.fillRect(getX()+30, getY()+(10*i)+2, 5, 5);
			g.fillRect(getX()+45, getY()+(10*i)+2, 5, 5);
		}
		g.setColor(new Color(0xFF004FB7));
		g.fillRect(getX()+40, getY(), getWidth()-40, getHeight());
		g.fillRect(getX(), getY()+160, getWidth()-10, getHeight()-120);
	}

}
