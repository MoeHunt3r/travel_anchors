package de.castcrafter.travel_anchors.setup;

import de.castcrafter.travel_anchors.TravelAnchors;
import de.castcrafter.travel_anchors.base.ContainerBase;
import de.castcrafter.travel_anchors.block.BlockTravelAnchor;
import de.castcrafter.travel_anchors.block.ContainerTravelAnchor;
import de.castcrafter.travel_anchors.block.TileTravelAnchor;
import de.castcrafter.travel_anchors.enchantments.RangeEnchantment;
import de.castcrafter.travel_anchors.enchantments.TeleportationEnchantment;
import de.castcrafter.travel_anchors.item.ItemTravelStaff;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static de.castcrafter.travel_anchors.TravelAnchors.MODID;

public class Registration {

    public static final EnchantmentType TELEPORTABLE = EnchantmentType.create(TravelAnchors.MODID + "_teleportable",
            item -> item == Registration.TRAVEL_STAFF.get() || EnchantmentType.BREAKABLE.canEnchantItem(item));

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<BlockTravelAnchor> TRAVEL_ANCHOR_BLOCK = BLOCKS.register("travel_anchor", () -> new BlockTravelAnchor(AbstractBlock.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f)));

    public static final RegistryObject<Item> TRAVEL_ANCHOR_ITEM = ITEMS.register("travel_anchor", () -> new BlockItem(TRAVEL_ANCHOR_BLOCK.get(), new Item.Properties().group(TravelAnchors.ITEM_GROUP)));
    public static final RegistryObject<Item> TRAVEL_STAFF = ITEMS.register("travel_staff", () -> new ItemTravelStaff(new Item.Properties().group(TravelAnchors.ITEM_GROUP).maxStackSize(1)));

    public static final RegistryObject<TileEntityType<TileTravelAnchor>> TRAVEL_ANCHOR_TILE = TILES.register("travel_anchor", () -> TileEntityType.Builder.create(TileTravelAnchor::new, TRAVEL_ANCHOR_BLOCK.get()).build(null));

    public static final RegistryObject<ContainerType<ContainerTravelAnchor>> TRAVEL_ANCHOR_CONTAINER = CONTAINERS.register("travel_anchor", () -> ContainerBase.createContainerType(ContainerTravelAnchor::new));

    public static final RegistryObject<Enchantment> RANGE_ENCHANTMENT = ENCHANTMENTS.register("range", () -> new RangeEnchantment(Enchantment.Rarity.RARE, TELEPORTABLE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND}));
    public static final RegistryObject<Enchantment> TELEPORTATION_ENCHANTMENT = ENCHANTMENTS.register("teleportation", () -> new TeleportationEnchantment(Enchantment.Rarity.RARE, EnchantmentType.BREAKABLE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND}));
}

