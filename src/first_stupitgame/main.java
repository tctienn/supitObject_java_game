package first_stupitgame;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {

		JFrame windowFrame = new JFrame();
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		;
		windowFrame.setResizable(false);
		windowFrame.setTitle("stupit game");

		GamePanel gamePanel = new GamePanel();
		windowFrame.add(gamePanel);
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null);
		windowFrame.setVisible(true);

		gamePanel.startGameThread();

	}

}
