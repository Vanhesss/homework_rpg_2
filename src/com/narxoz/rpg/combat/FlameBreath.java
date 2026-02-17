package com.narxoz.rpg.combat;

/**
 * Fire-themed ability: Flame Breath
 * AoE damage attack with burn effect.
 */
public class FlameBreath implements Ability {

    private String name = "Flame Breath";
    private int damage = 150;
    private String description = "Breathe a massive cone of fire, dealing AoE damage and applying burn effect";

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
        return new FlameBreath();
    }

    @Override
    public String toString() {
        return String.format("[%s] Damage: %d - %s", name, damage, description);
    }
}
