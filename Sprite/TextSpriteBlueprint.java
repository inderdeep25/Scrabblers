package com.asherLaakes.cavemanRunner.Sprite;

import com.threed.jpct.SimpleVector;

public class TextSpriteBlueprint {
    public String TextureName;
    public SimpleVector Position;
    public float Scale;
    public char[] CharOrder;
    public int Width;
    public int Height;
    public String Message;

    public TextSpriteBlueprint(String message, String tex, SimpleVector position, float scale, char[] charOrder, int width, int height){
        TextureName = tex;
        Position = position;
        Scale = scale;
        CharOrder = charOrder;
        Width = width;
        Height = height;
        Message = message;
    }
}
