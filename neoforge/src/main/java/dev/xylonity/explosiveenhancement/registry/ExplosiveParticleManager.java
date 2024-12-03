package dev.xylonity.explosiveenhancement.registry;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Defines a custom registry for particle types using DeferredRegister.
 * The `PARTICLE_TYPES` supplier holds the registry for `ParticleType` instances,
 * and `PARTICLE_REGISTER` manages the deferred registration process. The `setup`
 * method initializes the registry and registers all custom particles in the
 * event bus, ensuring that they are properly integrated within the mod's lifecycle.
 */
public class ExplosiveParticleManager {

    public static Registry<ParticleType<?>> PARTICLE_TYPES;
    public static final ResourceKey<Registry<ParticleType<?>>> PARTICLE_TYPE_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(ExplosiveEnhancement.MOD_ID, "particle_types"));
    public static final DeferredRegister<ParticleType<?>> PARTICLE_REGISTER = DeferredRegister.create(PARTICLE_TYPE_KEY, ExplosiveEnhancement.MOD_ID);

    public static void setup(IEventBus eventBus) {
        PARTICLE_TYPES = PARTICLE_REGISTER.makeRegistry((s) -> s.sync(false));
        PARTICLE_REGISTER.register(eventBus);
    }

}
