package net.amvitkus.drugcraft;

//checking if blocks fall
public class Checker {

	public Checker() {

	}

	public static void tick() {

		for (int y = 0; y < Level.block.length; y++) {
			for (int x = 0; x < Level.block[0].length; x++) {
				// check if sand falls
				if (Level.block[x][y].id == Tile.sand
						&& Level.block[x][y + 1].id == Tile.air) {
					Level.block[x][y].id = Tile.air;
					Level.block[x][y + 1].id = Tile.sand;
				}
				// check if lava can spread
				if (Level.block[x][y].id == Tile.lava
						&& Level.block[x][y + 1].id == Tile.air) {
					Level.block[x][y + 1].id = Tile.lava;
				} else if (Level.block[x][y].id == Tile.lava
						&& Level.block[x - 1][y].id == Tile.air) {
					Level.block[x - 1][y].id = Tile.lava;
				} else if (Level.block[x][y].id == Tile.lava
						&& Level.block[x + 1][y].id == Tile.air) {
					Level.block[x + 1][y].id = Tile.lava;
				}
				
				//Checks to see if sand can fall into lava
				if(Level.block[x][y].id == Tile.sand && Level.block[x][y + 1].id == Tile.lava){
					Level.block[x][y + 1].id = Tile.sand;
					Level.block[x][y].id = Tile.air;
				}

			}
		}
	}
}
