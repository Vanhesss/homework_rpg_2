package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Example complex boss enemy — THE REASON BUILDER PATTERN EXISTS.
 *
 * ============================================================
 * READ THIS CAREFULLY — THIS IS THE CORE LEARNING MOMENT!
 * ============================================================
 *
 * Look at this constructor. REALLY look at it.
 * Count the parameters. Imagine using it in Main.java.
 * Imagine a teammate trying to understand what each parameter means.
 *
 * This is called the "Telescoping Constructor" anti-pattern.
 * It's the #1 problem that the Builder pattern solves.
 *
 * YOUR MISSION:
 * After studying this horror, you will create an EnemyBuilder
 * that constructs DragonBoss (and other complex enemies)
 * step-by-step, with clear and readable code.
 *
 * Compare:
 *
 *   BEFORE (Telescoping Constructor — current code):
 *   new DragonBoss("Fire Dragon", 50000, 500, 200, 50, "FIRE",
 *       abilities, 30000, 15000, 5000, loot, "AGGRESSIVE",
 *       true, true, 20);
 *   // What does 'true, true, 20' mean? Nobody knows!
 *
 *   AFTER (Builder Pattern — your implementation):
 *   new BossEnemyBuilder()
 *       .setName("Fire Dragon")
 *       .setHealth(50000)
 *       .setDamage(500)
 *       .setDefense(200)
 *       .setSpeed(50)
 *       .setElement("FIRE")
 *       .addAbility(new FlameBreath())
 *       .addAbility(new WingBuffet())
 *       .addPhase(1, 50000)
 *       .addPhase(2, 30000)
 *       .addPhase(3, 15000)
 *       .setLootTable(fireLoot)
 *       .setAI("AGGRESSIVE")
 *       .build();
 *   // Every parameter is labeled! Readable! Maintainable!
 *
 * ============================================================
 * TODO: After implementing your Builder, REFACTOR this class!
 * ============================================================
 * - Remove (or simplify) the telescoping constructor
 * - Make DragonBoss constructable ONLY through a Builder
 * - Make the built DragonBoss IMMUTABLE (no setters!)
 * - The Builder handles all the complexity
 */
public class DragonBoss implements Enemy {

    // --- Basic Stats ---
    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    // --- Elemental Theme ---
    private String element;

    // --- Abilities ---
    private List<Ability> abilities;

    // --- Boss Phases (health thresholds that trigger behavior changes) ---
    // Phase number -> health threshold at which this phase activates
    private Map<Integer, Integer> phases;

    // --- Loot ---
    private LootTable lootTable;

    // --- AI Behavior ---
    private String aiBehavior;

    // --- Special Properties ---
    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;

    /**
     * CONSTRUCTOR (called only by BossEnemyBuilder).
     *
     * Package: visible from same package (com.narxoz.rpg.builder)
     * Direct instantiation outside of Builder is strongly discouraged!
     *
     * This solves the "Telescoping Constructor" anti-pattern:
     *   BEFORE: public constructor with 15 parameters (nightmare!)
     *   AFTER: accessed only via BossEnemyBuilder
     */
    public DragonBoss(String name, int health, int damage, int defense,
                      int speed, String element,
                      List<Ability> abilities,
                      int phase1Threshold, int phase2Threshold, int phase3Threshold,
                      LootTable lootTable, String aiBehavior,
                      boolean canFly, boolean hasBreathAttack, int wingspan) {

        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.element = element;
        this.abilities = abilities != null ? abilities : new ArrayList<>();
        this.phases = new HashMap<>();
        this.phases.put(1, phase1Threshold);
        this.phases.put(2, phase2Threshold);
        this.phases.put(3, phase3Threshold);
        this.lootTable = lootTable;
        this.aiBehavior = aiBehavior;
        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }

    // ============================================================
    // ENEMY INTERFACE IMPLEMENTATION
    // ============================================================

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
        System.out.println("=== " + name + " (Dragon Boss) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element);
        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability ability : abilities) {
            System.out.println("  - " + ability.getName() + ": " + ability.getDescription());
        }
        System.out.println("Boss Phases: " + phases.size());
        for (Map.Entry<Integer, Integer> phase : phases.entrySet()) {
            System.out.println("  Phase " + phase.getKey()
                    + ": triggers at " + phase.getValue() + " HP");
        }
        System.out.println("AI Behavior: " + aiBehavior);
        System.out.println("Can Fly: " + canFly
                + " | Breath Attack: " + hasBreathAttack
                + " | Wingspan: " + wingspan);
        if (lootTable != null) {
            System.out.println(lootTable.getLootInfo());
        }
    }

    @Override
    public Enemy clone() {
        // Deep copy: create new dragon with cloned abilities and loot
        DragonBoss copy = new DragonBoss(
            this.name, this.health, this.damage, this.defense, this.speed,
            this.element,
            new ArrayList<>(this.abilities), // Will be deep copied below
            this.phases.getOrDefault(1, this.health),
            this.phases.getOrDefault(2, this.health / 2),
            this.phases.getOrDefault(3, this.health / 4),
            this.lootTable != null ? this.lootTable.clone() : null,
            this.aiBehavior,
            this.canFly,
            this.hasBreathAttack,
            this.wingspan
        );

        // Deep copy abilities
        copy.abilities.clear();
        for (Ability ability : this.abilities) {
            copy.abilities.add(ability.clone());
        }

        return copy;
    }

    // ============================================================
    // HELPER METHODS FOR VARIANT CREATION
    // ============================================================

    public void multiplyStats(double multiplier) {
        this.health = (int) Math.round(this.health * multiplier);
        this.damage = (int) Math.round(this.damage * multiplier);
        this.defense = (int) Math.round(this.defense * multiplier);
        this.speed = (int) Math.round(this.speed * multiplier);
    }

}
