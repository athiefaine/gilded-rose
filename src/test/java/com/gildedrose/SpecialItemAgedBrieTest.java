package com.gildedrose;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialItemAgedBrieTest extends GildedRoseAbstractTest {

    public static final String ITEM_AGED_BRIE_NAME = "Aged Brie";
    public static final int ITEM_AGED_BRIE_INITIAL_QUALITY = 0;
    public static final int ITEM_AGED_BRIE_INITIAL_SELLIN = 2;
    public static final int NEGATIVE_SELLIN = -1;

    private Item itemAgedBrie;
    private Item itemAgedBrieWithNegativeSellIn;

    @Before
    public void setup() {
        itemAgedBrie = new Item(ITEM_AGED_BRIE_NAME,
                ITEM_AGED_BRIE_INITIAL_SELLIN,
                ITEM_AGED_BRIE_INITIAL_QUALITY);

        itemAgedBrieWithNegativeSellIn = new Item(ITEM_AGED_BRIE_NAME,
                NEGATIVE_SELLIN,
                ITEM_AGED_BRIE_INITIAL_QUALITY);
    }

    @Test
    public void item_agedBrie_quality_increases_once() {
        new ItemUpdater(itemAgedBrie).update();
        assertThat(itemAgedBrie.quality).isEqualTo(ITEM_AGED_BRIE_INITIAL_QUALITY + ONCE);
    }

    @Test
    public void item_agedBrie_quality_increases_twice_when_sellIn_is_negative() {
        new ItemUpdater(itemAgedBrieWithNegativeSellIn).update();
        assertThat(itemAgedBrieWithNegativeSellIn.quality).isEqualTo(ITEM_AGED_BRIE_INITIAL_QUALITY + TWICE);
    }

    @Test
    public void item_agedBrie_sellIn_decreases_once() {
        new ItemUpdater(itemAgedBrie).update();
        assertThat(itemAgedBrie.sellIn).isEqualTo(ITEM_AGED_BRIE_INITIAL_SELLIN - ONCE);
    }

    @Test
    public void item_agedBrie_quality_is_always_between_0_and_50() {
        ItemUpdater itemUpdater = new ItemUpdater(itemAgedBrie);
        repeatManyTimes(() -> {
            itemUpdater.update();
            assertThat(itemAgedBrie.quality).isBetween(0, 50);
        });
    }
}
