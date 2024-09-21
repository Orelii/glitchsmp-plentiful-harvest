package me.glasscrab.plentiful_harvest.Listeners.Brewing;

import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

/*
 * This is not my code:
 * https://www.spigotmc.org/threads/updated-creating-custom-brewing-recipes.472074/
 */

public abstract class BrewAction {
    public abstract void brew(BrewerInventory inventory, ItemStack item, ItemStack ingredient);
}