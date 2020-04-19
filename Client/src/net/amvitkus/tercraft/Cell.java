package net.amvitkus.drugcraft;

import java.awt.*;

//Class for cells in inventory
public class Cell extends Rectangle {

	private static final long serialVersionUID = 1L;

	public int[] id = { 0, 0 };

	// Rectangle called cell
	public Cell(Rectangle size, int[] id) {
		setBounds(size);// x, y, height for the cell
		this.id = id;
	}

	//Render inventory selection
	public void render(Graphics g, boolean isSelected) {
		if (Inventory.isOpen
				&& contains(new Point(Component.mse.x / Component.pixelSize,
						Component.mse.y / Component.pixelSize))) {
			g.setColor(new Color(255, 255, 255, 140));
			g.fillRect(x, y, width, height);
		}

		g.drawImage(Tile.tile_cell, x, y, width, height, null);// cursor

		if (id != Tile.air) {
			g.drawImage(Tile.tileset_terrain, x + Tile.invItemBorder, y
					+ Tile.invItemBorder, x - Tile.invItemBorder + width, y
					- Tile.invItemBorder + height, id[0] * Tile.tileSize, id[1]
					* Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize,
					id[1] * Tile.tileSize + Tile.tileSize, null);
		}

		if (isSelected && !Inventory.isOpen) {
			g.drawImage(Tile.tile_select, x - 1, y - 1, width + 2, height + 2,
					null);// +2 makes it a little bigger
		}
	}

}

//These two for loops scan through level. Used throughout
			//for (int x = 0; x < Level.block.length; x++) {
				// System.out.println("Called");
				//for (int y = 0; y < Level.block[0].length; y++) {
