package org.btwr.tough_environment;

import net.fabricmc.api.ClientModInitializer;
import org.btwr.tough_environment.event.ModClientEvents;
import org.btwr.tough_environment.render.BlockRenderLayerMappings;

public class ToughEnvironmentClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMappings.register();
        ModClientEvents.register();
    }
}