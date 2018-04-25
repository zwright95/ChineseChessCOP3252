
import java.io.Serializable;
import java.util.*;

public class Piece implements Serializable
{

    protected int x, y;
    protected int player;

    //default constructor
    Piece()
    {
        this(0, 0, 0);
    }

    //init piece with x of a, y of b, and player
    Piece(int a, int b, int player)
    {
        x = a;
        y = b;
        this.player = player;
    }

    //setters to adjust the piece's location
    public void setX(int a)
    {
        x = a;
    }

    public void setY(int b)
    {
        y = b;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getPlayer()
    {
        return player;
    }

    static public int getOpponent(int a)
    {
        switch (a)
        {
            case 1:
                return 2;
            case 2:
                return 1;
        }
        return 0;
    }

    //used for checkmate method
    //try move this to (a,b)
    public void moveTo(int a, int b, Pieces board)
    {
        int[][] board_state = board.getState();
        ArrayList<Pair> available = availableMove(board);
        Pair temp = new Pair(a, b);
        //if (a,b) is in available, move
        for (int i = 0; i < available.size(); ++i)
        {
            if (temp.equals(available.get(i)) == true)
            {
                //a opponent's piece is at (a,b), remove it
                if (board_state[a][b] == getOpponent(player))
                {
                    board.removePiece(a, b, getOpponent(player));
                }
                x = a;
                y = b;
            }
        }
        System.out.println("Invaild Move");
    }

    public boolean equals(Piece a)
    {
        return x == a.x && y == a.y && player == a.player;
    }

    public ArrayList<Pair> availableMove(Pieces board)
    {
        return new ArrayList<>();
    }
}
