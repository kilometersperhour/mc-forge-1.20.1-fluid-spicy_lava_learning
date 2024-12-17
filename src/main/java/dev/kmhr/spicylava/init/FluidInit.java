package dev.kmhr.spicylava.init;

import dev.kmhr.spicylava.SpicyLava;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.*;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;


public class FluidInit {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, SpicyLava.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SpicyLava.MODID);

    // Thanks to the following resources
    // https://github.com/DaRealTurtyWurty/1.19TutorialMod/commit/f9a57c257f58d8bc482ce8772fb7cb6f2144e6e7
    // https://github.com/Tutorials-By-Kaupenjoe/Forge-Tutorial-1.19/tree/457e37bd15a69ee102e5b234a49724c04669b956/src/main/java/net/kaupenjoe/tutorialmod/fluid
    // https://github.com/MinecraftForge/MinecraftForge/blob/0b9573a91f5dbc6b61bc3eaa6bdb178b8d8d09f4/src/test/java/net/minecraftforge/debug/fluid/FluidTypeTest.java
    // https://github.com/Lothrazar/Cyclic/blob/409d1b2898fa6de3a74a50e14231499ff9126ddf/src/main/java/com/lothrazar/cyclic/fluid/FluidXpJuiceHolder.java
    // TODO: Really like this "Holder" style approach to fluid initialization from Cyclic/Lothrazar, may adapt toward that

    public static ForgeFlowingFluid.Properties fluidProperties()
    {
        return new ForgeFlowingFluid.Properties(
                LAVA_SPICY_TYPE,
                LAVA_SPICY_SOURCE,
                LAVA_SPICY_FLOWING)
                .block(BlockInit.LAVA_SPICY_BLOCK)
                .bucket(ItemInit.LAVA_SPICY_BUCKET);
    }
    public static final RegistryObject<FlowingFluid> LAVA_SPICY_SOURCE = FLUIDS.register("lava_spicy", () -> new ForgeFlowingFluid.Source(fluidProperties()));
    public static final RegistryObject<Fluid> LAVA_SPICY_FLOWING = FLUIDS.register("lava_spicy_flowing", () -> new ForgeFlowingFluid.Flowing(fluidProperties()));
    public static final RegistryObject<FluidType> LAVA_SPICY_TYPE = FLUID_TYPES.register("lava_spicy", () -> new FluidType(FluidType.Properties.create().canExtinguish(false).canHydrate(false).canPushEntity(true).lightLevel(15).temperature(1800).viscosity(10)) {

                        @Override
                        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                            consumer.accept(new IClientFluidTypeExtensions() {

                                private static final String ID = "lava_spicy";
                                private static final ResourceLocation FLOW = new ResourceLocation(SpicyLava.MODID + ":block/fluid/" + ID + "_flow");
                                private static final ResourceLocation STILL = new ResourceLocation(SpicyLava.MODID + ":block/fluid/" + ID + "_still");

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
                                    return null;
                                }
                            });
                        }
                    });




}