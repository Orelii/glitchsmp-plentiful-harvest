package me.glasscrab.plentiful_harvest.Listeners.BlockHandling;

import me.glasscrab.plentiful_harvest.Manager;
import me.glasscrab.plentiful_harvest.PlentifulHarvest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Entity;

public class PlaceRegularCrops implements Listener {
    private final Manager manager;
    private final PlentifulHarvest mainClass;

    public PlaceRegularCrops(Manager manager, PlentifulHarvest mainClass){
        this.manager = manager;
        this.mainClass = mainClass;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (manager.isSolidCropBlock(e.getBlockPlaced().getType())){
            Entity blockmarker = e.getPlayer().getWorld().spawnEntity(e.getBlockPlaced().getLocation(), EntityType.MARKER);
            blockmarker.setMetadata("playerplaced", new FixedMetadataValue(mainClass, true));
        }
    }
}
