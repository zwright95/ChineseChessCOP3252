import java.util.ArrayList;

public class Counselor extends Piece
{
    Counselor (int a, int b, int player)
    {
        super(a, b, player);
    }

    public ArrayList<Pair> availableMove(Pieces board)
    {
        int [][] board_state = board.getState();
        ArrayList<Pair> available = new ArrayList<> (4);
        
        available.add(new Pair(x - 1, y - 1));
        available.add(new Pair(x + 1, y + 1));
        available.add(new Pair(x + 1, y - 1));
        available.add(new Pair(x - 1, y + 1));
        
        int y_lower, y_upper;
        y_lower = y_upper = 0;
        if (player == 1)
        {
            y_lower = 0;
            y_upper = 2;
        }
        else if (player == 2)
        {
            y_lower = 7;
            y_upper = 9;
        }
        //remove move out of bound
        for (int i = 0; i < available.size(); ++i)
        {
            Pair temp = available.get(i);
            if (temp.x < 3 || temp.x > 5)
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