package application;

import java.io.FileInputStream;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Monstre extends Entite{

	public Monstre(Zone z1, int a) {
		try{
			if(a==1) {
				corps= new ImageView(new Image(new FileInputStream("jeuJavaFx/Monstre.png")));
				position(z1);
				//sound
				AudioClip clip = new AudioClip("File:jeuJavaFx/Monstre1.mp3");
				 clip.play();
			}else {
				corps= new ImageView(new Image(new FileInputStream("jeuJavaFx/MonstreFinal.png")));
				//position(z1);
				KeyFrame F	=new KeyFrame(Duration.millis(2500),ae -> position(z1));
				Timeline timeline = new Timeline(F);
						
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
				//sound
				AudioClip clip = new AudioClip("File:jeuJavaFx/Monstre2.mp3");
				 clip.play();
			}
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		
	}

	@Override
	public void setAlive(boolean alive) {
		// TODO Auto-generated method stub
		super.setAlive(alive);
		if(!alive) {
			//sound
				AudioClip clip = new AudioClip("File:///D:/Downloads/jeuJavaFx/KillM.mp3");
				 clip.play();
		}
	}

}
