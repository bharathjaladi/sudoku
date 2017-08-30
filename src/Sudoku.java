
public class Sudoku {
	
	private int[][] board = new int[9][9];
	private boolean[][] default_or_no = new boolean[9][9];
	
	public Sudoku(int[][] input) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				board[i][j] = input[i][j];
				if(board[i][j]==0) default_or_no[i][j] = true;
				else default_or_no[i][j] = false;
			}
		}
	}
	
	public void add(int x, int y, int input) {
		board[x][y]=input;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public boolean check(int x, int y) {
		int count = 0;
		if(board[x][y]==0) return true;
		for(int i = 0; i < 9; i++) {
			if(board[x][i]==board[x][y]) count++;
			if(board[i][y]==board[x][y]) count++;
			
		}
		for(int i = x/3*3; i < (x/3+1)*3; i++) {
			for(int j = y/3*3; j < (y/3+1)*3; j++) {
				if(board[i][j] == board[x][y]) count++;
			}
		}
		return count <= 3;
	}
	
	public boolean[] hint(int d, boolean[] toCheck, int count) {
		if(count == 81) return toCheck;
		if(!toCheck[count])
			return hint(d, toCheck, ++count);
		if (board[count/9][count%9] == d) {
			for(int i = 0; i < 9; i++) {
				toCheck[count/9*9+i] = false;
				toCheck[count%9+9*i] = false;
			}
			for(int i = count/9/3*3; i < (count/9/3+1)*3; i++) {
				for(int j = count%9/3*3; j < (count%9/3+1)*3; j++) {
					toCheck[i*9+j] = false;
				}
			}
		}
		else if(board[count/9][count%9] != 0) {
			toCheck[count] = false;
		}
		count++;
		return hint(d,toCheck,count);
	}
	
	public int getValue(int x, int y) {
		return board[x][y];
	}
	
	public boolean getBoolValue(int x, int y) {
		return default_or_no[x][y];
	}
	
	public void remove(int x, int y) {
		board[x][y] = 0;
	}
	
}
