package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builder for complex boss enemies like Dragons, Demon Lords.
 * Supports boss-specific features like phases.
 */
public class BossEnemyBuilder implements EnemyBuilder {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;
    private String element;
    private List<Ability> abilities;
    private LootTable lootTable;
    private String aiBehavior;
    private Map<Integer, Integer> phases; // phaseNumber -> healthThreshold
    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;

    public BossEnemyBuilder() {
        this.abilities = new ArrayList<>();
        this.phases = new HashMap<>();
        this.element = "NONE";
        this.aiBehavior = "NEUTRAL";
        this.canFly = false;
        this.hasBreathAttack = false;
        this.wingspan = 0;
    }

    @Override
    public EnemyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public EnemyBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    @Override
    public EnemyBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public EnemyBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    @Override
    public EnemyBuilder setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public EnemyBuilder setElement(String element) {
        this.element = element;
        return this;
    }

    @Override
    public EnemyBuilder addAbility(Ability ability) {
        if (ability != null) {
            this.abilities.add(ability);
        }
        return this;
    }

    @Override
    public EnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities = abilities != null ? new ArrayList<>(abilities) : new ArrayList<>();
        return this;
    }

    @Override
    public EnemyBuilder setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
        return this;
    }

    @Override
    public EnemyBuilder setAI(String aiBehavior) {
        this.aiBehavior = aiBehavior;
        return this;
    }

    @Override
    public EnemyBuilder addPhase(int phaseNumber, int healthThreshold) {
        this.phases.put(phaseNumber, healthThreshold);
        return this;
    }

    /**
     * Set boss-specific property: can it fly?
     */
    public BossEnemyBuilder setCanFly(boolean canFly) {
        this.canFly = canFly;
        return this;
    }

    /**
     * Set boss-specific property: has breath attack?
     */
    public BossEnemyBuilder setHasBreathAttack(boolean hasBreathAttack) {
        this.hasBreathAttack = hasBreathAttack;
        return this;
    }

    /**
     * Set boss-specific property: wingspan.
     */
    public BossEnemyBuilder setWingspan(int wingspan) {
        this.wingspan = wingspan;
        return this;
    }

    @Override
    public Enemy build() {
        // Validate mandatory fields
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("Boss name is mandatory!");
        }
        if (health <= 0) {
            throw new IllegalStateException("Boss health must be positive!");
        }
        if (phases.isEmpty()) {
            throw new IllegalStateException("Boss must have at least one phase!");
        }

        // Extract phase thresholds (assuming phases 1, 2, 3)
        int phase1 = phases.getOrDefault(1, health);
        int phase2 = phases.getOrDefault(2, health / 2);
        int phase3 = phases.getOrDefault(3, health / 4);

        // Create DragonBoss using the constructor
        // NOTE: DragonBoss constructor is package-private, called only by this builder
        DragonBoss boss = new DragonBoss(
            name, health, damage, defense, speed, element,
            abilities, phase1, phase2, phase3, lootTable, aiBehavior,
            canFly, hasBreathAttack, wingspan
        );

        return boss;
    }
}
