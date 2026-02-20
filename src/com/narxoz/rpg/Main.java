package com.narxoz.rpg;

import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.FlameBreath;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.enemy.Skeleton;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;

/**
 * Main demonstration of all 4 design patterns working together:
 * Abstract Factory → Builder → Factory Method → Prototype
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Enemy System - Design Patterns ===\n");

        // ============================================================
        // PART 1: ABSTRACT FACTORY
        // ============================================================
        System.out.println("1. ABSTRACT FACTORY");
        System.out.println("-----------------");
        System.out.println("Creating themed component families:\n");

        EnemyComponentFactory fireFactory = new FireComponentFactory();
        EnemyComponentFactory iceFactory = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        System.out.println("Fire:   " + fireFactory.createAbilities().stream().map(Ability::getName).toList() + 
                          " → AI: " + fireFactory.createAIBehavior());
        System.out.println("Ice:    " + iceFactory.createAbilities().stream().map(Ability::getName).toList() + 
                          " → AI: " + iceFactory.createAIBehavior());
        System.out.println("Shadow: " + shadowFactory.createAbilities().stream().map(Ability::getName).toList() + 
                          " → AI: " + shadowFactory.createAIBehavior());

        System.out.println("\nResult: Each factory guarantees consistent themed components.\n");

        // ============================================================
        // PART 2: BUILDER
        // ============================================================
        System.out.println("\n2. BUILDER");
        System.out.println("----------");
        System.out.println("Building complex enemies step-by-step:\n");

        Enemy fireDragon = new BossEnemyBuilder()
            .setName("Fire Dragon")
            .setHealth(5000)
            .setDamage(200)
            .setElement("FIRE")
            .setAbilities(fireFactory.createAbilities())
            .setLootTable(fireFactory.createLootTable())
            .addPhase(1, 5000)
            .addPhase(2, 2500)
            .addPhase(3, 1250)
            .build();

        System.out.println("Created: " + fireDragon.getName() + " (HP: " + fireDragon.getHealth() + 
                          ", DMG: " + fireDragon.getDamage() + ")");

        EnemyDirector director = new EnemyDirector(new BossEnemyBuilder());
        Enemy iceBoss = director.createMiniBoss(iceFactory);
        Enemy shadowBoss = director.createRaidBoss(shadowFactory);

        System.out.println("Created: " + iceBoss.getName() + " (HP: " + iceBoss.getHealth() + ")");
        System.out.println("Created: " + shadowBoss.getName() + " (HP: " + shadowBoss.getHealth() + ")");

        System.out.println("\nResult: Fluent builder replaces 15-parameter constructor.\n");

        // ============================================================
        // PART 3: PROTOTYPE
        // ============================================================
        System.out.println("\n3. PROTOTYPE");
        System.out.println("-----------");
        System.out.println("Creating variants through cloning:\n");

        // Goblin difficulty tiers
        Goblin baseGoblin = new Goblin("Goblin");
        baseGoblin.setLootTable(fireFactory.createLootTable());
        baseGoblin.addAbility(new FlameBreath());

        Enemy eliteGoblin = baseGoblin.clone();
        if (eliteGoblin instanceof Goblin) {
            ((Goblin) eliteGoblin).multiplyStats(2.0);
        }

        Enemy championGoblin = baseGoblin.clone();
        if (championGoblin instanceof Goblin) {
            ((Goblin) championGoblin).multiplyStats(5.0);
        }

        Enemy kingGoblin = baseGoblin.clone();
        if (kingGoblin instanceof Goblin) {
            ((Goblin) kingGoblin).multiplyStats(10.0);
        }

        System.out.println("Goblin variants:");
        System.out.println("  Base:      HP " + baseGoblin.getHealth());
        System.out.println("  Elite (2x): HP " + eliteGoblin.getHealth());
        System.out.println("  Champion (5x): HP " + championGoblin.getHealth());
        System.out.println("  King (10x): HP " + kingGoblin.getHealth());

        // Skeleton variants
        Skeleton baseSkeleton = new Skeleton("Skeleton");
        baseSkeleton.setLootTable(iceFactory.createLootTable());

        Enemy eliteSkeleton = baseSkeleton.clone();
        if (eliteSkeleton instanceof Skeleton) {
            ((Skeleton) eliteSkeleton).multiplyStats(3.0);
        }

        System.out.println("\nSkeleton variants:");
        System.out.println("  Base:      HP " + baseSkeleton.getHealth());
        System.out.println("  Elite (3x): HP " + eliteSkeleton.getHealth());

        System.out.println("\nVerification (original unchanged):");
        System.out.println("  Base Goblin: HP " + baseGoblin.getHealth() + " (intact)");
        System.out.println("  Elite Goblin: HP " + eliteGoblin.getHealth() + " (cloned & modified)");

        System.out.println("\nResult: Deep-copy cloning creates independent variants.\n");

        // ============================================================
        // PART 4: ALL PATTERNS TOGETHER
        // ============================================================
        System.out.println("\n4. ALL PATTERNS INTEGRATED");
        System.out.println("-------------------------");
        System.out.println("Complete pipeline in action:\n");

        System.out.println("Factory → Builder → Registry → Prototype\n");

        Enemy demonLord = new BossEnemyBuilder()
            .setName("Demon Lord")
            .setHealth(8000)
            .setDamage(250)
            .setElement("SHADOW")
            .setAbilities(shadowFactory.createAbilities())
            .setLootTable(shadowFactory.createLootTable())
            .addPhase(1, 8000)
            .addPhase(2, 4000)
            .addPhase(3, 2000)
            .build();

        EnemyRegistry registry = new EnemyRegistry();
        registry.registerTemplate("demon-lord", demonLord);

        Enemy greater = registry.createFromTemplate("demon-lord");
        if (greater instanceof com.narxoz.rpg.enemy.DragonBoss) {
            ((com.narxoz.rpg.enemy.DragonBoss) greater).multiplyStats(2.0);
        }

        Enemy supreme = registry.createFromTemplate("demon-lord");
        if (supreme instanceof com.narxoz.rpg.enemy.DragonBoss) {
            ((com.narxoz.rpg.enemy.DragonBoss) supreme).multiplyStats(3.0);
        }

        System.out.println("Demon Lord variants:");
        System.out.println("  Original:       HP " + demonLord.getHealth());
        System.out.println("  Greater (2x):   HP " + greater.getHealth());
        System.out.println("  Supreme (3x):   HP " + supreme.getHealth());

        // ============================================================
        // SUMMARY
        // ============================================================
        System.out.println("\n\n=== SUMMARY ===\n");
        System.out.println("ABSTRACT FACTORY: Themed components (Fire/Ice/Shadow)");
        System.out.println("BUILDER: Fluent step-by-step construction");
        System.out.println("FACTORY METHOD: Embedded in build() and Director");
        System.out.println("PROTOTYPE: Deep-copy cloning for variants\n");
        System.out.println("All 4 patterns working together in unified RPG enemy system.");
        System.out.println("\n=== Complete ===");
    }
}
