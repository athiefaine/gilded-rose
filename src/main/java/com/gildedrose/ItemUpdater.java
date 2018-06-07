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
                    .perform(it -> increaseQuality())
                    .atLeastOnce()
                    .andAlsoWhenSellInIsBelow(0)
                    .on(item);
        } else if (this.item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            ItemQualityUpdater
                    .perform(it -> increaseQuality())
                    .atLeastOnce()
                    .andAlsoWhenSellInIsBelow(10)
                    .andAlsoWhenSellInIsBelow(5)
                    .on(item);

            ItemQualityUpdater
                    .perform(it -> destroyQuality())
                    .whenSellInIsBelow(0)
                    .on(item);
        } else {
            ItemQualityUpdater
                    .perform(it -> decreaseQuality())
                    .atLeastOnce()
                    .andAlsoWhenSellInIsBelow(0)
                    .on(item);
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

        private Consumer<Integer> action;
        private final Collection<Integer> sellInThresholds = new ArrayList<>();

        private ItemQualityUpdater() {

        }

        static ItemQualityUpdater perform(Consumer<Integer> action) {
            ItemQualityUpdater itemQualityUpdater = new ItemQualityUpdater();
            itemQualityUpdater.action = action;
            return itemQualityUpdater;
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

        public void on(Item item) {
                    sellInThresholds.stream()
                    .filter(val -> item.sellIn < val )
                    .forEach(action);
        }

    }
}