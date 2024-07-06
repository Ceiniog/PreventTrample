package me.meibion.PreventTrample;

import me.meibion.PreventTrample.Events.OnMove;
import me.meibion.PreventTrample.Events.OnTrample;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static Server server = Bukkit.getServer();

    @Override
    public void onDisable() {
        server.getLogger().info("[PreventTrample] - Plugin Disabled");
    }

    @Override
    public void onEnable() {
        plugin = this;

        // EVENT LISTENERS
        Bukkit.getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, new OnTrample().pl, Event.Priority.High, this);
        Bukkit.getPluginManager().registerEvent(Event.Type.ENTITY_INTERACT, new OnTrample().el, Event.Priority.High, this);
        Bukkit.getPluginManager().registerEvent(Event.Type.PLAYER_MOVE, new OnMove().pl, Event.Priority.High, this);

        server.getLogger().info("[PreventTrample] - Plugin Enabled");
    }
}
