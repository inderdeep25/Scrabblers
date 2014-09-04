package com.sprites;

import java.util.HashMap;
import java.util.Map;

public class SpriteBlueprintProvider {
    private static SpriteBlueprintProvider _instance = null;

    public static SpriteBlueprintProvider GetInstance(){
        if(_instance == null)
            _instance = new SpriteBlueprintProvider();

        return _instance;
    }

    private Map<String, SimpleSpriteBlueprint> _simpleSprites;
    private Map<String, TextSpriteBlueprint> _textSprites;

    private SpriteBlueprintProvider(){
        _simpleSprites = new HashMap<String, SimpleSpriteBlueprint>();
        _textSprites = new HashMap<String, TextSpriteBlueprint>();
    }

    public SimpleSpriteBlueprint GetSprite(String key){
        return _simpleSprites.get(key);
    }

    public TextSpriteBlueprint GetTextSprite(String key) {return _textSprites.get(key);}

    public void AddSimpleSpriteBlueprint(String key, SimpleSpriteBlueprint sprite){
        if(!_simpleSprites.containsKey(key))
            _simpleSprites.put(key, sprite);
    }

    public void AddTextSpriteBlueprint(String key, TextSpriteBlueprint sprite){
        if(!_textSprites.containsKey(key))
            _textSprites.put(key, sprite);
    }
}
