package me.glasscrab.plentiful_harvest;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Manager {
    private static Manager manager;
    private static final Set<Material> seedItems = new HashSet<>();
    private static final Set<Material> cropBlocks = new HashSet<>();
    private static final Set<Material> solidCropBlocks = new HashSet<>();
    private static final Map<Material, Double> cocoaBlockValues = new HashMap<Material, Double>();

    public Manager() {
        manager = this;

        seedItems.add(Material.WHEAT_SEEDS);
        seedItems.add(Material.BEETROOT_SEEDS);
        seedItems.add(Material.CARROT);
        seedItems.add(Material.NETHER_WART);
        seedItems.add(Material.POTATO);

        cropBlocks.add(Material.BEETROOTS);
        cropBlocks.add(Material.CARROTS);
        cropBlocks.add(Material.NETHER_WART);
        cropBlocks.add(Material.POTATOES);
        cropBlocks.add(Material.WHEAT);

        solidCropBlocks.add(Material.COCOA);
        solidCropBlocks.add(Material.MELON);
        solidCropBlocks.add(Material.PUMPKIN);

        cocoaBlockValues.put(Material.DIAMOND_ORE, 0.07);
        cocoaBlockValues.put(Material.EMERALD_ORE, 0.09);
        cocoaBlockValues.put(Material.IRON_ORE, 0.02);
        cocoaBlockValues.put(Material.GOLD_ORE, 0.03);
        cocoaBlockValues.put(Material.LAPIS_ORE, 0.0235);
        cocoaBlockValues.put(Material.COPPER_ORE, 0.02);
        cocoaBlockValues.put(Material.COAL_ORE, 0.01);
        cocoaBlockValues.put(Material.REDSTONE_ORE, 0.03);
        cocoaBlockValues.put(Material.DEEPSLATE_DIAMOND_ORE, 0.07);
        cocoaBlockValues.put(Material.DEEPSLATE_EMERALD_ORE, 0.09);
        cocoaBlockValues.put(Material.DEEPSLATE_IRON_ORE, 0.02);
        cocoaBlockValues.put(Material.DEEPSLATE_GOLD_ORE, 0.03);
        cocoaBlockValues.put(Material.DEEPSLATE_LAPIS_ORE, 0.0235);
        cocoaBlockValues.put(Material.DEEPSLATE_COPPER_ORE, 0.02);
        cocoaBlockValues.put(Material.DEEPSLATE_COAL_ORE, 0.01);
        cocoaBlockValues.put(Material.DEEPSLATE_REDSTONE_ORE, 0.03);
        cocoaBlockValues.put(Material.DIRT, 0.001);
        cocoaBlockValues.put(Material.SAND, 0.001);
        cocoaBlockValues.put(Material.GRAVEL, 0.001);
        cocoaBlockValues.put(Material.RED_SAND, 0.001);
        cocoaBlockValues.put(Material.MUD, 0.001);
        cocoaBlockValues.put(Material.MOSS_BLOCK, 0.001);
        cocoaBlockValues.put(Material.STONE, 0.003);
        cocoaBlockValues.put(Material.DEEPSLATE, 0.003);
        cocoaBlockValues.put(Material.NETHERRACK, 0.0005);
        cocoaBlockValues.put(Material.BLACKSTONE, 0.003);
        cocoaBlockValues.put(Material.BASALT, 0.003);
        cocoaBlockValues.put(Material.GRANITE, 0.003);
        cocoaBlockValues.put(Material.ANDESITE, 0.003);
        cocoaBlockValues.put(Material.DIORITE, 0.003);
        cocoaBlockValues.put(Material.TUFF, 0.003);
        cocoaBlockValues.put(Material.CALCITE, 0.003);
        cocoaBlockValues.put(Material.END_STONE, 0.004);
        cocoaBlockValues.put(Material.OAK_LOG, 0.005);
        cocoaBlockValues.put(Material.BIRCH_LOG, 0.005);
        cocoaBlockValues.put(Material.SPRUCE_LOG, 0.005);
        cocoaBlockValues.put(Material.JUNGLE_LOG, 0.005);
        cocoaBlockValues.put(Material.ACACIA_LOG, 0.005);
        cocoaBlockValues.put(Material.DARK_OAK_LOG, 0.005);
        cocoaBlockValues.put(Material.WARPED_STEM, 0.0055);
        cocoaBlockValues.put(Material.CRIMSON_STEM, 0.0055);
        cocoaBlockValues.put(Material.MANGROVE_LOG, 0.005);
        cocoaBlockValues.put(Material.CHERRY_LOG, 0.005);
        cocoaBlockValues.put(Material.WHEAT, 0.0065);
        cocoaBlockValues.put(Material.POTATOES, 0.0065);
        cocoaBlockValues.put(Material.BEETROOTS, 0.0065);
        cocoaBlockValues.put(Material.NETHER_WART, 0.0065);
        cocoaBlockValues.put(Material.CARROTS, 0.0065);
        cocoaBlockValues.put(Material.PUMPKIN, 0.0065);
        cocoaBlockValues.put(Material.COCOA, 0.0065);
        cocoaBlockValues.put(Material.MELON, 0.0065);
    }
    /*
     * Create a super crop item
     * @param name - name of the super crop
     * @param material - material of the super crop
     * @param lore - lore of the super crop
     * @param customModelData - custom model data of the super crop
     * @param amount - amount of the super crop
     * @returns ItemStack of the new super crop
     */
    public ItemStack makeSuperCrop(String name, Material material, List<String> lore, int customModelData, int amount) {
        ItemStack superCropItem = new ItemStack(material, amount);
        ItemMeta superCropItemMeta = superCropItem.getItemMeta();

        if(superCropItemMeta == null) {
            return null;
        }

        superCropItemMeta.setDisplayName(name);
        superCropItemMeta.setLore(lore);
        superCropItemMeta.setCustomModelData(customModelData);
        superCropItem.setItemMeta(superCropItemMeta);

        return superCropItem;
    }

    /*
     * Checks if the player's inventory is full
     * @param player - the player targeted
     * @returns boolean dictating if the inventory is full
     */
    public boolean isFull(Player player) {
        //Returns -1 if the inventory is full
        return player.getInventory().firstEmpty() == -1;
    }

    /*
     * Adds an item to the player's inventory
     * @param player - the player targeted
     * @param item - the item to be added
     * @returns the remaining item if the inventory is full
     */
    public ItemStack addInventory(Player player, ItemStack item) {
        Map<Integer, ItemStack> remaining = player.getInventory().addItem(item);
        return remaining.isEmpty() ? null : remaining.get(0);
    }

    /*
     * Sends an on-screen message to the player informing them that their inventory is full and they cannot collect supercrops
     * @param player - the player targeted
     * @param message - the message to be sent
     * @returns void
     */
    public void fullInventoryAlert(Player player){
        var miniMessage = MiniMessage.miniMessage();
        Audience audience = PlentifulHarvest.INSTANCE.audiences.player(player);
        Component parsedText = miniMessage.deserialize("<red>YOUR INVENTORY IS FULL! YOU CANNOT COLLECT SUPER CROPS!</red>");
        audience.sendActionBar(parsedText);
        player.playSound(player, Sound.BLOCK_BELL_USE, 0.6f, 1);
    }

    /*
     * Gives the player a super crop
     * @param player - the player targeted
     * @param superCrop - the super crop to be given
     * @returns void
     */
    public void giveSuperCrop(Player player, ItemStack superCrop) {
        ItemStack item = addInventory(player, superCrop);
        if (item == null) return;
        if (isFull(player)) {
            fullInventoryAlert(player);
            Item superCropItem = player.getWorld().dropItem(player.getLocation(), item);//Drops to world
            superCropItem.setGlowing(true);
        }
    }

    /*
     * Checks if the item is an old hoe
     * @param item - the item to be checked
     * @returns boolean dictating if the item is an old hoe
     */
    public boolean isOldHoe(ItemStack item){
        if(!item.getType().equals(Material.WOODEN_HOE)) return false;
        if(!item.hasItemMeta()) return false;
        if(item.getItemMeta() == null) return false;
        if(!item.getItemMeta().hasCustomModelData()) return false;
        return item.getItemMeta().getCustomModelData() < 106 && item.getItemMeta().getCustomModelData() > 0;
    }

    /*
     * Checks if the item is a crop seed
     * @param cropSeedType - the type of the crop seed
     * @returns boolean dictating if the item is a crop seed
     */
    public boolean isCropSeed(Material cropSeedType){
        return seedItems.contains(cropSeedType);
    }

    /*
     * Checks if the item is a crop block
     * @param cropBlockType - the type of the crop block
     * @returns boolean dictating if the item is a crop block
     */
    public boolean isCropBlock(Material cropBlockType){
        return cropBlocks.contains(cropBlockType);
    }

    /*
     * Checks if the item is a solid crop block
     * @param cropBlockType - the type of crop block
     * @returns boolean dictating if the item is a solid crop block
     */
    public boolean isSolidCropBlock(Material cropBlockType){
        return solidCropBlocks.contains(cropBlockType);
    }

    /*
     * Converts a crop block to an item
     * @param cropBlockType - the type of the crop block
     * @returns the item of the crop block
     */
    public Material cropBlockToItem(Material cropBlockType){
        return switch (cropBlockType) {
            case WHEAT -> Material.WHEAT;
            case CARROTS -> Material.CARROT;
            case POTATOES -> Material.POTATO;
            case BEETROOTS -> Material.BEETROOT;
            case NETHER_WART -> Material.NETHER_WART;
            case PUMPKIN -> Material.JACK_O_LANTERN;
            case MELON -> Material.GLISTERING_MELON_SLICE;
            case COCOA -> Material.BROWN_DYE;
            default -> Material.STONE;
        };
    }

    /*
     * Converts a crop block to a lore
     * @param cropBlockType - the type of the crop block
     * @returns the lore of the crop block
     */
    public String cropBlockLore(Material cropBlockType){
        return switch (cropBlockType) {
            case WHEAT -> "A whole lot better than the other kind.";
            case CARROTS -> "A single one could feed 100 horses.";
            case POTATOES -> "Opposite to the poisonous potato, and much rarer.";
            case BEETROOTS -> "A beetroot so old it's been infused with magic.";
            case NETHER_WART -> "Like a four leaf clover, found very rarely.";
            case PUMPKIN -> "Possessed by a vengeful spirit who couldn't find anything scarier to haunt.";
            case MELON -> "This rare fruit sparkles in a way that looks like little fairies.";
            case COCOA -> "A flavour so delicious it'll kick your mouth in the ass.";
            default -> "Dm me if u got this item";
        };
    }

    /*
     * Converts a crop block to a name
     * @param cropBlockType - the type of the crop block
     * @returns the name of the crop block
     */
    public String cropBlockName(Material cropBlockType){
        return switch (cropBlockType) {
            case WHEAT -> ChatColor.YELLOW+"Whole Wheat";
            case CARROTS -> ChatColor.GOLD+"Hyper Carrot";
            case POTATOES -> ChatColor.GREEN+"Medicinal Potato";
            case BEETROOTS -> ChatColor.LIGHT_PURPLE+"Mystic Beetroot";
            case NETHER_WART -> ChatColor.DARK_AQUA+"Warped Nether Wart";
            case PUMPKIN -> ChatColor.GOLD+"Haunted Pumpkin";
            case MELON -> ChatColor.GREEN+"Fairy Melon";
            case COCOA -> ChatColor.RED+"Sweet Cocoa";
            default -> ChatColor.GRAY+"Error Crop";
        };
    }

    /*
     * Converts a crop block to a message
     * @param cropBlockType - the type of the crop block
     * @returns the message of the crop block
     */
    public String cropBlockMessage(Material cropBlockType){
        return switch (cropBlockType) {
            case WHEAT -> ChatColor.YELLOW+"You harvested a bundle of Whole Wheat!";
            case CARROTS -> ChatColor.GOLD+"You uprooted a Hyper Carrot!";
            case POTATOES -> ChatColor.GREEN+"You dug up a Medicinal Potato!";
            case BEETROOTS -> ChatColor.LIGHT_PURPLE+"You felt the aura of a Mystic Beetroot!";
            case NETHER_WART -> ChatColor.DARK_AQUA+"You uncovered a Warped Nether Wart!";
            case PUMPKIN -> ChatColor.GOLD+"You were startled by a Haunted Pumpkin!";
            case MELON -> ChatColor.GREEN+"You discovered a Fairy Melon!";
            case COCOA -> ChatColor.RED+"You picked a bunch of Sweet Cocoa!";
            default -> ChatColor.GRAY+"Error Crop Message";
        };
    }

    /*
     * Checks if the item is a custom hoe
     * @param item - the item to be checked
     * @returns boolean dictating if the item is a custom hoe
     */
    public boolean isCustomHoe(ItemStack item) {
        return item.getType().equals(Material.WOODEN_HOE) &&
                item.getItemMeta() != null &&
                item.getItemMeta().hasCustomModelData() &&
                (item.getItemMeta().getCustomModelData() == 2767 ||
                item.getItemMeta().getCustomModelData() == 2768);
    }

    /*
     * Replants a crop
     * @param block - the block to be replanted
     * @param cropType - the type of the crop
     * @param age - the age of the crop
     * @param droppedItems - the items dropped from the crop
     * @returns void
     */
    public void replant(Block block, Material cropType, BlockData age, List<Item> droppedItems) {
        block.setType(cropType);
        block.setBlockData(age);

        for(Item droppedItem : droppedItems){
            droppedItem.remove();
        }
    }

    /*
     * Checks if the item is a custom hoe
     * Checks custom model data 2767
     * @param hoe - the hoe to be checked
     * @returns true if the hoe is a custom hoe
     */
    public boolean isCustomHoeOne(ItemStack hoe) {
        return hoe.getType().equals(Material.WOODEN_HOE) &&
                hoe.getItemMeta() != null &&
                hoe.getItemMeta().hasCustomModelData() &&
                hoe.getItemMeta().getCustomModelData() == 2767;
    }

    /*
     * Checks if the item is a custom hoe
     * Checks custom model data 2768
     * @param hoe - the hoe to be checked
     * @returns true if the hoe is a custom hoe
     */
    public boolean isCustomHoeTwo(ItemStack hoe) {
        return hoe.getType().equals(Material.WOODEN_HOE) &&
                hoe.getItemMeta() != null &&
                hoe.getItemMeta().hasCustomModelData() &&
                hoe.getItemMeta().getCustomModelData() == 2768;
    }

    /*
     * Replants a crop later
     * @param block - the block to be replanted
     * @param cropType - the type of the crop
     * @param age - the age of the crop
     * @returns void
     */
    public void replantLater(Block block, Material cropType, BlockData age) {
        new BukkitRunnable() {
            public void run() {
                block.setType(cropType);
                block.setBlockData(age);
            }
        }.runTaskLater(PlentifulHarvest.INSTANCE, 1);
    }

    /*
     * Checks if a given block has a monetary value associated with it
     * @param block - a block material
     * @returns true if the block has a value, false otherwise
     */
    public boolean isBlockWorthChocolate(Material block){
        return cocoaBlockValues.containsKey(block);
    }

    /*
     * Gets the monetary value of block
     * @param block - a block material
     * @returns the block's associated value if it has one, 0 otherwise
     */
    public double getBlockChocolateValue(Material block){
        if (cocoaBlockValues.containsKey(block)) return cocoaBlockValues.get(block);
        return 0;
    }

    /*
     * Gets the manager
     * @returns the manager
     */
    public static Manager getManager() {
        return manager;
    }
}
