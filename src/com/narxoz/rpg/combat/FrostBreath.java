package com.narxoz.rpg.combat;

/**
 * Ice-themed ability: Frost Breath
 * Damage attack that slows enemies.
 */
public class FrostBreath implements Ability {

    private String name = "Frost Breath";
    private int damage = 120;
    private String description = "Exhale a freezing breath, dealing damage and slowing enemy movement by 50%";

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
        return new FrostBreath();
    }

    @Override
    public String toString() {
        return String.format("[%s] Damage: %d - %s", name, damage, description);
    }
}
