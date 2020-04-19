package net.amvitkus.drugcraft;

import java.awt.*;

public class Character extends DoubleRectangle {

	public double fallingSpeed = 1;
	public double movementSpeed = 0.6;
	public double jumpingSpeed = 1;

	public byte jumpingHeight = 50;
	public byte jumpingCount = 0;
	public byte animation = 0;
	public byte animationFrame = 0, animationTime = 30;

	private byte timer = 0;
	private byte timerTotal = 5;

	public static byte health = 100;
	public static byte fullHealth = 100;
	public static byte healthBarWidth = health;
	public static byte healthBarHeight = 10;
	public static byte redHealthBarWidth = healthBarWidth;
	public static byte fullHealthBarWidth = healthBarWidth;

	private int amount = 1;

	public static boolean isDead = false;
	public boolean isJumping = false;
	public static boolean isHurting = false;

	public Character(int width, int height) {
		setBounds((Component.pixel.width >> 1) - (width >> 1),
				(Component.pixel.height >> 1) - (height >> 1), width, height);
	}

	public void tick() {
		if (timer == timerTotal) {
			timer = 0;

			if (isCollidingWithLava(new Point((int) (x), (int) y), new Point(
					(int) (x), (int) y))) {
				hurt(1);
				isHurting = true;
			} else {
				isHurting = false;
			}
		} else {
			timer++;
		}

		if (!isJumping
				&& !isCollidingWithBlock(new Point((int) x + 2,
						(int) (y + height)), new Point((int) (x + width - 2),
						(int) (y + height)))) {
			y += fallingSpeed;
			Component.sY += fallingSpeed;

		} else {

			if (Component.isJumping && !Inventory.isOpen) {
				isJumping = true;

			}
		}

		if (Component.isMoving && !Inventory.isOpen) {
			boolean canMove = false;

			if (Component.dir == movementSpeed) {

				canMove = isCollidingWithBlock(new Point((int) (x + width),
						(int) y), new Point((int) (x + width),
						(int) (y + (height - 2))));

			} else if (Component.dir == -movementSpeed) {

				canMove = isCollidingWithBlock(new Point((int) x - 1, (int) y),
						new Point((int) x - 1, (int) (y + (height - 2))));

			}
			// here for af to hump wall
			if (animationFrame >= animationTime) {
				if (animation > 1) {
					animation = 1;
				} else {
					animation += 1;
				}

				animationFrame = 0;

			} else {
				animationFrame += 1;
			}

			if (!canMove) {

				x += Component.dir;
				// Move the screen with character
				Component.sX += Component.dir;
			}
		} else {
			animation = 0;
		}

		if (isJumping) {
			if (!isCollidingWithBlock(new Point((int) (x + 2), (int) y),
					new Point((int) (x + width - 2), (int) y))) {
				if (jumpingCount >= jumpingHeight) {
					isJumping = false;
					jumpingCount = 0;
				} else {
					y -= jumpingSpeed;
					Component.sY -= jumpingSpeed;

					jumpingCount += 1;
				}
			} else {
				isJumping = false;
				jumpingCount = 0;
			}
		}

	}

	public boolean isCollidingWithBlock(Point pt1, Point pt2) { // Points are
																// points on a
																// screen,
																// coordinates

		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x
				/ Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y
					/ Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Component.level.block.length
						&& y < Component.level.block[0].length)// Check if going
																// outside of
																// level
					if (Component.level.block[x][y].id != Tile.air
							&& Component.level.block[x][y].id != Tile.star
							&& Component.level.block[x][y].id != Tile.lava) {
						if (Component.level.block[x][y].contains(pt1)
								|| Component.level.block[x][y].contains(pt2)) {
							return true;
						}
					}
			}
		}
		return false;
	}

	public boolean isCollidingWithLava(Point pt1, Point pt2) {
		for (int x = (int) (this.x / Tile.tileSize); x < (int) (this.x
				/ Tile.tileSize + 3); x++) {
			for (int y = (int) (this.y / Tile.tileSize); y < (int) (this.y
					/ Tile.tileSize + 3); y++) {
				if (x >= 0 && y >= 0 && x < Component.level.block.length
						&& y < Component.level.block[0].length)
					if (Component.level.block[x][y].id == Tile.lava
							|| Component.level.block[x][y + 1].id == Tile.lava)// last
																				// y
																				// +
																				// 1
																				// for
																				// better
																				// lava
																				// detection
						if (Component.level.block[x][y].contains(pt1)
								|| (Component.level.block[x][y].contains(pt2))) {
							return true;
						}
			}
		}

		return false;
	}

	// put in amount that hurts character
	public void hurt(int amount) {
		this.amount = amount;
		if (health > 0) {
			// Decrease health by damage amount
			health -= amount;
			healthBarWidth -= amount;
		}
		if (health <= 0) {
			isDead = true;
		}
	}

	// Gaining health/healing
	public static int getHealth() {
		return health;
	}

	public void render(Graphics g) {
		// render health
		if (health > 0) {
			g.setColor(Color.RED);
			// 60x, 237y, on screen
			g.fillRect(60, 237, redHealthBarWidth, healthBarHeight);
			// this will be place above the red
			g.setColor(Color.GREEN);
			g.fillRect(60, 237, healthBarWidth, healthBarHeight);
		}
		if (health <= 0) {
			g.setColor(Color.RED);
			g.fillRect(60, 237, redHealthBarWidth, healthBarHeight);
		}

		// render character and movement
		if (Component.dir == movementSpeed) {// animation to go right
			g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX,
					(int) y - (int) Component.sY, (int) (x + width)
							- (int) Component.sX, (int) (y + height)
							- (int) Component.sY,/**/

					(Tile.character[0] * Tile.tileSize)
							+ (Tile.tileSize * animation), Tile.character[1]
							* Tile.tileSize,
					(Tile.character[0] * Tile.tileSize)
							+ (Tile.tileSize * animation) + (int) width,
					Tile.character[1] * Tile.tileSize + (int) height, null);
		} else {// reverse animation to go left
			g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX,
					(int) y - (int) Component.sY, (int) (x + width)
							- (int) Component.sX, (int) (y + height)
							- (int) Component.sY,/**/

					(Tile.character[0] * Tile.tileSize)
							+ (Tile.tileSize * animation) + (int) width,
					Tile.character[1] * Tile.tileSize,
					(Tile.character[0] * Tile.tileSize)
							+ (Tile.tileSize * animation), Tile.character[1]
							* Tile.tileSize + (int) height, null);

		}

	}

}
