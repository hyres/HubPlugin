package gg.maiko.hub.bungee.listener;

import gg.maiko.hub.mHub;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class BungeeIncomingListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
            final String subchannel = in.readUTF();
            if (subchannel.equals("PlayerCount")) {
                String server = in.readUTF();
                int playerCount = in.readInt();

                if (server.toLowerCase().equalsIgnoreCase("all")) {
                    mHub.instance.getBungeeHandler().setTotalPlayerCount(playerCount);
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
