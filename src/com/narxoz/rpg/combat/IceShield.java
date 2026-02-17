package com.narxoz.rpg.combat;

/**
 * Ice-themed ability: Ice Shield
 * Defensive buff that freezes attackers.
 */
public class IceShield implements Ability {

    private String name = "Ice Shield";
    private int damage = 0; // Defensive ability, no direct damage
    private String description = "Form a shield of ice that freezes attackers for 2 seconds";

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
        return new IceShield();
    }

    @Override
    public String toString() {
        return String.format("[%s] Defense - %s", name, description);
    }
}
