package delaunay.diabat.tp4;

public class ModeleMorpions implements SpecifModeleMorpions {

	private int[][] tab;
	private int nbCoupsJoues;
	private SpecifModeleMorpions.Etat etatJeu;

	private void setValue(int ligne, int colonne, int val)
	{
	  tab[(ligne - 1)][(colonne - 1)] = val;
	}
	  
	private int getValue(int ligne, int colonne) {
	   return tab[(ligne - 1)][(colonne - 1)];
	}
	  
	/**
	 * Constructeur sans param�tres
	 * @return une grille de Morpions (matrice 3x3) avec les cases initialis�es � 0
	 */
	public ModeleMorpions() {
	
	    nbCoupsJoues = 0;
	    etatJeu = SpecifModeleMorpions.Etat.J1_JOUE;
	    
	    tab = new int[3][3];
	    for (int lig = 1; lig <= 3; lig++)
	    {
	      for (int col = 1; col <= 3; col++)
	        setValue(lig, col, 0);
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
		
		for(int i = 1; i < tab.length; ++i) 
		{
			for(int j = 1; j < tab.length; ++j) 
			{
				if (tab[i][j] == 0) { return false; }
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
	 * getNombreCoups() : nombre de coups jou�s
	 * @return nombre de coups jou�s
	 */
	@Override
	public int getNombreCoups() {
		
		return nbCoupsJoues;
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
	      nbCoupsJoues += 1;
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
	    else if (nbCoupsJoues == 9)
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
		
		for(int i = 1; i < this.tab.length; ++i) 
		{
			for(int j = 1; j < this.tab.length; ++j) 
			{
				str.append(" " + tab[i][j] );
			}
			
			str.append("\r\n");
		}		
		
		System.out.println(str);
	}
}
