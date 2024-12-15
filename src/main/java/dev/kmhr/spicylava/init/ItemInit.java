package dev.kmhr.spicylava.init;

import dev.kmhr.spicylava.SpicyLava;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.rmi.registry.Registry;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpicyLava.MODID);

    public static final RegistryObject<BlockItem> MARSHMALLOW_BLOCK_ITEM = CreativeTabInit.addToTab(ITEMS.register("marshmallow_block",
            () -> new BlockItem(BlockInit.MARSHMALLOW_BLOCK.get(),
                    new Item.Properties()
                            .rarity(Rarity.UNCOMMON)
            )));

    // TODO spicy lava fluid for toasting (registryobject blockitem?)



    // stick for toasting marshmallows over spicy lava
    public static final RegistryObject<Item> STICK_TOASTING = CreativeTabInit.addToTab(ITEMS.register(
            "stick_toasting",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.RARE)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(
                                    () -> new MobEffectInstance
                                            (MobEffects.ABSORPTION,
                                            200,
                                            1),
                                    1f)
                            .build())
            )
    ));



}
