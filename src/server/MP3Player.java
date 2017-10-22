package server;

import java.io.*;
import javazoom.jl.player.Player;

public class MP3Player {
	
	private String filename;
	private Player player;

	public MP3Player(String filename) {
		  this.filename = filename;
	}
	public void play() {
		try {
				BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
				player = new Player(buffer);
				player.play();
				Thread.sleep(60000);/**≤•∑≈ Æ∑÷÷”**/
				player.close();
		 	} catch (Exception e) {
		
		        System.out.println(e);
		 	}
	}

}
