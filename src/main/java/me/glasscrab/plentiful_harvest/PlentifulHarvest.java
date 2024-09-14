package me.glasscrab.plentiful_harvest;

import me.glasscrab.plentiful_harvest.Commands.GiveCropCommand;
import me.glasscrab.plentiful_harvest.Listeners.*;
import me.glasscrab.plentiful_harvest.Listeners.Brewing.WarpedNetherWartBrewEvent;
import me.glasscrab.plentiful_harvest.Listeners.BlockHandling.*;
import me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents.DisableNonConsumableCropsEvent;
import me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents.OnMysticBeetrootConsumeEvent;
import me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents.OnWarpedPotionConsumeEvent;
import me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents.OnWholeBreadConsumeEvent;
import me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents.OnXocolatlConsumeEvent;
import me.glasscrab.plentiful_harvest.Recipes.CompactCropRecipes;
import me.glasscrab.plentiful_harvest.Recipes.CustomRecipes;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlentifulHarvest extends JavaPlugin {

    public static PlentifulHarvest INSTANCE;
    public BukkitAudiences audiences;

    @Override
    public void onEnable() {
        Manager manager = new Manager();
        INSTANCE = this;
        this.audiences = BukkitAudiences.create(this);
        
        this.getServer().getPluginManager().registerEvents(new CropBreakEvent(manager), this);
        this.getServer().getPluginManager().registerEvents(new FarmlandTrampleEvent(), this);
        this.getServer().getPluginManager().registerEvents(new AnimalFeedEvent(), this);
        this.getServer().getPluginManager().registerEvents(new WarpedNetherWartBrewEvent(), this);
        this.getServer().getPluginManager().registerEvents(new OnWarpedPotionConsumeEvent(), this);
        this.getServer().getPluginManager().registerEvents(new OnWholeBreadConsumeEvent(), this);
        this.getServer().getPluginManager().registerEvents(new OnMysticBeetrootConsumeEvent(), this);
        this.getServer().getPluginManager().registerEvents(new OnShiftRightClickPotatoEvent(), this);
        this.getServer().getPluginManager().registerEvents(new DisableNonConsumableCropsEvent(), this);
        this.getServer().getPluginManager().registerEvents(new OnToggleHoeEvent(), this);
        this.getServer().getPluginManager().registerEvents(new DisableNonPlantableCropsEvent(manager), this);
        this.getServer().getPluginManager().registerEvents(new EditHoeOnJoinEvent(manager), this);
        this.getServer().getPluginManager().registerEvents(new PlaceRegularCrops(manager, INSTANCE), this);
        this.getServer().getPluginManager().registerEvents(new RemoveMarkerFromSolidCropsEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PushSolidCropBlockEvent(manager), this);
        this.getServer().getPluginManager().registerEvents(new RemoveMarkerOnExplosion(manager), this);
        this.getServer().getPluginManager().registerEvents(new XocolatlBreakEvent(manager), this);
        this.getServer().getPluginManager().registerEvents(new OnXocolatlConsumeEvent(), this);
        this.getCommand("givecrop").setExecutor(new GiveCropCommand());

        CustomRecipes cr = new CustomRecipes(this);
        Bukkit.addRecipe(cr.farmersBootsShapedRecipe());
        Bukkit.addRecipe(cr.farmersHoeShapedRecipe());
        Bukkit.addRecipe(cr.wholeFlourStoneCuttingRecipe());
        Bukkit.addRecipe(cr.wholeBreadSmokingRecipe());
        Bukkit.addRecipe(cr.xocolatlRecipe());
        CompactCropRecipes ccr = new CompactCropRecipes(this);
        Bukkit.addRecipe(ccr.compactWheatSeedsShapedRecipe());
        Bukkit.addRecipe(ccr.uncompactWheatSeedsShapelessRecipe());
        Bukkit.addRecipe(ccr.compactCarrotShapedRecipe());
        Bukkit.addRecipe(ccr.uncompactCarrotShapelessRecipe());
        Bukkit.addRecipe(ccr.compactPotatoShapedRecipe());
        Bukkit.addRecipe(ccr.uncompactPotatoShapelessRecipe());
        Bukkit.addRecipe(ccr.compactBeetrootSeedsShapedRecipe());
        Bukkit.addRecipe(ccr.uncompactBeetrootSeedsShapelessRecipe());
        Bukkit.addRecipe(ccr.compactBeetrootShapedRecipe());
        Bukkit.addRecipe(ccr.uncompactBeetrootShapelessRecipe());
        Bukkit.addRecipe(ccr.compactNetherWartShapedRecipe());
        Bukkit.addRecipe(ccr.uncompactNetherWartShapelessRecipe());

        this.getLogger().info("Farming plugin has enabled!");

    }

    @Override
    public void onDisable() {
        this.getLogger().info("Farming plugin has disabled!");
        if (this.audiences != null){
            this.audiences.close();
            this.audiences = null;
        }
    }
}
