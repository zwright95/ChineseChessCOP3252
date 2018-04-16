import javax.swing.*;
import java.awt.*;

public class Pieces{
	static GamePiece [][] board = new GamePiece[9][10];
	static int []xPoints = new int[]{100, 150, 200, 250, 300, 350, 400, 450, 500};
	static int []yPoints = new int[]{75, 125, 175, 225, 275, 295, 345, 395, 445, 495};
	public Pieces(){
		//initialize pieces
		board[0][0] = new Chariot("Chariot", Color.BLACK, 100, 75);
		board[1][0] = new Horse("Horse", Color.BLACK, 150, 75);
		board[2][0] = new Elephant("Elep", Color.BLACK, 200, 75);
		board[3][0] = new Councellor("Councel", Color.BLACK, 250, 75);
		board[4][0] = new General("General", Color.BLACK, 300, 75);
		board[5][0] = new Councellor("Councel", Color.BLACK, 350, 75);
		board[6][0] = new Elephant("Elep", Color.BLACK, 400, 75);
		board[7][0] = new Horse("Horse", Color.BLACK, 450, 75);
		board[8][0] = new Chariot("Chariot", Color.BLACK, 500, 75);
		for(int i = 0; i < 9; i++)
			board[i][1] = null;
		board[0][2] = null;
		board[1][2] = new Cannon("Cannon", Color.BLACK, 150, 175);
		for(int i = 2; i < 7; i++)
			board[i][2] = null;
		board[7][2] = new Cannon("Cannon", Color.BLACK, 450, 175);
		board[8][2] = null;
		board[0][3] = new Pawn("Pawn", Color.BLACK, 100, 225);
		board[1][3] = null;
		board[2][3] = new Pawn("Pawn", Color.BLACK, 200, 225);
		board[3][3] = null;
		board[4][3] = new Pawn("Pawn", Color.BLACK, 300, 225);
		board[5][3] = null;
		board[6][3] = new Pawn("Pawn", Color.BLACK, 400, 225);
		board[7][3] = null;
		board[8][3] = new Pawn("Pawn", Color.BLACK, 500, 225);
		for(int i = 4; i < 6; i++)
		{
			for(int j = 0; j < 9; j++)
				board[j][i] = null;
		}
		board[0][6] = new Pawn("Pawn", Color.RED, 100, 345);
		board[1][6] = null;
		board[2][6] = new Pawn("Pawn", Color.RED, 200, 345);
		board[3][6] = null;
		board[4][6] = new Pawn("Pawn", Color.RED, 300, 345);
		board[5][6] = null;
		board[6][6] = new Pawn("Pawn", Color.RED, 400, 345);
		board[7][6] = null;
		board[8][6] = new Pawn("Pawn", Color.RED, 500, 345);
		board[0][7] = null;
		board[1][7] = new Cannon("Cannon", Color.RED, 150, 395);
		for(int i = 2; i < 7; i++)
			board[i][7] = null;
		board[7][7] = new Cannon("Cannon", Color.RED, 450, 395);
		board[8][7] = null;
		for(int i = 0; i < 9; i++)
			board[i][8] = null;
		board[0][9] = new Chariot("Chariot", Color.RED, 100, 495);
		board[1][9] = new Horse("Horse", Color.RED, 150, 495);
		board[2][9] = new Elephant("Elep", Color.RED, 200, 495);
		board[3][9] = new Councellor("Councel", Color.RED, 250, 495);
		board[4][9] = new General("General", Color.RED, 300, 495);
		board[5][9] = new Councellor("Councel", Color.RED, 350, 495);
		board[6][9] = new Elephant("Elep", Color.RED, 400, 495);
		board[7][9] = new Horse("Horse", Color.RED, 450, 495);
		board[8][9] = new Chariot("Chariot", Color.RED, 500, 495);
	}

	public boolean movePiece(int r, int c, Point point){
		int x = (int) point.getX();
		int y = (int) point.getY();
		int xArrayVal = 0;
		int yArrayVal = 0;
		int pieceX = 0, pieceY = 0;
		for(int i = 0; i < 9; i++){
			if(xPoints[i] + 5 > x && xPoints[i] - 5 < x)
			{
				pieceX = xPoints[i];
				xArrayVal = i;
				System.out.println(i);
				break;
			}
		}
		for(int i = 0; i < 10; i++){
			if(yPoints[i] + 5 >= y && yPoints[i] - 5 <= y)
			{
				pieceY = yPoints[i];
				yArrayVal = i;
				System.out.println(i);
				break;
			}
		}
		if(pieceX != 0 && pieceY != 0){
			GamePiece piece = board[r][c];
			//if spot is empty
			if(board[xArrayVal][yArrayVal] == null){
				piece.x = pieceX;
				piece.y = pieceY;
				//check if this piece can move to this spot


				board[xArrayVal][yArrayVal] = piece;
				board[r][c] = null;
			}
			//move to spot that is occupied
			//check if piece is a opponents piece
			return true;
		}
		return false;
	}

	public String validPiece(Point point){
		int x = (int) point.getX();
		int y = (int) point.getY();
		int pieceX = 0, pieceY = 0;
		for(int i = 0; i < 9; i++){
			if(xPoints[i] + 5 > x && xPoints[i] - 5 < x)
			{
				pieceX = xPoints[i];
				break;
			}
		}
		for(int i = 0; i < 10; i++){
			if(yPoints[i] + 5 >= y && yPoints[i] - 5 <= y)
			{
				pieceY = yPoints[i];
				break;
			}
		}
		if(pieceX != 0 && pieceY != 0){//actual point was clicked
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 10; j++)
				{
					if(board[i][j] != null)
					{
						if(board[i][j].getX() == pieceX && board[i][j].getY() == pieceY)
						{
							return Integer.toString(i) + "," + Integer.toString(j);
						}
					}
				}
			}
			return "No";
		}
		return "No";
	}

	public void draw(Graphics2D g2d)
	{
		int i = 0, j = 0;
		//drawing pieces
		for(i = 0; i < 9; i++)
		{
			for(j = 0; j < 10; j++)
			{
				if(board[i][j] != null)
				{
					g2d.setColor(board[i][j].getColor());
					g2d.fillOval(board[i][j].getX() - 25, board[i][j].getY() - 25, 50, 50);
					g2d.setColor(Color.WHITE);
					g2d.drawString(board[i][j].getName(), board[i][j].getX() - 25, board[i][j].getY());
				}
			}
		}
	}

}