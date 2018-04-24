import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;


public class Game{
	public static void main(String [] args) throws IOException{
		JFrame frame = new JFrame("Xiangqi: Chinese Chess");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int answer = JOptionPane.showConfirmDialog(frame, "Would you like to load the last saved game?");
		StartGame game = new StartGame(answer);
		frame.add(game);
		frame.setSize(700, 750);
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
	boolean endGame;
	JButton button1;
	JButton button2;
	Pair oldCoords; 	//used if a player puts themselves in Check
	public StartGame(int newGame){
		if(newGame == JOptionPane.NO_OPTION)//value is 1
		{
			pieces = new Pieces();
			System.out.println("New");
			currentPlayer = 1;
		}	
		else if(newGame == JOptionPane.YES_OPTION)//load game data value is 0
		{//pass in something to the Pieces() object to load the previous game
			//set currentPlayer to correct player
			try
			{
				FileInputStream fis = new FileInputStream("game.txt");
				ObjectInputStream ois = new ObjectInputStream(fis);
				ArrayList<Piece> tempPiece = (ArrayList<Piece>) ois.readObject();
				ois.close();
				pieces = new Pieces(tempPiece);
				Scanner s = new Scanner(new File("player.txt"));
				while(s.hasNextLine())
				{
					currentPlayer = Integer.parseInt(s.nextLine());
				}
				s.close();

			}
			catch(IOException | ClassNotFoundException e2)
			{
				pieces = new Pieces();
				currentPlayer = 1;
			}
		}
		else
		{
			currentPlayer = 1;
			pieces = new Pieces();
			System.exit(0);
		}
		board = new DrawBoard();
		pointsPressed = 0;
		pair = null;
		oldCoords = new Pair();
		printStr = "";
		endGame = false;
		button1 = new JButton("Save");
		button2 = new JButton("Forfeit");
		add(button1);
		add(button2);
		button1.addActionListener(new ActionListener()
		{
			//save game
			@Override
			public void actionPerformed(ActionEvent e)
			{//only need to store the arraylist of pieces and the current player
				try
				{
					saveGame();
				}
				catch(IOException e2) 
		        {
		          	System.out.println("Error");
		        }
			}

		});
		button2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				getOpposite();
				printStr = "Player " + currentPlayer + ", you win!";
				finishGame();
			}
		});
		addMouseListener(new MouseAdapter() { //mouse clicking
      		public void mousePressed(MouseEvent event) {
      		if(endGame != true){
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
	        			else{
	        				//valid piece was selected
	        				printStr = "Selected piece: " + piece.getX() + ", " + piece.getY();
	        				oldCoords.x = piece.getX();
	        				oldCoords.y = piece.getY();
	        			}
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
					        	String ret = pieces.movePiece(piece, pair, oldCoords);
					        	if(!ret.equals("Cannot put self in check!")){
						        	printStr = ret;	//get the return value to let the player know what type of move happened
						        	getOpposite(); //change player and change turn

						        	//start of new player move
						        	if(pieces.check(currentPlayer) == true)//check if current player is in check
						        	{
						        		printStr = "Player " + currentPlayer + " you are in check!";
						        	}

						        	if(pieces.checkMate(currentPlayer) == true){ //check if current player is in checkmate
						        			getOpposite();
						        			printStr = "Player " + currentPlayer + ", you win!";
						        			endGame = true;
						        			repaint();
						        			finishGame(); //end game 
						        	}

						        	if(pieces.staleMate(currentPlayer) == true)
						        	{
						        		getOpposite();
						        		printStr = "Player " + currentPlayer + ", you win!";
						        		endGame = true;
						        		repaint();
						        		finishGame(); //end game 
						        	}
					       		}
					       		printStr = ret;
					        	break;
					        }
					    }
	        			repaint();	//repaint board to show new pieces and new string message
	        		}
	        		else
	        			System.out.println("Invalid");
        		}
      		}
      	}
    	});
		repaint();
	}

	public void saveGame() throws IOException, FileNotFoundException
	{
		FileOutputStream fos = new FileOutputStream("game.txt", false);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(pieces.getPieces());
		oos.close();
		//write player
		FileWriter wr = new FileWriter("player.txt", false);
		wr.write(String.valueOf(currentPlayer));
		wr.close();
		JOptionPane.showOptionDialog(null,"Game Saved.", "Exit Game", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		System.exit(0);
	}

	public void finishGame()
	{
		 int n = JOptionPane.showOptionDialog(null,printStr, "Game Over!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		 if(n == 0 || n == 1 || n == -1){
		 	System.exit(0);
		 }  
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
		g2d.drawString("Player " + currentPlayer + "'s turn!", 300, 60);
		g2d.drawString(printStr, 300, 700);

	}
}
