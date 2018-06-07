package com.gildedrose;

public class ItemUpdaterHelper {
    public static void destroyQuality(Item item) {
        item.quality = 0;
    }

    public static void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    public static void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    public static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
