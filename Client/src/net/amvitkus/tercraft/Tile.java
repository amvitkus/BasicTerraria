package net.amvitkus.drugcraft;

import java.awt.image.*;

import javax.imageio.ImageIO;
import java.io.*;

public class Tile {

	public static byte tileSize = 20;
	public static byte invCellSize = 25;
	public static byte invLength = 8;
	public static byte invHeight = 4;
	public static byte invCellSpace = 4;
	public static byte invBorderSpace = 4;
	public static byte invItemBorder = 3;
	public static byte maxMobs = 6; //anymore than 126 make it an int

	public static final int[] air = { -1, -1 };
	public static final int[] earth = { 0, 0 };
	public static final int[] grass = { 1, 0 }; // thats the coordinate on
												// sprite sheet, x1, y0
	public static final int[] sand = { 2, 0 };
	public static final int[] solidair = { 3, 0 };
	public static final int[] wood = { 4, 0 };
	public static final int[] tnt = { 5, 0 };
	public static final int[] star = { 6, 0 };
	public static final int[] lava = { 7, 0 };
	public static final int[] stone = { 8, 0};
	public static final int[] leaves = { 9, 0};

	public static int[] mobClone = { 0, 16 };

	public static int[] character = { 0, 18 };

	// bufferedimage for games, prevent flickering
	public static BufferedImage tileset_terrain;
	public static BufferedImage tile_cell;
	public static BufferedImage tile_select;
	public static BufferedImage rain;

	public Tile() {

		try {
			Tile.tileset_terrain = ImageIO.read(new File(
					"res/tileset_terrain.png"));
			Tile.tile_cell = ImageIO.read(new File("res/tile_cell.png"));
			Tile.tile_select = ImageIO.read(new File("res/tile_select.png"));
			Tile.rain = ImageIO.read(new File("res/rain.png"));
		} catch (Exception e) {

		}

	}

}
