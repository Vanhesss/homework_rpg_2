package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.FlameBreath;
import com.narxoz.rpg.combat.FireShield;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;

/**
 * Director class that creates preset enemy configurations using the Builder.
 *
 * Instead of manually calling builder.setName().setHealth().etc(),
 * the Director provides high-level methods like createMinion(), createRaidBoss().
 *
 * This demonstrates the relationship between Builder and Factory Method:
 * - The Builder constructs the object step-by-step
 * - The Director uses a specific Builder polymorphically
 * - The build() method IS a Factory Method (creates different enemy types)
 */
public class EnemyDirector {

    private EnemyBuilder builder;

    public EnemyDirector(EnemyBuilder builder) {
        this.builder = builder;
    }

    /**
     * Create a weak minion enemy.
     */
    public Enemy createMinion(EnemyComponentFactory factory) {
        return builder
            .setName("Minion")
            .setHealth(50)
            .setDamage(5)
            .setDefense(2)
            .setSpeed(20)
            .setAbilities(factory.createAbilities())
            .setLootTable(factory.createLootTable())
            .setAI(factory.createAIBehavior())
            .build();
    }

    /**
     * Create a medium-difficulty elite enemy.
     */
    public Enemy createElite(EnemyComponentFactory factory) {
        return builder
            .setName("Elite Enemy")
            .setHealth(200)
            .setDamage(20)
            .setDefense(8)
            .setSpeed(25)
            .setAbilities(factory.createAbilities())
            .setLootTable(factory.createLootTable())
            .setAI(factory.createAIBehavior())
            .build();
    }

    /**
     * Create a challenging mini-boss.
     */
    public Enemy createMiniBoss(EnemyComponentFactory factory) {
        if (!(builder instanceof BossEnemyBuilder)) {
            throw new IllegalStateException("MiniBoss requires BossEnemyBuilder!");
        }

        BossEnemyBuilder bossBuilder = (BossEnemyBuilder) builder;
        bossBuilder
            .setName("Mini Boss")
            .setHealth(1000)
            .setDamage(50)
            .setDefense(15)
            .setSpeed(30)
            .addPhase(1, 1000)
            .addPhase(2, 500)
            .setAbilities(factory.createAbilities())
            .setLootTable(factory.createLootTable())
            .setAI(factory.createAIBehavior());
        
        return bossBuilder.build();
    }

    /**
     * Create the ultimate raid boss.
     */
    public Enemy createRaidBoss(EnemyComponentFactory factory) {
        if (!(builder instanceof BossEnemyBuilder)) {
            throw new IllegalStateException("RaidBoss requires BossEnemyBuilder!");
        }

        BossEnemyBuilder bossBuilder = (BossEnemyBuilder) builder;
        return bossBuilder
            .setName("Ancient Dragon")
            .setHealth(10000)
            .setDamage(200)
            .setDefense(50)
            .setSpeed(40)
            .addPhase(1, 10000)
            .addPhase(2, 5000)
            .addPhase(3, 2500)
            .setAbilities(factory.createAbilities())
            .setLootTable(factory.createLootTable())
            .setAI(factory.createAIBehavior())
            .build();
    }
}
