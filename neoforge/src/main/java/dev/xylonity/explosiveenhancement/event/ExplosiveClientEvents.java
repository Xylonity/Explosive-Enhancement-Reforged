package dev.xylonity.explosiveenhancement.event;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.particle.custom.*;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@EventBusSubscriber(modid = ExplosiveEnhancement.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ExplosiveClientEvents {

    @SubscribeEvent
    public static void registerProviders(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ExplosiveParticles.BLASTWAVE.get(), BlastWaveParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.FIREBALL.get(), FireballParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.BLANK_FIREBALL.get(), FireballParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.SMOKE.get(), SmokeParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.SPARKS.get(), SparksParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.BUBBLE.get(), BubbleParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.BLANK_SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.UNDERWATERBLASTWAVE.get(), UnderwaterBlastwaveParticle.Provider::new);
        event.registerSpriteSet(ExplosiveParticles.UNDERWATERSPARKS.get(), UnderwaterSparksParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerConfigScreen(final FMLClientSetupEvent event) {
        ModList.get().getModContainerById(ExplosiveEnhancement.MOD_ID).ifPresent(container -> container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new));
    }

}
