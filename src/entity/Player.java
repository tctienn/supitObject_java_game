package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import first_stupitgame.GamePanel;
import first_stupitgame.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyHandler;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler keyHandle) {

		this.gp = gp;
		this.keyHandler = keyHandle;

		screenX = gp.screenWith / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
//		worldX = gp.tileSize * 23;
//		worldY = gp.tileSize * 21;
		worldX = gp.tileSize * 2;
		worldY = 100;
		speed = 4;
		direction = "down";

	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/body_up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/body_up2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/body_left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/body_left2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/body_down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/body_down2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/body_right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/body_right2.png"));

		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public void update() {
		if (keyHandler.upPressed == true) {
			direction = "up";
			worldY -= speed;
		} else if (keyHandler.downPressed == true) {
			direction = "down";
			worldY += speed;
		} else if (keyHandler.leftPressed == true) {
			direction = "left";
			worldX -= speed;
		} else if (keyHandler.rightPressed == true) {
			direction = "right";
			worldX += speed;
		}
		;
		if (keyHandler.downPressed == true || keyHandler.upPressed == true || keyHandler.leftPressed == true
				|| keyHandler.rightPressed) {
			spriteCouter++;
			if (spriteCouter > 10) {
				if (spriterNum == 1) {
					spriterNum = 2;
				} else if (spriterNum == 2) {
					spriterNum = 1;
				}
				spriteCouter = 0;
			}
		}

	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;
		switch (direction) {
		case "up":
			if (spriterNum == 1)
				image = up1;
			if (spriterNum == 2)
				image = up2;
			break;
		case "down":
			if (spriterNum == 1)
				image = down1;
			if (spriterNum == 2)
				image = down2;
			break;
		case "left":
			if (spriterNum == 1)
				image = left1;
			if (spriterNum == 2)
				image = left2;
			break;
		case "right":
			if (spriterNum == 1)
				image = right1;
			if (spriterNum == 2)
				image = right2;
			break;

//			g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

		}
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);

	}
}
