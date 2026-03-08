package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class SmokeParticle extends SingleQuadParticle {

    private static final double AXIS_MARKER = 0.15D;
    private static final double EPS = 1.0E-3D;

    private final SpriteSet sprites;

    SmokeParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(level, x, y, z, sprites.first());
        this.sprites = sprites;

        this.friction = 0.6F;
        this.lifetime = this.random.nextInt(35) + 1;
        this.gravity = 3.0E-6F;
        this.hasPhysics = true;

        double absX = Math.abs(velX);
        double absZ = Math.abs(velZ);

        if (almostZero(velZ)) {
            this.quadSize = Math.max(0.01F, (float) absX * 0.25F);
            this.lifetime += (int) (absX * this.random.nextInt(3, 22));
            this.xd = 0.0D;
            this.zd = 0.0D;
        }
        else if (almost(absX, AXIS_MARKER)) {
            this.quadSize = Math.max(0.01F, (float) absZ * 0.25F);
            this.lifetime += (int) (absZ * this.random.nextInt(3, 22));
            this.xd = Math.copySign(absZ * 0.5D * AXIS_MARKER, velX);
            this.zd = 0.0D;
        }
        else if (almost(absZ, AXIS_MARKER)) {
            this.quadSize = Math.max(0.01F, (float) absX * 0.25F);
            this.lifetime += (int) (absX * this.random.nextInt(3, 22));
            this.xd = 0.0D;
            this.zd = Math.copySign(absX * 0.5D * AXIS_MARKER, velZ);
        }
        else {
            double sizeBase = Math.max(absX, absZ);
            this.quadSize = Math.max(0.01F, (float) sizeBase * 0.25F);
            this.lifetime += (int) (sizeBase * this.random.nextInt(3, 22));
            this.xd = velX * 0.5D;
            this.zd = velZ * 0.5D;
        }

        this.yd = velY / 1.85D;
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

        this.setSpriteFromAge(this.sprites);

        if (this.age == 12) {
            this.xd = 0.0D;
            this.yd = 0.05D;
            this.zd = 0.0D;
        }

        this.move(this.xd, this.yd, this.zd);
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float tint) {
        if (ExplosiveValues.emissiveExplosion && this.age <= this.lifetime * 0.12F) {
            return 15728880;
        }
        else if (ExplosiveValues.emissiveExplosion && this.age <= this.lifetime * 0.17F) {
            int base = super.getLightColor(tint);
            return Mth.clamp(base + this.age + 30, base, 15728880);
        }
        else {
            return super.getLightColor(tint);
        }

    }

    private static boolean almost(double a, double b) {
        return Math.abs(a - b) < EPS;
    }

    private static boolean almostZero(double value) {
        return Math.abs(value) < EPS;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            return new SmokeParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }

    }

}