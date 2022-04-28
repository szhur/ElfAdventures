package com.ryan.elfadventure.global;

import java.util.ArrayList;

public class InventoryState {
    public void add(Cell _cell) {
        for (Cell cell : mInventory) {
            if (!cell.isValid()) {
                cell = _cell;
                return;
            }
        }

        mInventory.add(_cell);
    }

    public void remove(int _index) {
        get(_index).invalidate();
    }

    public Cell get(int _index) {
        return mInventory.get(_index);
    }

    public int size() {
        return mInventory.size();
    }

    private ArrayList<Cell> mInventory = new ArrayList<>();
}
