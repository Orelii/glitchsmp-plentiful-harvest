package me.glasscrab.plentiful_harvest.Recipes;

import me.glasscrab.plentiful_harvest.Manager;
import me.glasscrab.plentiful_harvest.PlentifulHarvest;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class CustomRecipes {
    private final Plugin plugin;
    public CustomRecipes(PlentifulHarvest plugin){
        this.plugin = plugin;
    }

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

    public ShapedRecipe farmersBootsShapedRecipe(){
        ItemStack farmersBoots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta farmersBootsMeta = farmersBoots.getItemMeta();
        ((LeatherArmorMeta) farmersBootsMeta).setColor(Color.fromRGB(245, 113, 37));
        farmersBootsMeta.setDisplayName(ChatColor.GOLD+"Farmer's Boots");
        farmersBootsMeta.setLore(List.of(ChatColor.GRAY+"Wear these and you'll never have", ChatColor.GRAY+"to worry about trampling crops again!"));
        farmersBootsMeta.setCustomModelData(1);
        farmersBootsMeta.setUnbreakable(true);
        farmersBootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(NamespacedKey.minecraft("generic.armor"), 0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        farmersBootsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        farmersBootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        farmersBootsMeta.addItemFlags(ItemFlag.HIDE_DYE);

        farmersBoots.setItemMeta(farmersBootsMeta);

        NamespacedKey farmersBootsKey = new NamespacedKey(plugin, "farmers_boots");
        ShapedRecipe farmersBootsRecipe = new ShapedRecipe(farmersBootsKey, farmersBoots);

        farmersBootsRecipe.shape("OOO","ONO","OOO");
        farmersBootsRecipe.setIngredient('O', Material.ORANGE_WOOL);
        farmersBootsRecipe.setIngredient('N', Material.NETHERITE_BOOTS);
        return farmersBootsRecipe;
    }

    public ShapedRecipe farmersHoeShapedRecipe(){
        ItemStack farmersHoe = new ItemStack(Material.WOODEN_HOE);
        ItemMeta farmersHoeMeta = farmersHoe.getItemMeta();
        farmersHoeMeta.setDisplayName(ChatColor.GOLD+"Farmer's Hoe");
        farmersHoeMeta.setLore(List.of(ChatColor.GRAY+"New technology allows for automatic",ChatColor.GRAY+"replanting of crops."));
        farmersHoeMeta.setCustomModelData(2767);
        farmersHoeMeta.setUnbreakable(true);
        farmersHoeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        farmersHoeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        farmersHoe.setItemMeta(farmersHoeMeta);

        NamespacedKey farmersHoeKey = new NamespacedKey(plugin, "farmers_hoe");
        ShapedRecipe farmersHoeRecipe = new ShapedRecipe(farmersHoeKey, farmersHoe);

        farmersHoeRecipe.shape(" C ","CHC"," C ");
        farmersHoeRecipe.setIngredient('C', new RecipeChoice.ExactChoice(List.of(makeSuperCrop(ChatColor.YELLOW+"Whole Wheat",Material.WHEAT,List.of(ChatColor.GRAY + "A whole lot better than the other kind."),1,1),
                                                                                        makeSuperCrop(ChatColor.GOLD+"Hyper Carrot",Material.CARROT,List.of(ChatColor.GRAY + "A single one could feed 100 horses."),1,1),
                                                                                        makeSuperCrop(ChatColor.GREEN+"Medicinal Potato",Material.POTATO,List.of(ChatColor.GRAY + "Opposite to the poisonous potato, and much rarer."),1,1),
                                                                                        makeSuperCrop(ChatColor.LIGHT_PURPLE+"Mystic Beetroot",Material.BEETROOT,List.of(ChatColor.GRAY + "A beetroot so old it's been infused with magic."),1,1),
                                                                                        makeSuperCrop(ChatColor.DARK_AQUA+"Warped Nether Wart",Material.NETHER_WART,List.of(ChatColor.GRAY + "Like a four leaf clover, found very rarely."),1,1))));

        farmersHoeRecipe.setIngredient('H', Material.NETHERITE_HOE);
        return farmersHoeRecipe;
    }

    public StonecuttingRecipe wholeFlourStoneCuttingRecipe(){
        ItemStack wholeFlour = new ItemStack(Material.SUGAR);
        ItemMeta wholeFlourMeta = wholeFlour.getItemMeta();
        wholeFlourMeta.setDisplayName(ChatColor.WHITE+"Whole Flour");
        wholeFlourMeta.setLore(List.of(ChatColor.GRAY+"100% whole grain."));
        wholeFlourMeta.setCustomModelData(1);

        wholeFlour.setItemMeta(wholeFlourMeta);

        NamespacedKey wholeFlourKey = new NamespacedKey(plugin, "whole_flour");
        StonecuttingRecipe wholeFlourRecipe = new StonecuttingRecipe(wholeFlourKey,wholeFlour,new RecipeChoice.ExactChoice(makeSuperCrop(ChatColor.YELLOW+"Whole Wheat",Material.WHEAT,List.of(ChatColor.GRAY + "A whole lot better than the other kind."),1,1)));
        return wholeFlourRecipe;
    }

    public SmokingRecipe wholeBreadSmokingRecipe(){
        ItemStack wholeFlour = new ItemStack(Material.SUGAR);
        ItemMeta wholeFlourMeta = wholeFlour.getItemMeta();
        wholeFlourMeta.setDisplayName(ChatColor.WHITE+"Whole Flour");
        wholeFlourMeta.setLore(List.of(ChatColor.GRAY+"100% whole grain."));
        wholeFlourMeta.setCustomModelData(1);

        wholeFlour.setItemMeta(wholeFlourMeta);

        ItemStack wholeBread = new ItemStack(Material.BREAD);
        ItemMeta wholeBreadMeta = wholeBread.getItemMeta();
        wholeBreadMeta.setDisplayName(ChatColor.YELLOW+"Whole Bread");
        wholeBreadMeta.setLore(List.of(ChatColor.GRAY+"Refills your whole hunger bar."));
        wholeBreadMeta.setCustomModelData(1);

        wholeBread.setItemMeta(wholeBreadMeta);

        NamespacedKey wholeBreadKey = new NamespacedKey(plugin, "whole_bread");
        SmokingRecipe wholeBreadRecipe = new SmokingRecipe(wholeBreadKey, wholeBread, new RecipeChoice.ExactChoice(wholeFlour), 10f, 400);
        return wholeBreadRecipe;
    }

    public FurnaceRecipe xocolatlRecipe(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "A flavour so delicious it'll kick your mouth in the ass.");
        ItemStack sweetCocoa = Manager.getManager().makeSuperCrop(ChatColor.RED + "Sweet Cocoa", Material.BROWN_DYE, lore, 1, 1);
        

        ItemStack xocolatl = new ItemStack(Material.POTION);
        PotionMeta xocolatlMeta = (PotionMeta) xocolatl.getItemMeta();
        xocolatlMeta.setColor(Color.fromRGB(71,31,19));
        xocolatlMeta.setItemName("Xocolatl");
        xocolatlMeta.setLore(List.of(ChatColor.RESET+""+ChatColor.GRAY+"The food of the gods."));
        xocolatlMeta.setCustomModelData(2);
        xocolatlMeta.setMaxStackSize(4);
        xocolatlMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        xocolatl.setItemMeta(xocolatlMeta);

        NamespacedKey xocolatlKey = new NamespacedKey(plugin, "xocolatl");
        FurnaceRecipe xocolatlRecipe = new FurnaceRecipe(xocolatlKey, xocolatl, new RecipeChoice.ExactChoice(sweetCocoa), 10f, 500);
        return xocolatlRecipe;
    }
}
