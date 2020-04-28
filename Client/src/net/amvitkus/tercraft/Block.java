package net.amvitkus.drugcraft;

import java.awt.*;

public class Block extends Rectangle {

	private static final long serialVersionUID = 1L;

	public int[] id = { -1, -1 };// indicate types of blocks, dirt and sand, etc

	// Constructor
	public Block(Rectangle size, int[] id) {

		setBounds(size);
		this.id = id;

	}

	public void render(Graphics g) {// takes the Graphics g object as usual
		if (id != Tile.air) {
			g.drawImage(Tile.tileset_terrain, x - (int) Component.sX, y
					- (int) Component.sY, x + width - (int) Component.sX, y
					+ height - (int) Component.sY, id[0] * Tile.tileSize, id[1]
					* Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize,
					id[1] * Tile.tileSize + Tile.tileSize, null);
		}
	}
}
