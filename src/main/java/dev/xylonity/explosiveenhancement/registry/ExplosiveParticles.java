package dev.xylonity.explosiveenhancement.registry;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.particle.ExplosiveParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ExplosiveParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, ExplosiveEnhancement.MOD_ID);

    public static final RegistryObject<ExplosiveParticleType> BLASTWAVE = register("blastwave");
    public static final RegistryObject<ExplosiveParticleType> FIREBALL = register("fireball");
    public static final RegistryObject<ExplosiveParticleType> BLANK_FIREBALL = register("blank_fireball");
    public static final RegistryObject<ExplosiveParticleType> SMOKE = register("smoke");
    public static final RegistryObject<ExplosiveParticleType> SPARKS = register("sparks");
    public static final RegistryObject<SimpleParticleType> BUBBLE = PARTICLES.register("bubble", () -> new SimpleParticleType(false));
    public static final RegistryObject<ExplosiveParticleType> SHOCKWAVE = register("shockwave");
    public static final RegistryObject<ExplosiveParticleType> BLANK_SHOCKWAVE = register("blank_shockwave");
    public static final RegistryObject<ExplosiveParticleType> UNDERWATERBLASTWAVE = register("underwaterblastwave");
    public static final RegistryObject<ExplosiveParticleType> UNDERWATERSPARKS = register("underwatersparks");

    private static RegistryObject<ExplosiveParticleType> register(String name) {
        return PARTICLES.register(name, () -> new ExplosiveParticleType());
    }

}
