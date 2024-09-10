package me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import me.glasscrab.plentiful_harvest.PlentifulHarvest;

public class OnWholeBreadConsumeEvent implements Listener {


    @EventHandler
    public void onWholeBreadConsume(PlayerItemConsumeEvent e){
        if(!e.getItem().getType().equals(Material.BREAD)) return;
        if(!e.getItem().hasItemMeta()) return;
        if(e.getItem().getItemMeta() == null) return;
        if(!e.getItem().getItemMeta().hasCustomModelData()) return;
        if(e.getItem().getItemMeta().getCustomModelData() != 1) return;

        e.getPlayer().setFoodLevel(20);
        e.getPlayer().setSaturation(20);
        
        var miniMessage = MiniMessage.miniMessage();
        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
        Component parsedText = miniMessage.deserialize("<yellow>You feel nourished.</yellow>");
        audience.sendActionBar(parsedText);
    }
}
