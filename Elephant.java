import java.util.ArrayList;
public class Elephant extends Piece
{
    Elephant(int a, int b, int player)
    {
        super(a, b, player);
    }

    public ArrayList<Pair> availableMove(Pieces board)
   	{
        int [][] board_state = board.getState();
        ArrayList<Pair> available = new ArrayList<> (4);
        try 
        {
            if (board_state[x - 1][y - 1] == 0)
                available.add(new Pair(x - 2, y - 2));
        }
        catch (IndexOutOfBoundsException e) {}
        try
        {
            if (board_state[x - 1][y + 1] == 0)
                available.add(new Pair(x - 2, y + 2));
        }
        catch (IndexOutOfBoundsException e) {}
        try
        {
            if (board_state[x + 1][y + 1] == 0)
                available.add(new Pair(x + 2, y + 2));
        }
        catch (IndexOutOfBoundsException e) {}
        try
        {
            if (board_state[x + 1][y - 1] == 0)
                available.add(new Pair(x + 2, y - 2));
        }
        catch (IndexOutOfBoundsException e) {}
        
        int y_lower, y_upper;
        y_lower = y_upper = 0;
        if (player == 1)
        {
            y_lower = 0;
            y_upper = 4;
        }
        else if (player == 2)
        {
            y_lower = 5;
            y_upper = 9;
        }
        
        for (int i = 0; i < available.size(); ++i)
        {
            Pair temp = available.get(i);
            if (temp.x < 0 || temp.x >= 9)
            {
                available.remove(i);
                --i;
            }
            else if (temp.y < y_lower || temp.y > y_upper)
            {
                available.remove(i);
                --i;
            }
            else if (board_state[temp.x][temp.y] == player)
            {
                available.remove(i);
                --i;
            }
        }
        return available;
    }
}