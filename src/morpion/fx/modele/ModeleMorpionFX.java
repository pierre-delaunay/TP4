package morpion.fx.modele;

import delaunay.diabat.tp4.SpecifModeleMorpions;
import delaunay.diabat.tp4.SpecifModeleMorpions.Etat;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.*;

public class ModeleMorpionFX implements SpecifModeleMorpions {

	//private int[][] plateau;
	private ReadOnlyIntegerWrapper[][] plateau;
	private ReadOnlyIntegerWrapper nbCoupsJoues;
	private SpecifModeleMorpions.Etat etatJeu;
	// Symbole désignant le joueur courant
	private StringProperty symboleJoueurCourant;


	private void setValue(int ligne, int colonne, int val)
	{
		ligne--; colonne--;
		plateau[ligne][colonne].setValue(val);

	}
	  
	private int getValue(int ligne, int colonne) {
		ligne--; colonne--;
		return plateau[ligne][colonne].getValue() ;
	}
	  
	/**
	 * Constructeur sans param�tres
	 * @return une grille de Morpions (matrice 3x3) avec les cases initialis�es � 0
	 */
	public ModeleMorpionFX() {
	
		this.setNbCoups(0);
	    etatJeu = SpecifModeleMorpions.Etat.J1_JOUE;
	    
	    plateau = new ReadOnlyIntegerWrapper[3][3];
	    for (int lig = 1; lig <= 3; lig++)
	    {
	      for (int col = 1; col <= 3; col++)
	        setValue(lig, col, 0);
	    }
	    
	    setSymboleJoueurCourant(this.symboleJoueur(this.getJoueur()));

	}
	
	
	// ajout TP4
	public ReadOnlyIntegerProperty nbCoupsProperty()
	{ 
		return nbCoupsJoues.getReadOnlyProperty() ;      
	}
	
	/**
	 * Mutateur TP4
	 * @param v
	 */
	public void setNbCoups(int v) {
		nbCoupsJoues.set(v);
	}

	/**
	 * getNombreCoups() : nombre de coups jou�s
	 * @return nombre de coups jou�s
	 */
	@Override
	public int getNombreCoups() {
		
		return nbCoupsJoues.intValue();
	}

	public StringProperty symboleJoueurCourantProperty()
	{
		return this.symboleJoueurCourant;
	}

	// Accesseurs "Java Bean" sur la valeur encapsulée
	public String getSymboleJoueurCourant()
	{
		return symboleJoueurCourant.getValue();
	}
	
	private void setSymboleJoueurCourant(String ch)
	{
		symboleJoueurCourant.setValue(ch);
	}
	
	public ReadOnlyIntegerProperty casePlateauProperty(int ligne, int colonne)
	{
		ligne--; colonne--;
		return plateau[ligne][colonne].getReadOnlyProperty() ;
	}
	
	public String symboleJoueur(int val)
	{
		switch (val)
		{
			case 1 : return "x" ;
			case 2 : return "o" ;
			default : return " " ;
		}
	}
	
	/**
	 * Scan complet pour la recherche des vainqueurs
	 * @return 1 si le joueur 1 a gagn�, 2 si le joueur 2 a gagn�, 0 sinon
	 */
	public int fullScan() {
		
		int produit = 1;
		
		// Scan des lignes
		for(int lig = 1; lig <= 3; ++lig) 
		{
			produit = 1;
			for(int col = 1; col <= 3; ++col) 
			{
				produit *= getValue(lig, col);
			}
			
			if (produit == 1)  { return 1; }
			if (produit == 8) { return 2; }
		}
		
		// Scan des colonnes
		for(int col = 1; col <= 3; ++col) 
		{
			produit = 1;
			for(int lig = 1; lig <= 3; ++lig) 
			{
				produit *= getValue(lig, col);
			}
			
			if (produit == 1)  { return 1; }
			if (produit == 8) { return 2; }
		}
		
		// Scan des diagonales
		
		int diag1 = getValue(1,1) * getValue(2,2) * getValue(3,3);
		int diag2 = getValue(1,3) * getValue(2,2) * getValue(3,1); 
		
		if (diag1 == 1) { return 1; }
		if (diag1 == 8) { return 2; }
		if (diag2 == 1) { return 1; }
		if (diag2 == 8) { return 2; }
		
		
		return 0;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean estPleine() {
		
		for(int i = 1; i < plateau.length; ++i) 
		{
			for(int j = 1; j < plateau.length; ++j) 
			{
				if (plateau[i][j].getValue() == 0) { return false; }
			}
		}	
		return true;
	}
	
	
	@Override
	public Etat getEtatJeu() {
		
		return this.etatJeu;
	}

	@Override
	public int getJoueur() {

		if (etatJeu == SpecifModeleMorpions.Etat.J1_JOUE) return 1;
		if (etatJeu == SpecifModeleMorpions.Etat.J2_JOUE) return 2;
		
		return 0;
	}

	/**
	 * getVainqueur() : num�ro du vainqueur
	 * @return num�ro du vainqueur (1 ou 2), ou 0 s'il n'y a pas, ou pas encore, de vainqueur
	 */
	@Override
	public int getVainqueur() {
		
	    if (etatJeu == SpecifModeleMorpions.Etat.J1_VAINQUEUR) return 1;
	    if (etatJeu == SpecifModeleMorpions.Etat.J2_VAINQUEUR) return 2;
	    
	    return 0;
	}


	/**
	 * estFinie() : d�termine si la partie est termin�e ou non
	 * @return vrai si et seulement si getEtatJeu() est dans {MATCH_NUL, J1_VAINQUEUR, J2_VAINQUEUR}
	 */
	@Override
	public boolean estFinie() {

		return (etatJeu != SpecifModeleMorpions.Etat.J1_JOUE) && (etatJeu != SpecifModeleMorpions.Etat.J2_JOUE);
	}

	public boolean estCaseValide(int ligne, int colonne)
	{
	    return (1 <= ligne) && (ligne <= 3) && (1 <= colonne) && (colonne <= 3);
	}
	  
	  
	@Override
	public boolean estCoupAutorise(int ligne, int colonne) {

	    return (estCaseValide(ligne, colonne)) && (getValue(ligne, colonne) == 0);
	}

	@Override
	public void jouerCoup(int ligne, int colonne) {
		
	    assert (!estFinie()) : "La partie est termin�e";
	    assert (estCoupAutorise(ligne, colonne)) : "Le coup n'est pas autoris�";
	    if (estCoupAutorise(ligne, colonne))
	    {

	      setValue(ligne, colonne, getJoueur());
	      this.setNbCoups(this.getNombreCoups() + 1);
	      // ajout TP4
	      setSymboleJoueurCourant(this.symboleJoueur(this.getJoueur()));
	      resync();
	      
	      
	    }
	}
	
	public void resync()
	{
	    int vainqueur = fullScan();
	    
	    if (vainqueur == 1)
	    {
	      etatJeu = SpecifModeleMorpions.Etat.J1_VAINQUEUR;
	    }
	    else if (vainqueur == 2)
	    {
	      etatJeu = SpecifModeleMorpions.Etat.J2_VAINQUEUR;
	    }
	    else if (this.getNombreCoups() == 9)
	    {
	      etatJeu = SpecifModeleMorpions.Etat.MATCH_NUL;
	    }
	    else if (etatJeu == SpecifModeleMorpions.Etat.J1_JOUE)
	    {
	      etatJeu = SpecifModeleMorpions.Etat.J2_JOUE;
	    }
	    else
	    {
	      etatJeu = SpecifModeleMorpions.Etat.J1_JOUE;
	    }
	}
	  
	public void afficherTab() {
		
		StringBuilder str = new StringBuilder("");
		
		for(int i = 1; i < this.plateau.length; ++i) 
		{
			for(int j = 1; j < this.plateau.length; ++j) 
			{
				str.append(" " + plateau[i][j] );
			}
			
			str.append("\r\n");
		}		
		
		System.out.println(str);
	}
}
