package com.ryan.elfadventure.global;

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

    public LevelState getLevelState() {
        return mLevelState;
    }

    public InventoryState getInvState() {
        return mInvState;
    }

    private String mQuestLog;

    private LevelState mLevelState = new LevelState();
    private InventoryState mInvState = new InventoryState();
}
