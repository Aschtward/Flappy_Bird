package com.omg.world;

import com.omg.entities.Obstruct;
import com.omg.main.Game;

public class ObstructGenerator {
	
	public int time = 0;
	public int maxTime = 150;

	public void tick() {
		time++;
		if(time == maxTime) {
			//new obstruct
			time = 0;
			int altura1 = Game.rand.nextInt(400);
			int spacebetwen = Game.rand.nextInt(50) + 150;
			int altura2 = Game.HEIGHT - (altura1 + spacebetwen);
			Obstruct obs1 = new Obstruct(Game.WIDTH,0,70,altura1,null);
			Obstruct obs2 = new Obstruct(Game.WIDTH,Game.HEIGHT - altura2,70,altura2,null);
			Game.entities.add(obs1);
			Game.entities.add(obs2);
		}
	}

}
