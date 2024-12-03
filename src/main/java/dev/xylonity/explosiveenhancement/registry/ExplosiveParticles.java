package dev.xylonity.explosiveenhancement.registry;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.particle.custom.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ExplosiveParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, ExplosiveEnhancement.MOD_ID);

    public static final RegistryObject<SimpleParticleType> BLASTWAVE = register("blastwave");
    public static final RegistryObject<SimpleParticleType> FIREBALL = register("fireball");
    public static final RegistryObject<SimpleParticleType> BLANK_FIREBALL = register("blank_fireball");
    public static final RegistryObject<SimpleParticleType> SMOKE = register("smoke");
    public static final RegistryObject<SimpleParticleType> SPARKS = register("sparks");
    public static final RegistryObject<SimpleParticleType> BUBBLE = register("bubble");
    public static final RegistryObject<SimpleParticleType> SHOCKWAVE = register("shockwave");
    public static final RegistryObject<SimpleParticleType> BLANK_SHOCKWAVE = register("blank_shockwave");
    public static final RegistryObject<SimpleParticleType> UNDERWATERBLASTWAVE = register("underwaterblastwave");
    public static final RegistryObject<SimpleParticleType> UNDERWATERSPARKS = register("underwatersparks");

    public static void registerProviders(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(BLASTWAVE.get(), BlastWaveParticle.Provider::new);
        event.registerSpriteSet(FIREBALL.get(), FireballParticle.Provider::new);
        event.registerSpriteSet(BLANK_FIREBALL.get(), FireballParticle.Provider::new);
        event.registerSpriteSet(SMOKE.get(), SmokeParticle.Provider::new);
        event.registerSpriteSet(SPARKS.get(), SparksParticle.Provider::new);
        event.registerSpriteSet(BUBBLE.get(), BubbleParticle.Provider::new);
        event.registerSpriteSet(SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        event.registerSpriteSet(BLANK_SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        event.registerSpriteSet(UNDERWATERBLASTWAVE.get(), UnderwaterBlastwaveParticle.Provider::new);
        event.registerSpriteSet(UNDERWATERSPARKS.get(), UnderwaterSparksParticle.Provider::new);
    }

    private static RegistryObject<SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

}
