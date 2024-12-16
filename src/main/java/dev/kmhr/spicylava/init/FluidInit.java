package dev.kmhr.spicylava.init;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.kmhr.spicylava.SpicyLava;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.*;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class FluidInit {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, SpicyLava.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SpicyLava.MODID);



    /* TODO this was mentioned at the following link but I don't know how to add the arguments it wants to return.
    // https://github.com/MinecraftForge/MinecraftForge/blob/0b9573a91f5dbc6b61bc3eaa6bdb178b8d8d09f4/src/test/java/net/minecraftforge/debug/fluid/FluidTypeTest.java#L76
    */
    public static ForgeFlowingFluid.Properties fluidProperties()
    {
        return new ForgeFlowingFluid.Properties(
                LAVA_SPICY_TYPE,
                LAVA_SPICY,
                LAVA_SPICY_FLOWING)
                .block(LAVA_SPICY_BLOCK)
                .bucket(LAVA_SPICY_BUCKET);
    }

    public static final RegistryObject<FluidType> LAVA_SPICY_TYPE = FLUID_TYPES.register(
            "lava_spicy",
            () ->
                    new FluidType(
                            FluidType.Properties.create()
                                    .canExtinguish(false)
                                    .canHydrate(false)
                                    .canPushEntity(true)
                                    .lightLevel(15)
                                    .temperature(1800)
                                    .viscosity(10)
                    ) {

                        @Override
                        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                            consumer.accept(new IClientFluidTypeExtensions() {
                                /*
                                private static final ResourceLocation STILL = new ResourceLocation(SpicyLava.MODID,"fluid/lava_spicy_still.png"), // new ResourceLocation("spicylava:fluid/lava_spicy_still"),

                                        FLOW = new ResourceLocation(SpicyLava.MODID,"fluid/lava_spicy_flow.png"),
                                        OVERLAY = new ResourceLocation("block/basalt_side.png"),
                                        VIEW_OVERLAY = new ResourceLocation("textures/block/basalt_side.png");
                                */
                                private static final ResourceLocation STILL = new ResourceLocation(SpicyLava.MODID,"textures/block/lava_spicy"),
                                        FLOW = new ResourceLocation(SpicyLava.MODID,"textures/block/lava_spicy"),
                                        OVERLAY = new ResourceLocation("block/basalt_side"),
                                        VIEW_OVERLAY = new ResourceLocation("textures/block/obsidian.png");

                                @Override
                                public ResourceLocation getStillTexture() {
                                    return STILL;
                                }

                                @Override
                                public ResourceLocation getFlowingTexture() {
                                    return FLOW;
                                }

                                @Override
                                public ResourceLocation getOverlayTexture() {
                                    return OVERLAY;
                                }

                                @Override
                                public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                                    return VIEW_OVERLAY;
                                }

                                @Override
                                public int getTintColor() {
                                    return 0x80FFFF00;
                                }

                                @Override
                                public @NotNull Vector3f modifyFogColor(Camera camera,
                                                                        float partialTick,
                                                                        ClientLevel level,
                                                                        int renderDistance,
                                                                        float darkenWorldAmount,
                                                                        Vector3f fluidFogColor) {
                                    int color = this.getTintColor();
                                    return new Vector3f(
                                            (color >> 16 & 0xFF) / 255F,
                                            (color >> 8 & 0xFF) / 255F,
                                            (color & 0xFF) / 255F
                                    );
                                }

                                @Override
                                public void modifyFogRender(Camera camera,
                                                            FogRenderer.FogMode mode,
                                                            float renderDistance,
                                                            float partialTick,
                                                            float nearDistance,
                                                            float farDistance,
                                                            FogShape shape) {
                                    nearDistance = -8F;
                                    farDistance = 24F;

                                    if (farDistance > renderDistance) {
                                        farDistance = renderDistance;
                                        shape = FogShape.CYLINDER;
                                    }

                                    RenderSystem.setShaderFogStart(nearDistance);
                                    RenderSystem.setShaderFogEnd(farDistance);
                                    RenderSystem.setShaderFogShape(shape);
                                }
                            });
                        }
                    });
    public static final RegistryObject<FlowingFluid> LAVA_SPICY = FLUIDS.register("lava_spicy", () ->
            new ForgeFlowingFluid.Source(fluidProperties()));
    public static final RegistryObject<Fluid> LAVA_SPICY_FLOWING = FLUIDS.register("lava_spicy_flowing", () ->
            new ForgeFlowingFluid.Flowing(fluidProperties()));
    public static final RegistryObject<LiquidBlock> LAVA_SPICY_BLOCK = BlockInit.BLOCKS.register("lava_spicy_block", () ->
            new LiquidBlock(LAVA_SPICY, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .replaceable()
                    .liquid()
                    .lightLevel(state -> 15)
                    .pushReaction(PushReaction.DESTROY)
            ));
    public static final RegistryObject<Item> LAVA_SPICY_BUCKET = ItemInit.ITEMS.register(
            "lava_spicy_bucket",
            () -> new BucketItem(
                    LAVA_SPICY,
                    new Item.Properties()
                            .craftRemainder(Items.BUCKET)
                            .stacksTo(1)
            )
    );

}