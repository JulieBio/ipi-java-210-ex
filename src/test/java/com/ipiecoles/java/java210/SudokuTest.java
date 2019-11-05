package com.ipiecoles.java.java210;


import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SudokuTest {


	@Test
	//Exercice 1
	public void exo01SudokuDeclarationInitialisationConstante() throws Exception {
		//Déclarer au niveau de la classe monSudoku, une constante de classe
		//de type String, nommée FIN_SAISIE contenant le texte "FIN"

		Field finSaisie = null;
		try {
			finSaisie = Sudoku.class.getField("FIN_SAISIE");
			Assertions.assertThat(finSaisie.get(null)).as("Le champ FIN_SAISIE n'a pas la valeur attendue").isEqualTo("FIN");
		} catch (NoSuchFieldException exception) {
			Assertions.fail("Aucun champ nommé FIN_SAISIE n'a été trouvé");
		}
		Assertions.assertThat(Modifier.isFinal(finSaisie.getModifiers())).as("Le champ FIN_SAISIE n'est pas une constante").isTrue();
		Assertions.assertThat(Modifier.isStatic(finSaisie.getModifiers())).as("Le champ FIN_SAISIE n'est pas une constante de classe").isTrue();
		Assertions.assertThat(Modifier.isPublic(finSaisie.getModifiers())).as("Le champ FIN_SAISIE n'est pas accessible").isTrue();
	}

	@Test
	//Exercice 2
	public void exo02SudokuDeclarationResolu() throws Exception {
		//Déclarer au niveau de la classe monSudoku, un booléen nommé resolu initialisé à false

		Field resolu = null;
		try {
			resolu = Sudoku.class.getDeclaredField("resolu");
			Assertions.assertThat(resolu.getType()).as("Le champ resolu n'est pas du bon type").isEqualTo(boolean.class);
			//Assertions.assertThat(resolu.getBoolean(null)).as("Le champ resolu n'a pas la bonne valeur").isFalse();
		} catch (NoSuchFieldException exception) {
			Assertions.fail("Aucun champ nommé resolu n'a été trouvé");
		}
	}

	@Test
	//Exercice 3
	public void exo03SsudokuDeclarationTableau() throws Exception {
		//Déclarer au niveau de la classe monSudoku, un tableau à deux dimensions
		//pouvant contenir des éléments de type short, nommée sudokuAResoudre
		Field sudokuAResoudre = null;
		try {
			sudokuAResoudre = Sudoku.class.getDeclaredField("sudokuAResoudre");
			Assertions.assertThat(sudokuAResoudre.getType().getTypeName()).as("Le champ sudokuAResoudre n'est pas du bon type").isEqualTo("short[][]");
		} catch (NoSuchFieldException exception) {
			Assertions.fail("Aucun champ nommé sudokuAResoudre n'a été trouvé");
		}
	}

	@Test
	//Exercice 4
	public void exo04sudokuCreerMethodeGetSet() throws Exception {
		//Créer une méthode getSudokuAResoudre ne prenant aucun argument
		//et retournant le tableau sudokuAResoudre
		Method getSudokuAResoudre = null;
		try {
			getSudokuAResoudre = Sudoku.class.getDeclaredMethod("getSudokuAResoudre");
			Assertions.assertThat(getSudokuAResoudre.getReturnType().getTypeName()).as("La méthode getSudokuAResoudre doit retourner le bon type").isEqualTo("short[][]");
		} catch (NoSuchMethodException exception) {
			Assertions.fail("Aucune méthode nommée getSudokuAResoudre n'a été trouvée");
		}
		Assertions.assertThat(Modifier.isPublic(getSudokuAResoudre.getModifiers())).as("La méthode getSudokuAResoudre n'est pas publique").isTrue();

		//Créer une méthode setSudokuAResoudre prenant en argument un tableau de short à deux dimensions
		//et qui affecte cet argument au tableau sudokuAResoudre
		Method setSudokuAResoudre = null;
		try {
			setSudokuAResoudre = Sudoku.class.getDeclaredMethod("setSudokuAResoudre", short[][].class);

		} catch (NoSuchMethodException exception) {
			Assertions.fail("Aucune méthode nommée setSudokuAResoudre n'a été trouvée");
		}
		Assertions.assertThat(setSudokuAResoudre.getParameterCount()).isEqualTo(1);
		Assertions.assertThat(setSudokuAResoudre.getParameterTypes()[0].getTypeName()).isEqualTo("short[][]");
		Assertions.assertThat(setSudokuAResoudre.getReturnType()).as("La méthode setSudokuAResoudre doit retourner le bon type").isEqualTo(void.class);
		Assertions.assertThat(Modifier.isPublic(getSudokuAResoudre.getModifiers())).as("La méthode getSudokuAResoudre n'est pas publique").isTrue();

		Sudoku monSudoku = new Sudoku();
		short[][] tabASetter = new short[4][4];
		short[] tabASetter1 = new short[4];
		Arrays.fill(tabASetter1, (short)5);
		Arrays.fill(tabASetter, tabASetter1);
		invokeSetter(monSudoku, "sudokuAResoudre", tabASetter);
		Assertions.assertThat(invokeGetter(monSudoku, "sudokuAResoudre")).isEqualTo(tabASetter);
	}

	@Test
	//Exercice 5
	public void exo05SudokuInitialisationTableau() throws Exception {
		//Dans la méthode Sudoku(), initialiser le tableau
		//pour qu'il puisse contenir tous les éléments du sudoku (3 par 3)
		Sudoku monSudoku = new Sudoku();
		short[][] sudokuNonInitialise = (short[][]) invokeGetter(monSudoku, "sudokuAResoudre");
		Assertions.assertThat(sudokuNonInitialise).hasSize(9);
		Arrays.stream(sudokuNonInitialise).forEach(sousTableau -> {
			Assertions.assertThat(sousTableau).hasSize(9);
			Assertions.assertThat(sousTableau).containsExactly((short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0);
		});
		
	}
		
	
	@Test
	//Exercice 6
	public void exo06LigneSaisieEstCoherenteLigneNulleVideEspaces() {
		//Dans la méthode ligneSaisieEstCoherente, tester le paramètre ligneSaisie 
		//pour vérifier:
		//La ligne ne doit pas être nulle ou vide, ou remplie d'espaces
		//La ligne doit faire exactement 3 caractères
		//La ligne doit avoir le format XYZ ou X et Y sont compris entre 0 et 8, et Y entre 1 et 9

	    String message = "Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces\n";
	    
		checkValeurLigneSaisie(null, false, message);
		checkValeurLigneSaisie("", false, message);
		checkValeurLigneSaisie("    ", false, message);
		
	}
	
	
	@Test
	//Exercice 7
	public void exo07LigneSaisieEstCoherenteLongeur3() {
		//Dans la méthode ligneSaisieEstCoherente, tester le paramètre ligneSaisie 
		//pour vérifier qu'il fait 3 caractères
	    String message = "Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères\n";
	    
	    checkValeurLigneSaisie("5555", false, message);
	    checkValeurLigneSaisie("55", false, message);
	    checkValeurLigneSaisie("567", true, "");
	}
	
	@Test
	//Exercice 8
	public void exo08LigneSaisieEstCoherenteAbscisse0_8() {
		//Dans la méthode ligneSaisieEstCoherente, tester le paramètre ligneSaisie 
		//pour vérifier que le premier caractère est un chiffre entre 0 et 8
	    String message = "L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9\n";
	   	
		checkValeurLigneSaisie("911", false, message);
		checkValeurLigneSaisie(" 11", false, message);
		checkValeurLigneSaisie("X11", false, message);
		checkValeurLigneSaisie("567", true, "");
	}
	
	@Test
	//Exercice 9
	public void exo09LigneSaisieEstCoherenteOrdonnee0_8() {
		//Dans la méthode ligneSaisieEstCoherente, tester le paramètre ligneSaisie 
		//pour vérifier que le deuxième caractère est un chiffre entre 0 et 8
	    String message = "L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9\n";
	   	
		checkValeurLigneSaisie("191", false, message);
		checkValeurLigneSaisie("1 1", false, message);
		checkValeurLigneSaisie("1X1", false, message);
		checkValeurLigneSaisie("567", true, "");
	}
	
	@Test
	//Exercice 10
	public void exo10LigneSaisieEstCoherenteValeur1_9() {
		//Dans la méthode ligneSaisieEstCoherente, tester le paramètre ligneSaisie 
		//pour vérifier que le troisième caractère est un chiffre entre 1 et 9
	    String message = "L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9\n";
	   	
		checkValeurLigneSaisie("110", false, message);
		checkValeurLigneSaisie("10 ", false, message);
		checkValeurLigneSaisie("10X", false, message);
		checkValeurLigneSaisie("567", true, "");
	}
	
	@Test
	//Exercice 11
	public void exo11DemandeCoordonneesSudoku() {
		//Dans la méthode demandeCoordonneesSudoku, lire dans la console les coordonnées de chaque chiffre
		//que doit contenir le sudoku avant résolution tant que l'utilisateur de renseigne pas la valeur FIN
		//indiquant la fin de sa saisie. Contrôler la validité de la ligne en appelant la méthode ligneSaisieEstCoherente
		//précédemment développée. Mettre les coordonnées saisies dans un tableau de String est le retourner en fin de méthode

		ByteArrayInputStream inContent;
		inContent = new ByteArrayInputStream("015\n024\nFIN".getBytes());
	    System.setIn(inContent);
	    
	    String[] coordonnees = Sudoku.demandeCoordonneesSudoku();
	    Assertions.assertThat(coordonnees).isNotNull();
	    Assertions.assertThat(coordonnees).isNotEmpty();
	    Assertions.assertThat(coordonnees.length >= 81).isTrue();
	    Assertions.assertThat(coordonnees[0]).isEqualTo("015");
	    Assertions.assertThat(coordonnees[1]).isEqualTo("024");
	    Assertions.assertThat(coordonnees[2]).isNull();
	    
		inContent = new ByteArrayInputStream("FIN".getBytes());
	    System.setIn(inContent);
	    
	    coordonnees = Sudoku.demandeCoordonneesSudoku();
	    Assertions.assertThat(coordonnees).isNotNull();
	    Assertions.assertThat(coordonnees).isNotEmpty();
	    Assertions.assertThat(coordonnees.length >= 81).isTrue();
	    Assertions.assertThat(coordonnees[0]).isNull();

	}

	@Test
	//Exercice 12
	public void exo12TestRemplitSudokuATrous() {
		//Ecrire le contenu de la méthode remplitSudokuATrous
		Sudoku monSudoku = new Sudoku();
		
		monSudoku.remplitSudokuATrous(new String[] {});
		
		short[][] sudokuAResoudre = (short[][]) invokeGetter(monSudoku, "sudokuAResoudre");
		Assertions.assertThat(sudokuAResoudre).isNotNull();
		short[][] tab =
			{
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			};
		
		Assertions.assertThat(sudokuAResoudre).isEqualTo(tab);
		
		monSudoku = new Sudoku();
		short[][] tab2 =
			{
			    {0, 2, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			};
		monSudoku.remplitSudokuATrous(new String[] {"012"});
		
		sudokuAResoudre = (short[][]) invokeGetter(monSudoku, "sudokuAResoudre");
		Assertions.assertThat(sudokuAResoudre).isEqualTo(tab2);
		
		monSudoku = new Sudoku();
		short[][] tab3 = {
				{1, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 4, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 5, 0, 0, 0, 0, 0, 0},
			    {3, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 2, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 6, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 9, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 8, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 7, 0, 0}
			};
		monSudoku.remplitSudokuATrous(new String[] {"001", "114", "225", "303", "412", "526", "649", "758", "867"});
	
		sudokuAResoudre = (short[][]) invokeGetter(monSudoku, "sudokuAResoudre");
		Assertions.assertThat(sudokuAResoudre).isEqualTo(tab3);
	
	}
	
	@Test
	//Exercice 13
	public void exo13EcrireSudoku() {
		//Ecrire le contenu de la méthode ecrireSudoku
		Sudoku monSudoku = new Sudoku();
		short[][] tab = {
			    {0, 8, 0, 4, 0, 2, 0, 6, 0},
			    {0, 3, 4, 0, 0, 0, 9, 1, 0},
			    {9, 6, 0, 0, 0, 0, 0, 8, 4},
			    {0, 0, 0, 2, 1, 6, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 3, 5, 7, 0, 0, 0},
			    {8, 4, 0, 0, 0, 0, 0, 7, 5},
			    {0, 2, 6, 0, 0, 0, 1, 3, 0},
			    {0, 9, 0, 7, 0, 1, 0, 4, 0},
			};
		ByteArrayOutputStream outContent;
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
		monSudoku.ecrireSudoku(tab);
		
		Assertions.assertThat(outContent.toString().trim()).isEqualToNormalizingNewlines(
				"-----------------------\n" + 
				"|   8   | 4   2 |   6   |\n" + 
				"|   3 4 |       | 9 1   |\n" + 
				"| 9 6   |       |   8 4 |\n" + 
				" -----------------------\n" + 
				"|       | 2 1 6 |       |\n" + 
				"|       |       |       |\n" + 
				"|       | 3 5 7 |       |\n" + 
				" -----------------------\n" + 
				"| 8 4   |       |   7 5 |\n" + 
				"|   2 6 |       | 1 3   |\n" + 
				"|   9   | 7   1 |   4   |\n" + 
				" -----------------------");
		
	}

	@Test
	//Exercice 14
	public void exo14estAutorise() {
		//Ecrire le contenu de la estAutorise
		short[][] tab = {
				{0, 8, 0, 4, 0, 2, 0, 6, 0},
				{0, 3, 4, 0, 0, 0, 9, 1, 0},
				{9, 6, 0, 0, 0, 0, 0, 8, 4},
				{0, 0, 0, 2, 1, 6, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 3, 5, 7, 0, 0, 0},
				{8, 4, 0, 0, 0, 0, 0, 7, 5},
				{0, 2, 6, 0, 0, 0, 1, 3, 0},
				{0, 9, 0, 7, 0, 1, 0, 4, 0},
		};

		Assertions.assertThat(Sudoku.estAutorise(0,0, (short) 2, tab)).isFalse();
		Assertions.assertThat(Sudoku.estAutorise(0,4, (short) 1, tab)).isFalse();
		Assertions.assertThat(Sudoku.estAutorise(0,2, (short) 5, tab)).isTrue();
		Assertions.assertThat(Sudoku.estAutorise(0,0, (short) 3, tab)).isFalse();

		Assertions.assertThat(Sudoku.estAutorise(0,0, (short) 2, tab)).isFalse();
		Assertions.assertThat(Sudoku.estAutorise(0,4, (short) 1, tab)).isFalse();
		Assertions.assertThat(Sudoku.estAutorise(0,2, (short) 5, tab)).isTrue();
		Assertions.assertThat(Sudoku.estAutorise(0,0, (short) 3, tab)).isFalse();

	}

	@Test
	//Exercice 15
	public void exo15ResoudreSudoku() {
		//
		Sudoku monSudoku = new Sudoku();
		short[][] tab = {
			    {0, 8, 0, 4, 0, 2, 0, 6, 0},
			    {0, 3, 4, 0, 0, 0, 9, 1, 0},
			    {9, 6, 0, 0, 0, 0, 0, 8, 4},
			    {0, 0, 0, 2, 1, 6, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 3, 5, 7, 0, 0, 0},
			    {8, 4, 0, 0, 0, 0, 0, 7, 5},
			    {0, 2, 6, 0, 0, 0, 1, 3, 0},
			    {0, 9, 0, 7, 0, 1, 0, 4, 0},
			};
		ByteArrayOutputStream outContent;
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    Assertions.assertThat(monSudoku.resoudre(0, 0, tab)).isTrue();
	    monSudoku.ecrireSudoku(tab);
		Assertions.assertThat(outContent.toString().trim()).isEqualToNormalizingNewlines(
				"-----------------------\n" + 
				"| 1 8 7 | 4 9 2 | 5 6 3 |\n" + 
				"| 5 3 4 | 6 7 8 | 9 1 2 |\n" + 
				"| 9 6 2 | 1 3 5 | 7 8 4 |\n" + 
				" -----------------------\n" + 
				"| 4 5 8 | 2 1 6 | 3 9 7 |\n" + 
				"| 2 7 3 | 8 4 9 | 6 5 1 |\n" + 
				"| 6 1 9 | 3 5 7 | 4 2 8 |\n" + 
				" -----------------------\n" + 
				"| 8 4 1 | 9 6 3 | 2 7 5 |\n" + 
				"| 7 2 6 | 5 8 4 | 1 3 9 |\n" + 
				"| 3 9 5 | 7 2 1 | 8 4 6 |\n" + 
				" -----------------------");
		
	}

	public void exo16JavaDoc(){
		//Ecrire la JavaDoc de la classe et des méthodes non documentées (hors getter/setter) puis la générer

	}

	public void exo17Debug(){
		//Debugguer l'application à partir de la classe Main.
	}

	private void checkValeurLigneSaisie(String valeur, boolean ok, String message) {
		ByteArrayOutputStream outContent;
		boolean resultat;
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    resultat = Sudoku.ligneSaisieEstCoherente(valeur);
		Assertions.assertThat(resultat).as("La vérification de la valeur " + valeur + " devrait renvoyer : " + ok).isEqualTo(ok);
		Assertions.assertThat(outContent.toString()).as("Le message affiché devrait être : " + message).isEqualToNormalizingNewlines(message);
	}
	
	private void invokeSetter(Object obj, String variableName, Object variableValue){
      /* variableValue is Object because value can be an Object, Integer, String, etc... */
      try {
        /**
         * Get object of PropertyDescriptor using variable name and class
         * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
         */
         PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
         /* Set field/variable value using getWriteMethod() */
         objPropertyDescriptor.getWriteMethod().invoke(obj, variableValue);
      } catch (Exception e) {
        Assertions.fail("Impossible d'appeler le setter");
      }
   }
	
	private Object invokeGetter(Object obj, String variableName){
      try {
        /**
         * Get object of PropertyDescriptor using variable name and class
         * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
         */
         PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
        /**
         * Get field/variable value using getReadMethod()
         * variableValue is Object because value can be an Object, Integer, String, etc...
         */
         Object variableValue = objPropertyDescriptor.getReadMethod().invoke(obj);
        /* Print value of variable */
         return variableValue;
      } catch (Exception e) {
    	  Assertions.fail("Impossible d'appeler le getter");
      }
      return null;
   }
}
