package dev.kmhr.spicylava;

import dev.kmhr.spicylava.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SpicyLava.MODID)
public class SpicyLava {
    public static final String MODID = "spicylava";

    public SpicyLava() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);

    }

}