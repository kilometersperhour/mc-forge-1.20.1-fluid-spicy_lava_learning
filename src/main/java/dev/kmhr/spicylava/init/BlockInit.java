package dev.kmhr.spicylava.init;

import dev.kmhr.spicylava.SpicyLava;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpicyLava.MODID);

    public static final RegistryObject<Block> MARSHMALLOW_BLOCK = BLOCKS.register("marshmallow_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK)
                    .ignitedByLava()
                    //.jumpFactor(2f)
                    .mapColor(MapColor.SNOW)
                    //.friction(0.1f)
                    .strength(5.0f)
                    //.lightLevel(state -> 7) // why not!
                    .explosionResistance(2.0f)
            )
    );
}
