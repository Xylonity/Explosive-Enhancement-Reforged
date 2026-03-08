package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.state.QuadParticleRenderState;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.joml.Quaternionf;

public class BlastWaveParticle extends SingleQuadParticle {

    private final SpriteSet sprites;

    private static final Quaternionf BASE_ROTATION = new Quaternionf(0.0F, -0.7F, 0.7F, 0.0F);

    public BlastWaveParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(level, x, y + 0.5D, z, sprites.first());

        this.sprites = sprites;
        this.quadSize = (float) velX;
        this.setParticleSpeed(0.0D, 0.0D, 0.0D);
        this.lifetime = (int) (15 + Math.floor(velX / 5.0D));
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void extract(QuadParticleRenderState reusedState, Camera camera, float partialTick) {
        Quaternionf top = new Quaternionf(BASE_ROTATION);
        Quaternionf bottom = new Quaternionf(BASE_ROTATION).rotateY((float) Math.PI);

        this.extractRotatedQuad(reusedState, camera, top, partialTick);
        this.extractRotatedQuad(reusedState, camera, bottom, partialTick);
    }

    @Override
    protected int getLightColor(float partialTick) {
        return ExplosiveValues.emissiveExplosion ? 15728880 : super.getLightColor(partialTick);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }
    
    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            return new BlastWaveParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }

    }

}