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
                .perform(ItemUpdaterHelper::decreaseSellIn);
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
                        .perform(ItemUpdaterHelper::increaseQuality);
                break;

            case "Backstage passes to a TAFKAL80ETC concert":
                on(item)
                        .once()
                        .andAlsoWhenSellInIsBelow(10)
                        .andAlsoWhenSellInIsBelow(5)
                        .perform(ItemUpdaterHelper::increaseQuality)
                        .andNext()
                        .whenSellInIsBelow(0)
                        .perform(ItemUpdaterHelper::destroyQuality);
                break;

            default:
                on(item)
                        .once()
                        .andAlsoWhenSellInIsBelow(0)
                        .perform(ItemUpdaterHelper::decreaseQuality);
        }
    }

}