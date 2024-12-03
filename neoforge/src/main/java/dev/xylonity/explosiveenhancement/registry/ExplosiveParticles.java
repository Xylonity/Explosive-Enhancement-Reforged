package dev.xylonity.explosiveenhancement.registry;

import dev.xylonity.explosiveenhancement.particle.custom.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ExplosiveParticles {

    public static void init() { ;; }

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLASTWAVE = register("blastwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FIREBALL = register("fireball");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLANK_FIREBALL = register("blank_fireball");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SMOKE = register("smoke");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPARKS = register("sparks");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BUBBLE = register("bubble");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHOCKWAVE = register("shockwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLANK_SHOCKWAVE = register("blank_shockwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> UNDERWATERBLASTWAVE = register("underwaterblastwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> UNDERWATERSPARKS = register("underwatersparks");

    public static void registerProviders(final RegisterParticleProvidersEvent event) {
        IExplosiveParticleHandler manager = ((IExplosiveParticleHandler) Minecraft.getInstance().particleEngine);
        manager.registerMutableSprite(BLASTWAVE, BlastWaveParticle.Provider::new);
        manager.registerMutableSprite(FIREBALL, FireballParticle.Provider::new);
        manager.registerMutableSprite(BLANK_FIREBALL, FireballParticle.Provider::new);
        manager.registerMutableSprite(SMOKE, SmokeParticle.Provider::new);
        manager.registerMutableSprite(SPARKS, SparksParticle.Provider::new);
        manager.registerMutableSprite(BUBBLE, BubbleParticle.Provider::new);
        manager.registerMutableSprite(SHOCKWAVE, ShockwaveParticle.Provider::new);
        manager.registerMutableSprite(BLANK_SHOCKWAVE, ShockwaveParticle.Provider::new);
        manager.registerMutableSprite(UNDERWATERBLASTWAVE, UnderwaterBlastwaveParticle.Provider::new);
        manager.registerMutableSprite(UNDERWATERSPARKS, UnderwaterSparksParticle.Provider::new);
    }

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String name) {
        return ExplosiveParticleManager.PARTICLE_REGISTER.register(name, () -> new SimpleParticleType(false));
    }

}
