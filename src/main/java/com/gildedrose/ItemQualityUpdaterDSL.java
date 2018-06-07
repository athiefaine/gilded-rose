package com.gildedrose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class ItemQualityUpdaterDSL {

    private final Collection<Integer> sellInThresholds = new ArrayList<>();
    private Item item;

    private ItemQualityUpdaterDSL() {

    }

    public static ItemQualityUpdaterDSL on(Item item) {
        ItemQualityUpdaterDSL itemQualityUpdaterDSL = new ItemQualityUpdaterDSL();
        itemQualityUpdaterDSL.item = item;
        return itemQualityUpdaterDSL;
    }

    ItemQualityUpdaterDSL perform(Consumer<Item> action) {
        sellInThresholds.stream()
                .filter(val -> item.sellIn < val)
                .map(i -> item)
                .forEach(action);
        return this;
    }

    public ItemQualityUpdaterDSL once() {
        sellInThresholds.add(Integer.MAX_VALUE);
        return this;
    }

    public ItemQualityUpdaterDSL whenSellInIsBelow(Integer threshold) {
        sellInThresholds.add(threshold);
        return this;
    }

    public ItemQualityUpdaterDSL andAlsoWhenSellInIsBelow(Integer threshold) {
        return whenSellInIsBelow(threshold);
    }

    public ItemQualityUpdaterDSL andNext() {
        sellInThresholds.clear();
        return this;
    }


}
