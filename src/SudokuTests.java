import static org.junit.Assert.*;

import org.junit.Test;


public class SudokuTests {

	Sudoku empty = new Sudoku
			(new int[][] { 
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
			});
			
			
	Sudoku incomplete = new Sudoku
			(new int[][] { 
			{5,3,0,0,7,0,0,0,0},
			{6,0,0,1,9,5,0,0,0},
			{0,9,8,0,0,0,0,6,0},
			{8,0,0,0,6,0,0,0,3},
			{4,0,0,8,0,3,0,0,1},
			{7,0,0,0,2,0,0,0,6},
			{0,6,0,0,0,0,2,8,0},
			{0,0,0,4,1,9,0,0,5},
			{0,0,0,0,8,0,0,7,9}
			});
	
	Sudoku mostlyComplete = new Sudoku
			(new int[][] { 
			{5,3,4,6,7,8,0,1,2},
			{6,7,2,1,0,5,3,4,8},
			{1,0,8,3,4,2,5,6,7},
			{8,5,0,7,6,1,4,2,3},
			{4,2,6,8,5,3,7,0,1},
			{7,1,3,0,2,4,8,5,6},
			{0,6,1,5,3,7,2,8,4},
			{2,8,7,4,1,0,6,3,5},
			{3,4,5,2,8,6,1,7,0}
			});
	
	Sudoku complete = new Sudoku
			(new int[][] { 
			{5,3,4,6,7,8,9,1,2},
			{6,7,2,1,9,5,3,4,8},
			{1,9,8,3,4,2,5,6,7},
			{8,5,9,7,6,1,4,2,3},
			{4,2,6,8,5,3,7,9,1},
			{7,1,3,9,2,4,8,5,6},
			{9,6,1,5,3,7,2,8,4},
			{2,8,7,4,1,9,6,3,5},
			{3,4,5,2,8,6,1,7,9}
			});
	
	boolean[] result = new boolean[81];
	boolean[] result2 = new boolean[81];
	
	@Test
	public void checkValidMoveTest() {
		incomplete.add(1,1,7);
		assertTrue(incomplete.check(1,1));
	}
	
	@Test
	public void invalidMoveSameNumberInSameRowTest() {
		incomplete.add(0,2,5);
		assertFalse(incomplete.check(0,2));
	}
	
	@Test
	public void invalidMoveSameNumberInSameColumnTest() {
		incomplete.add(6,0,7);
		assertFalse(incomplete.check(6,0));
	}
	
	@Test
	public void invalidMoveSameNumberInSameBoxTest() {
		incomplete.add(7,2,6);
		assertFalse(incomplete.check(7,2));
	}
	
	@Test
	public void hintNoPossibleSpacesCompleteBoard() {
		for(int i = 0; i < 81; i++) {
			result[i] = true;
		}
		result2 = complete.hint(5,result,0);
		for(int i = 0; i < 81; i++) {
			assertFalse(result2[i]);
		}
	}
	
	@Test
	public void hintNoPossibleSpacesMostlyCompleteBoard() {
		for(int i = 0; i < 81; i++) {
			result[i] = true;
		}
		result2 = mostlyComplete.hint(5,result,0);
		for(int i = 0; i < 81; i++) {
			assertFalse(result2[i]);
		}
	}
	
	@Test
	public void hintNinePossibleSpacesMostlyCompleteBoard() {
		for(int i = 0; i < 81; i++) {
			result2[i] = true;
			result[i] = false;
		}
		result[6] = true;
		result[13] = true;
		result[19] = true;
		result[29] = true;
		result[43] = true;
		result[48] = true;
		result[54] = true;
		result[68] = true;
		result[80] = true;
		
		result2 = mostlyComplete.hint(9, result2, 0);
		
		for(int i = 0; i < 81; i++) {
			assertEquals(result[i], result2[i]);
		}
	}
	
	@Test
	public void hintManyPossibleSpacesIncompleteBoard() {
		for(int i = 0; i < 81; i++) {
			result2[i] = true;
		}
		
		result2 = incomplete.hint(9, result2, 0);
		
		for(int i = 0; i < 81; i++) {
			if(result2[i]) {
				incomplete.add(i/9,i%9,9);
				assertTrue(incomplete.check(i/9,i%9));
				incomplete.remove(i/9,i%9);
			}
		}
	}
	
	@Test
	public void hintAllPossibleSpacesEmptyBoard() {
		for(int i = 0; i < 81; i++) {
			result[i] = true;
		}
		result2 = empty.hint(9, result, 0);
		for(int i = 0; i < 81; i++) {
			assertTrue(result2[i]);
		}
	}
}
