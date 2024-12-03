package dev.xylonity.explosiveenhancement;

import com.mojang.logging.LogUtils;
import dev.xylonity.explosiveenhancement.config.ExplosiveEnhancementConfig;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticleManager;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import org.slf4j.Logger;

@Mod(ExplosiveEnhancement.MOD_ID)
public class ExplosiveEnhancement {

    public static final String MOD_ID = "explosiveenhancement";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExplosiveEnhancement(IEventBus modEventBus, ModContainer modContainer) {
        if (FMLLoader.getDist() == Dist.DEDICATED_SERVER)
            LOGGER.warn("Explosive Enhancement: Reforged won't be loaded as it should be initialized on the client side.");
        else {
            LOGGER.info("Explosive Enhancement: Reforged loaded correctly, initializing particles...");
            this.init(modEventBus, modContainer);
        }
    }

    private void init(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, ExplosiveEnhancementConfig.SPEC, "explosiveenhancement.toml");

        ExplosiveParticleManager.setup(modEventBus);

        // Ensures that all DeferredRegister entries are registered prior to the RegisterEvent being fired.
        // This is necessary because attempting to register entries after this event is fired will cause an Exception.
        ExplosiveParticles.init();

        modEventBus.addListener(ExplosiveParticles::registerProviders);
    }

}
