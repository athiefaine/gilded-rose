package com.gildedrose;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ItemUpdater {


    private Item item;

    public ItemUpdater(Item item) {
        this.item = item;
    }

    void updateQuality() {
        if (this.item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        decreaseSellIn();

        if (this.item.name.equals("Aged Brie")) {
            updateQualityWithAction(it -> increaseQuality(), true, 0);
        } else if (this.item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateQualityWithAction(it -> increaseQuality(), true, 5, 10);
            updateQualityWithAction(it -> destroyQuality(), false, 0);
        } else {
            updateQualityWithAction(it -> decreaseQuality(), true, 0);
        }
    }

    private void updateQualityWithAction(Consumer<Integer> action, Boolean always, Integer... sellInThresholds) {
        Stream.concat(always ? Stream.of( Integer.MAX_VALUE) : Stream.empty(),
                Stream.of(sellInThresholds))
                .filter(val -> this.item.sellIn < val)
                .forEach(action);
    }

    private void destroyQuality() {
        this.item.quality = 0;
    }

    private void decreaseSellIn() {
        this.item.sellIn = this.item.sellIn - 1;
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