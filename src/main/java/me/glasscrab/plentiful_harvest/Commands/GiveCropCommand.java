package me.glasscrab.plentiful_harvest.Commands;

import me.glasscrab.plentiful_harvest.Manager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GiveCropCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }

        if(!player.hasPermission("farming.givecrop")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if(args.length != 3) {
            player.sendMessage(ChatColor.RED + "Usage: /givecrop <crop> <player> <amount>");
            return true;
        }

        String crop = args[0];
        Player target = player.getServer().getPlayer(args[1]);
        int amount = Integer.parseInt(args[2]);

        if(target == null) {
            player.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        if(amount < 1) {
            player.sendMessage(ChatColor.RED + "Amount must be greater than 0.");
            return true;
        }

        ItemStack superCrop = null;

        switch (crop) {
            case "carrot" -> {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "A single one could feed 100 horses.");
                superCrop = Manager.getManager().makeSuperCrop(ChatColor.GOLD + "Hyper Carrot", Material.CARROT, lore, 1, amount);
            }

            case "beetroot" -> {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "A beetroot so old it's been infused with magic.");
                superCrop = Manager.getManager().makeSuperCrop(ChatColor.LIGHT_PURPLE + "Mystic Beetroot", Material.BEETROOT, lore, 1, amount);
            }

            case "potato" -> {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "Opposite to the poisonous potato, and much rarer.");
                superCrop = Manager.getManager().makeSuperCrop(ChatColor.GREEN + "Medicinal Potato", Material.POTATO, lore, 1, amount);
            }

            case "netherwart" -> {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "Like a four leaf clover, found very rarely.");
                superCrop = Manager.getManager().makeSuperCrop(ChatColor.DARK_AQUA + "Warped Nether Wart", Material.NETHER_WART, lore, 1, amount);
            }

            case "wheat" -> {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "A whole lot better than the other kind.");
                superCrop = Manager.getManager().makeSuperCrop(ChatColor.YELLOW + "Whole Wheat", Material.WHEAT, lore, 1, amount);
            }

            case "pumpkin" -> {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "Possessed by a vengeful spirit who couldn't find anything scarier to haunt.");
                superCrop = Manager.getManager().makeSuperCrop(ChatColor.GOLD + "Haunted Pumpkin", Material.JACK_O_LANTERN, lore, 1, amount);
            }

            case "melon" -> {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "This rare fruit sparkles in a way that looks like little fairies.");
                superCrop = Manager.getManager().makeSuperCrop(ChatColor.GREEN + "Fairy Melon", Material.GLISTERING_MELON_SLICE, lore, 1, amount);
            }

            case "cocoa" -> {
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "A flavour so delicious it'll kick your mouth in the ass.");
                superCrop = Manager.getManager().makeSuperCrop(ChatColor.RED + "Sweet Cocoa", Material.BROWN_DYE, lore, 1, amount);
            }
        }

        if(superCrop == null) {
            player.sendMessage(ChatColor.RED + "Invalid crop.");
            return true;
        }

        Manager.getManager().giveSuperCrop(target, superCrop);
        player.sendMessage(ChatColor.GREEN + "You gave " + target.getName() + " " + amount + " " + crop + " super crops.");

        return true;
    }
}
