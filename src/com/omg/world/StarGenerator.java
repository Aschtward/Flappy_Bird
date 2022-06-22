package com.omg.world;

import com.omg.entities.Star;
import com.omg.main.Game;

public class StarGenerator {
	
	public void tick() {
		if(Game.rand.nextInt(100) > 90) {
			int altura = Game.rand.nextInt(300) + 40;
			Star star = new Star(Game.WIDTH,altura,2,2,null);
			Game.star.add(star);
		}
	}

}
