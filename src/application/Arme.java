package application;

import java.io.FileInputStream;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Arme extends Entite {
	public Circle sortie=new Circle(0,0,5);	
	
	public Arme(Hero hero) {
		try{
			 //corps=new ImageView(new Image(new FileInputStream("hero.png")));
			 corps= new ImageView(new Image(new FileInputStream("jeuJavaFx/Arme.png")));
			 corps.setTranslateX(hero.getCorps().getTranslateX());
			 corps.setTranslateY(hero.getCorps().getTranslateY());
			 sortie.setFill(Color.BLACK);
			 Modifsortie();

		 }catch (Exception e) {
			 e.printStackTrace();
		}
	}

	private void Modifsortie() {
		sortie.setCenterX(corps.getTranslateX()+9);
		sortie.setCenterY(corps.getTranslateY()-18);
	}

	public Circle getSortie() {
		return sortie;
	}
	public void rotateLeft() {
		if(corps.getRotate()>=-75) {
		// TODO Auto-generated method stub
		corps.setRotate(-20+corps.getRotate());
		Upgrade();
		}
	}

	public void rotateRight() {
		// TODO Auto-generated method stub
		if(corps.getRotate()<=75) {
		corps.setRotate(20+corps.getRotate());
		Upgrade();
		}
	}
	public double getRotate(){
		return corps.getRotate()-90;
	}
	public void Upgrade() {
		double Rotate = Math.toRadians(corps.getRotate()-90);
		double a = Math.toDegrees(Math.cos(Rotate));
		double b = Math.toDegrees(Math.sin(Rotate));
		sortie.setCenterX(10+corps.getTranslateX()+a);
		sortie.setCenterY(37+corps.getTranslateY()+b);
	}

}
