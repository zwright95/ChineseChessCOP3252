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

	static int []xPoints = new int[]{110, 170, 230, 290, 350, 410, 470, 530, 590};
	static int []yPoints = new int[]{95, 155, 215, 275, 335, 395, 455, 515, 575, 635};
	public DrawBoard()
	{

	}

	public void draw(Graphics2D g2d)
	{
		Color brown = new Color(153, 102, 0);
		Color tan = new Color(245,222,179);

		//fill background border
		g2d.setColor(brown);
		g2d.fillRect(90, 75, 520, 580);
		//fill board color
		g2d.setColor(tan);
		g2d.fillRect(110, 95, 480, 540);
		int x, y;
		int i = 0, j = 0;
		//draw first half of board
		for(i = 0; i < 8; i++){
			for(j = 0; j < 4; j++)
			{
				g2d.setColor(brown);
				x = i * 60 + 110;
				y = j * 60 + 95;
				g2d.drawRect(x, y, 60, 60);
			}
		}
		
		//draw river
		y = j * 60 + 95;
		g2d.drawRect(110, y, 480, 60);
		//draw second half of board

		for(i = 0; i < 8; i++)
		{
			int oldY = 395;
			for(j = 0; j < 4; j++)
			{
				g2d.setColor(brown);
				x = i * 60 + 110;
				g2d.drawRect(x, oldY, 60, 60);
				oldY = oldY + 60;

			}
		}
		
		//first X
		g2d.drawLine(290, 95, 410, 215);
		g2d.drawLine(410, 95, 290, 215);
		//second X
		g2d.drawLine(290, 515, 410, 635);
		g2d.drawLine(410, 515, 290, 635);
	}

}
