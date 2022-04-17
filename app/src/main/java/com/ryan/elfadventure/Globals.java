package com.ryan.elfadventure;

public class Globals {
    private static Globals globalsInstance = new Globals();

    public static Globals getInstance() {
        return globalsInstance;
    }

    private Globals() {
    }

    public String getQuestLog() {
        return mQuestLog;
    }

    public void setQuestLog(String _questLog) {
        mQuestLog = _questLog;
    }

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

    private String mQuestLog;

    private int mStage = 1;
    private int mLevel;
}
