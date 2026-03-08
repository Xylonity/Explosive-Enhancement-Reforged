package dev.xylonity.explosiveenhancement.event;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.particle.custom.*;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExplosiveEnhancement.MOD_ID, value = Dist.CLIENT)

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
        event.registerSpriteSet(ExplosiveParticles.EMPTY.get(), EmptyParticle.Provider::new);
    }

}
