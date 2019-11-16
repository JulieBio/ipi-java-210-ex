# ipi-java-210-ex 
![Build status](https://travis-ci.org/pjvilloud/ipi-java-210-ex.svg?branch=correction)

Cours de Java, module 210 pour l'IPI

## TP Java 210
	
Tout d'abord, créer la classe ```Main``` et y ajouter la méthode ```main``` en tant que point d'entrée du programme. Cela vous permettra de tester votre programme.

1. Déclarer au niveau de la classe ```Sudoku```, une constante de classe de type ```String```, nommée ```FIN_SAISIE``` contenant le texte ```"FIN"```.
2. Déclarer au niveau de la classe ```Sudoku```, un booléen nommé resolu initialisé à ```false```.
3. Déclarer au niveau de la classe ```Sudoku```, un tableau à deux dimensions pouvant contenir des éléments de type ```short```, nommée ```sudokuAResoudre```.
4. Créer une méthode ```getSudokuAResoudre``` ne prenant aucun argument et retournant le tableau ```sudokuAResoudre```. Créer une méthode ```setSudokuAResoudre``` prenant en argument un tableau de ```short``` à deux dimensions et qui affecte cet argument au tableau ```sudokuAResoudre```.
5. Dans la méthode ```Sudoku()```, initialiser le tableau pour qu'il puisse contenir tous les éléments du sudoku (9 par 9).
6. Dans la méthode ```ligneSaisieEstCoherente```, tester le paramètre ```ligneSaisie``` pour vérifier que la ligne ne soit pas nulle ou vide, ou remplie d'espaces.
7. Dans la méthode ```ligneSaisieEstCoherente```, tester le paramètre ```ligneSaisie``` pour vérifier qu'il fait 3 caractères.
8. Dans la méthode ```ligneSaisieEstCoherente```, tester le paramètre ```ligneSaisie``` pour vérifier que le premier caractère est un chiffre entre 0 et 8.
9. Dans la méthode ```ligneSaisieEstCoherente```, tester le paramètre ```ligneSaisie``` pour vérifier que le deuxième caractère est un chiffre entre 0 et 8.
10. Dans la méthode ```ligneSaisieEstCoherente```, tester le paramètre ```ligneSaisie``` pour vérifier que le troisième caractère est un chiffre entre 1 et 9.
11. Dans la méthode ```demandeCoordonneesSudoku```, lire dans la console les coordonnées de chaque chiffre que doit contenir le sudoku avant résolution tant que l'utilisateur de renseigne pas la valeur ```FIN``` indiquant la fin de sa saisie. Contrôler la validité de la ligne en appelant la méthode ```ligneSaisieEstCoherente``` précédemment développée. Mettre les coordonnées saisies dans un tableau de ```String``` est le retourner en fin de méthode.
12. Ecrire le contenu de la méthode ```remplitSudokuATrous```.
13. Ecrire le contenu de la méthode ```ecrireSudoku```.
14. Ecrire le contenu de la méthode ```estAutorise```.
15. Ecrire le contenu de la méthode ```resoudre```.
16. Ecrire la JavaDoc de la classe et des méthodes non documentées (hors getter/setter) puis la générer,
17. Debugguer l'application à partir de la classe ```Main```.
18. Finaliser l'exécution de l'application en appelant les méthodes nécessaires dans la méthode ```main``` de la classe ```Main```.
