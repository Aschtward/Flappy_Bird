package com.omg.world;

import com.omg.entities.Buildings;
import com.omg.main.Game;

public class BackGround {
	
	private int timer = 0;
	
	public void tick() {
		if(timer == 30) {
			int buildingNumber = Game.rand.nextInt(4);
			timer = 0;
			if(buildingNumber <3) {
				int altura1 = Game.rand.nextInt(100)+100;
				Buildings build = new Buildings(Game.WIDTH,Game.HEIGHT - altura1,60,altura1,null);
				Game.buildings.add(build);
			}
		}
		timer++;
	}
}
