
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import javax.swing.*;

public class Game implements Runnable {
	
	private ArrayList<int[][]> puzzles = new ArrayList<int[][]>();
	private ArrayList<int[][]> savedPuzzles = new ArrayList<int[][]>();
	private int num = 0;
	
	public void run() {
		
		Reader r = null;
		BufferedReader r1 = null;
		Reader r3 = null;
		BufferedReader r2 = null;
		try {
			r = new FileReader("puzzles.txt");
			r1 = new BufferedReader(r);
			r3 = new FileReader("savedPuzzles.txt");
			r2 = new BufferedReader(r3);
			while(r1.ready()) {
			int[][] temp = new int[9][9];
			int[][] temp2 = new int[9][9];
			for(int i = 0; i < 9; i++) {
				String line = r1.readLine();
				String line2 = r2.readLine();
				for(int j = 0; j < 9; j++) {
					temp[i][j] = Integer.parseInt(line.substring(j,j+1));
					temp2[i][j] = Integer.parseInt(line2.substring(j,j+1));
				}
			}
			puzzles.add(temp);
			savedPuzzles.add(temp2);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				r.close();
				r1.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		final JFrame frame = new JFrame("Sudoku");
		frame.setLocation(100, 100);
		final GameCourt court = new GameCourt(puzzles.get(num));
		frame.add(court, BorderLayout.CENTER);
		
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.NORTH);
		status_panel.setLayout(new GridLayout(2,2));
		final JPanel numbers_panel = new JPanel();
		frame.add(numbers_panel, BorderLayout.SOUTH);
		
		final JButton prev = new JButton("Previous Puzzle");
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num--;
				if(num < 0) num = puzzles.size()-1;
				court.change(puzzles.get(num));
			}
		});
		status_panel.add(prev);
		
		final JButton next = new JButton("Next Puzzle");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num++;
				if(num >= puzzles.size()) num = 0;
				court.change(puzzles.get(num));
			}
		});
		status_panel.add(next);
		
		final JButton save = new JButton("Save Puzzle");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savedPuzzles.set(num, court.getBoard());	
				try {
					save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		});
		status_panel.add(save);
		
		final JButton open = new JButton("Open Saved Version");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.change(puzzles.get(num),savedPuzzles.get(num));
			}});
		status_panel.add(open);
		
		numbers_panel.setLayout(new GridLayout(3,4));
		final JButton one = new JButton("1");
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(1);
			}
		});
		numbers_panel.add(one);

		final JButton two = new JButton("2");
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(2);
			}
		});
		numbers_panel.add(two);

		final JButton three = new JButton("3");
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(3);
			}
		});
		numbers_panel.add(three);

		final JButton four = new JButton("4");
		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(4);
			}
		});
		numbers_panel.add(four);
		
		final JButton five = new JButton("5");
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(5);
			}
		});
		numbers_panel.add(five);
		
		final JButton six = new JButton("6");
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(6);
			}
		});
		numbers_panel.add(six);
		
		final JButton seven = new JButton("7");
		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(7);
			}
		});
		numbers_panel.add(seven);
		
		final JButton eight = new JButton("8");
		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(8);
			}
		});
		numbers_panel.add(eight);
		
		final JButton nine = new JButton("9");
		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(9);
			}
		});
		numbers_panel.add(nine);
		
		final JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setCurrent(0);
			}
		});
		numbers_panel.add(clear);
		
		final JButton hint = new JButton("Hint");
		hint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.hint();
			} 
		});
		numbers_panel.add(hint);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		final JButton help = new JButton("Help");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "To read the rules of Sudoku, "
						+ "please visit this website: http://www.sudoku.name/rules/en."
						+ "\nTo fill squares, first click the number you wish to fill "
						+ "squares with and then click on the square. If it turns red,\n"
						+ "that means your move is incorrect given the current state of "
						+ "the board. However, just because the square does\nnot turn red "
						+ "does not mean your move is correct - it may lead to inaccuracies"
						+ " as you progress.\nTo clear a square that you filled, click on the"
						+ " clear button and then click on the square you wish to clear.\n"
						+ "To get a hint, click on a number and then click on the hint button. "
						+ "Given the current state of the board, all\npossible places where that"
						+ " number could potentially be placed will be highlighted. Note: All of "
						+ "these places\nwill not necessarily be filled with this number when the "
						+ "board is solved. To save your puzzle so you can come\nback to it later, "
						+ "press the save puzzle button. To retrieve the most recently saved version "
						+ "of the current puzzle,\nclick on the open saved version button. Puzzles will"
						+ " stay saved even if you close the applictation and come back\nlater! To attempt a "
						+ "new puzzle, click on the previous or next puzzle buttons. Good luck!", "Help", 1);
			} 
		});
		numbers_panel.add(help);
	}
	
	public void save() throws IOException {
		BufferedWriter w = new BufferedWriter(new FileWriter("savedPuzzles.txt"));
		for(int[][] p : savedPuzzles) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					w.write(Integer.toString(p[i][j]));
				}
				System.out.println();
				w.newLine();
			}
			}
		w.close();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
