package com.omg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.omg.main.Game;

public class Star extends Entity {

	public Star(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		setX(getX()-2);
		if(getX()+Game.WIDTH <= 0) {
			Game.star.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(0xFFC9D8FF));
		g.fillRect(getX(), getY(), getWidth()+1, getHeight()+1);
	}
	

}
