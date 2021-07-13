package whackamole;
import java.util.Random;
import java.util.*;


public class WhackAMole {
    //Instance variables
    int score;
    int molesLeft;
    int attemptsLeft;
    
    char[][] moleGrid;
    
    //Constructors
    public WhackAMole(int numAttempts, int gridDimension) {
        /** Initialize the moleGrid with the appropriate character
         * Constructor for the game, specifies the number of whacks
         * allowed and the grid dimensions.
         */
	int placedMoles = 0;
	Random row = new Random();
	Random col = new Random();
	
	
	molesLeft = 10;
	attemptsLeft = numAttempts;
		
	moleGrid = new char[gridDimension][gridDimension];
	for (int i = 0; i < moleGrid.length; i++) {
	    for (int j = 0; j < moleGrid[0].length; j++) {
		moleGrid[i][j] = '*';
	    }
	}
	
	while(placedMoles < molesLeft) {
	    int rand_row = row.nextInt(moleGrid.length); 
	    int rand_col = col.nextInt(moleGrid[0].length);
	    if (place(rand_row, rand_col) == true) {
		placedMoles++;
	    }
	}	
    }
    
    //Methods
   public boolean place(int x, int y) {
        /** given a location, place a mole at that location. 
         * return true if you can place a mole
         * update number of moles left
         */
	if (moleGrid[x][y] != 'M') {
	    moleGrid[x][y] = 'M';
	    return true; 
	}
	else {
	    return false;
	}	
    }
    
    public void whack(int x, int y) {
	/** Given a location, take a whack at that location. If the location
	 * contains a mole, the score, number of attemptsLeft, and molesLeft
	 * is updated. If that location does not contain a mole, only 
	 * attemptsLeft is updated.
	 */
	attemptsLeft -= 1;
	if (moleGrid[x][y] == 'M') {
	    molesLeft -= 1;
	    score +=1;
	    moleGrid[x][y] = 'W';
	    System.out.println("You got one!");
	}
    }
    
    public void printGrid() {
	/** Print the grid completely. This is effectively dumping the 2d 
	 * array on the screen. The places where the moles are should be 
	 * printed as an ‘M’. The places where the moles have been whacked 
	 * should be printed as a ‘W’. The places that don’t have a mole 
	 * should be printed as *.
	 */
	for (int i = 0; i < moleGrid.length; i++) {
	    for (int j = 0; j < moleGrid[0].length; j++) {
		System.out.print(moleGrid[i][j] + " ");
	    }
	    System.out.print("\n");
	}
	System.out.println("\n");
    }
    
    public void printGridToUser() {
	/** Print the grid without showing where the moles are. For every spot 
	 * that has recorded a “whacked mole,” print out a W, or * otherwise.
	 */
	for (int i = 0; i < moleGrid.length; i++) {
	    for (int j = 0; j < moleGrid[0].length; j++) {
		if (moleGrid[i][j] == 'W') {
		    System.out.print("W ");
		} 
		else {
		    System.out.print("* ");
		}
	    }
	    System.out.print("\n");
	}
	System.out.println("\n");
    }
   
    public static void main(String[] args) {
	WhackAMole game = new WhackAMole(20, 10);
	Scanner scanner = new Scanner(System.in);
	int x;
	int y;
	boolean validInput;
	boolean givingUp = false;
	
	System.out.println("Welcome to a game of Whack A Mole!!\n");
	System.out.println("The dimensions of the mole grid are: " + game.moleGrid.length +
		           " x " + game.moleGrid[0].length + "\n");
	System.out.println("You have " + game.attemptsLeft + " attempts.\n");
	System.out.printf("To whack a mole, enter an x coordinate between %d and %d and a y coordinate between %d and %d\n\n", 0, game.moleGrid.length-1, 0, game.moleGrid[0].length-1);
	System.out.println("To end the game, enter -1, -1 as coordinates.\n");
	System.out.println("***********************************************************************");
	game.printGrid();
	while (game.molesLeft > 0 && game.attemptsLeft > 0) {
	    System.out.println(game.attemptsLeft + " attempts left. " + 
		               "Enter coordinates to whack, x then y.\n");
	    validInput = false;
	    do {
		x = scanner.nextInt();
	        y = scanner.nextInt();
	        if ((x == -1) && (y == -1)) {
		    System.out.println("Oh no, you've given up! :(");
		    System.out.println("Here is the grid:");
		    game.printGrid();
		    givingUp = true;
	        }
	        if((x < -1 || x > game.moleGrid.length) || 
			     (y < -1 || y > game.moleGrid[0].length)) {
	            System.out.println("Those are not valid values for x and y. Please try again.");
	            validInput = false;
	        }
	        else {
	            validInput = true;
	        }
	        
	    } while(validInput == false && givingUp == false);
	    
	    if (givingUp == true) {
		break;
	    }
	    System.out.printf("Whacking at position (%d, %d)...\n", x, y);
	    game.whack(x,y);
	    System.out.println("Attempts left: " + game.attemptsLeft);
	    System.out.println("Score: " + game.score);
	    game.printGridToUser();
	    
	}
	scanner.close();
	
	if (game.molesLeft == 0) {
	    System.out.println("You won!!! Congratualtions!!!");
	}
	else {
	    System.out.println("You lost :'(. Better luck next time.");
	    game.printGrid();
	}
    }

}
