package application;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero extends Entite{
	public int HealP =6;
	public Hero(Zone z2) {
		try{
			 //corps=new ImageView(new Image(new FileInputStream("hero.png")));
			 corps= new ImageView(new Image(new FileInputStream("jeuJavaFx/hero.png")));
			 position(z2);
		 }catch (Exception e) {
			 e.printStackTrace();
		}
	}

}
