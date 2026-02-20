package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.ArrayList;

/**
 * Skeleton enemy - another basic enemy type for variety.
 */
public class Skeleton implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;
    private List<Ability> abilities;
    private LootTable lootTable;

    public Skeleton(String name) {
        this.name = name;
        // Skeleton stats: moderate, balanced
        this.health = 150;
        this.damage = 20;
        this.defense = 10;
        this.speed = 30;
        this.abilities = new ArrayList<>();
        this.lootTable = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public List<Ability> getAbilities() {
        return new ArrayList<>(abilities);
    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (Skeleton) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability ability : abilities) {
            System.out.println("  - " + ability.getName() + ": " + ability.getDescription());
        }
        if (lootTable != null) {
            System.out.println(lootTable.getLootInfo());
        } else {
            System.out.println("Loot: None");
        }
    }

    @Override
    public Enemy clone() {
        Skeleton copy = new Skeleton(this.name);
        copy.health = this.health;
        copy.damage = this.damage;
        copy.defense = this.defense;
        copy.speed = this.speed;

        copy.abilities = new ArrayList<>();
        for (Ability ability : this.abilities) {
            copy.abilities.add(ability.clone());
        }

        copy.lootTable = this.lootTable != null ? this.lootTable.clone() : null;
        return copy;
    }

    public void multiplyStats(double multiplier) {
        this.health = (int) Math.round(this.health * multiplier);
        this.damage = (int) Math.round(this.damage * multiplier);
        this.defense = (int) Math.round(this.defense * multiplier);
        this.speed = (int) Math.round(this.speed * multiplier);
    }

    public void addAbility(Ability ability) {
        if (ability != null) {
            this.abilities.add(ability);
        }
    }

    public void setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
    }
}
