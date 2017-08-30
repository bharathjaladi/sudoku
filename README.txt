=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: bjaladi
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D-Arrays:
  The current Sudoku board that the user is completing will be modeled as a 2-D array. 
  A standard Sudoku board is a 9x9 grid, which is further broken down as nine 3x3 grids. 
  Each square in the grid will hold a number from 1 to 9 upon completion. At the start of 
  the game, each square in the grid will either be blank or will be filled with the correct 
  number it will hold upon completion. The user will use these pre-filled squares to figure 
  out what number belongs in each blank square. The rules of the game are that each 3x3 grid, 
  row, and column must use the numbers 1 to 9 exactly once.
  
  I also used 2D-Arrays when storing all possible starting game boards and all saved game boards
  after reading in from their corresponding text files. Furthermore, I used 2D-Arrays to model 
  the grid of buttons representing each square that the user sees when playing the game.

  2. I/O:
  I will use I/O to read in from a text file. The text file will contain a number of starting 
  positions for Sudoku game boards. Each starting position will be written on 9 lines with 9 
  numbers per line. Each number will represent the value that belongs in the corresponding square 
  in the grid, with 0 representing a blank square. The user will be presented with a game board 
  and will have the opportunity to solve it. The user may also choose to move on to another game 
  board through the use of next and previous buttons. Finally, the user has the option to save a 
  game board at its current state so that he or she can return later. When the user hits save, the
  updated saved game board will be written to a text file along with the most recently saved copies
  of all the other game boards (basically the portion of the text file pertaining to the current 
  game board will be overwritten). The user can also choose to open the most recently saved version 
  of a game board, which involves reading in from the saved game boards text file.

  3. JUnit Testing:
  The recursive algorithm I implemented to return a hint to the user is tested using JUnit. 
  The algorithm will provide a unique type of hint to a player. The player will select one of the 
  nine possible numbers and the hint algorithm will return all of the possible positions where that 
  number could possibly appear on the game board given the current state. I also used JUnit to test 
  the algorithm that will highlight a square in red when the player fills it with an invalid number. 
  Invalid numbers are determined solely based off the game board as it is filled up until that point. 
  For example, if a number is incorrect but for the time being it is a valid move, it will not be 
  highlighted in red. However, if a user places a 3 next to a 3, it will be highlighted in red. The 
  JUnit testing ensures that both of these algorithms return the right values. These values, of course, 
  are later interpreted by the GUI class and cause something to be displayed. JUnit tests the algorithms 
  directly.

  4. Recursion:
  I created a hint-giving recursive function that takes 3 arguments: a number from 1-9 for which a 
  hint is desired, an boolean array whose indices representing all of the squares in the game board
  and whose values representing whether each square is a valid location for the desired number 
  (initially the array will be entirely true, subject to checking for validity by the function), and a 
  counter that will initially be 0. The function will check the square contained in the array at the 
  position of the counter. If it is equal to the desired value, that square and all squares in the same 
  row, column, or 3x3 grid will be set to false and the counter incremented by one. If it is filled, but 
  not with the desired value, it will be set to false and the counter incremented by one. Else, the 
  counter will just be incremented by one. The function will be repeatedly called with the desired value, 
  updated array, and updated counter until the counter is equal to the length of the array. At this point, 
  the array will be returned and used to output the hint to the player graphically. The hint will highlight 
  all the squares in which the desired value could possibly appear given the current game state.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Sudoku class: This class provides the logic for the game. It includes 
  the functionality for creating a Sudoku game object from an 2D integer array. 
  It also has methods for adding a value to the game board, removing a value, 
  checking if an added value is valid (in the current state), and for providing 
  a hint to the user. It also has some getter methods for ease of use.
  
  GameCourt class: This class handles the graphics and functionality for the 
  center compartment of the GUI. This is where the main game is played. It 
  creates the main GUI of the board, creates a grid of buttons representing 
  each of the squares that can be clicked, creates a Sudoku game object, and 
  colors the grid appropriately as the game progresses. The handling of the user 
  adding a number, removing a number, or otherwise affecting the game board is done
  in this class.
  
  Game class: This class handles the graphics and functionality for the outer panels 
  of the GUI and sets up the main framework for the application. It reads in the text 
  file that stores the starting positions of all game boards and the text file that stores 
  the most recently saved version of all game boards. It provides the input for the GameCourt 
  class to create the current Sudoku board. It also implements the top and bottom panels that 
  include buttons for the next puzzle, previous puzzle, open saved version, save puzzle, 
  all the numbers, getting a hint, getting help, and clearing. All of these will have some effect
  on the GameCourt object. Furthermore, it also handles saving to the text file.
  
  SudokuTests class: This class uses JUnit to test the logic of the check and hint 
  algorithms in the Sudoku class.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  There were no major stumbling blocks. However, as the algorithms I had to 
  implement were number-intensive, I had to check carefully to avoid mistakes. 
  It also took me some time to understand which class should house which functionality. 
  Finally, the last thing I needed to spend some time on was the saving functionality. 
  The particular challenge was updating the game state when reading in the most 
  recently saved version. It was important to track which numbers from the saved version 
  came with the starting position and which numbers the user had input before saving.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I think there is a really good separation of functionality because I spent a lot 
  of time thinking about it. The only thing I may refactor is moving the hint button 
  implementation into the GameCourt class. The logic is all handled in the GameCourt 
  class right now - so it is fine as is. However, I make the button in the Game class 
  because I want it to be on the bottom panel. I might as well move it entirely into 
  the GameCourt class, if graphically possible.



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  To get a lot of Sudoku boards that were stored in a text file, I used a 
  modified version of the text file found at this link 
  https://projecteuler.net/project/resources/p096_sudoku.txt.


