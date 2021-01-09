package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel{
	
	public static final Color GREEN = new Color(1666073);
	
	@Override
	protected void  paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Snake snake = Snake.snake;
		
		g.setColor(GREEN);

		// cor da cobreinha
		g.fillRect(0 ,0, 800, 700);
		g.setColor(Color.BLUE);
		
		for (Point point : snake.snakeParts){
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.RED);
		g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.fillRect(snake.cherry2.x * Snake.SCALE, snake.cherry2.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		// Pontuação
		String string = "Pontuação: " + snake.score + ", Tamanho: " + snake.tailLenght + ", Subindo para superfície: " + snake.speed + " camadas";
		
		// style da pontuação
		g.setColor(Color.white);
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 20);
		
		// Game Over
		string = "GAME OVER!";
		
		// 
		if (snake.over) {
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 5);
		}
		
		string = "Pausado!";
		if (snake.paused && !snake.over) {
			g.drawString(string,  (int) (getWidth() / 2 - string.length() * 2f), (int) snake.dim.getHeight() / 9);
		}
		
	}
}
