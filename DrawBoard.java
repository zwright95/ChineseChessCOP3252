import javax.swing.*;
import java.awt.*;

public class DrawBoard{
		/*  board points are: 
		Loop through xPoints and yPoints (x, y)
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; i < 10; j++){
				x = xPoints[i];
				y = xPoints[j];
			}
		}
		
		*/

	static int []xPoints = new int[]{100, 150, 200, 250, 300, 350, 400, 450, 500};
	static int []yPoints = new int[]{75, 125, 175, 225, 275, 325, 375, 425, 475, 525};
	public DrawBoard()
	{

	}

	public void draw(Graphics2D g2d)
	{
		Color brown = new Color(153, 102, 0);
		Color tan = new Color(245,222,179);

		//fill background border
		g2d.setColor(brown);
		g2d.fillRect(65, 40, 470, 520);
		//fill board color
		g2d.setColor(tan);
		g2d.fillRect(100, 75, 400, 450);
		int x, y;
		int i = 0, j = 0;
		//draw first half of board
		for(i = 0; i < 8; i++){
			for(j = 0; j < 4; j++)
			{
				g2d.setColor(brown);
				x = i * 50 + 100;
				y = j * 50 + 75;
				g2d.drawRect(x, y, 50, 50);
			}
		}
		//draw river
		y = j * 50 + 75;
		g2d.drawRect(100, y, 400, 50);
		//draw second half of board
		for(i = 0; i < 8; i++)
		{
			int oldY = 325;
			for(j = 0; j < 4; j++)
			{
				g2d.setColor(brown);
				x = i * 50 + 100;
				g2d.drawRect(x, oldY, 50, 50);
				oldY = oldY + 50;

			}
		}
		//first X
		g2d.drawLine(250, 75, 350, 175);
		g2d.drawLine(350, 75, 250, 175);
		//second X
		g2d.drawLine(250, 425, 350, 525);
		g2d.drawLine(350, 425, 250, 525);
	}

}