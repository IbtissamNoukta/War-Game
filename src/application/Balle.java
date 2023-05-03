package application;


import javafx.geometry.Point2D;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Balle extends Entite{
 private Point2D direction= new Point2D(0,0);
 //pour Arme
public Balle(Arme arme) {
	Circle corps= new Circle(0,0,5,Color.RED);

	corps.setCenterX(arme.getSortie().getCenterX());
	corps.setCenterY(arme.getSortie().getCenterY());
	this.corps=corps;
	ModifDirect(arme.getRotate());
	//sound
	AudioClip clip = new AudioClip("File:jeuJavaFx/balleH.mp3");
	 clip.play();

}
//pour monstre
public Balle(Monstre monstre) {
	Circle corps= new Circle(0,0,5,Color.BLACK);
	corps.setCenterX(monstre.getCorps().getTranslateX()+30);
	corps.setCenterY(monstre.getCorps().getTranslateY()+50);
	this.corps=corps;
	Modif();
	//sound
		AudioClip clip = new AudioClip("File:jeuJavaFx/balleM.mp3");
		 clip.play();
}
//pour arme
public void ModifDirect(double rotation) {	
		Point2D point;
		double a = Math.cos(Math.toRadians(rotation));
		
		double b = Math.sin(Math.toRadians(rotation));
		//System.out.println(" (a,b) :"+a+" "+b);
		point=new Point2D(a,b);
		direction=point.normalize().multiply(7);
		}
//pour monstre
public void Modif() {	
	Point2D point = new Point2D(6.123233995736766E-17,1.0);
	direction=point.normalize().multiply(7);
	}

public void update() {
	// TODO Auto-generated method stub
	 corps.setTranslateX(corps.getTranslateX()+direction.getX());
	 corps.setTranslateY(corps.getTranslateY()+direction.getY());

}


}
