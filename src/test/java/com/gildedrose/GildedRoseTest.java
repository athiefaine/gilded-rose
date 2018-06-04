package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class GildedRoseTest {

    @Test
    public void update_quality_on_a_single_item_once() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20)
        };
        Inn inn = new Inn(items);
        inn.updateQuality();

        String[] expectedInnContents =  {
                "+5 Dexterity Vest, 9, 19",
        };

        assertInnContentsEquals(expectedInnContents, inn);
    }

    @Test
    public void update_quality_for_full_item_stock_once() {
        Inn inn = new Inn();
        inn.updateQuality();

        String[] expectedInnContents =  {
                "+5 Dexterity Vest, 9, 19",
                "Aged Brie, 1, 1",
                "Elixir of the Mongoose, 4, 6",
                "Sulfuras, Hand of Ragnaros, 0, 80",
                "Backstage passes to a TAFKAL80ETC concert, 14, 21",
                "Conjured Mana Cake, 2, 5"
        };

        assertInnContentsEquals(expectedInnContents, inn);
    }

    @Test
    public void update_quality_for_full_item_stock_twice() {
        Inn inn = new Inn();
        inn.updateQuality();
        inn.updateQuality();

        String[] expectedInnContents =  {
                "+5 Dexterity Vest, 8, 18",
                "Aged Brie, 0, 2",
                "Elixir of the Mongoose, 3, 5",
                "Sulfuras, Hand of Ragnaros, 0, 80",
                "Backstage passes to a TAFKAL80ETC concert, 13, 22",
                "Conjured Mana Cake, 1, 4"
        };

        assertInnContentsEquals(expectedInnContents, inn);
    }

    @Test
    public void update_quality_for_full_item_stock_one_hundred_times() {
        Inn inn = new Inn();
        for (int i=0; i< 100; i++) {
            inn.updateQuality();
        }

        String[] expectedInnContents =  {
                "+5 Dexterity Vest, -90, 0",
                "Aged Brie, -98, 50",
                "Elixir of the Mongoose, -95, 0",
                "Sulfuras, Hand of Ragnaros, 0, 80",
                "Backstage passes to a TAFKAL80ETC concert, -85, 0",
                "Conjured Mana Cake, -97, 0"
        };

        assertInnContentsEquals(expectedInnContents, inn);
    }

    @Test
    public void update_quality_behaves_as_in_golden_master() {
        Inn inn = new Inn();
        LegacyInn legacyInn = new LegacyInn();
        for (int i=0; i< 100; i++) {
            inn.updateQuality();
            legacyInn.updateQuality();
            assertInnAndLegacyInnContentAreEquals(inn, legacyInn);
        }

    }

    private void assertInnAndLegacyInnContentAreEquals(Inn inn, LegacyInn legacyInn) {
        assertArrayEquals(
                itemsToStringArray(legacyInn.items),
                itemsToStringArray(inn.items));
    }

    private void assertInnContentsEquals(String[] expectedInnContents, Inn inn) {
        assertArrayEquals(
                expectedInnContents,
                itemsToStringArray(inn.items));
    }

    private Object[] itemsToStringArray(Item[] items) {
        return Arrays.stream(items)
                .map(item -> item.toString())
                .toArray();
    }
}
