package dev.kmhr.spicylava.init;

import dev.kmhr.spicylava.SpicyLava;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpicyLava.MODID);

    public static final RegistryObject<LiquidBlock> LAVA_SPICY_BLOCK = BlockInit.BLOCKS.register(
            "lava_spicy_block",
            () -> new LiquidBlock(FluidInit.LAVA_SPICY_SOURCE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .replaceable()
                    .liquid()
                    .lightLevel(state -> 15)
                    .pushReaction(PushReaction.DESTROY)
            ));
}
