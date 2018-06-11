package com.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialItemSulfurasTest extends GildedRoseAbstractTest {

    private static final int ITEM_SULFURAS_INITIAL_QUALITY = 80;
    private static final int ITEM_SULFURAS_INITIAL_SELLIN = 0;

    @Test
    public void item_sulfuras_never_changes() {
        Item itemSulfuras = new Item(
                "Sulfuras, Hand of Ragnaros",
                ITEM_SULFURAS_INITIAL_SELLIN,
                ITEM_SULFURAS_INITIAL_QUALITY);
        ItemUpdater itemUpdater = new ItemUpdater(itemSulfuras);
        repeatManyTimes(() -> {
            itemUpdater.update();
            assertThat(itemSulfuras.sellIn).isEqualTo(ITEM_SULFURAS_INITIAL_SELLIN);
            assertThat(itemSulfuras.quality).isEqualTo(ITEM_SULFURAS_INITIAL_QUALITY);
        });
    }

}
