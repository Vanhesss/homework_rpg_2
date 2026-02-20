package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;

/**
 * Builder interface for constructing Enemy objects fluently.
 *
 * This solves the "Telescoping Constructor" problem:
 *   BEFORE: new DragonBoss(name, health, damage, defense, speed, element, ...)  // 15 params!
 *   AFTER:  new BossEnemyBuilder()
 *           .setName("Dragon")
 *           .setHealth(5000)
 *           .build()
 *
 * Key principle: FLUENT INTERFACE
 * Every setter returns 'this' so methods can be chained.
 */
public interface EnemyBuilder {

    // ============================================================
    // STAT SETTERS (fluent - return this)
    // ============================================================

    EnemyBuilder setName(String name);

    EnemyBuilder setHealth(int health);

    EnemyBuilder setDamage(int damage);

    EnemyBuilder setDefense(int defense);

    EnemyBuilder setSpeed(int speed);

    EnemyBuilder setElement(String element);

    // ============================================================
    // ABILITY METHODS (fluent - return this)
    // ============================================================

    /**
     * Add a single ability to the enemy.
     */
    EnemyBuilder addAbility(Ability ability);

    /**
     * Set all abilities at once (replaces existing).
     */
    EnemyBuilder setAbilities(List<Ability> abilities);

    // ============================================================
    // LOOT METHOD (fluent - return this)
    // ============================================================

    EnemyBuilder setLootTable(LootTable lootTable);

    // ============================================================
    // AI BEHAVIOR (fluent - return this)
    // ============================================================

    EnemyBuilder setAI(String aiBehavior);

    // ============================================================
    // BOSS-SPECIFIC METHODS (some builders may ignore these)
    // ============================================================

    /**
     * Add a boss phase: triggers when health drops below threshold.
     * BasicEnemyBuilder may ignore this.
     * BossEnemyBuilder will use it.
     */
    EnemyBuilder addPhase(int phaseNumber, int healthThreshold);

    // ============================================================
    // FINAL BUILD METHOD
    // ============================================================

    /**
     * Validates and returns the constructed Enemy.
     * Must throw IllegalStateException if validation fails.
     *
     * @return The fully constructed Enemy object
     * @throws IllegalStateException if mandatory fields are missing or invalid
     */
    Enemy build();
}
