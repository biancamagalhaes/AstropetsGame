package br.com.biancamagalhaes;

import java.util.Random;

public class Pets extends Sprite {

	private int initY;
	
	public Pets(int x, int y) {
		super(x, y);
		this.initY = y;
		initPets();
	}
	
	private String choosePet(int number) {
		switch(number) {
		case 0: 
			return "/imagens/pet1.png";
		case 1:
			return "/imagens/pet2.png";
		case 2:
			return "/imagens/pet3.png";
		case 3:
			return "/imagens/pet4.png";
		}
		return "";
	}

	private void initPets() {
		Random rand = new Random();
		carregarImagem(choosePet(rand.nextInt(4)));
		getImageDimensions();
	}

	public void move() {
		if (y < 0) {
			y = initY;
		}
		y += 1;
	}

}
