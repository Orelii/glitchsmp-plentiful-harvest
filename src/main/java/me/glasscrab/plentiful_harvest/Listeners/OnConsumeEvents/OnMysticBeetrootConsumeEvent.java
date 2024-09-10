package me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import me.glasscrab.plentiful_harvest.PlentifulHarvest;

public class OnMysticBeetrootConsumeEvent implements Listener {

    @EventHandler
    public void onMysticBeetrootConsume(PlayerItemConsumeEvent e){
        if(!e.getItem().getType().equals(Material.BEETROOT)) return;
        if(!e.getItem().hasItemMeta()) return;
        if(e.getItem().getItemMeta() == null) return;
        if(!e.getItem().getItemMeta().hasCustomModelData()) return;
        if(e.getItem().getItemMeta().getCustomModelData() != 1) return;

        for(ItemStack i : e.getPlayer().getInventory().getContents()){
            if(i == null) continue;
            if(!i.hasItemMeta()) continue;
            if(i.getItemMeta() instanceof Damageable){
                Damageable damageable = (Damageable)i.getItemMeta();
                damageable.setDamage(0);
                i.setItemMeta(damageable);
            }
        }
        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
        var miniMessage = MiniMessage.miniMessage();
        Component parsedText = miniMessage.deserialize("<light_purple>The beetroot's aura envelops your tools..</light_purple>");
        audience.sendActionBar(parsedText);
    }
}
