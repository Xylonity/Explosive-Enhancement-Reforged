package dev.xylonity.explosiveenhancement.registry;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ExplosiveParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ExplosiveEnhancement.MOD_ID);

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
    public static final RegistryObject<SimpleParticleType> EMPTY = register("empty");

    public static void init(IEventBus modBus) {
        PARTICLES.register(modBus);
    }

    private static RegistryObject<SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

}
