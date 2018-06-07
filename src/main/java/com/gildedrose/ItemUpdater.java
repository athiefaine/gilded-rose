package com.gildedrose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

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
            ItemQualityUpdater
                    .on(item)
                    .atLeastOnce()
                    .andAlsoWhenSellInIsBelow(0)
                    .perform(it -> increaseQuality());
        } else if (this.item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            ItemQualityUpdater
                    .on(item)
                    .atLeastOnce()
                    .andAlsoWhenSellInIsBelow(10)
                    .andAlsoWhenSellInIsBelow(5)
                    .perform(it -> increaseQuality())
                    .andNext()
                    .whenSellInIsBelow(0)
                    .perform(it -> destroyQuality())                    ;
        } else {
            ItemQualityUpdater
                    .on(item)
                    .atLeastOnce()
                    .andAlsoWhenSellInIsBelow(0)
                    .perform(it -> decreaseQuality());
        }
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

    private static class ItemQualityUpdater {

        private final Collection<Integer> sellInThresholds = new ArrayList<>();
        private Item item;

        private ItemQualityUpdater() {

        }

        public static ItemQualityUpdater on(Item item) {
            ItemQualityUpdater itemQualityUpdater = new ItemQualityUpdater();
            itemQualityUpdater.item = item;
                    return itemQualityUpdater;
        }

         ItemQualityUpdater perform(Consumer<Integer> action) {
                    sellInThresholds.stream()
                    .filter(val -> item.sellIn < val )
                    .forEach(action);
            return this;
        }

        public ItemQualityUpdater atLeastOnce() {
            sellInThresholds.add(Integer.MAX_VALUE);
            return this;
        }

        public ItemQualityUpdater whenSellInIsBelow(Integer threshold) {
            sellInThresholds.add(threshold);
            return this;
        }

        public ItemQualityUpdater andAlsoWhenSellInIsBelow(Integer threshold) {
            return whenSellInIsBelow(threshold);
        }

        public ItemQualityUpdater andNext() {
            sellInThresholds.clear();
            return this;
        }


    }
}