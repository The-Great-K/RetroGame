package retrogame.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import retrogame.GamePanel;
import retrogame.handlers.KeyHandler;

public class Player extends Entity {

	GamePanel gPanel;
	KeyHandler keyHandler;

	public Player(GamePanel gPanel, KeyHandler keyHandler) {

		this.gPanel = gPanel;
		this.keyHandler = keyHandler;

		setDefaultValues();

	}

	public void setDefaultValues() {

		x = 100;
		y = 100;
		speed = 4;
		direction = "down";

	}

	public void getPlayerImage() {

		try {

			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right2.png"));

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public void update() {

		if (keyHandler.upPressed) {

			direction = "up";
			y -= speed;

		} else if (keyHandler.leftPressed) {

			direction = "left";
			x -= speed;

		} else if (keyHandler.downPressed) {

			direction = "down";
			y += speed;

		} else if (keyHandler.rightPressed) {

			direction = "right";
			x += speed;

		}

	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
		case "up":
			image = up1;
			break;
		case "down":
			image = down1;
			break;
		case "left":
			image = left1;
			break;
		case "right":
			image = right1;
			break;
		}

		g2.drawImage(image, x, y, gPanel.tileSize, gPanel.tileSize, null);

	}

}
