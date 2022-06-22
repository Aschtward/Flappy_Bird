package com.omg.world;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.omg.entities.*;
import com.omg.graph.Spritesheet;
import com.omg.graph.UI;
import com.omg.main.Game;

public class World {
	
	public static int width, height;

	public World(String path) {
	}
	
	public static void worldRestart(String path) {
		Game.entities.clear();
		Game.buildings.clear();
		Game.star.clear();
		Game.image = new BufferedImage(Game.WIDTH,Game.HEIGHT,BufferedImage.TYPE_INT_RGB);
		Game.spritesheet = new Spritesheet("/text.png");
		Game.player = new Player(Game.WIDTH/2-100,Game.HEIGHT/2,32,32, Game.spritesheet.getSprite(32, 0, 16, 16));
		Game.entities.add(Game.player);
		Game.score = 0;
		Game.world = new World(path);
		Game.ui = new UI();
	}
	
	
	public void render(Graphics g) {
	}

}
