package com.sprites;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

import java.util.UUID;

public class AnimatedSprite implements ISprite {
    private UUID _id;
    public UUID GetId() {return _id;}
    public void SetId(UUID value) {_id = value;}

    private SimpleVector _position;
    public SimpleVector GetPosition(){
        return _position;
    }
    public void SetPosition(SimpleVector value){
        _position = value;
    }

    private float _scale;
    public float GetScale() {return _scale;}
    public void SetScale(float value) {
        _scale = value;}

    private String _message;
    public String GetMessage() {return _message;}
    public void SetMessage(String value) {_message = value;}

    private Texture _atlasTexture;
    public Texture GetTexture() {return _atlasTexture;}
    public void SetTexture(Texture value) {_atlasTexture = value;}

    private AnimationTracker _animation;
    public int GetAnimationIndex(){return _animation.GetCurrentAnimation();}
    public void SetAnimationIndex(int animationIndex){_animation.SwitchAnimation(animationIndex);}

    public void FireTemporaryAnimation(int animationIndex){
        _animation.FireTemporaryAnimation(animationIndex);
    }

    int _textureWidth;
    int _textureHeight;

    public AnimatedSprite(String spriteName){
        _id = UUID.randomUUID();

        AnimatedSpriteBlueprint blueprintData = SpriteBlueprintProvider.GetInstance().GetAnimatedSprite(spriteName);

        _atlasTexture = TextureManager.getInstance().getTexture(blueprintData.TextureName);
        _textureWidth = blueprintData.Width;
        _textureHeight = blueprintData.Height;
        _scale = blueprintData.Scale;
        _message = blueprintData.Message;

        _position = new SimpleVector(0,0,0);

        _animation = new AnimationTracker(blueprintData.FrameLength, blueprintData.FrameWidths);
    }

    private TextureCoords IndexToCoordinates(int animation, int frame){
        int atlasWidth = _atlasTexture.getWidth();
        int atlasHeight = _atlasTexture.getHeight();

        // ensure index exists
        int targetX = _textureWidth * frame;
        int targetY = _textureHeight * animation;

        if(targetX < atlasWidth && targetY < atlasHeight)
            return new TextureCoords(targetX, targetY);

        // otherwise, couldn't find the index
        return null;
    }

    public void Update(float elapsedTime){
        _animation.Update(elapsedTime);
    }

    public void Draw(FrameBuffer fb){
        TextureCoords target = IndexToCoordinates(_animation.GetCurrentAnimation(),
                                                  _animation.GetCurrentFrame());

        fb.blit(_atlasTexture, target.x, target.y, (int) _position.x, (int) _position.y,
                _textureWidth, _textureHeight,
                (int) (_textureWidth * _scale), (int) (_textureHeight * _scale),
                255, false);
    }
}
