package me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectTypeCategory;

import me.glasscrab.plentiful_harvest.PlentifulHarvest;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class OnFairyMelonConsumeEvent implements Listener {
    
    @EventHandler
    public void onFairyMelonConsume(PlayerItemConsumeEvent e){
        if (e.getItem().getType() != Material.GLISTERING_MELON_SLICE) return;
        if (!e.getItem().getItemMeta().hasFood()) return;

        for (PotionEffect potion : e.getPlayer().getActivePotionEffects()){
            if (potion.getType().getCategory() == PotionEffectTypeCategory.HARMFUL) e.getPlayer().removePotionEffect(potion.getType());
        }

        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
        var miniMessage = MiniMessage.miniMessage();
        e.getPlayer().playSound(e.getPlayer(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1, 2);
        audience.sendActionBar(miniMessage.deserialize("<green>It feels like a weight has been lifted off your shoulders...</green>"));    
    }
}
