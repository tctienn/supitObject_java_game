package first_stupitgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// screen setting
	final int originalTitleSize = 16;
	final int scale = 3;

	public int tileSize = originalTitleSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWith = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyHandler);

	// fps
	int fps = 60;

	TileManager tileManager = new TileManager(this);

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWith, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

//	@Override
//	public void run() {
//		double drawInterval = 1000000000 / fps;
//		double nextDrawTime = System.nanoTime() + drawInterval;
//
//		while (gameThread != null) {
//			update();
//			repaint();
//			double remainingTime = nextDrawTime - System.nanoTime();
//			remainingTime = remainingTime / 1000000;
//			if (remainingTime < 0)
//				remainingTime = 0;
//			try {
//				Thread.sleep((long) remainingTime);
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		;
//
//	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		System.out.println("ssssss" + lastTime);
		while (gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}

	}

	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		tileManager.draw(g2);
		player.draw(g2);
		g2.dispose();
	}

}
