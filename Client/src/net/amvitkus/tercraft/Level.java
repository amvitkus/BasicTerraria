package net.amvitkus.drugcraft;

import java.awt.*;
import java.util.*;

public class Level {

	public static int worldW = 50, worldH = 50;// The levels width and height

	// Multidimensional array, will hold all of the blocks
	public static Block[][] block = new Block[worldW][worldH];

	public static int blockBreakTimer = 0;
	public static int blockBreakTime = 50;

	// Constructor
	public Level() {
		for (int x = 0; x < block.length; x++) { // levelforex.png
			for (int y = 0; y < block[0].length; y++) {
				block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y
						* Tile.tileSize, Tile.tileSize, Tile.tileSize),
						Tile.air);

			}
		}

		generateLevel();

	}

	public void generateLevel() {
		// Generating mountains, hills, earth, etc randomly.
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if (y > worldH >> 4) {
					if (new Random().nextInt(100) > 20) {
						try {
							if (block[x - 1][y - 1].id == Tile.earth) {
								block[x][y].id = Tile.earth;
							}
						} catch (Exception e) {
						}
					}

					if (new Random().nextInt(100) > 30) {
						try {
							if (block[x + 1][y - 1].id == Tile.earth) {
								block[x][y].id = Tile.earth;
							}
						} catch (Exception e) {
						}
					}

					try {
						if (block[x][y - 1].id == Tile.earth) {
							block[x][y].id = Tile.earth;
						}
					} catch (Exception e) {
					}

					if (new Random().nextInt(100) < 2) {
						block[x][y].id = Tile.earth;
					}
				}
			}
		}

		// Placing out stone blocks
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				if (y > worldH >> 3) {
					if (new Random().nextInt(100) > 20) {
						try {
							if (block[x - 1][y - 1].id == Tile.stone) {
								block[x][y].id = Tile.stone;
							}
						} catch (Exception e) {

						}
					}
					if (new Random().nextInt(100) > 30) {
						try {
							if (block[x + 1][y - 1].id == Tile.stone) {
								block[x][y].id = Tile.stone;
							}
						} catch (Exception e) {

						}
					}
					try {
						if (block[x][y - 1].id == Tile.stone) {
							block[x][y].id = Tile.stone;
						}
					} catch (Exception e) {

					}

					if (new Random().nextInt(100) < 2) {
						block[x][y].id = Tile.stone;
					}
				}
			}
		}

		// Placing out trees
		for (int y = 0; y < block.length; y++) { // levelforex.png
			for (int x = 0; x < block[0].length; x++) {
				try {
					if (block[x][y + 1].id == Tile.earth
							&& block[x][y].id == Tile.air) {
						if (new Random().nextInt(100) <= 10) {
							for (int i = 0; i < new Random().nextInt(5) + 4; i++) {
								block[x][y - i].id = Tile.wood;
							}
						}
					}
				} catch (Exception e) {
				}
			}
		}

		// Placing out leaves on top of trees
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				try {
					if (block[x][y + 1].id == Tile.wood
							&& block[x][y].id == Tile.air) {
						block[x][y].id = Tile.leaves;
					}
				} catch (Exception e) {

				}
			}
		}

		// Placing out more leaves
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				try {
					if (block[x + 1][y].id == Tile.leaves
							&& block[x][y].id == Tile.air
							&& block[x][y + 1].id == Tile.air) {
						block[x][y].id = Tile.leaves;
					}
				} catch (Exception e) {

				}
			}
		}

		// Placing out more leaves on top of leaves
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				try {
					if (block[x - 1][y].id == Tile.leaves
							&& block[x][y].id == Tile.air
							&& block[x][y + 1].id == Tile.air
							&& block[x][y - 1].id == Tile.air
							&& block[x - 3][y + 1].id == Tile.air) {
						block[x][y].id = Tile.leaves;
					}
				} catch (Exception e) {

				}
			}
		}

		// Placing out more leaves on top of leaves
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				try {
					if (block[x][y + 1].id == Tile.leaves
							&& block[x][y].id == Tile.air
							&& block[x][y + 1].id == Tile.leaves) {
						block[x][y].id = Tile.leaves;
					}
				} catch (Exception e) {

				}
			}
		}

		// Placing out more leaves on top of leaves
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				try {
					if (block[x][y + 1].id == Tile.leaves
							&& block[x][y].id == Tile.air
							&& block[x][y + 1].id == Tile.leaves) {
						block[x][y].id = Tile.leaves;
					}
				} catch (Exception e) {

				}
			}
		}

		// Placing grass blocks
		for (int y = 0; y < block.length; y++) { // levelforex.png
			for (int x = 0; x < block[0].length; x++) {
				if (block[x][y].id == Tile.earth
						&& block[x][y - 1].id == Tile.air) {
					block[x][y].id = Tile.grass;
				}
			}
		}

		// Placing solid air around the whole level, so you cant fall off or
		// escape! TNT still a problem
		for (int y = 0; y < block.length; y++) { // levelforex.png
			for (int x = 0; x < block[0].length; x++) {
				if (x == 0 || y == 0 || x == block[0].length - 1
						|| y == block[0].length - 1) {
					block[x][y].id = Tile.solidair;
				}
			}
		}

	}

	// Characters interaction with level/world, mouse interaction
	public void building(int camX, int camY, int renW, int renH) {
		if (Component.isMouseLeft || Component.isMouseRight) {
			for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize)
					+ renW; x++) { // levelforex.png
				for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize)
						+ renH; y++) {// get y coordinate of start coordinate
					if (x >= 0 && y >= 0 && x < worldW && y < worldH) {
						if (block[x][y].contains(new Point(
								(Component.mse.x / Component.pixelSize)
										+ (int) Component.sX,
								(Component.mse.y / Component.pixelSize)
										+ (int) Component.sY))) {
							int sid[] = Inventory.invBar[Inventory.selected].id;// selectedblock
																				// =
																				// sid
							// Cant mine solid air blocks! Left mouse
							// interaction
							if (Component.isMouseLeft) {
								if (block[x][y].id != Tile.solidair) {
									if (block[x][y].id != Tile.air) {
										// TNT blowing up in a square
										if (block[x][y].id == Tile.tnt) {
											try {

												// This code needs fixing
												block[x][y].id = Tile.air;
												if (block[x + 1][y].id != Tile.solidair) {
													block[x + 1][y].id = Tile.air;
												}
												if (block[x - 1][y].id != Tile.solidair) {
													block[x - 1][y].id = Tile.air;
												}
												if (block[x][y - 1].id != Tile.solidair) {
													block[x][y + 1].id = Tile.air;
												}
												if (block[x][y - 1].id != Tile.solidair) {
													block[x][y - 1].id = Tile.air;
												}
												if (block[x + 1][y + 1].id != Tile.solidair) {
													block[x + 1][y + 1].id = Tile.air;
												}
												if (block[x - 1][y + 1].id != Tile.air) {
													block[x - 1][y + 1].id = Tile.air;
												}
												if (block[x + 1][y - 1].id != Tile.air) {
													block[x + 1][y - 1].id = Tile.air;
												}
												if (block[x - 1][y - 1].id != Tile.air) {
													block[x - 1][y - 1].id = Tile.air;
												}

											} catch (Exception e) {
											}

										} else if (blockBreakTime <= blockBreakTimer) {
											//Sound.blockBreak.play(); //play sound
											block[x][y].id = Tile.air;
											blockBreakTimer = 0;
										} else {
											blockBreakTimer++;
										}

									}
								}
								// Right mouse click interaction
							} else if (Component.isMouseRight) {
								if (block[x][y].id == Tile.air || block[x][y].id == Tile.lava) {
									if (sid == Tile.earth || sid == Tile.grass
											|| sid == Tile.sand
											|| sid == Tile.wood
											|| sid == Tile.tnt
											|| sid == Tile.lava
											|| sid == Tile.stone
											|| sid == Tile.leaves) {

										block[x][y].id = sid;

										if (block[x][y + 1].id == Tile.grass) {
											block[x][y + 1].id = Tile.earth;
										}
									}
								}
							}

							break;
						}
					}
				}
			}
		}
	}

	// Tick, allow building if inv isnt open
	public void tick(int camX, int camY, int renW, int renH) {
		if (!Inventory.isOpen) {
			building(camX, camY, renW, renH);
		}
	}

	// Rendering blocks
	public void render(Graphics g, int camX, int camY, int renW, int renH) {// renderwidth
		for (int x = (camX / Tile.tileSize); x < (camX / Tile.tileSize) + renW; x++) { // levelforex.png
			for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize)
					+ renH; y++) {// Get y coordinate of start coordinate
				if (x >= 0 && y >= 0 && x < worldW && y < worldH) {
					block[x][y].render(g);// Draw all blocks on screen
					
					if (block[x][y].id != Tile.solidair){
						g.setColor(new Color(0, 0, 0, Sky.light));
						g.fillRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width, block[x][y].height);
					}

					if (block[x][y].id != Tile.air
							&& block[x][y].id != Tile.solidair
							&& !Inventory.isOpen) {
						// If block contains mouse cursor on it
						if (block[x][y].contains(new Point(
								(Component.mse.x / Component.pixelSize)
										+ (int) Component.sX,
								(Component.mse.y / Component.pixelSize)
										+ (int) Component.sY))) {
							g.setColor(new Color(255, 255, 255, 60));
							g.fillRect(block[x][y].x - camX, block[x][y].y
									- camY, block[x][y].width,
									block[x][y].height);// camX and -1 to get
														// selection to show
						}
					}
				}
			}
		}
	}
}
