package dev.kmhr.spicylava.init;

import dev.kmhr.spicylava.SpicyLava;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpicyLava.MODID);

    // TODO spicy lava fluid for toasting (registryobject blockitem?)
    public static final RegistryObject<Item> LAVA_SPICY_BUCKET = ItemInit.ITEMS.register(
            "lava_spicy_bucket",
            () -> new BucketItem(
                    FluidInit.LAVA_SPICY_SOURCE,
                    new Item.Properties()
                            .craftRemainder(Items.BUCKET)
                            .stacksTo(1)
            )
    );

}
