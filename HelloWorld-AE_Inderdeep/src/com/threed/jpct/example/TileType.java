package com.threed.jpct.example;

public enum TileType {
	BLANK_TILE(5), DOUBLE_LETTER_TILE(1), TRIPPLE_LETTER_TILE(2), DOUBLE_WORD_TILE(
			3), TRIPPLE_WORD_TILE(4), NULL_TILE(0),CENTER_TILE(10),LETTER_TILE(12);

	private int frequency;

	private TileType(int frequency) {
		this.frequency = frequency;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		String x = null;
		switch (this.frequency) {
		case 5:
			// System.out.println("BLANK_TILE");
			x = "BLANK_TILE";
			break;
		case 1:
			// System.out.println("DOUBLE_LETTER_TILE");
			x = "DOUBLE_LETTER_TILE";
			break;
		case 2:
			// System.out.println("Tripple_LETTER_TILE");
			x = "Tripple_LETTER_TILE";
			break;
		case 3:
			// System.out.println("Double_word_TILE");
			x = "Double_word_TILE";
			break;
		case 4:
			// System.out.println("TRIPLE_WORD_TILE");
			x = "TRIPPLE_WORD_TILE";
			break;
		case 0:
			// System.out.println("NULL_TILE");
			x = "NULL_TILE";
			break;

		// default: throw new IllegalArgumentException();
		default:
			x = "default";
			break;

		}
		return x;

	}

}
