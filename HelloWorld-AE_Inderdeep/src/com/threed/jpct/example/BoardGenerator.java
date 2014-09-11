package com.threed.jpct.example;

import java.util.ArrayList;
import java.util.List;
import java.math.*;

import android.util.Log;
import com.threed.jpct.SimpleVector;

public class BoardGenerator {

	public static int sizeOfBoard;
	public static Tile[][] board, extend;
	static int center;

	// public List<List<Integer>> listOfBoardTiles = new
	// ArrayList<List<Integer>>();
	// List<String> x = new ArrayList<String>();

	public static float scale;
	public static int addFactor;

	public static float getScale() {
		// scale = 480 / (32 * sizeOfBoard);
		scale = ScreenInfoProvider.ScreenWidth / (32 * sizeOfBoard);
		//scale=1.5f;
		// add=;
		return scale;
	}

	public static int returnAddFactor() {
		addFactor = (int) ((int) ((center - 1) * 32 * getScale()) - (32 * getScale()));
		//addFactor=240;
		return addFactor;
	}

	public BoardGenerator(int sizeOfBoard) {
		this.sizeOfBoard = sizeOfBoard;

		board = new Tile[sizeOfBoard][sizeOfBoard];
		center = (sizeOfBoard - 1) / 2;
		Messager.GetInstance().Subscribe(TouchDownMessage.class, new IAction() {

			public void Invoke(IMessage message) {
				// TODO Auto-generated method stub

				onTouchDownMessage((TouchDownMessage) message);
			}

		});

	}

	public void generateBoard() {

		for (int i = 0; i < sizeOfBoard; i++) {
			for (int j = 0; j < sizeOfBoard; j++) {

				Log.d("In board:", "worked");
				board[i][j] = new Tile(new BoardCoordinates(i, j),
						TileGenerator.getRandomTileType(), null);

				board[i][j].setPixelPosition(new SimpleVector(((float) i) * 32
						* getScale(),
						returnAddFactor() + ((float) j) * getScale() * 32, 0));

			}

		}

		board[center][center].Delete();
		board[center][center] = new Tile(new BoardCoordinates((int) center,
				(int) center), TileType.CENTER_TILE, null);

		board[center][center].setPixelPosition(new SimpleVector(
				(((float) center) * getScale() * 32),
				(returnAddFactor() + ((float) center) * getScale() * 32), 0));
	}

	public void onTouchDownMessage(TouchDownMessage message) {

		
		if ((int) message._pixely < returnAddFactor()) {

		} else if ((int) message._pixely > (ScreenInfoProvider.ScreenHeight - returnAddFactor())) {

		} else {
			placeTile((int) ArrayCoord.ArrayCoordX((int) message._pixelx),
					(int) ArrayCoord.ArrayCoordY((int) message._pixely,
							ScreenInfoProvider.ScreenHeight,
							ScreenInfoProvider.ScreenWidth));
			
			Log.d("ArrayCoord",
					Float.toString(ArrayCoord
							.ArrayCoordX((int) message._pixelx))
							+ Float.toString(ArrayCoord.ArrayCoordY(
									(int) message._pixely,
									ScreenInfoProvider.ScreenHeight,
									ScreenInfoProvider.ScreenWidth)));
		}
		
	}

	/*
	 * public void extendBoard() { sizeOfBoard += 2; int i, j; extend = new
	 * Tile[sizeOfBoard][sizeOfBoard]; for (i = 0; i < sizeOfBoard-2 ; i++) {
	 * for (j = 0; j < sizeOfBoard - 2; j++) { Log.d("inside extend output",
	 * Integer.toString(i) + Integer.toString(j)); // if(i>1 && j>1 &&
	 * i<sizeOfBoard-1 && j<sizeOfBoard-1) extend[i][j] = board[i][j]; } }
	 * 
	 * for (i = sizeOfBoard-2; i < sizeOfBoard; i++) { //
	 * Log.d("inside extend output",Integer.toString(i)+Integer.toString(j));
	 * for(j=0;j<sizeOfBoard;j++){ Log.d("inside extend output",
	 * Integer.toString(i) + Integer.toString(j));
	 * 
	 * extend[i][j] = new Tile(new BoardCoordinates(i, j),
	 * TileGenerator.getRandomTileType());
	 * 
	 * extend[i][j].setPixelPosition(new SimpleVector( ((float) i) * 32 *
	 * getScale(), 120 + ((float) j) * getScale() * 32, 0)); } } for (j =
	 * sizeOfBoard-2; j < sizeOfBoard; j++) { //
	 * Log.d("inside extend output",Integer.toString(i)+Integer.toString(j));
	 * for(i=0;i<sizeOfBoard-2;i++){ Log.d("inside extend output",
	 * Integer.toString(i) + Integer.toString(j));
	 * 
	 * extend[i][j] = new Tile(new BoardCoordinates(i, j),
	 * TileGenerator.getRandomTileType());
	 * 
	 * extend[i][j].setPixelPosition(new SimpleVector( ((float) i) * 32 *
	 * getScale(), 120 + ((float) j) * getScale() * 32, 0)); } }
	 * 
	 * 
	 * board = new Tile[sizeOfBoard][sizeOfBoard]; board = extend;
	 * 
	 * }
	 */
	public static void placeTile(int x, int y) {
		
		board[x][y] = new Tile(new BoardCoordinates((int) x, (int) y),
				TileType.LETTER_TILE, new LetterData('S', 3, 0));

		Log.d("Board Called", Integer.toString(x) + "  " + Integer.toString(y));
		
		board[x][y].setLetterPosition(new SimpleVector(((float) x) * getScale()
				* 32, (returnAddFactor() + ((float) y) * getScale() * 32), 0));
	}

}
