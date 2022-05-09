package com.ryan.elfadventure.manager;

import com.ryan.elfadventure.entity.inventory.Item;
import com.ryan.elfadventure.util.XmlUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class InvertoryManager {
    public InvertoryManager(XmlPullParser _parser) throws IOException, XmlPullParserException {
        _parser.require(XmlPullParser.START_TAG, null, "items");
        while (_parser.next() != XmlPullParser.END_TAG) {
            if (_parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = _parser.getName();
            if (name.equals("item")) {
                readItem(_parser);
            } else {
                XmlUtil.skip(_parser);
            }
        }
    }

    public void addItem(Item _item) {
        mItems.add(_item);
    }

    public Item getItem(int _index) {
        return mItems.get(_index);
    }

    private void readItem(XmlPullParser _parser) throws IOException, XmlPullParserException {
        Item item = new Item();
        _parser.require(XmlPullParser.START_TAG, null, "item");
        while (_parser.next() != XmlPullParser.END_TAG) {
            if (_parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = _parser.getName();
            if (name.equals("name")) {
                item.setName(XmlUtil.readText(_parser));
            } else if (name.equals("image")) {
                item.setImagePath(XmlUtil.readText(_parser));
            } else {
                XmlUtil.skip(_parser);
            }
        }
        mItems.add(item);
    }

    private ArrayList<Item> mItems = new ArrayList<>();
}
