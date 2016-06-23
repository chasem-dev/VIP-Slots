import com.google.inject.Inject;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.IsCancelled;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.util.Tristate;

import java.util.logging.Logger;

/**
 * Created by chase on 6/22/2016.
 */
@Plugin(id = "vipslots", name = "VIPSlots", version = "1.0")
public class VIPSlots {

    @Inject
    private Logger logger;
    @Inject
    private Game game;

    private static VIPSlots instance;

    public static VIPSlots getInstance() {
        return instance;
    }

    public Logger getLogger() {
        return this.logger;
    }


    @Listener
    public void serverStartedEvent(GameStartedServerEvent event) {
        instance = this;
        getLogger().info("VIP-Slots Activated!");
    }


    @Listener
    @IsCancelled(Tristate.UNDEFINED)
    public void playerLoginEvent(ClientConnectionEvent.Login event) {
        if (Sponge.getServer().getOnlinePlayers().size() >= Sponge.getServer().getMaxPlayers()) {
            if (event.getTargetUser().hasPermission("vipslots.vip")) {
                event.setCancelled(false);
            }
        }
    }

}
