package net.amvitkus.drugcraft;

import java.awt.*;
import java.util.Random;

public class Sky {

	public byte day = 0;
	public byte night = 1;
	public byte time = day;

	public int r1 = 135, g1 = 206, b1 = 250;// day
	public int r2 = 0, g2 = 0, b2 = 139;// night
	public int r = r1, g = g1, b = b1;
	
	public static int light1 = 0;
	public static int light = light1;
	public int light2 = 150;
	
	public int dayFrame = 0, dayTime = 2000;
	public int changeFrame = 0, changeTime = 4;
	
	public static boolean hasOnce = false;
	public static boolean removeStar = false;
	
	private byte count = 0;
	private byte loop = 20;
	private byte rainTime = 5;
	private byte rainTimer = 0;
	private byte tillRainTime = 50;
	private byte tillTimer = 0;
	private boolean isAllowedToRain = true;
	public static boolean isRaining = false;

	public Sky() {
		if (time == day) {
			r = r1;
			g = g1;
			b = b1;
			light = light1;
		} else if (time == night) {
			r = r2;
			g = g2;
			b = b2;
			light = light2;
		}
	}

	public void tick() {
		if (dayFrame >= dayTime) {
			if (time == day) {
				time = night;
			} else if (time == night) {
				time = day;
			}

			dayFrame = 0;

		} else {
			dayFrame += 1;
		}

		if (changeFrame >= changeTime) {
			if (time == day) {
				if (light > light1){
					light -= 1;
				}
				
				
				if (r < r1) {
					r += 1;
				}
				if (g < g1) {
					g += 1;
				}
				if (b < b1) {
					b += 1;
				}

			} else if (time == night) {
				if(light < light2){
					light += 1;
				}
				
				
				if (r > r2) {
					r -= 1;
				}
				if (g > g2) {
					g -= 1;
				}
				if (b > b2) {
					b -= 1;
				}
			}
			changeFrame = 0;
		} else {
			changeFrame += 1;
		}
		
		if(time == night){

			generateStars();
		
		}
		if(time == day){
			if(removeStar){
			removeStars();
		}
		}
		//check if allowed to rain
		if(isAllowedToRain){
			if(new Random().nextInt(10000) < 10){
				isRaining = true;
				if(isRaining){
					rainTimer++;
					if(rainTimer >= rainTime){
						isRaining = false;
						rainTimer = 0;
						isAllowedToRain = false;
					}
				}
			}
		} else if (!isRaining){
			if(new Random().nextInt(10000) > 40){
				tillTimer++;
				if(tillTimer >= tillRainTime){
					isAllowedToRain = true;
					tillTimer = 0;
				}
			}
		}
	}
	
	public void generateStars(){
		if(!hasOnce){
		for(int y = 0; y < Level.block.length; y++){
			for(int x = 0; x < Level.block[0].length; x++){

				if(new Random().nextInt(100) < 5){
					if(Level.block[x][y].id == Tile.air && Level.worldH > 15 && Level.block[x][y+1].id != Tile.grass){
						Level.block[x][y].id = Tile.star;
						hasOnce = true;
						removeStar = true;
					}	
				}
			}
		}
	}
	}
	
	public void removeStars(){
		for(int y = 0; y < Level.block.length; y++){
			for(int x = 0; x < Level.block[0].length; x++){
				if(Level.block[x][y].id == Tile.star){
					Level.block[x][y].id = Tile.air;
					hasOnce = false;
					removeStar = false;
				}
	}
		}
	}
	public void render(Graphics gr) {
		gr.setColor(new Color(r, g, b));
		gr.fillRect(0, 0, Component.pixel.width, Component.pixel.height);
		if(isRaining){
			for(int count = 0; count < loop; count++){
				int randWidth = new Random().nextInt(350);
				int randHeight = new Random().nextInt(280);
				if(randWidth < 350 && randHeight < 280){
					gr.drawImage(Tile.rain, randWidth, randHeight, null);
				}
			}
		}

	}
}
