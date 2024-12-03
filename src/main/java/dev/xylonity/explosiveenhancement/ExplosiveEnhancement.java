package dev.xylonity.explosiveenhancement;

import com.mojang.logging.LogUtils;
import dev.xylonity.explosiveenhancement.config.ExplosiveEnhancementConfig;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

@Mod(ExplosiveEnhancement.MOD_ID)
public class ExplosiveEnhancement {

    public static final String MOD_ID = "explosiveenhancement";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExplosiveEnhancement() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ExplosiveEnhancementConfig.SPEC, "explosiveenhancement.toml");
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ExplosiveParticles.PARTICLES.register(modBus);

        if (FMLLoader.getDist() == Dist.CLIENT) {
            modBus.addListener(ExplosiveParticles::registerProviders);
        }
    }

    // This is need to avoid a player joining a dedicated server if any side is not using the mod
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventSubscriber {

        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(ModNetwork::register);
        }

    }

    public static class ModNetwork {
        private static final String PROTOCOL_VERSION = "1";
        public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(ExplosiveEnhancement.MOD_ID, "main_channel"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        public static void register() { ;; }
    }

}
