package retrogame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import retrogame.entities.Player;
import retrogame.handlers.KeyHandler;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = -8670981618588737184L;

	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;

	int TPS = 50;

	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;

	Player player = new Player(this, keyHandler);

	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);

	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / TPS;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		int frames = 0;

		while (gameThread != null) {

			long now = System.nanoTime();
			delta += (now - lastTime) / drawInterval;
			lastTime = now;

			if (delta >= 1) {

				update();
				repaint();

				delta--;

			}

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {

				timer += 1000;
				System.out.println("FPS:" + frames);
				frames = 0;

			}

		}

	}

	public void update() {

		player.update();

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		player.draw(g2);

		g2.dispose();

	}

}
