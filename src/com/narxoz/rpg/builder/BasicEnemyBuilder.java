package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for simple enemies like Goblins, Skeletons, Orcs.
 * These don't need complex boss features like phases.
 */
public class BasicEnemyBuilder implements EnemyBuilder {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;
    private String element;
    private List<Ability> abilities;
    private LootTable lootTable;
    private String aiBehavior;

    public BasicEnemyBuilder() {
        this.abilities = new ArrayList<>();
        this.element = "NONE";
        this.aiBehavior = "NEUTRAL";
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
        // BasicEnemyBuilder ignores phases (not needed for simple enemies)
        return this;
    }

    @Override
    public Enemy build() {
        // Validate mandatory fields
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("Enemy name is mandatory!");
        }
        if (health <= 0) {
            throw new IllegalStateException("Enemy health must be positive!");
        }

        // Create the basic enemy
        Goblin enemy = new Goblin(name);
        
        // Set custom stats
        enemy.setHealthValue(health);
        enemy.setDamageValue(damage);
        enemy.setDefenseValue(defense);
        enemy.setSpeedValue(speed);
        
        // Set loot and abilities
        enemy.setLootTable(lootTable);
        for (Ability ability : abilities) {
            enemy.addAbility(ability);
        }

        return enemy;
    }
}
