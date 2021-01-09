package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {
	// para criar uma 'class', o nome deve ter letra maíscula no começo, e o 'pakege' não pode
	public static Snake snake;
	
	// Ele é resposável pela parte gráfica
	public JFrame jframe;
	public RenderPanel renderPanel;
	
	// Criando o objeto time
	public Timer timer = new Timer(20, this);

	// cobrinha
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	
	// definindo as variáveis
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	public int ticks, direction, score, tailLenght, time, speed;
	
	public Point head, cherry, cherry2;
	public Random random;
	public boolean over = false, paused;
	
	// criando a dimensão
	public Dimension dim;
	
	public Snake() {
		// definindo a posição da parte gráfica
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		// configurando a pate gráfica da aplicação
		// Criando uma interase para parte visual
		jframe = new JFrame("Snake");
		
		// definindo a visibilidade
		jframe.setVisible(true);

		// definindo o tamanho
		jframe.setSize(805, 700);
		
		// permite redimencionar a janela
		jframe.setResizable(true);
		
		// definindo o local
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);

		// renderizando as cores
		jframe.add(renderPanel = new RenderPanel());
		
		// definindo o tamanho
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// mexendo a cobrinha
		jframe.addKeyListener(this);
		
		// iniciando o Game
		startGame();
	}

	public void startGame() {
		// preparando o Jogo
		over = false;
		paused = false;
		time = 0;
		score = 0;
		tailLenght = 0;
		ticks = 0;
		direction = DOWN;
		speed = 10;
		
		//cobrinha
		head = new Point(0, -1);
		random = new Random();
		snakeParts.clear();
		cherry = new Point(random.nextInt(79), random.nextInt(55));
		cherry2 = new Point(random.nextInt(55), random.nextInt(79));
		
		// definindo o Tempo
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// renderiza a parte visual
		renderPanel.repaint();
		
		// cobrinha
		ticks++;
		
		// tricks = manobras. Aumentando o número você aumenta a velocidade
		if (ticks % speed == 0 && head != null && !over && !paused) {
			
			time++;
			
			snakeParts.add(new Point(head.x, head.y));
			
			if (direction == UP) { 
				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
					head = new Point(head.x, head.y - 1);
				} else {
					over = true;
				}
			}
			if (direction == DOWN) {
				if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1)) {
					head = new Point(head.x, head.y + 1);
				} else {
					over = true;
				}
			}
			if (direction == LEFT) {
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
					head = new Point(head.x - 1, head.y);
				} else {
					over = true;
				}
			}
			if (direction == RIGHT) {
				if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
					head = new Point(head.x + 1, head.y);
				} else {
					over = true;
				}
			}
			
			// tamanho da cobrinha
			if (snakeParts.size() >  tailLenght) {
				snakeParts.remove(0);				
			}
			
			// docinho que quando come aumenta o tamanho da cobrinha
			if (cherry != null || cherry2 !=null ) {
				if (head.equals(cherry) || head.equals(cherry2)) {
					score += 10;
					tailLenght += 2;
					
					// ajustando a velocidade
					if (score == 10) {
						speed = 7;
					} if (score == 20) {
						speed = 6;
					} if (score == 30) {
						speed = 5;
					} if (score > 40) {
						speed = 4;
					} if (score == 50) {
						speed = 3;
					} if (score == 60) {
						speed = 2;
					} if (score == 70) {
						speed = 1;
					} if (score == 80) {
						speed = -1;
					} if (score == 90) {
						speed = 4;
					} if (score == 100) {
						speed = 3;
					} if (score == 110) {
						speed = 2;
					} if (score == 120) {
						speed = 1;
					}
					
					
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
					cherry2.setLocation(random.nextInt(50), random.nextInt(40));
//					System.out.println(random.nextInt(50));
				}
			}
		}
	}
	
	public boolean noTailAt(int x, int y) {
		for (Point point : snakeParts) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// chama a parte visual
		snake = new Snake();
	}	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		
		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT) {
			direction = LEFT;
		}
		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT) {
			direction = RIGHT;
		}
		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN) {
			direction = UP;
		}
		
		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP) {
			direction = DOWN;
		}
			
		if (i == KeyEvent.VK_SPACE) {
			if (over) {
				startGame();
			} else {
				paused = !paused;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}