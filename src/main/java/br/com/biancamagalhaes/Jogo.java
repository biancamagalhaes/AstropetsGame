package br.com.biancamagalhaes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Jogo extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Timer timer;
	private Nave nave;
	

	private int score = 0;

	private ArrayList<Pets> pets = new ArrayList<Pets>();

	private Random random = new Random();

	private boolean endgame = false;

	private int LOST_PETS = 0;

	private final int DELAY = 10;

	private final int B_WIDTH = 800;
	private final int B_HEIGHT = 460;

	public Jogo() {
		initJogo();
	}

	private void initJogo() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setDoubleBuffered(true);
		nave = new Nave(40, 60, B_WIDTH);
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void drawGameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		g.setColor(Color.black);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
		ImageIcon gameover = new ImageIcon(this.getClass().getResource("/imagens/gameover.png"));
		g.drawImage(gameover.getImage(), 0, 0, null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!endgame) {
			desenhar(g);
		} else {
			drawGameOver(g);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	private void desenhar(Graphics g) {
		ImageIcon img = new ImageIcon(this.getClass().getResource("/imagens/background.jpg"));
		ImageIcon logo = new ImageIcon(this.getClass().getResource("/imagens/logo.png"));
		g.drawImage(img.getImage(), 0, 0, null);
		if (nave.getX() < 0) {
			g.drawImage(nave.getImage(), 10, 430, this);
		} else {
			g.drawImage(nave.getImage(), nave.getX(), 430, this);
		}

		for (Pets i : pets) {
			if (i.isVisible()) {
				if (i.getX() > 720) {
					g.drawImage(i.getImage(), 720, i.getY(), this);
				} else {
					g.drawImage(i.getImage(), i.getX(), i.getY(), this);
				}
			}
		}
		g.setColor(Color.white);
		g.drawImage(logo.getImage(), 5, 0, null);
		g.drawString("Save Pets : " + score, 700, 15);
		g.drawString("Lost Pets : " + LOST_PETS, 700, 30);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		stopGame();
		updateNave();
		updateInimigo();
		checkCollisions();
		repaint();
	}

	public void checkCollisions() {
		if (LOST_PETS > 20) {
			endgame = true;
		} else {
			Rectangle r3 = nave.getBounds(true);
			for (Pets pet : pets) {
				if (pet.getY() > 400) {
					LOST_PETS++;
					pet.setVisible(false);
				}
				Rectangle r2 = pet.getBounds(false);
				if (r3.intersects(r2)) {
					pet.setVisible(false);
					score++;
					if (score > 100) {
						endgame = true;
					}
				}
			}
		}
	}

	private void updateInimigo() {
		while (pets.size() < 5) {
			pets.add(new Pets(random.nextInt(B_WIDTH) + 10, 0));
		}

		for (int i = 0; i < pets.size(); i++) {
			Pets pet = pets.get(i);
			if (pet.isVisible()) {
				pet.move();
			} else {
				pets.remove(pet);
			}
		}

	}

	private void stopGame() {
		if (endgame) {
			timer.stop();
		}
	}

	private void updateNave() {
		nave.move();
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			nave.keyPressed(e);
		}
	}
}