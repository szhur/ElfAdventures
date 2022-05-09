package com.ryan.elfadventure.entity.map;

public class Level {
    public Level(String _path) {
        mPath = _path;
        mIsVisited = false;
    }

    public String getPath() {
        return mPath;
    }

    public void visit() {
        mIsVisited = true;
    }

    public boolean isVisited() {
        return mIsVisited;
    }

    private final String mPath;
    private boolean mIsVisited;
}
