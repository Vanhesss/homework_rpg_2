package com.narxoz.rpg.loot;

/**
 * Interface for enemy loot/drop tables in the RPG system.
 *
 * Each enemy can have a loot table that determines what items they drop
 * when defeated. Loot tables are themed:
 * - Fire enemies drop fire-related items (Fire Gem, Dragon Scale)
 * - Ice enemies drop ice-related items (Ice Gem, Frost Scale)
 * - Shadow enemies drop shadow-related items (Shadow Gem, Dark Essence)
 *
 * Abstract Factory Connection:
 * Each themed EnemyComponentFactory creates a matching LootTable.
 * This guarantees Fire enemies always drop fire loot — no mixing!
 *
 * Prototype Pattern Note:
 * Loot tables must be DEEP-COPYABLE! When you clone an enemy,
 * its loot table must be an independent copy. Otherwise, modifying
 * a cloned enemy's loot will affect the original.
 *
 * TODO: Define what all loot tables have in common.
 * Think about:
 * - What items does the enemy drop?
 * - How much gold and experience are awarded?
 * - How should loot be displayed?
 * - How should loot tables be cloned?
 *
 * Consider methods like:
 * - List<String> getItems()
 * - int getGoldDrop()
 * - int getExperienceDrop()
 * - String getLootInfo()
 * - LootTable clone()   <-- Critical for Prototype pattern!
 */
import java.util.List;

public interface LootTable {

    /**
     * @return List of item names this loot table drops
     */
    List<String> getItems();

    /**
     * @return Amount of gold awarded for defeating this enemy
     */
    int getGoldDrop();

    /**
     * @return Amount of experience points awarded
     */
    int getExperienceDrop();

    /**
     * @return A formatted string showing all loot information
     */
    String getLootInfo();

    /**
     * Creates a deep copy of this loot table for Prototype pattern.
     * CRITICAL: Must be a true independent copy of the items list!
     *
     * @return A new LootTable instance with the same properties
     */
    LootTable clone();

}
