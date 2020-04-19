package net.amvitkus.drugcraft;

import java.awt.event.*;

public class Listening implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener {

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_D:
			// when D key is pressed
			Component.isMoving = true;
			// character is moving to the right.
			Component.dir = Component.character.movementSpeed;
			break;

		case KeyEvent.VK_A:

			// when a key is pressed
			Component.isMoving = true;
			// character is moving to the left.
			Component.dir = -Component.character.movementSpeed;
			break;

		case KeyEvent.VK_W:
			Component.isJumping = true;
			break;

		case KeyEvent.VK_F:
			if (Inventory.isOpen) {
				Inventory.isOpen = false;
			} else {
				Inventory.isOpen = true;
			}
			break;
		}

	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_D:
			if (Component.dir == Component.character.movementSpeed) {
				Component.isMoving = false;
			}
			break;

		case KeyEvent.VK_A:
			if (Component.dir == -Component.character.movementSpeed) {
				Component.isMoving = false;
			}
			break;

		case KeyEvent.VK_W:
			Component.isJumping = false;
			break;

		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) { // downward wheel
			if (Inventory.selected < Tile.invLength - 1) {
				Inventory.selected += 1;
			} else {
				Inventory.selected = 0;
			}
		} else if (e.getWheelRotation() < 0) { // upward wheel
			if (Inventory.selected > 0) {
				Inventory.selected -= 1;
			} else {
				Inventory.selected = Tile.invLength - 1;
			}

		}

	}

	public void mouseDragged(MouseEvent e) {
		Component.mse.setLocation(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		Component.mse.setLocation(e.getX(), e.getY());
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Component.isMouseLeft = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			Component.isMouseRight = true;
		}

		Inventory.click(e);
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Component.isMouseLeft = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			Component.isMouseRight = false;
		}
	}

}
