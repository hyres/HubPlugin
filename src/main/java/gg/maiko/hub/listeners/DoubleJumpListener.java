package gg.maiko.hub.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJumpListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) { //Double Jump Part 1 Main
        Player player = e.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE)
            return;
        e.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(2.5).setY(1));
        player.getWorld().playSound(player.getLocation(), Sound.BLAZE_HIT, 1.0F, 1.0F);

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) { //Double Jump Part 2
        Player player = e.getPlayer();
        if ((player.getGameMode() != GameMode.CREATIVE)
                && (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR)
                && (!player.isFlying()))
            player.setAllowFlight(true);
    }
}
