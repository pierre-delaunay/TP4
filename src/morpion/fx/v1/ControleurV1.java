package morpion.fx.v1;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import morpion.fx.modele.ModeleMorpionFX;

public class ControleurV1 {
	
	private ModeleMorpionFX modele;
	@FXML // pour rendre la variable visible depuis SceneBuilder
	private GridPane grille ;
	
	@FXML // pour rendre la méthode visible depuis SceneBuilder
	private void initialize() { 
		this.modele = new ModeleMorpionFX();
		
		for (Node n : grille.getChildren())
		{ 
            n.setOnMouseClicked((e) -> {
                this.clicBouton(e);
            });
			
		}
	}

	private void clicBouton(MouseEvent e)
	{  
	   Node n = (Node) e.getSource();
	   int ligne = grille.getColumnIndex(n) + 1;
	   int colonne = grille.getRowIndex(n) + 1;
	   modele.jouerCoup(ligne, colonne);
	   System.out.println("Coup joué : " + ligne + "/" + colonne);
	   System.out.println("résultat: " + modele.getEtatJeu());
	}
	

}
