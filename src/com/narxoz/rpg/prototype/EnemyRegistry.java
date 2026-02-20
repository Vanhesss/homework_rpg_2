package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Registry for storing and cloning enemy templates.
 *
 * Prototype Pattern: Store templates, clone them to create variants.
 *
 * Key principle: The registry MUST return clones, never the original template!
 * This prevents accidental modification of templates.
 *
 * Usage:
 *   EnemyRegistry registry = new EnemyRegistry();
 *   registry.registerTemplate("goblin", goblinTemplate);
 *
 *   // Create an elite goblin from template
 *   Enemy eliteGoblin = registry.createFromTemplate("goblin");
 *   eliteGoblin.multiplyStats(2.0);  // Clone is modified, template is safe!
 */
public class EnemyRegistry {

    private Map<String, Enemy> templates = new HashMap<>();

    /**
     * Register a template enemy.
     *
     * @param key Unique identifier (e.g., "goblin", "fire_dragon")
     * @param template The template enemy to store
     */
    public void registerTemplate(String key, Enemy template) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Template key cannot be null or empty!");
        }
        if (template == null) {
            throw new IllegalArgumentException("Template cannot be null!");
        }
        templates.put(key, template);
    }

    /**
     * Create a clone of a registered template.
     *
     * CRITICAL: Returns a CLONE, not the original!
     *
     * @param key The template key
     * @return A cloned copy of the template
     * @throws IllegalArgumentException if template not found
     */
    public Enemy createFromTemplate(String key) {
        if (!templates.containsKey(key)) {
            throw new IllegalArgumentException("Template '" + key + "' not found!");
        }
        Enemy original = templates.get(key);
        return original.clone();  // CLONE! Not the original!
    }

    /**
     * List all registered template keys.
     *
     * @return Set of template keys
     */
    public Set<String> listTemplates() {
        return templates.keySet();
    }

    /**
     * Check if a template exists.
     */
    public boolean hasTemplate(String key) {
        return templates.containsKey(key);
    }

    /**
     * Get the number of registered templates.
     */
    public int templateCount() {
        return templates.size();
    }
}
