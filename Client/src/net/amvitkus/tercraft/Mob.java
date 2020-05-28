package net.amvitkus.drugcraft;

import java.awt.*;
import java.util.Random;

//Class for mob enemy attributes. Mobs blueprint
public class Mob extends DoubleRectangle {
	public int[] id;

	public boolean isJumping = false;
	public boolean isMoving = false;
	public boolean isFalling = false;

	public double movementSpeed = 0.4;
	public double fallingSpeed = 1;
	public double jumpingSpeed = 1;
	public double dir = movementSpeed;
	public byte jumpingHeight = 50;
	public byte jumpingCount = 0;
	public byte animation = 0;
	public byte animationFrame = 0, animationTime = 30;

	public Mob(int x, int y, int width, int height, int[] id) {
		setBounds(x, y, width, height);

		this.id = id;

	}

	//Tick for time going by
	public void tick() {
		if (!isJumping
				&& !isCollidingWithBlock(new Point((int) x + 2,
						(int) (y + height)), new Point((int) (x + width - 2),
						(int) (y + height)))) {
			y += fallingSpeed;
			isFalling = true;

		} else {
			isFalling = false;
			// isJumping = false;
			if (new Random().nextInt(100) < 1) { // to start moving
				isMoving = true;

				if (new Random().nextInt(100) > 50) { // choose direction
					dir = -movementSpeed;
				} else {
					dir = movementSpeed;
				}
			}
		}

		if (isMoving) {
			boolean canMove = false;

			if (dir == movementSpeed) {

				canMove = isCollidingWithBlock(new Point((int) (x + width),
						(int) y), new Point((int) (x + width),
						(int) (y + (height - 2))));

			} else if (dir == -movementSpeed) {

				canMove = isCollidingWithBlock(new Point((int) x - 1, (int) y),
						new Point((int) x - 1, (int) (y + (height - 2))));
			}

			if (!canMove && !isFalling) {
				isJumping = true;
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

				x += dir;
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
							&& Component.level.block[x][y].id != Tile.star) {
						if (Component.level.block[x][y].contains(pt1)
								|| Component.level.block[x][y].contains(pt2)) {
							return true;

						}
					}
			}
		}
		return false;
	}

	public void render(Graphics g) {
		if (dir == movementSpeed) {// animation to go right
			
			g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX,
					(int) y - (int) Component.sY, (int) (x + width)
							- (int) Component.sX, (int) (y + height)
							- (int) Component.sY,/**/

					(id[0] * Tile.tileSize) + (Tile.tileSize * animation),
					id[1] * Tile.tileSize, (id[0] * Tile.tileSize)
							+ (Tile.tileSize * animation) + (int) width, id[1]
							* Tile.tileSize + (int) height, null);
		} else {// reverse animation to go left
			
			g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX,
					(int) y - (int) Component.sY, (int) (x + width)
							- (int) Component.sX, (int) (y + height)
							- (int) Component.sY,/**/

					(id[0] * Tile.tileSize) + (Tile.tileSize * animation)
							+ (int) width, id[1] * Tile.tileSize,
					(id[0] * Tile.tileSize) + (Tile.tileSize * animation),
					id[1] * Tile.tileSize + (int) height, null);

		}

	}

}
