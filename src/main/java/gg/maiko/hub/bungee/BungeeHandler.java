package gg.maiko.hub.bungee;

import gg.maiko.hub.mHub;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import lombok.Setter;
import gg.maiko.hub.bungee.listener.BungeeIncomingListener;
import org.bukkit.Bukkit;

public class BungeeHandler {

    private mHub plugin;
    @Getter @Setter private int totalPlayerCount;

    public BungeeHandler(mHub plugin) {
        this.plugin = plugin;
        this.totalPlayerCount = 0;
        this.plugin.getServer().getMessenger().registerOutgoingPluginChannel(this.plugin, "BungeeCord");
        this.plugin.getServer().getMessenger().registerIncomingPluginChannel(this.plugin, "BungeeCord", new BungeeIncomingListener());
    }

    public void requestCount() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF("ALL");
        Bukkit.getServer().sendPluginMessage(this.plugin, "BungeeCord", out.toByteArray());

    }

}