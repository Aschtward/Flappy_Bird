package com.omg.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.omg.main.Game;
import com.omg.world.World;

public class Player extends Entity{
	
	//Variáveis de render
	private BufferedImage standart;
	
	public boolean clicked = false,isflying = false;
	public float backupHeight = this.getY();
	public int instace = 0;
	public int timefall = 0;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		//Carregando sprites do spritesheet do game
		standart = Game.spritesheet.getSprite(0, 0, 16, 16);
	}
	
	public void tick() {
		if(clicked) {
			instace++;
			if(instace == 1) {
				backupHeight = getY();
			}
			if(getY() > 0)
				setY(getY()-6);
			if(getY() < 0) {
				setY(getY()+4);
				clicked = false;
				instace = 0;
			}
			if(getY() < backupHeight-100 || getY() == 32) {
				clicked = false;
				instace = 0;
				timefall = 0;
			}
		}else if(timefall < 15){
			isflying = true;
			timefall++;
		}else {
			isflying = false;
			setY(getY()+4);
		}
		if(getY() > Game.HEIGHT) {
			//Game Over
			World.worldRestart(null);        
		}
	}
	
	public void render(Graphics g) {	
		Graphics2D g1 = (Graphics2D) g;
		if(isflying) {
			g.drawImage(standart, this.getX(),this.getY(),50,50,null);
		}else if(clicked || getY() < backupHeight-100) {
			g1.rotate(Math.toRadians(-20),this.getX()+getWidth()/2,this.getY()+getHeight()/2);
			g1.drawImage(standart, getX(), getY(), 50,50,null);
			g1.rotate(Math.toRadians(20),this.getX()+getWidth()/2,this.getY()+getHeight()/2);
		}else if(!clicked) {
			g1.rotate(Math.toRadians(20),this.getX()+getWidth()/2,this.getY()+getHeight()/2);
			g1.drawImage(standart, getX(), getY(), 50,50,null);
			g1.rotate(Math.toRadians(-20),this.getX()+getWidth()/2,this.getY()+getHeight()/2);
		}
	}
}
