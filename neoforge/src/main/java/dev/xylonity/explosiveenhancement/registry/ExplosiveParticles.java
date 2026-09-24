package dev.xylonity.explosiveenhancement.registry;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.particle.ExplosiveParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExplosiveParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, ExplosiveEnhancement.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> BLASTWAVE = register("blastwave");
    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> FIREBALL = register("fireball");
    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> BLANK_FIREBALL = register("blank_fireball");
    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> SMOKE = register("smoke");
    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> SPARKS = register("sparks");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BUBBLE = PARTICLES.register("bubble", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> SHOCKWAVE = register("shockwave");
    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> BLANK_SHOCKWAVE = register("blank_shockwave");
    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> UNDERWATERBLASTWAVE = register("underwaterblastwave");
    public static final DeferredHolder<ParticleType<?>, ExplosiveParticleType> UNDERWATERSPARKS = register("underwatersparks");

    public static void init(IEventBus modBus) {
        PARTICLES.register(modBus);
    }

    private static DeferredHolder<ParticleType<?>, ExplosiveParticleType> register(String name) {
        return PARTICLES.register(name, ExplosiveParticleType::new);
    }

}
