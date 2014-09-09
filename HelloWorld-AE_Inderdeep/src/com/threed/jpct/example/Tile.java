package com.threed.jpct.example;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.sprites.SimpleSpriteToken;
import com.sprites.SpriteManager;
import com.sprites.TextSpriteToken;
import com.threed.jpct.SimpleVector;

public class Tile {
	SimpleSpriteToken background;
	TextSpriteToken letter;
	TextSpriteToken number;
	TextSpriteToken board;
	LetterData _letterData;
	float scale = 10;
	TileType boardTile;
	public Tile(BoardCoordinates x, TileType tileType) {
		// background =
		// SpriteManager.GetInstance().AddSimpleSprite("blank_tile_blueprint",
		// 0);
		setTileType(tileType);
		letter = SpriteManager.GetInstance().AddTextSprite(
				"alphabet_blueprint", 1);
		number = SpriteManager.GetInstance().AddTextSprite("number_blueprint",
				2);

		letter.SetMessage("");
		number.SetMessage("");
		/*
		 * background.SetScale(scale); letter.SetScale(scale);
		 * number.SetScale(scale);
		 */
		// setPosition(x);

	}

	public void Delete() {
		background.Delete();
		letter.Delete();
		number.Delete();
	}

	public void placeLetter() {
		// place background tile,tile points and letter.
		// takes parameter of letter data.
	}

	public void setTileType(TileType tileType) {
		if (background != null)
			background.Delete();

		switch (tileType) {
		case BLANK_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"blankTile_blueprint", 0);
			boardTile=TileType.BLANK_TILE;
			Log.d("xy", "BlankTile");
			break;
		case NULL_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"blankTile_blueprint", 0);
			boardTile=TileType.BLANK_TILE;
			Log.d("xy", "nullTile");
			break;

		case TRIPPLE_LETTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"trippleLetterTile_blueprint", 0);
			boardTile=TileType.TRIPPLE_LETTER_TILE;
			Log.d("xy", "trippleLetterTile");
			break;
		case TRIPPLE_WORD_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"trippleWordTile_blueprint", 0);
			boardTile=TileType.TRIPPLE_WORD_TILE;
			Log.d("xy", "trippleWordTile");
			break;
		case DOUBLE_LETTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"doubleLetterTile_blueprint", 0);
			boardTile=TileType.DOUBLE_LETTER_TILE;
			Log.d("xy", "doubleLetterTile");
			break;
		case DOUBLE_WORD_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"doubleWordTile_blueprint", 0);
			boardTile=TileType.DOUBLE_WORD_TILE;
			Log.d("xy", "doubleWordTile");
			break;
		case CENTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"centerTile_blueprint", 0);
			boardTile=TileType.CENTER_TILE;
			Log.d("xy", "centerTile");
			break;

		}
	}

	public void setPixelPosition(SimpleVector pos) {

		SimpleVector letterdelta = new SimpleVector(3.0f * 1.363f,
				3.0f * 1.363f, 0.0f);
		// letterdelta.scalarMul(scale);
		SimpleVector numberdelta = new SimpleVector(20.0f * 1.363f,
				20.0f * 1.363f, 0.0f);
		// numberdelta.scalarMul(scale);

		background.SetPosition(pos);
		letter.SetPosition(pos.calcAdd(letterdelta));
		number.SetPosition(pos.calcAdd(numberdelta));

	}
}
