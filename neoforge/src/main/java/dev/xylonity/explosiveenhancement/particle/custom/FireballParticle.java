package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class FireballParticle extends SingleQuadParticle {

    private final SpriteSet sprites;
    private final boolean important;

    FireballParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(level, x, y, z, sprites.first());
        this.sprites = sprites;

        this.lifetime = (int) (9 + Math.floor(velX / 5.0));
        this.quadSize = (float) velX;
        this.important = velY == 1.0;
        this.setParticleSpeed(0.0, 0.0, 0.0);
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

        if (this.age >= this.lifetime * 0.65 && ExplosiveValues.showSparks) {
            this.level.addParticle(ExplosiveParticles.SPARKS.get(), true, this.important, this.x, this.y, this.z, this.quadSize, this.yd, this.zd);
        }

        this.setSpriteFromAge(this.sprites);
    }

    @Override
    protected int getLightCoords(float a) {
        return ExplosiveValues.emissiveExplosion ? 15728880 : super.getLightCoords(a);
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
            return new FireballParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }

    }

}