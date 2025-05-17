package dev.xylonity.explosiveenhancement.registry;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExplosiveParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, ExplosiveEnhancement.MOD_ID);

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
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> EMPTY = register("empty");

    public static void init(IEventBus modBus) {
        PARTICLES.register(modBus);
    }

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

}
