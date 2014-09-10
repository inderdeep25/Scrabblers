package com.threed.jpct.example;

import java.util.ArrayList;
import java.util.List;
import java.math.*;

import android.util.Log;
import com.threed.jpct.SimpleVector;

public class BoardGenerator {

	public static int sizeOfBoard;
	public static Tile[][] board, extend;

	// public List<List<Integer>> listOfBoardTiles = new
	// ArrayList<List<Integer>>();
	// List<String> x = new ArrayList<String>();

	public static float scale;
	public static int add;
	public static float getScale() {
		//scale = 480 / (32 * sizeOfBoard);
		scale=1;
		//add=;
		return scale;
	}

	public BoardGenerator(int sizeOfBoard) {
		this.sizeOfBoard = sizeOfBoard;
		board = new Tile[sizeOfBoard][sizeOfBoard];
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
						TileGenerator.getRandomTileType());

				board[i][j].setPixelPosition(new SimpleVector(((float) i) * 32
						* getScale(), 160+((float) j) * getScale() * 32, 0));

			}

		}
		int center = (sizeOfBoard - 1) / 2;
		board[center][center].Delete();
		board[center][center] = new Tile(new BoardCoordinates((int) center,
				(int) center), TileType.CENTER_TILE);

		board[center][center].setPixelPosition(new SimpleVector(
				(((float) center) * getScale() * 32), (160+((float) center)
						* getScale() * 32), 0));
	}

	public void onTouchDownMessage(TouchDownMessage message) {

		//BoardCoordinates board_coor = new BoardCoordinates(Coordinates.getBoardX(),Coordinates.getBoardY());
		

		//placeTile(Coordinates.getBoardX(480, (int) message._pixelx),Coordinates.getBoardY(480, 800,Math.abs((int) message._pixely)));
		//priyanka 
		placeTile((int)ArrayCoord.ArrayCoordX((int) message._pixelx),(int)ArrayCoord.ArrayCoordY((int) message._pixely, 800,480));
		//extendBoard();
		Log.d("ArrayCoord",Float.toString(ArrayCoord.ArrayCoordX((int) message._pixelx))+Float.toString(ArrayCoord.ArrayCoordY((int) message._pixely, 800, 480)));
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
	//	x=y-2;
		//y=x-2;
		board[x][y].Delete();
		board[x][y] = new Tile(new BoardCoordinates((int) x,
				(int) y), TileType.CENTER_TILE);
		
		Log.d("Board Called", Integer.toString(x) + "  " + Integer.toString(y));
		//board[x][y].setPixelPosition(new SimpleVector((float)a,(float)b,0));
		board[x][y].setPixelPosition(new SimpleVector(((float) x)* getScale() * 32, (160+((float) y)* getScale() * 32), 0));
	}

}
