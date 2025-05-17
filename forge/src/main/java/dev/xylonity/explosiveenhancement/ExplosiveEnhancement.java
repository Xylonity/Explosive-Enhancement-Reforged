package dev.xylonity.explosiveenhancement;

import com.mojang.logging.LogUtils;
import dev.xylonity.explosiveenhancement.config.ExplosiveEnhancementConfig;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;
import org.slf4j.Logger;

@Mod(ExplosiveEnhancement.MOD_ID)
public class ExplosiveEnhancement {

    public static final String MOD_ID = "explosiveenhancement";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExplosiveEnhancement(FMLJavaModLoadingContext ctx) {
        ctx.registerConfig(ModConfig.Type.CLIENT, ExplosiveEnhancementConfig.SPEC, "explosiveenhancement.toml");
        IEventBus modBus = ctx.getModEventBus();

        ExplosiveParticles.init(modBus);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventSubscriber {

        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(ModNetwork::register);
        }

    }

    public static class ModNetwork {
        private static final String PROTOCOL_VERSION = "1";
        public static final SimpleChannel CHANNEL = ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(ExplosiveEnhancement.MOD_ID, "main_channel"))
                .networkProtocolVersion(1)
                .simpleChannel();

        public static void register() { ;; }
    }

}