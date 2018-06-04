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

        assertArrayEquals(expectedInnContents, Arrays.stream(inn.items).map(item -> item.toString()).toArray());
    }

}
