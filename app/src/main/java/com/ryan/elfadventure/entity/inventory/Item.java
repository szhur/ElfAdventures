package com.ryan.elfadventure.entity.inventory;

import com.ryan.elfadventure.enums.ItemType;

public class Item {
    public void setItemType(ItemType _itemType) {
        mItemType = _itemType;
    }

    public ItemType getItemType() {
        return mItemType;
    }

    public void setName(String _name) {
        mName = _name;
    }

    public String getName() {
        return mName;
    }

    public void setImagePath(String _imagePath) {
        mImagePath = _imagePath;
    }

    public String getImagePath() {
        return mImagePath;
    }

    private ItemType mItemType = ItemType.Simple;

    private int nCount;

    private String mName;
    private String mImagePath;
}
