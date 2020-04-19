package net.amvitkus.drugcraft;

import java.awt.*;

//Clone enemy
public class Clone extends Mob {

	public Clone(int x, int y, int width, int height){
		super(x, y, width, height, Tile.mobClone);
		
	}
	
	public void tick(){
		super.tick();
	}
	
	public void render(Graphics g){
		super.render(g);
	}
}