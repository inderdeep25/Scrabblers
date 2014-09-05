package com.sprites;

public class AnimationTracker {
    private float _frameLength;
    private float _timeTillNextFrame;

    private int _currentFrame;
    private int _currentAnimation;

    // each entry defines an animation's number of frames (from start to bottom of the atlas)
    private int[] _framesInAnimation;

    private boolean _inTemporaryAnimation;
    private int _sleepingAnimation;

    public AnimationTracker(float frameLength, int[] framesInAnimation){
        _frameLength = frameLength;
        _timeTillNextFrame = frameLength;
        _framesInAnimation = framesInAnimation;

        _inTemporaryAnimation = false;
        _sleepingAnimation = -1;
    }

    public int GetCurrentFrame(){
        return _currentFrame;
    }

    public int GetCurrentAnimation(){
        return _currentAnimation;
    }

    public void SwitchAnimation(int targetIndex){
        if(targetIndex != _currentAnimation && targetIndex < _framesInAnimation.length) {
            _currentAnimation = targetIndex;
            _currentFrame = 0;
        }
    }

    public void FireTemporaryAnimation(int targetIndex){
        if(targetIndex < _framesInAnimation.length){
            if(!_inTemporaryAnimation)
                _sleepingAnimation = _currentAnimation;
            _currentAnimation = targetIndex;
            _currentFrame = 0;

            _inTemporaryAnimation = true;
        }
    }

    private void ResetToSleepingAnimation(){
        _currentAnimation = _sleepingAnimation;
        _sleepingAnimation = -1;
        _currentFrame = 0;
        _inTemporaryAnimation = false;
    }

    public void Update(float elapsedTime){
        _timeTillNextFrame -= elapsedTime;

        if(_timeTillNextFrame <= 0){
            TickFrame();
            _timeTillNextFrame = _frameLength;
        }
    }

    private void TickFrame(){
        _currentFrame++;

        // if the current frame strip is over
        if(_currentFrame >= _framesInAnimation[_currentAnimation]) {
            // if we were in the middle of a fired animation,
            // we need to revert to the sleeping animation
            if(_inTemporaryAnimation)
                ResetToSleepingAnimation();
            else
                _currentFrame = 0;
        }
    }
}
