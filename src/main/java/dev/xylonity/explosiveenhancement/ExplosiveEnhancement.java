package dev.xylonity.explosiveenhancement;

import com.mojang.logging.LogUtils;
import dev.xylonity.explosiveenhancement.config.ExplosiveEnhancementConfig;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.Logger;

@Mod(ExplosiveEnhancement.MOD_ID)
public class ExplosiveEnhancement {

    public static final String MOD_ID = "explosiveenhancement";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExplosiveEnhancement() {
        if (FMLLoader.getDist() == Dist.DEDICATED_SERVER)
            LOGGER.warn("Explosive Enhancement: Reforged won't be loaded as it should be initialized on the client side.");
        else {
            LOGGER.info("Explosive Enhancement: Reforged loaded correctly, initializing particles...");
            this.init();
        }
    }

    private void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ExplosiveEnhancementConfig.SPEC, "explosiveenhancement.toml");
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ExplosiveParticleManager.setup(modBus);

        // Ensures that all DeferredRegister entries are registered prior to the RegisterEvent being fired.
        // This is necessary because attempting to register entries after this event is fired will cause an Exception.
        ExplosiveParticles.init();

        modBus.addListener(ExplosiveParticles::registerProviders);
    }

}
