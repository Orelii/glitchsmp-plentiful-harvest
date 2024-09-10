package me.glasscrab.plentiful_harvest.Listeners.BlockHandling;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

import me.glasscrab.plentiful_harvest.Manager;

public class PushSolidCropBlockEvent implements Listener {
    private final Manager manager;
    
    public PushSolidCropBlockEvent(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void pushSolidCrop(BlockPistonExtendEvent e){
        Block block = e.getBlock();
        switch (e.getDirection()){
            case DOWN -> block = e.getBlock().getLocation().add(0, -1, 0).getBlock();
            case UP -> block = e.getBlock().getLocation().add(0, 1, 0).getBlock();
            case EAST -> { block = e.getBlock().getLocation().add(1, 0, 0).getBlock(); }
            case WEST -> { block = e.getBlock().getLocation().add(-1, 0, 0).getBlock(); }
            case NORTH -> { block = e.getBlock().getLocation().add(0, 0, -1).getBlock(); }
            case SOUTH -> { block = e.getBlock().getLocation().add(0, 0, 1).getBlock(); }
        }
        if (!manager.isSolidCropBlock(block.getType())) return;
        if(!e.getBlock().getWorld().getNearbyEntities(block.getLocation(), 0.1, 0.1, 0.1).isEmpty()){
            for (Entity entity : e.getBlock().getWorld().getNearbyEntities(block.getLocation(), 0.1, 0.1, 0.1)){
                if (!entity.hasMetadata("playerplaced")) return;
                entity.remove();
            }
        }
    }
}
