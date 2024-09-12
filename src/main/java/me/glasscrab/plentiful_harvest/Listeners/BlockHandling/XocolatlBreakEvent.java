package me.glasscrab.plentiful_harvest.Listeners.BlockHandling;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

import java.math.BigDecimal;

import me.glasscrab.plentiful_harvest.Manager;
import me.glasscrab.plentiful_harvest.PlentifulHarvest;
import net.ess3.api.MaxMoneyException;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class XocolatlBreakEvent implements Listener {
    private Manager manager;
    
    public XocolatlBreakEvent(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void breakWithXocolatlEvent(BlockBreakEvent e){
        if (!manager.isBlockWorthChocolate(e.getBlock().getType())) return;
        if (!e.getPlayer().hasMetadata("xocolatlBlocks")) return;
        var metadata = e.getPlayer().getMetadata("xocolatlBlocks");
        MetadataValue blocksRemaining = metadata.get(0);

        if ((Integer) blocksRemaining.value() == -1) return;
        
        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
        var miniMessage = MiniMessage.miniMessage();
        double value = manager.getBlockChocolateValue(e.getBlock().getType());

        e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, (Integer) blocksRemaining.value() - 1));
        
        Essentials essentials = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
        User user = essentials.getUser(e.getPlayer());
        try {
            user.setMoney(user.getMoney().add(BigDecimal.valueOf(value)));
            audience.sendActionBar(miniMessage.deserialize("<green>You earned " + value + " from mining the " + e.getBlock().getType() + "!</green>"));
        } catch (MaxMoneyException e1) {
            audience.sendActionBar(miniMessage.deserialize("<red>You have too much money!</red>"));;
        }
        
        if ((Integer) blocksRemaining.value() - 1 <= 0){
            e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, -1));
            audience.sendActionBar(miniMessage.deserialize("<red>The effects of your xocolatl have worn off!</red>"));
        }
    }
}
