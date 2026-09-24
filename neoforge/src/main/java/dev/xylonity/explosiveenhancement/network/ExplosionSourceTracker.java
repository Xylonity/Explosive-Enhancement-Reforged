package dev.xylonity.explosiveenhancement.network;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class ExplosionSourceTracker {

    private static final int MAX_PENDING_ENTITIES = 64;
    private static final Deque<Source> PENDING_ENTITIES = new ArrayDeque<>();

    public static synchronized void add(double x, double y, double z, ResourceLocation entityType) {
        if (PENDING_ENTITIES.size() >= MAX_PENDING_ENTITIES) {
            PENDING_ENTITIES.removeFirst();
        }

        PENDING_ENTITIES.addLast(new Source(x, y, z, entityType));
    }

    public static synchronized boolean isBlacklisted(double x, double y, double z) {
        final Iterator<Source> iterator = PENDING_ENTITIES.iterator();
        while (iterator.hasNext()) {
            final Source source = iterator.next();
            if (source.x == x && source.y == y && source.z == z) {
                iterator.remove();
                return ExplosiveValues.entityBlacklist.contains(source.entityType);
            }

        }

        return false;
    }

    private record Source(
            double x,
            double y,
            double z,
            ResourceLocation entityType
    ) {
        ;;
    }

}