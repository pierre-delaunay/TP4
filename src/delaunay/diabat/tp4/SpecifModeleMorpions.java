package delaunay.diabat.tp4;

public interface SpecifModeleMorpions
{
	//-----------------------------------------------------------------------------------------------------------
	/* Taille du plateau */
	public static final int TAILLE = 3;

	//-----------------------------------------------------------------------------------------------------------
	/* Nombre de joueurs */
	public static final int NBJOUEURS = 2;

	//-----------------------------------------------------------------------------------------------------------
	/* SpecifMorpions.Etat : type �num�r� des �tats possibles du jeu :
	 * J1_JOUE      : le joueur 1 est le prochain � jouer
	 * J2_JOUE      : le joueur 1 est le prochain � jouer
	 * MATCH_NUL    : jeu fini, pas de vainqueur
	 * J1_VAINQUEUR : jeu fini, le  vainqueur est 1e joueur 1
	 * J2_VAINQUEUR : jeu fini, le  vainqueur est 1e joueur 2
	*/
	public static enum Etat {J1_JOUE, J2_JOUE, MATCH_NUL, J1_VAINQUEUR, J2_VAINQUEUR};

	//-----------------------------------------------------------------------------------------------------------
	/* getEtatJeu() : �tat actuel du jeu
	 * @return une des valeurs du type �num�r� SpecifMorpions.Etat
	 */
	public Etat getEtatJeu();

	//-----------------------------------------------------------------------------------------------------------
	/* getJoueur() : num�ro du joueur courant
	 * @return num�ro du prochain joueur (1 ou 2), ou 0 si le jeu est fini
	 */
	public int getJoueur() ;

	//-----------------------------------------------------------------------------------------------------------
	/* getVainqueur() : num�ro du vainqueur
	 * @return num�ro du vainqueur (1 ou 2), ou 0 s'il n'y a pas, ou pas encore, de vainqueur
	 */
	public int getVainqueur() ;

	//-----------------------------------------------------------------------------------------------------------
	/* getNombreCoups() : nombre de coups jou�s
	 * @return nombre de coups jou�s
	 */
	public int getNombreCoups();

	//-----------------------------------------------------------------------------------------------------------
	/* estFinie() : d�termine si la partie est termin�e ou non
	 * @return vrai si et seulement si getEtatJeu() est dans {MATCH_NUL, J1_VAINQUEUR, J2_VAINQUEUR}
	 */
	public boolean estFinie();

	//-----------------------------------------------------------------------------------------------------------
	/* estCoupAutorise() : valide le coup (ligne, colonne) sans le jouer
	 * @param ligne : num�ro de ligne
	 * @param colonne : num�ro de colonne
	 * @return true si le coup est autoris�, false sinon
	 */
	public boolean estCoupAutorise(int ligne, int colonne);

	//-----------------------------------------------------------------------------------------------------------
	/* jouerCoup() : joue le coup (ligne, colonne)
	 * @param ligne : num�ro de ligne
	 * @param colonne : num�ro de colonne
	 * @pre ! this.estFinie() ;
	 * @pre this.estCoupAutorise(ligne, colonne)
	 */
	public void jouerCoup(int ligne, int colonne);
}
