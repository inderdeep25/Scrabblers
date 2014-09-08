package com.threed.jpct.example;

import android.util.Log;

import com.sprites.SpriteBlueprintProvider;
import com.sprites.TextSpriteBlueprint;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

public class BoardGenerator {

	public int sizeOfBoard;
	public TileAC[][] board;
	
	public BoardGenerator() {

		sizeOfBoard = 9;
		board = new TileAC[sizeOfBoard][sizeOfBoard];
	}

	public BoardGenerator(int sizeOfBoard) {
		this.sizeOfBoard = sizeOfBoard;
		board = new TileAC[sizeOfBoard][sizeOfBoard];

	}

	public void generateBoard() {

		for (int i = 0; i < sizeOfBoard; i++) {
			for (int j = 0; j < sizeOfBoard; j++) {

				Log.d("In board:", "worked");
				board[i][j] = new TileAC(new BoardCoordinates(i, j),
						TileGenerator.getRandomTileType());

				board[i][j].setPixelPosition(new SimpleVector(
						((float) i) * 32 * 1.666f,
						120 + ((float) j) * 32 * 1.666f, 0));

			}

		}
		int center = (sizeOfBoard - 1) / 2;
		board[center][center].Delete();
		board[center][center] = new TileAC(new BoardCoordinates((int) center,
				(int) center), TileType.CENTER_TILE);

		board[center][center].setPixelPosition(new SimpleVector(
				(((float) center) * 32 * 1.666f),
				120 + (((float) center) * 32 * 1.666f), 0));
	}

	public void setTile(BoardCoordinates loc) {
		//board[loc.y-1][loc.x-1].Delete();
		board[loc.y-1][loc.x-1]= new TileAC(new BoardCoordinates(loc.y, loc.x), TileType.LETTER_TILE);
		
		board[loc.y-1][loc.x-1].setPixelPosition(new SimpleVector((loc.y-1) * 32 * 1.666f,
				120 + ((loc.x-1) * 32 * 1.666f), 0));
	}
}
