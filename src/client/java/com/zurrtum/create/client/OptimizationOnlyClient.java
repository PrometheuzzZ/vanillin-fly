package com.zurrtum.create.client;

import com.zurrtum.create.client.flywheel.backend.compile.FlwProgramsReloader;
import com.zurrtum.create.client.flywheel.impl.Flywheel;
import com.zurrtum.create.client.flywheel.impl.visualization.VisualizationEventHandler;
import com.zurrtum.create.client.vanillin.Vanillin;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class OptimizationOnlyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(FlwProgramsReloader.INSTANCE);

        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (!minecraft.isPaused() && minecraft.world != null) {
                VisualizationEventHandler.onClientTick(minecraft, minecraft.world);
            }
        });
        ClientEntityEvents.ENTITY_LOAD.register((entity, world) ->
            VisualizationEventHandler.onEntityJoinLevel(world, entity));
        ClientEntityEvents.ENTITY_UNLOAD.register((entity, world) ->
            VisualizationEventHandler.onEntityLeaveLevel(world, entity));

        new Flywheel().onInitializeClient();
        new Vanillin().onInitializeClient();
    }
}
