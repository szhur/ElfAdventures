package com.ryan.elfadventure.global;

public class Cell {
    public Cell(int _itemId, int _count)
    {
        mItemId = _itemId;
        mCount = _count;
    }

    public void setItemId(int _itemId) {
        mItemId = _itemId;
    }

    public int getItemId() {
        return mItemId;
    }

    public void setCount(int _count) {
        mCount = _count;
    }

    public void modifyCount(int _diff) {
        mCount += _diff;
    }

    public int getCount() {
        return mCount;
    }

    public void invalidate() {
        setItemId(0);
    }

    public boolean isValid() {
        return getItemId() != 0;
    }

    private int mItemId;
    private int mCount;
}
