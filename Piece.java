import java.io.Serializable;
import java.util.*;

public class Piece implements Serializable
{
    protected int x, y;
    protected int player;
    
    Piece ()
    {
        this(0,0,0);
    }
    
    Piece (int a, int b, int player)
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
    
    public boolean moveTo (int a, int b, Pieces board)    
    {
        int [][] board_state = board.getState();
        ArrayList<Pair> available = availableMove(board);
        Pair temp = new Pair (a, b);
        
        boolean flag = false;
        for (int i = 0; i < available.size(); ++i)
        {
            
            if (temp.equals(available.get(i)) == true)
            {
                if (board_state[a][b] == getOpponent(player))
                {
                    board.removePiece (a, b, getOpponent(player));
                    flag = true;
                }
                x = a;
                y = b;
                return flag;
            }
        }
        System.out.println("Invaild Move");
        return flag;
    }
        
    public boolean equals (Piece a)
    {
        return x == a.x && y == a.y && player == a.player;
    }

    public ArrayList<Pair> availableMove(Pieces board)
    {
        return new ArrayList<> ();
    }
}