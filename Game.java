import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game{
	public static void main(String [] args){
		JFrame frame = new JFrame("Xiangqi: Chinese Chess");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StartGame game = new StartGame();
		frame.add(game);
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}

class StartGame extends JPanel{
	DrawBoard board;
	Pieces pieces;
	int pointsPressed;
	int arrR, arrC;
	public StartGame(){
		board = new DrawBoard();
		pieces = new Pieces();
		pointsPressed = 0;
		arrR = 0;
		arrC = 0;
		addMouseListener(new MouseAdapter() {
      		public void mousePressed(MouseEvent event) {
        	System.out.println(event.getPoint());
        	pointsPressed++;
        	if(pointsPressed == 1){
        		String rowAndColumn = pieces.validPiece(event.getPoint());
        		if(rowAndColumn.indexOf(',') != -1)
        		{
        			String array1[]= rowAndColumn.split(",");
        			arrR = Integer.parseInt(array1[0]);
        			arrC = Integer.parseInt(array1[1]);
        			System.out.println("Valid piece");
        		}
        		else{
        			System.out.println("Not a valid piece");
        			pointsPressed--;
        		}
        	}
        	else if(pointsPressed == 2){
        		boolean valid = pieces.movePiece(arrR, arrC, event.getPoint());
        		pointsPressed = 0;
        		System.out.println(valid);
        		if(valid == true)
        			repaint();
        	}
      	}
    	});
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		board.draw(g2d);
		pieces.draw(g2d);
	}
}
