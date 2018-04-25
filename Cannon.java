import java.util.ArrayList;

public class Cannon extends Piece
{
    Cannon(int a, int b, int player)
    {
        super(a, b, player);
    }

    public ArrayList<Pair> availableMove(Pieces board)
    {
        int [][] board_state = board.getState();
        ArrayList<Pair> available = new ArrayList<> (); 
       
        boolean flag = false;
        boolean flag2 = true;
        //add all move in four direction
        for (int i = x + 1; i < 9; ++i)
        {
            //add all move until another piece
            if (board_state[i][y] == 0 && flag2 == true)
                available.add(new Pair(i, y));
            else if (board_state[i][y] != 0 && flag == false)
            {
                flag = true;
                flag2 = false;
            }
            //add enemy attack move
            else if (board_state[i][y] == getOpponent(player) && flag == true)
            {
                available.add(new Pair(i, y));
                break;
            }
        }
        
        flag = false;
        flag2 = true;
        for (int i = x - 1; i >= 0; --i)
        {
            if (board_state[i][y] == 0 && flag2 == true)
                available.add(new Pair(i, y));
            else if (board_state[i][y] != 0 && flag == false)
            {
                flag = true;
                flag2 = false;
            }
            else if (board_state[i][y] == getOpponent(player) && flag == true)
            {
                available.add(new Pair(i, y));
                break;
            }    
        }
        
        flag = false;
        flag2 = true;
        for (int i = y + 1; i < 10; ++i)
        {
            if (board_state[x][i] == 0 && flag2 == true)
                available.add(new Pair(x, i));
            else if (board_state[x][i] != 0 && flag == false)
            {
                flag = true;
                flag2 = false;
            }
            else if (board_state[x][i] == getOpponent(player) && flag == true)
            {
                available.add(new Pair(x, i));
                break;
            }    
        }
        
        flag = false;
        flag2 = true;
        for (int i = y - 1; i >= 0; --i)
        {
            if (board_state[x][i] == 0 && flag2 == true)
                available.add(new Pair(x, i));
            else if (board_state[x][i] != 0 && flag == false)
            {
                flag = true;
                flag2 = false;
            }
            else if (board_state[x][i] == getOpponent(player) && flag == true)
            {
                available.add(new Pair(x, i));
                break;
            }    
        }
        return available;
    }
}