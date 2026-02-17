package com.narxoz.rpg.loot;

import java.util.List;
import java.util.ArrayList;

/**
 * Ice-themed loot table.
 * Drops ice-related items when defeated.
 */
public class IceLootTable implements LootTable {

    private List<String> items;
    private int goldDrop;
    private int experienceDrop;

    public IceLootTable() {
        this.items = new ArrayList<>();
        this.items.add("Ice Gem");
        this.items.add("Frost Scale");
        this.items.add("Ice Rune");
        this.goldDrop = 450;
        this.experienceDrop = 225;
    }

    private IceLootTable(List<String> items, int goldDrop, int experienceDrop) {
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
        sb.append("=== Ice Loot ===\n");
        sb.append("Items: ").append(items).append("\n");
        sb.append("Gold: ").append(goldDrop).append("\n");
        sb.append("Experience: ").append(experienceDrop);
        return sb.toString();
    }

    @Override
    public LootTable clone() {
        return new IceLootTable(new ArrayList<>(this.items), this.goldDrop, this.experienceDrop);
    }

    @Override
    public String toString() {
        return String.format("IceLootTable [Items: %s, Gold: %d, XP: %d]", items, goldDrop, experienceDrop);
    }
}
