package com.gildedrose;

public class ItemUpdater {


    private Item item;

    public ItemUpdater(Item item) {
        this.item = item;
    }

    void updateQuality() {
        if (this.item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        this.item.sellIn = this.item.sellIn - 1;

        if (this.item.name.equals("Aged Brie")) {
            increaseQuality();

            if (this.item.sellIn < 0) increaseQuality();
        } else if (this.item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQuality();

            if (this.item.sellIn < 10) increaseQuality();
            if (this.item.sellIn < 5) increaseQuality();

            if (this.item.sellIn < 0) this.item.quality = 0;
        } else {
            decreaseQuality();

            if (this.item.sellIn < 0) decreaseQuality();
        }
    }

    void decreaseQuality() {
        if (this.item.quality > 0) {
            this.item.quality = this.item.quality - 1;
        }
    }

    void increaseQuality() {
        if (this.item.quality < 50) {
            this.item.quality = this.item.quality + 1;
        }
    }
}