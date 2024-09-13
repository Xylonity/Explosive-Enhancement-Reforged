package dev.xylonity.explosiveenhancement.mixin;

import dev.xylonity.explosiveenhancement.registry.IExplosiveParticleHandler;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticleManager;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.particle.ParticleEngine.MutableSpriteSet;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ParticleEngine.class)
public class ParticleManagerMixin implements IExplosiveParticleHandler {

    @Shadow @Final private Map<ResourceLocation, ParticleProvider<?>> providers;
    @Shadow @Final private Map<ResourceLocation, MutableSpriteSet> spriteSets;
    @Shadow protected ClientLevel level;

    /**
     * Registers a mutable sprite set for a specific particle type.
     * Creates a new MutableSpriteSet for the provided particle type (RegistryObject),
     * and associates it with both the spriteSets and providers maps. The spriteSet
     * is linked to the particle type's ID, and the provider is generated using
     * the provided ParticleEngine.SpriteParticleRegistration, which registers
     * the particle's sprite behavior in the engine.
     */
    @Override
    public <P extends ParticleOptions, E extends ParticleType<P>> void registerMutableSprite(RegistryObject<E> type, ParticleEngine.SpriteParticleRegistration<P> registration) {
        MutableSpriteSet spriteSet = new MutableSpriteSet();
        this.spriteSets.put(type.getId(), spriteSet);
        this.providers.put(type.getId(), registration.create(spriteSet));
    }

    /**
     * Intercepts the particle creation process to replace or modify particles using custom particle types
     * registered in the ExplosiveParticleManager.
     */
    @Inject(method = "makeParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;getKey(Ljava/lang/Object;)Lnet/minecraft/resources/ResourceLocation;"), cancellable = true)
    private <T extends ParticleOptions> void onMakeParticle(T pParticleData, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, CallbackInfoReturnable<Particle> cir) {
        ResourceLocation original = BuiltInRegistries.PARTICLE_TYPE.getKey(pParticleData.getType());
        if (ExplosiveParticleManager.PARTICLE_TYPES != null && ExplosiveParticleManager.PARTICLE_TYPES.get() != null) {

            ResourceLocation newResource = original == null ? ExplosiveParticleManager.PARTICLE_TYPES.get().getKey(pParticleData.getType()) : original;
            ParticleProvider<T> particleProvider = (ParticleProvider<T>) this.providers.get(newResource);

            if (particleProvider != null) {
                Particle particle = particleProvider.createParticle(pParticleData, this.level, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
                cir.setReturnValue(particle);
            } else {
                cir.setReturnValue(null);
            }

            cir.cancel();
        }
    }

}