package com.gildedrose;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomItemTest extends GildedRoseAbstractTest {


    public static final String ITEM_RANDOM_NAME = "The iron bar";
    public static final int ITEM_RANDOM_INITIAL_QUALITY = 20;
    public static final int ITEM_RANDOM_INITIAL_SELLIN = 10;
    public static final int NEGATIVE_SELLIN = -1;

    private Item itemRandom;
    private Item itemRandomWithNegativeSellIn;

    @Before
    public void setup() {
        itemRandom = new Item(ITEM_RANDOM_NAME,
                ITEM_RANDOM_INITIAL_SELLIN,
                ITEM_RANDOM_INITIAL_QUALITY);

        itemRandomWithNegativeSellIn = new Item(ITEM_RANDOM_NAME,
                NEGATIVE_SELLIN,
                ITEM_RANDOM_INITIAL_QUALITY);
    }

    @Test
    public void item_random_quality_decreases_once() {
        new ItemUpdater(itemRandom).update();
        assertThat(itemRandom.quality).isEqualTo(ITEM_RANDOM_INITIAL_QUALITY - ONCE);
    }

    @Test
    public void item_random_quality_decreases_twice_when_sellIn_is_negative() {
        new ItemUpdater(itemRandomWithNegativeSellIn).update();
        assertThat(itemRandomWithNegativeSellIn.quality).isEqualTo(ITEM_RANDOM_INITIAL_QUALITY - TWICE);
    }

    @Test
    public void item_random_sellIn_decreases_once() {
        new ItemUpdater(itemRandom).update();
        assertThat(itemRandom.sellIn).isEqualTo(ITEM_RANDOM_INITIAL_SELLIN - ONCE);
    }

    @Test
    public void item_random_quality_is_always_between_0_and_50() {
        ItemUpdater itemUpdater = new ItemUpdater(itemRandom);
        repeatManyTimes(() -> {
            itemUpdater.update();
            assertThat(itemRandom.quality).isBetween(0, 50);
        });
    }

}
