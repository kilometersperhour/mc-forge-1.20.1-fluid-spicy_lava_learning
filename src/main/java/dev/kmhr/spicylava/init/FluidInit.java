package dev.kmhr.spicylava.init;

import dev.kmhr.spicylava.SpicyLava;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.*;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
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
                .bucket(ItemInit.LAVA_SPICY_BUCKET)
                // Adapted from
                // https://github.com/CoFH/ThermalFoundation-1.12-Legacy/blob/1.12/src/main/java/cofh/thermalfoundation/fluid/BlockFluidPyrotheum.java
                .explosionResistance(1000F) // See L41 at link
                .tickRate(10); // See L39 at link
    }
    public static final RegistryObject<FlowingFluid> LAVA_SPICY_SOURCE = FLUIDS.register(
            "lava_spicy",
            () -> new ForgeFlowingFluid.Source(fluidProperties()));
    public static final RegistryObject<Fluid> LAVA_SPICY_FLOWING = FLUIDS.register(
            "lava_spicy_flowing",
            () -> new ForgeFlowingFluid.Flowing(fluidProperties()));
    public static final RegistryObject<FluidType> LAVA_SPICY_TYPE = FLUID_TYPES.register(
            "lava_spicy",
            () -> new FluidType(FluidType.Properties.create()
                    .canSwim(false)
                    .canDrown(false) // personally don't agree... why don't you drown in lava either? oh well.
                    .canExtinguish(false)
                    .canHydrate(false)
                    .canPushEntity(true)
                    // translated from old Fluid API in https://github.com/CoFH/ThermalFoundation-1.12-Legacy/blob/4f088d82a8e6fbd767cd4bc7d0c3428888a6ff2f/src/main/java/cofh/thermalfoundation/init/TFFluids.java#L75
                    .lightLevel(15)
                    .density(2000)
                    .viscosity(1200)
                    .temperature(4000)
                    .rarity(Rarity.RARE)
                    // from Lava definition in ForgeMod.java
                    .pathType(BlockPathTypes.LAVA) // ?
                    .adjacentPathType(null)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
            ) {

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

                @Override
                public double motionScale(Entity entity)
                {
                    return entity.level().dimensionType().ultraWarm() ? 0.007D : 0.0023333333333333335D;
                }

                @Override
                public void setItemMovement(ItemEntity entity)
                {
                    Vec3 vec3 = entity.getDeltaMovement();
                    entity.setDeltaMovement(vec3.x * (double)0.95F, vec3.y + (double)(vec3.y < (double)0.06F ? 5.0E-4F : 0.0F), vec3.z * (double)0.95F);
                }
                    }



                    );




}