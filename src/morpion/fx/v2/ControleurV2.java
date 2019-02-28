package morpion.fx.v2;

import delaunay.diabat.tp4.SpecifModeleMorpions.Etat;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import morpion.fx.modele.ModeleMorpionFX;

public class ControleurV2 {
	
	private ModeleMorpionFX modele;
	@FXML // pour rendre la variable visible depuis SceneBuilder
	private GridPane grille ;
	
	@FXML
	private Label labelNbCoups;
	
	@FXML
	private Label labelEtatJeu;
	
	@FXML
	private Label labelJoueur;
	
	@FXML // pour rendre la méthode visible depuis SceneBuilder
	private void initialize() { 
		this.modele = new ModeleMorpionFX();
		
		for (Node n : grille.getChildren())
		{ 
            n.setOnMouseClicked((e) -> {
                this.clicBouton(e);
            });
			
		}
		
		/* still buggy
		modele.nbCoupsProperty().addListener((obsValue, oldValue, newValue) ­> {
			this.majNbCoups(newValue.intValue()); 
		});
		*/

		
	}

	private void clicBouton(MouseEvent e)
	{  
	   Node n = (Node) e.getSource();
	   int ligne = grille.getColumnIndex(n) + 1;
	   int colonne = grille.getRowIndex(n) + 1;
	   modele.jouerCoup(ligne, colonne);
	   System.out.println("Coup joué : " + ligne + "/" + colonne);
	   System.out.println("résultat: " + modele.getEtatJeu());
	   recalculerLabelEtatJeu();
	}
	
	private void recalculerLabelEtatJeu()
	{
		Etat etat = modele.getEtatJeu();
		
		switch (etat) {
			case J1_JOUE : labelEtatJeu.setText("C'est au tour de Player 1");
			break;
			
			case J2_JOUE : labelEtatJeu.setText("C'est au tour de Player 2");
			break;
			
			case J1_VAINQUEUR : labelEtatJeu.setText("Le gagnant est Player 1");
			break;
			
			case J2_VAINQUEUR : labelEtatJeu.setText("Le gagnant est Player 2");
			break;
			
			case MATCH_NUL : labelEtatJeu.setText("Round draw");
			break;
		}
	}
	
	private void majNbCoups(int nb)
	{   
		if (nb == 0)
		{
			labelNbCoups.setText("aucun coup joué");
		}
		else
		{
			String ch ;
			if (nb == 1) ch = " coup joué" ;
			else ch = " coups joués" ;
			labelNbCoups.setText(Integer.toString(nb) + ch);
		}
	}

}
