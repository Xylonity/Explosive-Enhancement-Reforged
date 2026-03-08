package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class SparksParticle extends SingleQuadParticle {

    private final SpriteSet sprites;

    SparksParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(level, x, y, z, sprites.first());
        this.sprites = sprites;

        this.lifetime = (int) (5 + Math.floor(velX / 5.0));
        if (velX == 0.0) {
            this.quadSize = (float) ExplosiveValues.sparkSize;
        }
        else {
            this.quadSize = (float) (ExplosiveValues.sparkSize * (velX * 0.25F));
        }

        this.setParticleSpeed(0.0, 0.0, 0.0);
        this.alpha = (float) ExplosiveValues.sparkOpacity;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        this.yd -= this.gravity;
        this.move(this.xd, this.yd, this.zd);
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float partialTick) {
        return ExplosiveValues.emissiveExplosion ? 15728880 : super.getLightColor(partialTick);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            return new SparksParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }

    }

}