package me.glasscrab.plentiful_harvest.Listeners;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import me.glasscrab.plentiful_harvest.PlentifulHarvest;

public class OnShiftRightClickPotatoEvent implements Listener {

    @EventHandler
    public void onShiftRightClickPotato(PlayerInteractEvent e){
        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.PHYSICAL)) return;
        if(e.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        if(!e.getPlayer().isSneaking()) return;
        if(e.getItem() == null) return;
        if(!e.getItem().getType().equals(Material.POTATO)) return;
        if(!e.getItem().hasItemMeta()) return;
        if(e.getItem().getItemMeta() == null) return;
        if(!e.getItem().getItemMeta().hasCustomModelData()) return;
        if(e.getItem().getItemMeta().getCustomModelData() != 1) return;

        e.getPlayer().setHealth(e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        e.getPlayer().getWorld().spawnParticle(Particle.HAPPY_VILLAGER, e.getPlayer().getEyeLocation(),15,.3,.3,.3);
        e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.MASTER, 1,1);
        var miniMessage = MiniMessage.miniMessage();
        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
        Component parsedText = miniMessage.deserialize("<green>You have been healed!</green>");
        audience.sendActionBar(parsedText);
        e.getItem().setAmount(e.getItem().getAmount()-1);
    }
}
