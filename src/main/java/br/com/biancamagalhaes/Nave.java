package br.com.biancamagalhaes;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Nave extends Sprite {
	private int dx;
	private int dy;


	private ArrayList<Missil> missiles;

	public Nave(int x, int y, int alcance) {
		super(x, y);
		initNave();
	}

	private void initNave() {
		missiles = new ArrayList<Missil>();
		carregarImagem("/imagens/nave.png");
		getImageDimensions();
	}

	public void move() {
		x += dx;
		y += dy;
	}

	public Image getImage() {
		return image;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			dx = -1;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public ArrayList<Missil> getMissiles() {
		return missiles;
	}
}
