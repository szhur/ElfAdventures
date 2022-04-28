package com.ryan.elfadventure.global;

public class LevelState {

    public int getStage() {
        return mStage;
    }

    public void setStage(int _stage) {
        mStage = _stage;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int _level) {
        mLevel = _level;
    }

    private int mStage = 1;
    private int mLevel;
}
