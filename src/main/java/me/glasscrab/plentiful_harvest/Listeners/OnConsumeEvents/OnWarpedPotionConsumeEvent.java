package me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.glasscrab.plentiful_harvest.PlentifulHarvest;

public class OnWarpedPotionConsumeEvent implements Listener {

    @EventHandler
    public void onPotionConsume(PlayerItemConsumeEvent e){
        if(!e.getItem().getType().equals(Material.POTION)) return;
        if(!e.getItem().hasItemMeta()) return;
        if(e.getItem().getItemMeta() == null) return;
        if(!e.getItem().getItemMeta().hasCustomModelData()) return;
        if(e.getItem().getItemMeta().getCustomModelData() != 1) return;

        if(!e.getPlayer().getWorld().isPiglinSafe()){
            e.setCancelled(true);
            var miniMessage = MiniMessage.miniMessage();
            Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
            Component parsedText = miniMessage.deserialize("<red>You can only drink this in the Nether!</red>");
            audience.sendActionBar(parsedText);
        }
    }

}
