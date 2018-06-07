package com.gildedrose;

import static com.gildedrose.ItemQualityUpdaterDSL.*;

public class ItemUpdater {

    private Item item;

    public ItemUpdater(Item item) {
        this.item = item;
    }

    public void update() {
        updateSellIn();
        updateQuality();
    }

    private void updateSellIn() {
        if (!"Sulfuras, Hand of Ragnaros".equals(item.name)) {
            on(item)
                .once()
                .perform(it -> decreaseSellIn());
        }
    }

    private void updateQuality() {
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros":
                break;

            case "Aged Brie":
                on(item)
                        .once()
                        .andAlsoWhenSellInIsBelow(0)
                        .perform(it -> increaseQuality());
                break;

            case "Backstage passes to a TAFKAL80ETC concert":
                on(item)
                        .once()
                        .andAlsoWhenSellInIsBelow(10)
                        .andAlsoWhenSellInIsBelow(5)
                        .perform(it -> increaseQuality())
                        .andNext()
                        .whenSellInIsBelow(0)
                        .perform(it -> destroyQuality());
                break;

            default:
                on(item)
                        .once()
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

}