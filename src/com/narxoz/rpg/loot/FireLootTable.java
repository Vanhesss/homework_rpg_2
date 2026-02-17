package com.narxoz.rpg.loot;

import java.util.List;
import java.util.ArrayList;

/**
 * Fire-themed loot table.
 * Drops fire-related items when defeated.
 */
public class FireLootTable implements LootTable {

    private List<String> items;
    private int goldDrop;
    private int experienceDrop;

    public FireLootTable() {
        this.items = new ArrayList<>();
        this.items.add("Fire Gem");
        this.items.add("Dragon Scale");
        this.items.add("Flame Rune");
        this.goldDrop = 500;
        this.experienceDrop = 250;
    }

    private FireLootTable(List<String> items, int goldDrop, int experienceDrop) {
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
        sb.append("=== Fire Loot ===\n");
        sb.append("Items: ").append(items).append("\n");
        sb.append("Gold: ").append(goldDrop).append("\n");
        sb.append("Experience: ").append(experienceDrop);
        return sb.toString();
    }

    @Override
    public LootTable clone() {
        return new FireLootTable(new ArrayList<>(this.items), this.goldDrop, this.experienceDrop);
    }

    @Override
    public String toString() {
        return String.format("FireLootTable [Items: %s, Gold: %d, XP: %d]", items, goldDrop, experienceDrop);
    }
}
