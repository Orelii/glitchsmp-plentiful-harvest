package me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class DisableNonConsumableCropsEvent implements Listener {

    @EventHandler
    public void disableNonConsumableCrops(PlayerItemConsumeEvent e){
        if(!e.getItem().hasItemMeta()) return;
        if(e.getItem().getItemMeta() == null) return;
        if(!e.getItem().getItemMeta().hasCustomModelData()) return;
        if(e.getItem().getItemMeta().getCustomModelData() == 2){
            e.setCancelled(true);
        }

        if(e.getItem().getType().equals(Material.POTATO) ||
           e.getItem().getType().equals(Material.CARROT) ||
           e.getItem().getType().equals(Material.JACK_O_LANTERN)){
            if(e.getItem().getItemMeta().getCustomModelData() != 1) return;
            e.setCancelled(true);
        }
    }
}
