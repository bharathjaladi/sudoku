
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	final private JButton[][] grid = new JButton[9][9];
	private int current;
	private Sudoku game;

	public GameCourt(int[][] temp) {
		game = new Sudoku(temp);
		toDo();
		}
	
	public void toDo() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new GridLayout(9,9,-1,-1));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		for(int u = 0; u < 9; u++) {
			for(int v = 0; v < 9; v++) {
				final int i = u;
				final int j = v;
				grid[i][j] = new JButton();
				grid[i][j].setBackground(Color.WHITE);
				if(j == 2 || j == 5 || j == 8) {
					if(i == 0 || i == 3 || i == 6)
						grid[i][j].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK));
					else if(i == 8)
						grid[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK));
					else grid[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 2, Color.BLACK));
				}
				else if(j == 0) {
					if(i == 0 || i == 3 || i == 6)
						grid[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.BLACK));
					else if(i == 8)
						grid[i][j].setBorder(BorderFactory.createMatteBorder(1, 2, 2, 1, Color.BLACK));
					else grid[i][j].setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.BLACK));
				}
				else if(i == 0 || i == 3 || i == 6)
					grid[i][j].setBorder(BorderFactory.createMatteBorder(2, 1, 1, 1, Color.BLACK));
				else if(i == 8)
					grid[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.BLACK));
				else grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				grid[i][j].setBorderPainted(true);
				grid[i][j].setOpaque(true);
				if(game.getValue(i, j) != 0) {
				grid[i][j].setText(Integer.toString(game.getValue(i, j))); }
				grid[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(game.getBoolValue(i, j) == true) {
						if(current != 0) {
						for(int i = 0; i < 81; i++) {
						if(grid[i/9][i%9].getBackground() != Color.RED)
						grid[i/9][i%9].setBackground(Color.WHITE); }
						grid[i][j].setText(Integer.toString(current));
						game.add(i, j, current);
						if(!game.check(i, j)) grid[i][j].setBackground(Color.RED);
						if(game.check(i, j)) grid[i][j].setBackground(Color.WHITE);
						if(done()) {
							JOptionPane.showMessageDialog(null, "Congratulations, you completed "
									+ "this puzzle!", "Congratulations!", 1); }}
						else {
						for(int i = 0; i < 81; i++) {
						if(grid[i/9][i%9].getBackground() != Color.RED)
						grid[i/9][i%9].setBackground(Color.WHITE); }
						grid[i][j].setBackground(Color.WHITE);
						grid[i][j].setText("");
						game.remove(i,j);
						}}
					} });
				this.add(grid[i][j]);
			}}
	}
	
	public void hint() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(grid[i][j].getBackground() != Color.RED)
				grid[i][j].setBackground(Color.WHITE);}
			}
		if(current != 0) {
		boolean[] temp = new boolean[81];
		for(int i = 0; i < 81; i++) {
			temp[i] = true;
		}
		boolean[] ans = game.hint(current, temp, 0);
		for(int i = 0; i < 81; i++) {
		if(ans[i]) {
		grid[i/9][i%9].setBackground(Color.GREEN); }}
		}
	}

	public void setCurrent(int n) {
		current = n;
	}
	
	public int getCurrent() {
		return current;
	}
	
	public Sudoku getGame() {
		return game;
	}
	
	public int[][] getBoard() {
		return game.getBoard();
	}
	
	private boolean done() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(game.getValue(i,j) == 0) return false;
				if(grid[i][j].getBackground() == Color.RED) return false;
			}
		}
		return true;
	}
	
	public void change(int[][] temp) {
		game = new Sudoku(temp);
		this.removeAll();
		toDo();
	}
	
	public void change(int[][] temp, int[][] saved) {
		game = new Sudoku(temp);
		this.removeAll();
		toDo();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(saved[i][j] != temp[i][j]) {
					grid[i][j].setText(Integer.toString(saved[i][j]));
					game.add(i, j, saved[i][j]); }}}
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
				if(saved[i][j] != temp[i][j])
					if(!game.check(i, j)) grid[i][j].setBackground(Color.RED);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}
}
