import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game{
	public static void main(String [] args){
		JFrame frame = new JFrame("Xiangqi: Chinese Chess");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StartGame game = new StartGame();
		frame.add(game);
		frame.setSize(600, 650);
		frame.setVisible(true);
	}
}

class StartGame extends JPanel{
	DrawBoard board;	//contains the physical board
	Pieces pieces;		//contains the pieces and information about them
	int pointsPressed;	//to see how many times the user clicked
	Piece piece;		//used for moving a piece
	Pair pair;			//used for moving a piece to a point
	int currentPlayer;
	String printStr;	//string that will be printed on screen
	public StartGame(){
		board = new DrawBoard();
		pieces = new Pieces();
		pointsPressed = 0;
		pair = null;
		currentPlayer = 1;
		printStr = "";
		addMouseListener(new MouseAdapter() { //mouse clicking
      		public void mousePressed(MouseEvent event) {
        	System.out.println(event.getPoint());	//get point pressed
        	pointsPressed++;
        	if(pointsPressed == 1){	//if selecting a piece
        		piece = pieces.validPiece(event.getPoint());
        		if(piece.getX() != 100 && piece.getY() != 100 )//will be 100, 100 if not a valid piece
        		{
        			if(piece.getPlayer() != currentPlayer)//piece was selected but wrong player
        			{
		    			printStr = "Wrong player's piece!";
		    			pointsPressed--;
        			}
        			else//valid piece was selected 
        				printStr = "Selected piece: " + piece.getX() + ", " + piece.getY();
        		}
        		else{//print error message and decrease points pressed to reselect piece
        			printStr = "Not a valid piece!";
        			pointsPressed--;
        		}
        		repaint();//repaint to show the error message
        	}
        	else if(pointsPressed == 2){//if selecting a move position
        		pair = pieces.getSpot(event.getPoint());//get the spot on the board that was selected
        		pointsPressed = 0;
        		printStr = "Invalid movement, reselect piece!"; //will only remain like this if player moved wrong
        		if(pair.x != 100 && pair.y != 100)//check if a valid position was selected
        		{
        			System.out.println("Valid");
        			ArrayList<Pair> moves = piece.availableMove(pieces);	//find the players available moves
        			for (int i = 0; i < moves.size(); ++i)
				    {
				    	Pair move = moves.get(i);
				        System.out.print("Move: ");
				        System.out.println(move);
				        if(move.x == pair.x && move.y == pair.y)//move position was valid
				        {//going to move the piece here
				        	String ret = pieces.movePiece(piece, pair);
				        	printStr = ret;	//get the return value to let the player know what type of move happened
				        	getOpposite(); //change player
				        	break;
				        }
				    }
        			repaint();	//repaint board to show new pieces and new string message
        		}
        		else
        			System.out.println("Invalid");
        	}
      	}
    	});
		repaint();
	}

	public void getOpposite()
	{
		if(currentPlayer == 1)
			currentPlayer = 2;
		else
			currentPlayer = 1;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		board.draw(g2d);	//draw board
		pieces.draw(g2d);	//draw pieces

		g2d.setColor(Color.BLACK);
		//draw player string and printing string
		g2d.drawString("Player " + currentPlayer + "'s turn!", 250, 30);
		g2d.drawString(printStr, 250, 575);

	}
}
