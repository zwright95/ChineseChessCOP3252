
import java.util.ArrayList;

public class Horse extends Piece
{

    Horse(int a, int b, int player)
    {
        super(a, b, player);
    }

    public ArrayList<Pair> availableMove(Pieces board)
    {
        int[][] board_state = board.getState();
        ArrayList<Pair> available = new ArrayList<>();
        //see if a direction is blocked, if not add moves in that direction
        try {
            if (board_state[x - 1][y] == 0) {
                available.add(new Pair(x - 2, y - 1));
                available.add(new Pair(x - 2, y + 1));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (board_state[x + 1][y] == 0) {
                available.add(new Pair(x + 2, y - 1));
                available.add(new Pair(x + 2, y + 1));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (board_state[x][y + 1] == 0) {
                available.add(new Pair(x - 1, y + 2));
                available.add(new Pair(x + 1, y + 2));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (board_state[x][y - 1] == 0) {
                available.add(new Pair(x - 1, y - 2));
                available.add(new Pair(x + 1, y - 2));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        //remove any move out of bound or move on your own piece
        for (int i = 0; i < available.size(); ++i) {
            Pair temp = available.get(i);
            if (temp.x < 0 || temp.x >= 9) {
                available.remove(i);
                --i;
            } else if (temp.y < 0 || temp.y >= 10) {
                available.remove(i);
                --i;
            } else if (board_state[temp.x][temp.y] == player) {
                available.remove(i);
                --i;
            }
        }
        return available;
    }
}
