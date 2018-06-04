package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void update_quality_once() {
        Inn inn = new Inn();
        inn.updateQuality();

        assertEquals("+5 Dexterity Vest, 9, 19", inn.items[0].toString());
        assertEquals("Aged Brie, 1, 1", inn.items[1].toString());
        assertEquals("Elixir of the Mongoose, 4, 6", inn.items[2].toString());
        assertEquals("Sulfuras, Hand of Ragnaros, 0, 80", inn.items[3].toString());
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 14, 21", inn.items[4].toString());
        assertEquals("Conjured Mana Cake, 2, 5", inn.items[5].toString());
    }

}
