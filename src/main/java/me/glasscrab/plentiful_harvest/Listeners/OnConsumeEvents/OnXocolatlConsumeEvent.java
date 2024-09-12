package me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import me.glasscrab.plentiful_harvest.PlentifulHarvest;

public class OnXocolatlConsumeEvent implements Listener {

    @EventHandler
    public void onXocolatlConsume(PlayerItemConsumeEvent e){
        if(!e.getItem().getType().equals(Material.POTION)) return;
        if(!e.getItem().hasItemMeta()) return;
        if(e.getItem().getItemMeta() == null) return;
        if(!e.getItem().getItemMeta().hasCustomModelData()) return;
        if(e.getItem().getItemMeta().getCustomModelData() != 2) return;

        if (e.getPlayer().getMetadata("xocolatlBlocks").isEmpty()){
            e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, -1));
        }
        var metadata = e.getPlayer().getMetadata("xocolatlBlocks");
        MetadataValue blocksRemaining = metadata.get(0);        
        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
        var miniMessage = MiniMessage.miniMessage();    

        if ((Integer) blocksRemaining.value() != -1) {
            audience.sendActionBar(miniMessage.deserialize("<red>You are already under the effects of another xocolatl!<red>"));
            e.setCancelled(true);
        }
        else{
            e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, 400));
            audience.sendActionBar(miniMessage.deserialize("<green>You suddenly feel prosperous...!</green>"));
        }
    }

}
