import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Pieces implements Serializable
{

    private final ArrayList<Piece> pieces;
    private int[][] board_state;
    static Piece error;
    //these represent the points that pieces will be placed on the screen
    static int[] xPoints = new int[]{110, 170, 230, 290, 350, 410, 470, 530, 590};
    static int[] yPoints = new int[]{95, 155, 215, 275, 335, 395, 455, 515, 575, 635};

    public Pieces(ArrayList<Piece>... arr)
    {
        error = new Piece(100, 100, 0);
        if (arr.length > 0)//if game is being loaded
        {
            pieces = arr[0];
        } else {
            pieces = new ArrayList<>(32);
            initPieces();
        }
        updateBoardState();
    }

    public ArrayList<Piece> getPieces()
    {
        return pieces;
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
        for (int i = 0; i < pieces.size(); ++i) {
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
        for (int i = 0; i < 9; i++) {
            if (xPoints[i] + 20 > x && xPoints[i] - 20 < x) {
                pieceX = i;
                break;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (yPoints[i] + 20 >= y && yPoints[i] - 20 <= y) {
                pieceY = i;
                break;
            }
        }

        for (int i = 0; i < pieces.size(); i++) {
            Piece temp = pieces.get(i);
            if (temp.getX() == pieceX && temp.getY() == pieceY) {
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
        for (int i = 0; i < 9; i++) {
            if (xPoints[i] + 5 > x && xPoints[i] - 5 < x) {
                pieceX = i;
                break;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (yPoints[i] + 5 >= y && yPoints[i] - 5 <= y) {
                pieceY = i;
                break;
            }
        }

        return new Pair(pieceX, pieceY);
    }

    //function to actually move the piece
    public String movePiece(Piece piece, Pair pair, Pair oldCoords)
    {
        String retString = "";
        Piece removedPiece = null;
        boolean didRemove = false;
        for (int i = 0; i < pieces.size(); i++) {
            Piece temp = pieces.get(i);
            //piece is already at this position
            if (temp.getX() == pair.x && temp.getY() == pair.y) {
                //check if opponent piece
                if (temp.getPlayer() != piece.getPlayer()) {
                    removedPiece = temp;
                    pieces.remove(i); //remove old piece
                    didRemove = true;
                    retString = "Player " + piece.getPlayer() + " captured a piece!";
                    break;
                } else//not opponents piece, can't move here
                {
                    return "Cannot capture own piece";
                }
            }
        }
        //if made it out here then need to update the piece's location
        int index = 0;
        for (int i = 0; i < pieces.size(); i++) {
            Piece temp = pieces.get(i);
            //found piece
            if (temp.equals(piece)) {
                index = i;
                break;
            }
        }
        //change x and y for piece
        piece.setX(pair.x);
        piece.setY(pair.y);
        pieces.set(index, piece);
        if (check(piece.getPlayer()) == true)//put self in check
        {
            piece.setX(oldCoords.x);
            piece.setY(oldCoords.y);
            pieces.set(index, piece);
            if (didRemove == true) //readd removed piece
            {
                pieces.add(removedPiece);
            }
            retString = "Cannot put self in check!";
        }
        if (retString.equals("")) {
            retString = "Moved piece to: " + pair.x + ", " + pair.y;
        }
        return retString;
    }

    //return a player's general
    public Piece getPlayerGeneral(int a)
    {
        for (int i = 0; i < pieces.size(); ++i) {
            String temp = pieces.get(i).getClass().getSimpleName();
            if (temp.compareTo("General") == 0 && pieces.get(i).getPlayer() == a) {
                return pieces.get(i);
            }
        }
        System.out.println("error");
        return error;
    }

    //return piece with (x,y) of z player
    public Piece findPiece(int a, int b, int player)
    {
        Piece temp = new Piece(a, b, player);
        for (int i = 0; i < pieces.size(); ++i) {
            if (pieces.get(i).equals(temp) == true) {
                return pieces.get(i);
            }
        }
        System.out.println("error");
        return error;
    }

    //remove piece at (x,y) of player
    public void removePiece(int a, int b, int player)
    {
        Piece temp = new Piece(a, b, player);
        for (int i = 0; i < pieces.size(); ++i) {
            if (pieces.get(i).equals(temp) == true) {
                pieces.remove(i);
                return;
            }
        }
        System.out.println("error");
    }

    //return all piece of a player
    public ArrayList<Piece> getPlayerPieces(int a)
    {
        ArrayList<Piece> player_pieces = new ArrayList<>();
        for (int i = 0; i < pieces.size(); ++i) {
            if (pieces.get(i).getPlayer() == a) {
                player_pieces.add(pieces.get(i));
            }
        }
        return player_pieces;
    }

    //return true if current player is in check
    public boolean check(int a)
    {
        ArrayList<Piece> player_pieces = getPlayerPieces(Piece.getOpponent(a));
        Piece general = getPlayerGeneral(a);
        Pair general_xy = new Pair(general.getX(), general.getY());

        //check if any opponent's piece could move to general's position
        for (int i = 0; i < player_pieces.size(); ++i) {
            Piece temp = player_pieces.get(i);
            ArrayList<Pair> check_moves = temp.availableMove(this);
            if (contains(check_moves, general_xy)) {
                return true;
            }

        }
        return false;
    }

    //check if ArrayList contains b pair
    private static boolean contains(ArrayList<Pair> a, Pair b)
    {
        for (int i = 0; i < a.size(); ++i) {
            if (a.get(i).equals(b)) {
                return true;
            }
        }
        return false;
    }

    public boolean staleMate(int a)
    {//see if current player has no legal moves
        ArrayList<Piece> player_pieces = getPlayerPieces(a);
        ArrayList<Pair> allMoves = new ArrayList<Pair>();
        for (int i = 0; i < player_pieces.size(); ++i) {
            Piece temp = player_pieces.get(i);
            allMoves.addAll(temp.availableMove(this));
        }

        return allMoves.isEmpty();
    }

    //check if current player is in checkmate
    public boolean checkMate(int a)
    {
        Pieces copy = getDeepCopy();
        ArrayList<Piece> player_pieces = getPlayerPieces(a);

        if (check(a) == false) {
            return false;
        }
        //check if the current player has a move to break check
        for (int i = 0; i < player_pieces.size(); ++i) {
            player_pieces = copy.getPlayerPieces(a);
            ArrayList<Pair> moves = player_pieces.get(i).availableMove(this);
            for (int j = 0; j < moves.size(); ++j) {
                Pair move = moves.get(j);
                player_pieces = copy.getPlayerPieces(a);
                player_pieces.get(i).moveTo(move.x, move.y, copy);
                if (copy.check(a) == false) {
                    return false;
                }
                copy = getDeepCopy();
            }
            copy = getDeepCopy();
        }
        return true;
    }

    //return a deepcopy of this class
    public Pieces getDeepCopy()
    {
        try {
            ByteArrayOutputStream a = new ByteArrayOutputStream();
            ObjectOutputStream b = new ObjectOutputStream(a);
            b.writeObject(this);
            ByteArrayInputStream c = new ByteArrayInputStream(a.toByteArray());
            ObjectInputStream d = new ObjectInputStream(c);
            return (Pieces) d.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public void draw(Graphics2D g2d)
    {
        for (int i = 0; i < pieces.size(); i++) {
            Piece temp = pieces.get(i);
            if (temp.getPlayer() == 1) {
                g2d.setColor(Color.BLACK);
            } else {
                g2d.setColor(Color.RED);
            }
            //board positions
            int a = temp.getX();
            int b = temp.getY();
            String name = temp.getClass().getSimpleName();
            //draw piece
            g2d.fillOval(xPoints[a] - 30, yPoints[b] - 30, 60, 60);
            //draw name of piece
            g2d.setColor(Color.WHITE);
            if (name.equals("Counselor")) //advisor is same as counselor
            {
                g2d.drawString("Advisor", xPoints[a] - 30, yPoints[b]);
            } else if (name.equals("Elephant")) //DO YOU STILL NEED THIS??????????????????????????????????????????????????????????????
            {
                g2d.drawString("Elephant", xPoints[a] - 30, yPoints[b]);
            } else if (name.equals("Pawn") || name.equals("Horse")) {
                g2d.drawString(name, xPoints[a] - 20, yPoints[b]);
            } else {
                g2d.drawString(name, xPoints[a] - 30, yPoints[b]);
            }
        }

    }
}
