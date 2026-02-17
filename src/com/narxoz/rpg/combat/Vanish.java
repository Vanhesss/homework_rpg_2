package com.narxoz.rpg.combat;

/**
 * Shadow-themed ability: Vanish
 * Stealth/evasion ability that increases dodge chance.
 */
public class Vanish implements Ability {

    private String name = "Vanish";
    private int damage = 0; // Defensive ability, no direct damage
    private String description = "Melt into the shadows, increasing evasion chance to 60% for 3 turns";

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
        return new Vanish();
    }

    @Override
    public String toString() {
        return String.format("[%s] Evasion - %s", name, description);
    }
}
