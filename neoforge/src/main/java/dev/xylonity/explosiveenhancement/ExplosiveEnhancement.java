package dev.xylonity.explosiveenhancement;

import com.mojang.logging.LogUtils;
import dev.xylonity.explosiveenhancement.config.ExplosiveEnhancementConfig;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(ExplosiveEnhancement.MOD_ID)
public class ExplosiveEnhancement {

    public static final String MOD_ID = "explosiveenhancement";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ExplosiveEnhancement(IEventBus bus, ModContainer ctx) {
        ctx.registerConfig(ModConfig.Type.CLIENT, ExplosiveEnhancementConfig.SPEC, "explosiveenhancement.toml");

        ExplosiveParticles.init(bus);
    }

}