import java.util.ArrayList;

public class Chariot extends Piece
{
    Chariot (int a, int b, int player)
    {
        super(a, b, player);
    }

    public ArrayList<Pair> availableMove(Pieces board)
    {
        int [][] board_state = board.getState();
        ArrayList<Pair> available = new ArrayList<> ();
        
        for (int i = x + 1; i < 9; ++i)
        {
            if (board_state[i][y] == 0)
                available.add(new Pair(i, y));
            else if (board_state[i][y] == getOpponent(player))
            {
                available.add(new Pair(i, y));
                break;
            }
            else if (board_state[i][y] == player)
                break;
        }
        
        for (int i = x - 1; i >= 0; --i)
        {
            if (board_state[i][y] == 0)
                available.add(new Pair(i, y));
            else if (board_state[i][y] == getOpponent(player))
            {
                available.add(new Pair(i, y));
                break;
            }
            else if (board_state[i][y] == player)
                break;    
        }
        
        for (int i = y + 1; i < 10; ++i)
        {
            if (board_state[x][i] == 0)
                available.add(new Pair(x, i));
            else if (board_state[x][i] == getOpponent(player))
            {
                available.add(new Pair(x, i));
                break;
            }
            else if (board_state[x][i] == player)
                break;  
        }
        
        for (int i = y - 1; i >= 0; --i)
        {
            if (board_state[x][i] == 0)
                available.add(new Pair(x, i));
            else if (board_state[x][i] == getOpponent(player))
            {
                available.add(new Pair(x, i));
                break;
            }
            else if (board_state[x][i] == player)
                break;  
        }
        return available;
    }
}