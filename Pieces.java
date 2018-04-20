import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pieces{
	private final ArrayList<Piece> pieces;
    private int [][] board_state;
    static Piece error;
    //these represent the points that pieces will be placed on the screen
    static int []xPoints = new int[]{100, 150, 200, 250, 300, 350, 400, 450, 500};
	static int []yPoints = new int[]{75, 125, 175, 225, 275, 325, 375, 425, 475, 525};

    public Pieces(){
    	error = new Piece(100, 100, 0);
    	pieces = new ArrayList<>(32);
    	initPieces();
    	updateBoardState();
    }

    private void initPieces()
    {
        //General
        pieces.add(new General(4, 0, 1));
        pieces.add(new General(4, 9, 2));
        //Counselor
        pieces.add(new Counselor(3, 0, 1));
        pieces.add(new Counselor(5, 0, 1));
        pieces.add(new Counselor(3, 9, 2));
        pieces.add(new Counselor(5, 9, 2));
        //Elephant
        pieces.add(new Elephant(2, 0, 1));
        pieces.add(new Elephant(6, 0, 1));
        pieces.add(new Elephant(2, 9, 2));
        pieces.add(new Elephant(6, 9, 2));
        //Horse
        pieces.add(new Horse(1, 0, 1));
        pieces.add(new Horse(7, 0, 1));
        pieces.add(new Horse(1, 9, 2));
        pieces.add(new Horse(7, 9, 2));
        //Chariot
        pieces.add(new Chariot(0, 0, 1));
        pieces.add(new Chariot(8, 0, 1));
        pieces.add(new Chariot(0, 9, 2));
        pieces.add(new Chariot(8, 9, 2));
        //Cannon
        pieces.add(new Cannon(1, 2, 1));
        pieces.add(new Cannon(7, 2, 1));
        pieces.add(new Cannon(1, 7, 2));
        pieces.add(new Cannon(7, 7, 2));
        //Pawn
        pieces.add(new Pawn(0, 3, 1));
        pieces.add(new Pawn(2, 3, 1));
        pieces.add(new Pawn(4, 3, 1));
        pieces.add(new Pawn(6, 3, 1));
        pieces.add(new Pawn(8, 3, 1));
        pieces.add(new Pawn(0, 6, 2));
        pieces.add(new Pawn(2, 6, 2));
        pieces.add(new Pawn(4, 6, 2));
        pieces.add(new Pawn(6, 6, 2));
        pieces.add(new Pawn(8, 6, 2));
    }

    private void updateBoardState()
    {
    	board_state = new int[9][10];
    	for(int i = 0; i < pieces.size(); ++i)
        {
            Piece temp = pieces.get(i);
            board_state[temp.getX()][temp.getY()] = temp.getPlayer();
        }
    }

    public int[][] getState()
    {
        updateBoardState();
        return board_state;
    }

    //function to check if the user pressed a piece
    public Piece validPiece(Point point)
    {
    	int x = (int) point.getX();
		int y = (int) point.getY();
		int pieceX = 100, pieceY = 100;
		Piece piece = new Piece(100, 100, 0);
		for(int i = 0; i < 9; i++){
			if(xPoints[i] + 20 > x && xPoints[i] - 20 < x)
			{
				pieceX = i;
				break;
			}
		}
		for(int i = 0; i < 10; i++){
			if(yPoints[i] + 20 >= y && yPoints[i] - 20 <= y)
			{
				pieceY = i;
				break;
			}
		}

		for(int i = 0; i < pieces.size(); i++)
		{
			Piece temp = pieces.get(i);
			if(temp.getX() == pieceX && temp.getY() == pieceY)
			{
				return temp;
			}
		}
		return piece;
    }

    //function to get the spot that the user clicked to move to
    public Pair getSpot(Point point)
    {
    	int x = (int) point.getX();
		int y = (int) point.getY();
		int pieceX = 100, pieceY = 100;	
		for(int i = 0; i < 9; i++){
			if(xPoints[i] + 5 > x && xPoints[i] - 5 < x)
			{
				pieceX = i;
				break;
			}
		}
		for(int i = 0; i < 10; i++){
			if(yPoints[i] + 5 >= y && yPoints[i] - 5 <= y)
			{
				pieceY = i;
				break;
			}
		}

		return new Pair(pieceX, pieceY);
    }
    //function to actually move the piece
    public String movePiece(Piece piece, Pair pair)
    {
    	String retString = "";
    	for(int i = 0; i < pieces.size(); i++)
    	{
    		Piece temp = pieces.get(i);
    		//piece is already at this position
    		if(temp.getX() == pair.x && temp.getY() == pair.y)
    		{
    			//check if opponent piece
    			if(temp.getPlayer() != piece.getPlayer())
    			{
    				pieces.remove(i); //remove old piece
    				retString = "Player " + piece.getPlayer() + " captured a piece!";
    				break;
    			}
    			else//not opponents piece, can't move here
    			{
    				return "Cannot capture own piece";
    			}
    		}
    	}
    	//if made it out here then need to update the piece's location
    	int index = 0;
    	for(int i = 0; i < pieces.size(); i++)
    	{
    		Piece temp = pieces.get(i);
    		//found piece
    		if(temp.equals(piece))
    		{
    			index = i;
    			break;
    		}
    	}
    	//change x and y for piece
    	piece.setX(pair.x);
    	piece.setY(pair.y);
    	pieces.set(index, piece);
    	if(retString.equals(""))
    		retString = "Moved piece to: " + pair.x + ", " + pair.y;
    	return retString;
    }

    public Piece getPlayerGeneral(int a){
    	for (int i = 0; i < pieces.size(); ++i)
        {
            String temp = pieces.get(i).getClass().getSimpleName();
            if (temp.compareTo("General") == 0 && pieces.get(i).getPlayer() == a)
                return pieces.get(i);
        }
        System.out.println("error");
        return error;
    }

    public Piece findPiece (int a, int b, int player)
    {
        Piece temp = new Piece(a, b, player);
        for (int i = 0; i < pieces.size(); ++i)
        {
            if (pieces.get(i).equals(temp) == true)  
                return pieces.get(i);
        }
        System.out.println("error");
        return error;
    }
    
    public void removePiece (int a, int b, int player)
    {
        Piece temp = new Piece(a, b, player);
        for (int i = 0; i < pieces.size(); ++i)
        {
            if (pieces.get(i).equals(temp) == true)   
            {
                pieces.remove(i);
                return;
            }
        }
        System.out.println("error");
    }

    public ArrayList<Piece> getPlayerPieces(int a)
    {
    	ArrayList<Piece> player_pieces = new ArrayList<> ();
        for (int i = 0; i < pieces.size(); ++i)
        {
           if(pieces.get(i).getPlayer() == a)
               player_pieces.add(pieces.get(i));
        }
        return player_pieces;
    }

    //public boolean check(int a)
    //public boolean checkMate(int a)

    public void draw(Graphics2D g2d)
    {
    	for(int i = 0; i < pieces.size(); i++)
    	{
    		Piece temp = pieces.get(i);
    		if(temp.getPlayer() == 1)
    		{
    			g2d.setColor(Color.BLACK);
    		}
    		else
    			g2d.setColor(Color.RED);
    		//board positions
    		int a = temp.getX();
    		int b = temp.getY();
    		String name = temp.getClass().getSimpleName();
    		//draw piece
    		g2d.fillOval(xPoints[a] - 25, yPoints[b] - 25, 50, 50);
    		//draw name of piece
    		g2d.setColor(Color.WHITE);
  			if(name.equals("Counselor")) //advisor is same as counselor
  				g2d.drawString("Advisor", xPoints[a] - 25, yPoints[b]);
  			else if(name.equals("Elephant"))//shorter name
  				g2d.drawString("Eleph", xPoints[a] - 15, yPoints[b]);
    		else if(name.equals("Pawn") || name.equals("Horse"))
    			g2d.drawString(name, xPoints[a] - 20, yPoints[b]);
    		 else
    			g2d.drawString(name, xPoints[a] - 25, yPoints[b]);
    	}

    }
}