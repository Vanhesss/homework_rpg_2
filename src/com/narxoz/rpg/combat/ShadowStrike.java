package com.narxoz.rpg.combat;

/**
 * Shadow-themed ability: Shadow Strike
 * High single-target damage attack with blind effect.
 */
public class ShadowStrike implements Ability {

    private String name = "Shadow Strike";
    private int damage = 180;
    private String description = "Strike from the shadows with deadly precision, blinding the target for 1 attack";

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
        return new ShadowStrike();
    }

    @Override
    public String toString() {
        return String.format("[%s] Damage: %d - %s", name, damage, description);
    }
}
