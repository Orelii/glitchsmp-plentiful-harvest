package me.glasscrab.plentiful_harvest.Listeners.BlockHandling;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

import me.glasscrab.plentiful_harvest.Manager;

public class RemoveMarkerOnExplosion implements Listener {
    private Manager manager;

    public RemoveMarkerOnExplosion(Manager manager){
        this.manager = manager;
    }

    public void explode(BlockExplodeEvent e){
        if (manager.isSolidCropBlock(e.getExplodedBlockState().getType())){
            if(!e.getBlock().getWorld().getNearbyEntities(e.getBlock().getLocation(), 0.1, 0.1, 0.1).isEmpty()){
                for (Entity entity : e.getBlock().getWorld().getNearbyEntities(e.getBlock().getLocation(), 0.1, 0.1, 0.1)){
                    if (!entity.hasMetadata("playerplaced")) return;
                    entity.remove();
                }
            }
        }
    }
}
