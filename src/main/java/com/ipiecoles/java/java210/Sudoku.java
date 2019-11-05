package com.ipiecoles.java.java210;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sudoku {

	public static final String FIN_SAISIE = "FIN";
	
	public boolean resolu = false;
	
	public short[][] sudokuAResoudre;
	
	/**
	 * setSudokuAResoudre
	 * @param short[][]
	 * @return
	 */
	public void setSudokuAResoudre(short[][] sudokuAResoudre){
		this.sudokuAResoudre = sudokuAResoudre;
	}
	
	/**
	 * Constructeur par défaut
	 */
	public Sudoku() {
		this.sudokuAResoudre = new short [9][9]; //exos05
		//sudokuAResoudre = new short [9][9];
		//this.setSudokuAResoudre = new short [9][9];
	}
	
	/**
	 * getSudokuAResoudre
	 * @param 
	 * @return 
	 * @return sudokuAResoudre
	 */
	public short[][] getSudokuAResoudre(){
		return sudokuAResoudre;
	}
	

	public static boolean ligneSaisieEstCoherente(String ligneSaisie) { //exos06
		// X est la ligne, Y est la colonne, et Z est la valeur dans chaque case entre 1 et 9.
		if (ligneSaisie == null || ligneSaisie.trim().isEmpty()) {
			//on teste TOUJOURS le null avant car on execute une méthode sur un objet null, donc on teste la non nullité avant
			//on utilise == car avec equals ça va planter
			System.out.println("Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces");
			return false;
		}
		if (ligneSaisie.length() != 3) { //exos07
			System.out.println("Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères");
			return false;
		}	
		if (!ligneSaisie.substring(0,1).matches("[0-8]") //exos08
			||!ligneSaisie.substring(1,2).matches("[0-8]")  //exos09
			||!ligneSaisie.substring(2,3).matches("[1-9]*"))  //exos10
			{ //exos08
			System.out.println("L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9");
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour initialiser un sudoku à résoudre.
	 * Les coordonnées prennent la forme XYZ avec X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
	 * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée après chaque saisie. 
	 * Lorsqu'il a terminé sa saisie, il entre la chaîne FIN. La fonction remplit au fur et à mesure un tableau de String
	 * comportant les coordonnées des chiffres saisis.
	 * 
	 * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant la méthode ligneSaisieEstCoherente
	 * En cas de mauvaise saisie, la saisie ne doit pas être prise en compte et l'utilisateur doit pouvoir saisie une nouvelle ligne
	 * La fonction doit également gérer le cas où l'utilisateur ne rentre rien mais appuye sur Entrée
	 *
	 * @return Un tableau comportant les coordonnées des chiffres présents dans le sudoku à résoudre
	 */
	public static String[] demandeCoordonneesSudoku() { // exos11
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bonjour et bienvenue dans le résolveur de Sudoku !");
		System.out.println("Veuillez renseigner les coordonnées de chaque chiffre présent en appuyant sur ENTREE " 
				+ "entre chaque coordonnée (abscisse suivie de l'ordonnée suivie de la valeur) : " 
				+ "Exemple : 065 (première ligne, sixième colonne, valeur 5). Entrez FIN lorsque vous avez terminés");
		
		//Variable qui contient la ligne saisie par l'utilisateur
		String ligneSaisie = null;
		
		//Un sudoku fait 9 par 9, soit potentiellement 81 valeurs à remplir
		//même si remplir toutes les valeurs n'est pas très intéressant...
		String[] tableauCoordonnees = new String[81];
		
		//Initialisation de l'indice permettant de remplir le tableau de coordonnées
		int indiceTableauCoordonnees = 0;
		do {
			//Lecture de la saisie utilisateur
			try {
				ligneSaisie = scanner.nextLine();
			}
			catch (NoSuchElementException e) {
				//L'utilisateur a juste fait entrée, on sort de la boucle
				System.out.println("L'utilisateur n'a rien saisi !");
				break;
			}
			if(ligneSaisie.equals(FIN_SAISIE)) {
				//On sort de la boucle si l'utilisateur saisit FIN
				break;
			}
			else if(ligneSaisieEstCoherente(ligneSaisie)) {
				//Si la ligne est cohérente, on l'insère dans le tableau des coordonnées
				tableauCoordonnees[indiceTableauCoordonnees++] = ligneSaisie;
			}
			else {
				//Ligne incohérente, on demande à l'utilisateur de saisie une nouvelle valeur
				System.out.println("Les coordonnées du chiffre et/ou sa valeur ne sont pas cohérents. Merci de réessayer");
			}
		} while (!ligneSaisie.equals(FIN_SAISIE) && indiceTableauCoordonnees < 81);
		
		//Fermeture de la ressource
		scanner.close();
		
		return tableauCoordonnees;
	}

	
	/**
	 * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec X correspondant 
	 * à l'abscisse, Y l'ordonnée et Z la valeur et remplit le tableau sudokuAResoudre avec les bonnes valeurs
	 * au bon endroit. Ex 012, première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle est 
	 * rencontrée dans le tableau, on arrête le traitement
	 * 
	 * Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string)
	 * 
	 * @param tableauCoordonnees
	 */
	public void remplitSudokuATrous(String[] tableauCoordonnees) { //exos12
		// Parcourir mon tableau avec un for de tableau
		for(String coordonnee : tableauCoordonnees) {
			// Si j'atteins la fin de mon tableau ou que je rencontre une valeur nulle, je sors.
			if(coordonnee == null) {
				break;
				// ou return vide car void
				//return;
			}
			// pour chaque élément de mon tableau :
		
			// Premier caractère => ligne
			short ligne = (short) stringToInt(coordonnee.substring(0,1));
			// Deuxième caractère => ligne
			short colonne = (short) stringToInt(coordonnee.substring(1,2));;
			// Troisième caractère => ligne
			short valeur = (short) stringToInt(coordonnee.substring(2,3));;
			
		// Je mets la valeur à la bonne ligne et colonne dans sudokuAResoudre
		sudokuAResoudre[ligne][colonne]= valeur;
		
		}
    }
	
	
	private int stringToInt(String s) {
		return Integer.parseInt(s);
	}
	
	/**
	 * Cette méthode affiche un sudoku de manière formatée sur la console.
	 * Cela doit ressembler exactement à :
	 * -----------------------
	 * |   8   | 4   2 |   6   |
	 * |   3 4 |       | 9 1   |
	 * | 9 6   |       |   8 4 |
	 *  -----------------------
	 * |       | 2 1 6 |       |
	 * |       |       |       |
	 * |       | 3 5 7 |       |
	 *  -----------------------
	 * | 8 4   |       |   7 5 |
	 * |   2 6 |       | 1 3   |
	 * |   9   | 7   1 |   4   |
	 *  -----------------------
	 * 
	 * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu ou non). 
	 * Ce tableau fait 9 par 9 et contient des chiffres de 0 à 9, 0 correspondant à une valeur 
	 * non trouvée (dans ce cas, le programme affiche un blanc à la place de 0
	 */
	public void ecrireSudoku(short[][] sudoku) { //exos13
		String chaineSudoku = "";
		for (int i = 0; i < 9; ++i) {
		    if (i % 3 == 0){
		    	chaineSudoku += " -----------------------\n";
			}
		    for (int j = 0; j < 9; ++j) {
				if (j % 3 == 0) {
					chaineSudoku += "| ";
				}
				chaineSudoku += sudoku[i][j] == 0
						 ? " "
						 : Integer.toString(sudoku[i][j]);
				
				chaineSudoku += " ";
		    }
		    chaineSudoku += "|\n";
		}
		chaineSudoku += " -----------------------";
		System.out.println(chaineSudoku);
    }
	
	/**
	 * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
	 * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes : 
	 * 
	 * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
	 * 2 : Si le valeur est déjà dans la colonne, le chiffre n'est pas autorisé
	 * 3 : Si la valeur est déjà dans la boite, le chiffre n'est pas autorisé
	 * 
	 * @param abscisse
	 * @param ordonnee
	 * @param chiffre
	 * @param sudoku
	 * @return
	 */
	public static boolean estAutorise(int abscisse, int ordonnee, short chiffre, short[][] sudoku) { //exos14
		//1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
		for (int j = 0; j < 9; j ++) {
			if((int) chiffre == sudoku[abscisse][j]) {
				return false;
			}
		}
		//2 : Si le valeur est déjà dans la colonne, le chiffre n'est pas autorisé
		for (int k = 0; k < 9; k ++) {
			if((int) chiffre == sudoku[k][ordonnee]) {
				return false;		
			}
		}
		//3 : Si la valeur est déjà dans la boite, le chiffre n'est pas autorisé
		//on va calculer dans quelle boite se placer en gardant un entier pour tronquer apres la virgule
		int decI = (abscisse / 3)*3;
		int decJ = (ordonnee / 3)*3;
		for (int i = 0; i < 3; i++) {		
			for (int j = 0; j < 3; j++) {
				if(chiffre == sudoku[decI + i][decJ + j]) {
					return false;		
				}
			}
		}
		return true;
    }

	
	
	/**
	 * Méthode récursive permettant de résoudre le sudoku passé en paramètre
	 * @param abscisse abscisse de l'endroit où on en est
	 * @param ordonnee ordonnée de l'endroit où on en est
	 * @param sudoku tableau représentant le sudoku à résoudre
	 * @return true si le sudoku est résolu à l'issue du traitement, false sinon
	 */
	public boolean resoudre(int abscisse, int ordonnee, short[][] sudoku) {
		
		//Pour commencer, on teste si on est arrivé au bout de la ligne, si c'est
		//le cas, on remet l'abscisse à 0 et on augmente l'ordonnée mais si on est 
		//arrivé à 9, on retourne true (on a traité tout le sudoku)
		if (abscisse == 9) {
			//Si on est arrivé au bout de la ligne, on remet l'abscisse à 0
		    abscisse = 0;
		    //Et on augmente l'ordonnée
		    if (++ordonnee == 9) {
		    	//On sort de la méthode si on dépasse la dernière colonne
		    	return true; 
			}
		}
		
		//On passe les éléments déjà remplis en incrémentant l'abscisse
		if (sudoku[abscisse][ordonnee] != 0) {
			//Pour les autres on appelle la méthode de résolution
		    return resoudre(abscisse+1,ordonnee,sudoku);
		}
		
		//Sinon, on essaye chaque valeur dans la case en appelant la méthode estAutorise
		//Si c'est le cas, on met cette valeur dans le sudoku et on appelle de 
		//nouveau la méthode de résolution en incrémentant l'abscisse et si
		//cette dernière retourne true, on retourne true.
		for (short val = 1; val <= 9; ++val) {
		    if (estAutorise(abscisse,ordonnee,val,sudoku)) {  
				sudoku[abscisse][ordonnee] = val;       
				if (resoudre(abscisse+1,ordonnee,sudoku)) {
					return true;
			    }
			}
		}
		//Si aucune valeur n'est autorisée, on remet la valeur 0
		//dans le sudoku
		//et on fait machine arrière en retournant false
		sudoku[abscisse][ordonnee] = 0;
		return false;
    }
	
	
	
}