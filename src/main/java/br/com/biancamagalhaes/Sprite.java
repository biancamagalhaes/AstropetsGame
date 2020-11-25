package br.com.biancamagalhaes;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visibilty;

	protected Image image;

	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		visibilty = true;
	}

	protected void carregarImagem(String imageName) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imageName));
		image = ii.getImage();
	}

	protected void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		if(x > 750) {
			return 750;
		}
		return x;
	}

	public int getY() {
		if(y > 500) {
			return 500;
		}
		return y;
	}

	public boolean isVisible() {
		return visibilty;
	}

	public void setVisible(Boolean visible) {
		this.visibilty = visible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
