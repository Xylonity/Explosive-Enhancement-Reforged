package dev.xylonity.explosiveenhancement.registry;

import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.registries.RegistryObject;

/**
 * Defines a single abstract method `registerMutableSprite`, which is responsible
 * for registering mutable sprites associated with a custom particle type.
 */
@FunctionalInterface
public interface IExplosiveParticleHandler {

    <P extends ParticleOptions, E extends ParticleType<P>> void registerMutableSprite(RegistryObject<E> type, ParticleEngine.SpriteParticleRegistration<P> registration);

}
