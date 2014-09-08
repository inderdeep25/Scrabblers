package com.threed.jpct.example;

import android.util.Log;

import com.sprites.SimpleSpriteToken;
import com.sprites.SpriteManager;
import com.sprites.TextSpriteToken;
import com.threed.jpct.SimpleVector;

public class TileAC {
	SimpleSpriteToken background;
	TextSpriteToken letter;
	TextSpriteToken number;
	TextSpriteToken board;

	float scale = 10;

	public TileAC(BoardCoordinates x, TileType tileType) {
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

	public void Delete(){
		background.Delete();
		letter.Delete();
		number.Delete();
	}
	
	public void setTileType(TileType tileType) {
		if (background != null)
			background.Delete();

		switch (tileType) {
		case BLANK_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"blankTile_blueprint", 0);
			Log.d("xy", "BlankTile");
			break;
		case NULL_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"blankTile_blueprint", 0);
			Log.d("xy", "nullTile");
			break;
		case LETTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"blank_tile_blueprint", 1);
			Log.d("xy", "LetterTile");
			break;

		case TRIPPLE_LETTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"trippleLetterTile_blueprint", 0);
			Log.d("xy", "trippleLetterTile");
			break;
		case TRIPPLE_WORD_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"trippleWordTile_blueprint", 0);
			Log.d("xy", "trippleWordTile");
			break;
		case DOUBLE_LETTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"doubleLetterTile_blueprint", 0);
			Log.d("xy", "doubleLetterTile");
			break;
		case DOUBLE_WORD_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"doubleWordTile_blueprint", 0);
			Log.d("xy", "doubleWordTile");
			break;
		case CENTER_TILE:
			background = SpriteManager.GetInstance().AddSimpleSprite(
					"centerTile_blueprint", 0);
			Log.d("xy", "centerTile");
			break;

		}
	}

	public void setPixelPosition(SimpleVector pos) {

		SimpleVector letterdelta = new SimpleVector(3.0f * 1.666f,
				3.0f * 1.666f, 0.0f);
		// letterdelta.scalarMul(scale);
		SimpleVector numberdelta = new SimpleVector(20.0f * 1.666f,
				20.0f * 1.666f, 0.0f);
		// numberdelta.scalarMul(scale);

		background.SetPosition(pos);
		letter.SetPosition(pos.calcAdd(letterdelta));
		number.SetPosition(pos.calcAdd(numberdelta));

	}
}
