package net.amvitkus.drugcraft;

import java.util.*;

//Spawns mobs
public class Spawner implements Runnable {
	public boolean isRunning = true;

	public Spawner() {
		new Thread(this).start();
	}

	public void spawnMob(Mob mob) {
		Component.mob.add(mob);
	}

	public void run() {
		while (isRunning) {

			if (Component.mob.toArray().length < Tile.maxMobs) {
				spawnMob(new Clone(new Random().nextInt((Level.worldW - 2)
						* Tile.tileSize)
						+ Tile.tileSize + 30, 50, Tile.tileSize,
						Tile.tileSize * 2));
			}

			try {
				Thread.sleep(new Random().nextInt(8000) + 5000);
			} catch (Exception e) {
			}

		}
	}
}
