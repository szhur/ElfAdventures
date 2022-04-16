package com.ryan.elfadventure;

import java.util.ArrayList;

public class Stage {
    public String getText() {
        return mText;
    }

    public void setText(String _text) {
        mText = _text;
    }

    public int size() {
        return mMoves.size();
    }

    public void addMove(Move _move) {
        mMoves.add(_move);
    }

    public Move getMove(int _index) {
        return mMoves.get(_index);
    }

    private String mText;
    ArrayList<Move> mMoves = new ArrayList<>();
}
