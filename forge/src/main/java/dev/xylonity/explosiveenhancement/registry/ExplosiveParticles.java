package dev.xylonity.explosiveenhancement.registry;

import dev.xylonity.explosiveenhancement.particle.custom.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.registries.RegistryObject;

public class ExplosiveParticles {

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

    public static void init() {}

    private static RegistryObject<SimpleParticleType> register(String name) {
        return ExplosiveParticleManager.PARTICLE_REGISTER.register(name, () -> new SimpleParticleType(false));
    }

}
