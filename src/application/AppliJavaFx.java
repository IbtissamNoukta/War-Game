package application;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppliJavaFx extends Application {
	int Score=0;
	int nbr_monstres=0;
	//dead monstre final
	int dead =0;

	double width =800;
	double height=600;
	Pane pane= new Pane();
	//line
	Line line = new Line(0,200,800,200);
	//les zones
	Zone zone1= new Zone(0,0,line.getEndX(),line.getEndY());
	Zone zone2= new Zone(0,line.getStartY(),line.getEndX(),height);
    
	public static void main(String[] args) { launch(args);}
	@Override
	public void start(Stage primaryStage) throws Exception{
		//scene
		Scene s = new Scene(pane,width,height);
		primaryStage.setTitle("jeu de guerre");
		primaryStage.setScene(s);
		//icone
		primaryStage.getIcons().add(new Image("File:jeuJavaFx/icon.PNG"));
		//background
		ImageView Bg= new ImageView(new Image(new FileInputStream("jeuJavaFx/4.JPG")));
		Bg.setFitWidth(s.getWidth());
		Bg.setFitHeight(s.getHeight());
		pane.getChildren().add(Bg);
		//score
		Label text = new Label();
		text.setFont(new Font("Arial", 24));
        text.setLayoutX(10);
        text.setLayoutY(5);
        text.setText("Score : "+Integer.toString(Score));
        text.setTextFill(Color.CRIMSON);
        pane.getChildren().add(text);

		//line
		pane.getChildren().add(line);
		//hero
		Hero hero = new Hero(zone2);
        pane.getChildren().add(hero.getCorps());
        
        //HealP
  		Label text2 = new Label();
  		text2.setFont(new Font("Arial", 27));
        text2.setLayoutX(670);
        text2.setLayoutY(550);
        text2.setText("Heal : "+Integer.toString(hero.HealP));
        
        pane.getChildren().add(text2);
          
        //Arm
        Arme arme = new Arme(hero);
        pane.getChildren().add(arme.getCorps());
        pane.getChildren().add(arme.sortie);

		//Monster
       // Monstre monstre = new Monstre(zone1);
        
      //les objet du jeu
        List<Monstre> monstres= new ArrayList<>();
        List<Monstre> Mf= new ArrayList<>();
        List<Balle> balles= new ArrayList<>();
        List<Balle> ballesmonstre= new ArrayList<>();
        //sound
		 AudioClip clip = new AudioClip("File:jeuJavaFx/HitH.mp3");

        //Pleasures Monster
        AnimationTimer An = new AnimationTimer() {
        		 void Annimonstre(){
        			 
        			 //stage 1
        			if(hero.getAlive()) {
        			 if(nbr_monstres<5) {
        				 if(Math.random()<0.01) {
        				 Monstre monstre = new Monstre(zone1,1);
        				 nbr_monstres++;
        				 pane.getChildren().add(monstre.getCorps());
        				 monstres.add(monstre);
        				 }
        			 }
        			 //stage2
        			 if(nbr_monstres<12 && Score>=5) {
        				 if(Math.random()<0.05) {
        				 Monstre monstre = new Monstre(zone1,1);
        				 nbr_monstres++;
        				 pane.getChildren().add(monstre.getCorps());
        				 monstres.add(monstre);
        				 }
        			 }
        			 //stage3
        			 if(nbr_monstres==12 && Score==12) {
        				 Monstre monstrefinal = new Monstre(zone1,2);
        				 nbr_monstres++;
        				 pane.getChildren().add(monstrefinal.getCorps());
        				 Mf.add(monstrefinal);
        			 }
        			 //balles monstre
        			 for (Monstre monstre : monstres) {
        				 if(Math.random()<0.01) {
        				Balle balle = new Balle(monstre);
        				pane.getChildren().add(balle.getCorps());
        				 ballesmonstre.add(balle);
        				 } 
        			 }
        			//balles monstrefinal
        			 for (Monstre monstrefinal : Mf) {
        				 if(Math.random()<0.01) {
        				Balle balle = new Balle(monstrefinal);
        				pane.getChildren().add(balle.getCorps());
        				 ballesmonstre.add(balle);
        				 } 
        			 }
        			}
        			 //parcour la collection du balles pour mettre a jour leur position 
        			 for (Balle balle:balles) {
        		        	balle.update();
        		        }
        			 for (Balle balle:ballesmonstre) {
     		        	balle.update();
     		        }
        			 //tuer monstre
        			 for(Balle balle:balles) {
        				 for(Monstre monstre:monstres) {
        					 if(balle.touch(monstre)) {
        						 pane.getChildren().removeAll(balle.getCorps(),monstre.getCorps());
        						 balle.setAlive(false);
        						 monstre.setAlive(false);
        						// increase score
        						 Score =Score + 1;
        					 }
        				 }
        			 }
        			 //tuer monstrefinal
        			 for(Balle balle:balles) {
        				for(Monstre monstrefinal : Mf) {
        					 if(!balle.isDead() && balle.touch(monstrefinal)) {
        						 pane.getChildren().remove(balle.getCorps());
        						 balle.setAlive(false);
        	        			 dead=dead+1;
        	        			 Score =Score + 1;
        	        			//sound
    							 clip.play();
        						if(dead==5) {
        							pane.getChildren().removeAll(monstrefinal.getCorps());
        							//balle.setAlive(false);
        							monstrefinal.setAlive(false);
        							//win
        							pane.getChildren().clear();
        							ImageView win;
    									try {
											win = new ImageView(new Image(new FileInputStream("jeuJavaFx/win.jpg")));
											win.setFitWidth(s.getWidth());
	            							win.setFitHeight(s.getHeight());
	            							pane.getChildren().add(win);
	            							//sound
 	            							AudioClip clip = new AudioClip("File:jeuJavaFx/win.mp3");
	            							clip.play();
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}		
        						}
        					}
        				}
        			 }
        			 //tuer hero
        			 for(Balle balle:ballesmonstre) {
        					 if(!balle.isDead() && balle.touch(hero)) {
        						 pane.getChildren().remove(balle.getCorps());
        						 balle.setAlive(false);
        						 hero.HealP=hero.HealP-1;
        						 //sound
        						 AudioClip clip = new AudioClip("File:jeuJavaFx/HitH.mp3");
    							 clip.play();
        						 if(hero.HealP==0){
        						 pane.getChildren().removeAll(balle.getCorps(),hero.getCorps(),arme.getCorps(),arme.getSortie());
        						 hero.setAlive(false);
        						 //GameOver;
        						 pane.getChildren().clear();
        						 ImageView Go;
								try {
									Go = new ImageView(new Image(new FileInputStream("jeuJavaFx/5.JPG")));
									Go.setFitWidth(s.getWidth());
        							Go.setFitHeight(s.getHeight());
        							pane.getChildren().add(Go);
        							//sound
        							AudioClip clip1 = new AudioClip("File:jeuJavaFx/gameover.wav");
        							 clip1.play();
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
        						}
        							
        					 }
        			 }
        			 monstres.removeIf(Entite::isDead);
        			 balles.removeIf(Entite::isDead);
        			 Mf.removeIf(Entite::isDead);
        		 }

				@Override
        		 public void handle(long arg0) {
        			 // TODO Auto-generated method stub
 					Annimonstre(); 
                 // display player Heal point
                    text.setText("Score : "+Integer.toString(Score));
                    text2.setText("Heal : "+Integer.toString(hero.HealP));
                    if(hero.HealP>4) {
                        text2.setTextFill(Color.GREEN);
                     }else if(hero.HealP<=4 && hero.HealP>2) {
                     	text2.setTextFill(Color.ORANGERED);
                     }else {
                     	text2.setTextFill(Color.RED);
                       }
        		 }
        };
        An.start();
        

        
       
        
        //les evenements 
		EventHandler<KeyEvent> event= new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				
				switch(event.getCode()) {
				case X :
					arme.rotateLeft();break;
				case W :
					arme.rotateRight();break;
				case RIGHT :
					if(hero.getCorps().getTranslateX()<718.7683393547758) {
					hero.getCorps().setTranslateX(hero.getCorps().getTranslateX()+5);
					//System.out.println(hero.getCorps().getTranslateX());
					arme.getCorps().setTranslateX(arme.getCorps().getTranslateX()+5);
					arme.sortie.setCenterX(arme.sortie.getCenterX()+5);}break;
					
				case LEFT :
					if(hero.getCorps().getTranslateX()>-0.6459603612230467) {
					hero.getCorps().setTranslateX(hero.getCorps().getTranslateX()-5);
					//System.out.println(hero.getCorps().getTranslateX());
					arme.getCorps().setTranslateX(arme.getCorps().getTranslateX()-5);
					arme.sortie.setCenterX(arme.sortie.getCenterX()-5);}break;
				case UP :
					if(hero.getCorps().getTranslateY()>224.8271466918054) {
					hero.getCorps().setTranslateY(hero.getCorps().getTranslateY()-5);
					//System.out.println(hero.getCorps().getTranslateY());
					arme.getCorps().setTranslateY(arme.getCorps().getTranslateY()-5);
					arme.sortie.setCenterY(arme.sortie.getCenterY()-5);}break;
				
				case DOWN :
					if(hero.getCorps().getTranslateY()<493.2157677112894) {
					hero.getCorps().setTranslateY(hero.getCorps().getTranslateY()+5);
					//System.out.println(hero.getCorps().getTranslateY());
					arme.getCorps().setTranslateY(arme.getCorps().getTranslateY()+5);
					arme.sortie.setCenterY(arme.sortie.getCenterY()+5);}break;
				case SPACE :
					Balle balle = new Balle(arme);
				    pane.getChildren().add(balle.getCorps());
				    balles.add(balle);break;
				default:
					break;
			}
			}
		};
		s.setOnKeyPressed(event);
		primaryStage.show();
		
			}
		}
		
	
	

