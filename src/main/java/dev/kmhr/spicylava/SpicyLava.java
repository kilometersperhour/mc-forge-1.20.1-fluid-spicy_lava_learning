package dev.kmhr.spicylava;

import dev.kmhr.spicylava.init.BlockInit;
import dev.kmhr.spicylava.init.CreativeTabInit;
import dev.kmhr.spicylava.init.FluidInit;
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
        BlockInit.BLOCKS.register(bus);
        FluidInit.FLUID_TYPES.register(bus);
        FluidInit.FLUIDS.register(bus);
        CreativeTabInit.TABS.register(bus);

    }

}