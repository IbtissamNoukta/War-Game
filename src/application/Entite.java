package application;

import javafx.scene.Node;

public class Entite {
 protected Node corps;
 private double x;
 private double y;
 private boolean alive= true;
public Node getCorps() {
	return corps;
}


public void position(Zone z)
{
	 x=Math.random()*((z.getX2()-60)-z.getX1()+1)+z.getX1();//Math.random() * (max - min) + min
	 y=Math.random()*((z.getY2()-70)-z.getY1()+1)+z.getY1();//random value
	 corps.setTranslateX(x);
	 corps.setTranslateY(y);
}
public boolean touch(Entite ent) {
	return corps.getBoundsInParent().intersects(ent.getCorps().getBoundsInParent());
}


public boolean getAlive() {
	return alive;
}


public void setAlive(boolean alive) {
	this.alive = alive;
}
public boolean isDead() {
	return !alive;
}


}
