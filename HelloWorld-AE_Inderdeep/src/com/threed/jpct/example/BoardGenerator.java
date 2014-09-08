package com.threed.jpct.example;

import android.util.Log;

import com.sprites.SpriteBlueprintProvider;
import com.sprites.TextSpriteBlueprint;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

public class BoardGenerator {

	public int sizeOfBoard;
	public Tile[][] board;
	
	public BoardGenerator() {

		sizeOfBoard = 9;
		board = new Tile[sizeOfBoard][sizeOfBoard];
	}

	public BoardGenerator(int sizeOfBoard) {
		this.sizeOfBoard = sizeOfBoard;
		board = new Tile[sizeOfBoard][sizeOfBoard];

	}

	public void generateBoard() {

		for (int i = 0; i < sizeOfBoard; i++) {
			for (int j = 0; j < sizeOfBoard; j++) {

				Log.d("In board:", "worked");
				board[i][j] = new Tile(new BoardCoordinates(i, j),
						TileGenerator.getRandomTileType());

				board[i][j].setPixelPosition(new SimpleVector(
						((float) i) * 32 * 1.666f,
						120 + ((float) j) * 32 * 1.666f, 0));

			}

		}
		int center = (sizeOfBoard - 1) / 2;
		board[center][center].Delete();
		board[center][center] = new Tile(new BoardCoordinates((int) center,
				(int) center), TileType.CENTER_TILE);

		board[center][center].setPixelPosition(new SimpleVector(
				(((float) center) * 32 * 1.666f),
				120 + (((float) center) * 32 * 1.666f), 0));
	}

}
