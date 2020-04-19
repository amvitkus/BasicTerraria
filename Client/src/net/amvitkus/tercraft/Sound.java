package net.amvitkus.drugcraft;

import java.applet.Applet;
import java.applet.AudioClip;

//This class isnt working properly, fix later. No sounds for now
public class Sound {
	
	//create and find sound
	public static final Sound blockBreak = new Sound("/sounds/floop2.wav");
	
	private AudioClip clip;
	
	

	public Sound(String name){
		try{
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//play sound
	public void play(){
		try{
			new Thread(){ //method within a method
				public void run() {
					clip.play();
				}
			}.start();
			Thread.sleep(1);
		} catch (Exception e){
			e.printStackTrace();
		}

	}

}
