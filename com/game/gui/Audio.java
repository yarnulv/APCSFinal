package com.game.gui;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	private Clip clip;
	
	private AudioInputStream audioInputStream;
	
	public Audio(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(file));
		
		clip = AudioSystem.getClip();
		
		clip.open(audioInputStream);
		
	}
	
	public void play() {
		clip.start();
	}
	
	public void stop() {
		clip.stop();
		clip.close();
	}
	
	public Clip getClip() {
		return this.clip;
	}

}