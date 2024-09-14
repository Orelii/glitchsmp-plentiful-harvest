package me.glasscrab.plentiful_harvest.Listeners.BlockHandling;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.Set;
import java.util.HashSet;

import me.glasscrab.plentiful_harvest.Manager;
import me.glasscrab.plentiful_harvest.PlentifulHarvest;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class XocolatlBreakEvent implements Listener {
    private Manager manager;
    private static final Set<Material> usesDoubleDurability = new HashSet<>();
    private static final Set<Material> usesQuadrupleDurability = new HashSet<>();
    private static final Set<Material> usesOctupleDurability = new HashSet<>();

    public XocolatlBreakEvent(Manager manager){
        this.manager = manager;

        usesDoubleDurability.add(Material.GOLD_ORE);
        usesDoubleDurability.add(Material.DEEPSLATE_GOLD_ORE);
        usesDoubleDurability.add(Material.EMERALD_ORE);
        usesDoubleDurability.add(Material.DEEPSLATE_EMERALD_ORE);
        usesDoubleDurability.add(Material.IRON_ORE);
        usesDoubleDurability.add(Material.DEEPSLATE_IRON_ORE);

        usesQuadrupleDurability.add(Material.DIAMOND_ORE);
        usesQuadrupleDurability.add(Material.DEEPSLATE_DIAMOND_ORE);
        
        usesOctupleDurability.add(Material.ANCIENT_DEBRIS);
    }

    @EventHandler
    public void breakWithXocolatlEvent(BlockBreakEvent e){
        if (!manager.isBlockWorthChocolate(e.getBlock().getType())) return;
        if (!e.getPlayer().hasMetadata("xocolatlBlocks")) return;
        var metadata = e.getPlayer().getMetadata("xocolatlBlocks");
        MetadataValue blocksRemaining = metadata.get(0);

        if ((Integer) blocksRemaining.value() == -1.) return;
        
        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(e.getPlayer());
        var miniMessage = MiniMessage.miniMessage();
        double value = manager.getBlockChocolateValue(e.getBlock().getType());
        
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "economy give " + e.getPlayer().getName() + " " + value);
        audience.sendActionBar(miniMessage.deserialize("<green>You earned $" + value + " from mining the " + e.getBlock().getType() + "!</green>"));
        
        if (usesDoubleDurability.contains(e.getBlock().getType())){
            e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, (Integer) blocksRemaining.value() - 2));
        }
        else if (usesQuadrupleDurability.contains(e.getBlock().getType())){
            e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, (Integer) blocksRemaining.value() - 4));
        }
        else if (usesOctupleDurability.contains(e.getBlock().getType())){
            e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, (Integer) blocksRemaining.value() - 8));
        }
        else{
            e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, (Integer) blocksRemaining.value() - 1));
        }

        if ((Integer) blocksRemaining.value() <= 0){
            e.getPlayer().setMetadata("xocolatlBlocks", new FixedMetadataValue(PlentifulHarvest.INSTANCE, -1));
            audience.sendActionBar(miniMessage.deserialize("<red>The effects of your xocolatl have worn off!</red>"));
        }
            
    }
}
