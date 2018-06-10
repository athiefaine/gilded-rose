package com.gildedrose;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class GildedRoseDomainTest {


    private static final int ITEM_SULFURAS_INITIAL_QUALITY = 80;
    private static final int ITEM_SULFURAS_INITIAL_SELLIN = 0;
    private static final Item ITEM_SULFURAS = new Item(
            "Sulfuras, Hand of Ragnaros",
            ITEM_SULFURAS_INITIAL_SELLIN,
            ITEM_SULFURAS_INITIAL_QUALITY
    );

    private static Item[] TEST_ITEMS = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Conjured Mana Cake", 3, 6)
    };
    private static final String ITEM_SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";

    @Test
    public void item_sulfuras_never_ages() {
        Item itemSulfuras = ITEM_SULFURAS;
        for (int i = 0; i < 100; i++) {
            new ItemUpdater(itemSulfuras).update();
            assertEquals(ITEM_SULFURAS_INITIAL_SELLIN, itemSulfuras.sellIn);
            assertEquals(ITEM_SULFURAS_INITIAL_QUALITY, itemSulfuras.quality);
        }
    }

    @Test
    public void item_quality_never_exceeds_50() {
        Inn inn = new Inn(TEST_ITEMS);
        for (int i = 0; i < 100; i++) {
            inn.updateQuality();
            for (Item item : inn.items) {
                assertTrue("Item quality should never exceed 50",
                        item.quality <= 50);
            }
        }
    }

    @Test
    public void item_quality_is_never_negative() {
        Inn inn = new Inn(TEST_ITEMS);
        for (int i = 0; i < 100; i++) {
            inn.updateQuality();
            for (Item item : inn.items) {
                assertTrue("Item quality should never be negative",
                        item.quality >= 0);
            }
        }
    }

    @Test
    public void item_sellIn_decreases_continuously() {
        Inn inn = new Inn(TEST_ITEMS);
        int[] previousSellIns = Stream.of(inn.items).mapToInt(it -> it.sellIn).toArray();
        for (int i = 0; i < 100; i++) {
            inn.updateQuality();
            int[] currentSellIns = Stream.of(inn.items).mapToInt(it -> it.sellIn + 1).toArray();
            assertArrayEquals(currentSellIns, previousSellIns);
            previousSellIns = Stream.of(inn.items).mapToInt(it -> it.sellIn).toArray();
        }
    }
}
