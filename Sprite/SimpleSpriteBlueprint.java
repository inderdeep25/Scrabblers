package com.asherLaakes.cavemanRunner.Sprite;

import com.threed.jpct.SimpleVector;

public class SimpleSpriteBlueprint {
    public String TextureName;
    public SimpleVector Position;
    public float Scale;

    public SimpleSpriteBlueprint(String tex, SimpleVector position, float scale){
        TextureName = tex;
        Position = position;
        Scale = scale;
    }
}
