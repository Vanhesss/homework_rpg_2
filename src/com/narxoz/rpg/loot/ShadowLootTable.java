package com.narxoz.rpg.loot;

import java.util.List;
import java.util.ArrayList;

/**
 * Shadow-themed loot table.
 * Drops shadow-related items when defeated.
 */
public class ShadowLootTable implements LootTable {

    private List<String> items;
    private int goldDrop;
    private int experienceDrop;

    public ShadowLootTable() {
        this.items = new ArrayList<>();
        this.items.add("Shadow Gem");
        this.items.add("Dark Essence");
        this.items.add("Shadow Rune");
        this.goldDrop = 550;
        this.experienceDrop = 300;
    }

    private ShadowLootTable(List<String> items, int goldDrop, int experienceDrop) {
        this.items = new ArrayList<>(items);
        this.goldDrop = goldDrop;
        this.experienceDrop = experienceDrop;
    }

    @Override
    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public int getGoldDrop() {
        return goldDrop;
    }

    @Override
    public int getExperienceDrop() {
        return experienceDrop;
    }

    @Override
    public String getLootInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Shadow Loot ===\n");
        sb.append("Items: ").append(items).append("\n");
        sb.append("Gold: ").append(goldDrop).append("\n");
        sb.append("Experience: ").append(experienceDrop);
        return sb.toString();
    }

    @Override
    public LootTable clone() {
        return new ShadowLootTable(new ArrayList<>(this.items), this.goldDrop, this.experienceDrop);
    }

    @Override
    public String toString() {
        return String.format("ShadowLootTable [Items: %s, Gold: %d, XP: %d]", items, goldDrop, experienceDrop);
    }
}
