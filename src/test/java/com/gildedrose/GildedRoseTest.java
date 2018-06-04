package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class GildedRoseTest {

    @Test
    public void update_quality_once() {
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
    public void update_quality_twice() {
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


    private void assertInnContentsEquals(String[] expectedInnContents, Inn inn) {
        assertArrayEquals(expectedInnContents,
                Arrays.stream(inn.items)
                        .map(item -> item.toString())
                        .toArray());
    }

}
