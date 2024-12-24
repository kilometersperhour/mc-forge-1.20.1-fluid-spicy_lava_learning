package dev.kmhr.spicylava;

import dev.kmhr.spicylava.init.BlockInit;
import dev.kmhr.spicylava.init.CreativeTabInit;
import dev.kmhr.spicylava.init.FluidInit;
import dev.kmhr.spicylava.init.ItemInit;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(SpicyLava.MODID)
public class SpicyLava {
    public static final String MODID = "spicylava";

    public SpicyLava(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);


        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        FluidInit.FLUID_TYPES.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        CreativeTabInit.TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Add Interactions for source

        // Original list of blocks to convert:
        // https://github.com/CoFH/ThermalFoundation-1.12-Legacy/blob/4f088d82a8e6fbd767cd4bc7d0c3428888a6ff2f/src/main/java/cofh/thermalfoundation/fluid/BlockFluidPyrotheum.java#L171
        // FLUID turns THIS block into THAT block
        FluidInteractionRegistry.addInteraction(FluidInit.LAVA_SPICY_TYPE.get(),
                new FluidInteractionRegistry
                        .InteractionInformation(
                        ForgeMod.WATER_TYPE.get(),
                        Blocks.STONE.defaultBlockState()
                )
        );
        // See sources for FluidInteractionRegistry for the entry that creates Blocks.BASALT for more
        // todo use immersive weather's mixin for block spawner to add proper type of generation
        FluidInteractionRegistry.addInteraction(FluidInit.LAVA_SPICY_TYPE.get(),
            new FluidInteractionRegistry
                    .InteractionInformation(
                    (level, currentPos, relativePos, currentState) -> level.getBlockState(relativePos).is(Blocks.SAND),
                    Blocks.GLASS.defaultBlockState()
            )
    );
    }
}