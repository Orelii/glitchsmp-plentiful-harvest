package me.glasscrab.plentiful_harvest.Listeners.Brewing;

import org.bukkit.Material;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.glasscrab.plentiful_harvest.PlentifulHarvest;

/*
 * This is not my code:
 * https://www.spigotmc.org/threads/updated-creating-custom-brewing-recipes.472074/
 */

public class BrewingRecipe {

    private final ItemStack ingredient;
    private final ItemStack fuel;

    public int fuelSet;
    public int fuelCharge;

    private BrewAction action;
    private BrewClock clock;

    private boolean perfect;

    public BrewingRecipe(ItemStack ingredient, ItemStack fuel, BrewAction action, boolean perfect, int fuelSet,
        int fuelCharge) {
        this.ingredient = ingredient;
        this.fuel = (fuel == null ? new ItemStack(Material.AIR) : fuel);
        this.setFuelSet(fuelSet);
        this.setFuelCharge(fuelCharge);
        this.action = action;
        this.perfect = perfect;
    }

    public BrewingRecipe(Material ingredient, BrewAction action) {
        this(new ItemStack(ingredient), null, action, true, 40, 0);
    }

    public ItemStack getIngredient() {
        return ingredient;
    }

    public ItemStack getFuel() {
        return fuel;
    }

    public BrewAction getAction() {
        return action;
    }

    public void setAction(BrewAction action) {
        this.action = action;
    }

    public BrewClock getClock() {
        return clock;
    }

    public void setClock(BrewClock clock) {
        this.clock = clock;
    }

    public boolean isPerfect() {
        return perfect;
    }

    public void setPerfect(boolean perfect) {
        this.perfect = perfect;
    }

    public static BrewingRecipe getRecipe(BrewerInventory inventory) {
        for (BrewingRecipe recipe: PlentifulHarvest.INSTANCE.recipes) {
            if (inventory.getFuel() == null) {
                if (!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngredient().getType()) {
                    return recipe;
                }
                if (recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngredient())) {
                    return recipe;
                }
            } else {
                if (!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngredient().getType() &&
                    inventory.getFuel().getType() == recipe.getIngredient().getType()) {
                    return recipe;
                }
                if (recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngredient()) &&
                    inventory.getFuel().isSimilar(recipe.getFuel())) {
                    return recipe;
                }
            }
        }
        return null;
    }

    public void startBrewing(BrewerInventory inventory) {
        clock = new BrewClock(this, inventory, 400);
    }

    public int getFuelSet() {
        return fuelSet;
    }

    public void setFuelSet(int fuelSet) {
        this.fuelSet = fuelSet;
    }

    public int getFuelCharge() {
        return fuelCharge;
    }

    public void setFuelCharge(int fuelCharge) {
        this.fuelCharge = fuelCharge;
    }

    public class BrewClock extends BukkitRunnable {

        public BrewClock(BrewingRecipe recipe, BrewerInventory inventory, int time) {}

        @Override
        public void run() {}

    }

}