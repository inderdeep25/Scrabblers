package com.sprites;

import com.threed.jpct.SimpleVector;

public class AnimatedSpriteBlueprint {
    public String TextureName;
    public SimpleVector Position;
    public float Scale;
    public float FrameLength;
    public int Width;
    public int Height;
    public String Message;
    public int[] FrameWidths;

    public AnimatedSpriteBlueprint(String tex, SimpleVector position, float scale, int[] frameWidths, float frameLength, int width, int height){
        TextureName = tex;
        Position = position;
        Scale = scale;
        FrameLength = frameLength;
        Width = width;
        Height = height;
        FrameWidths = frameWidths;
    }
}
