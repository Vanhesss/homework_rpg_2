package com.narxoz.rpg.combat;

/**
 * Fire-themed ability: Fire Shield
 * Defensive buff that reflects fire damage back to attackers.
 */
public class FireShield implements Ability {

    private String name = "Fire Shield";
    private int damage = 0; // Defensive ability, no direct damage
    private String description = "Create a defensive shield of flames that reflects 30% of incoming fire damage";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Ability clone() {
        return new FireShield();
    }

    @Override
    public String toString() {
        return String.format("[%s] Defense - %s", name, description);
    }
}
