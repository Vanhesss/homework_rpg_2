package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;

/**
 * Base interface for all enemies in the RPG system.
 *
 * Every enemy — from a lowly Goblin to an Ancient Dragon — shares
 * certain characteristics: they have stats, abilities, and loot.
 * But HOW they are created varies dramatically.
 *
 * ============================================================
 * WHY THIS INTERFACE MATTERS FOR DESIGN PATTERNS:
 * ============================================================
 *
 * Builder Pattern:
 *   Complex enemies have many fields (stats, abilities, phases, loot, AI).
 *   The Builder pattern constructs enemies step-by-step instead of
 *   cramming everything into one monstrous constructor.
 *   → Think: Should Enemy be immutable once built? (Hint: YES!)
 *
 * Prototype Pattern:
 *   This interface includes a clone() method. Enemies must be CLONABLE
 *   so we can create variants efficiently:
 *     Base Goblin → Elite Goblin → Goblin Champion → Goblin King
 *   → Think: What needs DEEP copying? What can be SHALLOW copied?
 *
 * Factory Method:
 *   The Builder's build() method IS a factory method — it produces
 *   Enemy objects. Different builders produce different enemy types.
 *
 * Abstract Factory:
 *   Enemy components (abilities, loot) come from themed factories.
 *   A FireComponentFactory guarantees all components match the fire theme.
 *
 * ============================================================
 * YOUR TASKS:
 * ============================================================
 *
 * TODO: Decide — should this be an interface or abstract class?
 *   - Interface: If implementations are very different
 *   - Abstract class: If you want shared fields (name, health, etc.)
 *   Hint: An abstract class with shared stat fields might be cleaner.
 *
 * TODO: Define the core enemy contract.
 *   Every enemy should provide:
 *   - Basic stats (health, damage, defense, speed)
 *   - Abilities they can use
 *   - Loot they drop when defeated
 *   - Information display (for the demo)
 *   - Clone method (for Prototype pattern)
 *
 * TODO: Think about immutability.
 *   - Once built by the Builder, should enemy stats change?
 *   - Should clone() return a mutable or immutable copy?
 *   - How do you allow Prototype to modify cloned stats?
 */
public interface Enemy {

    // ============================================================
    // CORE STAT METHODS
    // ============================================================

    /**
     * @return The name of this enemy
     */
    String getName();

    /**
     * @return Current health points
     */
    int getHealth();

    /**
     * @return Damage this enemy deals per attack
     */
    int getDamage();

    /**
     * @return Defensive value (damage reduction)
     */
    int getDefense();

    /**
     * @return Speed stat (attack priority)
     */
    int getSpeed();

    // ============================================================
    // ABILITY METHODS
    // ============================================================

    /**
     * @return Unmodifiable list of this enemy's abilities
     */
    List<Ability> getAbilities();

    // ============================================================
    // LOOT METHODS
    // ============================================================

    /**
     * @return The loot table this enemy drops on defeat
     */
    LootTable getLootTable();

    // ============================================================
    // DISPLAY METHOD
    // ============================================================

    /**
     * Displays all information about this enemy:
     * - Name, Health, Damage, Defense, Speed
     * - All abilities with descriptions
     * - Loot drops
     */
    void displayInfo();

    // ============================================================
    // PROTOTYPE PATTERN METHOD
    // ============================================================

    /**
     * Creates a DEEP COPY of this enemy for variant creation.
     *
     * CRITICAL REQUIREMENTS:
     * - Primitive stats (health, damage, etc.) → direct copy
     * - Ability list → NEW list with CLONED abilities (not references!)
     * - LootTable → CLONED (not reference!)
     *
     * TEST: Clone an enemy, modify the clone's abilities.
     * If the original's abilities change → BUG: too shallow!
     *
     * @return A new Enemy instance completely independent from this one
     */
    Enemy clone();

}
