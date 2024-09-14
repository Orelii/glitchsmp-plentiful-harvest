package me.glasscrab.plentiful_harvest.Listeners.OnConsumeEvents;

import java.util.Set;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

import me.glasscrab.plentiful_harvest.PlentifulHarvest;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class OnFairyMelonConsumeEvent implements Listener {

    private static final Set<PotionEffectType> negativeEffects = new HashSet<>();
    
    public OnFairyMelonConsumeEvent(){
        negativeEffects.add(PotionEffectType.BAD_OMEN);
        negativeEffects.add(PotionEffectType.BLINDNESS);
        negativeEffects.add(PotionEffectType.DARKNESS);
        negativeEffects.add(PotionEffectType.HUNGER);
        negativeEffects.add(PotionEffectType.INFESTED);
        negativeEffects.add(PotionEffectType.LEVITATION);
        negativeEffects.add(PotionEffectType.MINING_FATIGUE);
        negativeEffects.add(PotionEffectType.NAUSEA);
        negativeEffects.add(PotionEffectType.OOZING);
        negativeEffects.add(PotionEffectType.POISON);
        negativeEffects.add(PotionEffectType.RAID_OMEN);
        negativeEffects.add(PotionEffectType.SLOWNESS);
        negativeEffects.add(PotionEffectType.TRIAL_OMEN);
        negativeEffects.add(PotionEffectType.UNLUCK);
        negativeEffects.add(PotionEffectType.WEAKNESS);
        negativeEffects.add(PotionEffectType.WEAVING);
        negativeEffects.add(PotionEffectType.WITHER);
    }

    public void onFairyMelonConsume(PlayerItemConsumeEvent e){
        if (e.getItem().getType() != Material.GLISTERING_MELON_SLICE) return;
        if (e.getItem().getItemMeta().getCustomModelData() != 1) return;

        for (PotionEffectType potion : negativeEffects){
            e.getPlayer().removePotionEffect(potion);
        }
        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
        var miniMessage = MiniMessage.miniMessage();
        audience.sendActionBar(miniMessage.deserialize("<green>It feels like a weight has been lifted off your shoulders...</green>"));    
    }
}
