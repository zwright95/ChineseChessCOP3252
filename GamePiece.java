import java.awt.Color;

public class GamePiece{
	public String name;
	//other methods will be applicable here once game logic is implemented
	public Color color;
	public int x, y;
	public GamePiece(String n, Color c, int tempX, int tempY){
		name = n;
		color = c;
		x = tempX;
		y = tempY;
	}

	public String getName(){
		return name;
	}

	public Color getColor(){
		return color;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}