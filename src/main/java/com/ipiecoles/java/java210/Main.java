package com.ipiecoles.java.java210;

public class Main {
	public static void main(String[] args) throws Exception {
		Sudoku monSudoku = new Sudoku();
		
		monSudoku.remplitSudokuATrous(monSudoku.demandeCoordonneesSudoku());
		monSudoku.ecrireSudoku(monSudoku.getSudokuAResoudre());
		if(monSudoku.resoudre(0, 0, monSudoku.getSudokuAResoudre())){
			monSudoku.ecrireSudoku(monSudoku.getSudokuAResoudre());
		} else {
			System.out.println("Pas de solution...");
		}
    }
}
/*018 034 052 076 
* 113 124 169 171 
* 209 216 278 284 
* 332 341 356     
* 533 545 557     
* 608 614 677 685 
* 712 726 761 773 
* 819 837 851 874 
*/