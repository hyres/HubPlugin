package gg.maiko.hub.listeners;

import com.google.common.base.Strings;
import gg.maiko.hub.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();

        //onJoin Message
        player.sendMessage(new String[]{
                Strings.repeat("-", 35),
                CC.translae("&7Welcome &e" + player.getDisplayName() + "&7 to the &6&lServerName Network"),
                "",
                CC.translae(" &8* &7Teamspeak: &fts.servername.com"),
                CC.translae(" &8* &7Website: &fwww.servername.com"),
                CC.translae("  &8* &7Store: &fstore.servername.com"),
                CC.translae(" &8* &7Twitter: &ftwitter.com/ignMaikoX"),
                Strings.repeat("-", 35)

        });
        //End of onJoin Message

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent e) {
        if (!e.getPlayer().hasPermission("hub.staff") && e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void FoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (player.hasPermission("chat.send")) {
            return;
        }
        player.sendMessage(CC.translae("&cYou may not speak in the hub."));
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}