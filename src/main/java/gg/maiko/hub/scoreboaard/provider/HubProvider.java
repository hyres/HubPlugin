package gg.maiko.hub.scoreboaard.provider;

import gg.maiko.hub.mHub;
import gg.maiko.hub.scoreboaard.AssembleAdapter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HubProvider implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return ChatColor.GOLD.toString() + "Server Name";
    }

    @Override
    public List<String> getLines(Player player) {
        final List<String> toReturn = new ArrayList<>();

        toReturn.add("&7&m----------------------");
        toReturn.add("&e&lOnline:");
        toReturn.add("&f" + mHub.instance.getBungeeHandler().getTotalPlayerCount());
        toReturn.add("");
        toReturn.add("&e&lRank:");
        toReturn.add(mHub.chat.getPlayerPrefix(player));
        toReturn.add("");
        toReturn.add("www.servername.com");
        toReturn.add("&7&m----------------------");

        return toReturn;
    }

}