package me.glasscrab.plentiful_harvest.Listeners.BlockHandling;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class RemoveMarkerFromSolidCropsEvent implements Listener {

    public RemoveMarkerFromSolidCropsEvent(){}

    @EventHandler
    public void onBlockRemove(BlockBreakEvent e){
        if(!e.getPlayer().getWorld().getNearbyEntities(e.getBlock().getLocation(), 0.1, 0.1, 0.1).isEmpty()){
            for (Entity entity : e.getPlayer().getWorld().getNearbyEntities(e.getBlock().getLocation(), 0.1, 0.1, 0.1)){
                if (!entity.hasMetadata("playerplaced")) return;
                entity.remove();
            }
        }
    }
}
