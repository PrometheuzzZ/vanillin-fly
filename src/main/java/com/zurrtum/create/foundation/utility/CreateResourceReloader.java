package com.zurrtum.create.foundation.utility;


import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.util.Identifier;

import static com.zurrtum.create.Create.MOD_ID;

public abstract class CreateResourceReloader
    implements SynchronousResourceReloader, IdentifiableResourceReloadListener {
    private final Identifier id;

    public CreateResourceReloader(String id) {
        this.id = Identifier.of(MOD_ID, id);
    }

    public CreateResourceReloader(Identifier id) {
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    @Override
    public Identifier getFabricId() {
        return id;
    }
}
