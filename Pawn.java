
import java.util.ArrayList;

public class Pawn extends Piece
{

    public Pawn(int a, int b, int player)
    {
        super(a, b, player);
    }

    public ArrayList<Pair> availableMove(Pieces board)
    {
        int[][] board_state = board.getState();
        ArrayList<Pair> available = new ArrayList<>(1);
        //move down for 1 and up for 2
        if (player == 1)
        {
            available.add(new Pair(x, y + 1));
        } else if (player == 2)
        {
            available.add(new Pair(x, y - 1));
        }

        int y_bound = 0;
        if (player == 1)
        {
            y_bound = 4;
        } 
        else if (player == 2)
        {
            y_bound = 5;
        }
        //if piece move pass river, add left and right
        if ((y > y_bound && player == 1) || (y < y_bound && player == 2))
        {
            available.add(new Pair(x - 1, y));
            available.add(new Pair(x + 1, y));
        }
        //remove any move out of bound or move on your own piece
        for (int i = 0; i < available.size(); ++i)
        {
            Pair temp = available.get(i);
            if (temp.x < 0 || temp.x >= 9)
            {
                available.remove(i);
                --i;
            } else if (temp.y < 0 || temp.y >= 10)
            {
                available.remove(i);
                --i;
            } else if (board_state[temp.x][temp.y] == player)
            {
                available.remove(i);
                --i;
            }
        }
        return available;
    }
}
