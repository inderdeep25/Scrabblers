package com.threed.jpct.example;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import com.threed.jpct.SimpleVector;

public class BoardGenerator {

	public int sizeOfBoard;
	public static Tile[][] board,extend;
	//public List<List<Integer>> listOfBoardTiles = new ArrayList<List<Integer>>();
	//List<String> x = new ArrayList<String>();

	public BoardGenerator(int sizeOfBoard) {
		this.sizeOfBoard = sizeOfBoard;
		board = new Tile[sizeOfBoard][sizeOfBoard];
		Messager.GetInstance().Subscribe(TouchDownMessage.class, new IAction(){

			
			public void Invoke(IMessage message) {
				// TODO Auto-generated method stub
				//placeTile((TouchDownMessage) message._pixelx,TouchDownMessage._pixely);
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

				board[i][j].setPixelPosition(new SimpleVector(
						((float) i) * 32 * 1.363f,
						120 + ((float) j) * 1.363f * 32, 0));

			}

		}
		int center = (sizeOfBoard - 1) / 2;
		board[center][center].Delete();
		board[center][center] = new Tile(new BoardCoordinates((int) center,
				(int) center), TileType.CENTER_TILE);

		board[center][center].setPixelPosition(new SimpleVector(
				(((float) center) * 1.363f * 32),
				120 + (((float) center) * 1.363f * 32), 0));
	}

	public void onTouchDownMessage(TouchDownMessage message){
		//placeTile((int) (message._pixelx/(1.363*32)),(int) (message._pixely/(1.363*32)));
		extendBoard();
	}
	public void extendBoard(){
		sizeOfBoard+=2;
		int i,j;
		extend=new Tile[sizeOfBoard][sizeOfBoard];
		for(i=0;i<sizeOfBoard;i++){
			for(j=0;j<sizeOfBoard;j++){
				if(i>2 && j>2 && i<sizeOfBoard-2 && j<sizeOfBoard-2)
				extend[i][j]=board[i-2][j-2];
				extend[i][j] = new Tile(new BoardCoordinates(i, j),
						TileGenerator.getRandomTileType());

				extend[i][j].setPixelPosition(new SimpleVector(
						((float) i) * 32 * 1.363f,
						120 + ((float) j) * 1.363f * 32, 0));
			}
		}
		int center = (sizeOfBoard - 1) / 2;
		extend[center][center].Delete();
		extend[center][center] = new Tile(new BoardCoordinates((int) center,
				(int) center), TileType.CENTER_TILE);

		extend[center][center].setPixelPosition(new SimpleVector(
				(((float) center) * 1.363f * 32),
				120 + (((float) center) * 1.363f * 32), 0));
		board=new Tile[sizeOfBoard][sizeOfBoard];
		board=extend;
		
	}
	public static void placeTile(int x, int y) {

		board[y][x].Delete();
		board[y][x] = new Tile(new BoardCoordinates((int) y, (int) x),
				TileType.CENTER_TILE);
		Log.d("Board Called",Integer.toString(x)+"  "+Integer.toString(y));
		board[y][x].setPixelPosition(new SimpleVector(
				(((float) y) * 1.363f * 32), 120 + (((float) x) * 1.363f * 32),
				0));
	}

}
