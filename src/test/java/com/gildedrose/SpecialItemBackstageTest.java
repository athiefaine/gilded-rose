package com.gildedrose;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialItemBackstageTest extends GildedRoseAbstractTest {

    public static final String ITEM_BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final int ITEM_BACKSTAGE_INITIAL_QUALITY = 20;
    public static final int ITEM_BACKSTAGE_INITIAL_SELLIN = 15;
    public static final int MIDDLE_TERM_SELLIN = 9;
    public static final int LONG_TERM_SELLIN = 4;
    public static final int EXPIRED_SELLIN = -1;

    private Item itemBackstage;
    private Item itemBackstageAtMiddleTerm;
    private Item itemBackstageAtLongTerm;
    private Item itemBackstageExpired;


    @Before
    public void setup() {
        itemBackstage = new Item(ITEM_BACKSTAGE_NAME,
                ITEM_BACKSTAGE_INITIAL_SELLIN,
                ITEM_BACKSTAGE_INITIAL_QUALITY);

        itemBackstageAtMiddleTerm = new Item(ITEM_BACKSTAGE_NAME,
                MIDDLE_TERM_SELLIN,
                ITEM_BACKSTAGE_INITIAL_QUALITY);

        itemBackstageAtLongTerm = new Item(ITEM_BACKSTAGE_NAME,
                LONG_TERM_SELLIN,
                ITEM_BACKSTAGE_INITIAL_QUALITY);

        itemBackstageExpired = new Item(ITEM_BACKSTAGE_NAME,
                EXPIRED_SELLIN,
                ITEM_BACKSTAGE_INITIAL_QUALITY);


    }

    @Test
    public void item_backstage_quality_increases_once() {
        new ItemUpdater(itemBackstage).update();
        assertThat(itemBackstage.quality).isEqualTo(ITEM_BACKSTAGE_INITIAL_QUALITY + ONCE);
    }

    @Test
    public void item_backstage_quality_increases_twice_at_middle_term() {
        new ItemUpdater(itemBackstageAtMiddleTerm).update();
        assertThat(itemBackstageAtMiddleTerm.quality).isEqualTo(ITEM_BACKSTAGE_INITIAL_QUALITY + TWICE);
    }

    @Test
    public void item_backstage_quality_increases_thrice_at_long_term() {
        new ItemUpdater(itemBackstageAtLongTerm).update();
        assertThat(itemBackstageAtLongTerm.quality).isEqualTo(ITEM_BACKSTAGE_INITIAL_QUALITY + THRICE);
    }

    @Test
    public void item_backstage_quality_is_zero_at_expiration() {
        new ItemUpdater(itemBackstageExpired).update();
        assertThat(itemBackstageExpired.quality).isEqualTo(0);
    }

    @Test
    public void item_backstage_sellIn_decreases_once() {
        int initialSellIn = ITEM_BACKSTAGE_INITIAL_SELLIN;
        new ItemUpdater(itemBackstage).update();
        assertThat(itemBackstage.sellIn).isEqualTo(initialSellIn - ONCE);
    }

    @Test
    public void item_backstage_quality_is_always_between_0_and_50() {
        ItemUpdater itemUpdater = new ItemUpdater(itemBackstage);
        repeatManyTimes(() -> {
            itemUpdater.update();
            assertThat(itemBackstage.quality).isBetween(0, 50);
        });
    }
}
