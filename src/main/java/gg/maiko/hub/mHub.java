package gg.maiko.hub;

import gg.maiko.hub.bungee.BungeeHandler;
import gg.maiko.hub.listeners.DoubleJumpListener;
import gg.maiko.hub.listeners.PlayerListener;
import gg.maiko.hub.scoreboaard.Assemble;
import gg.maiko.hub.scoreboaard.AssembleStyle;
import gg.maiko.hub.scoreboaard.provider.HubProvider;
import lombok.Getter;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

@Getter
public class mHub extends JavaPlugin {

    private BungeeHandler bungeeHandler;
    public static mHub instance;
    public Permission permission = null;
    public static Chat chat = null;

    public void onEnable() {
        instance = this;
        System.out.println("mHub loading....");
        registerBungee();
        registerListener();
        registerScoreboard();
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            System.out.println("Vault not found!");
            System.out.println("Failed to hook vault!");
            System.out.println("Disabling plugin...");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        } else {
            setupChat();
            setupPermissions();
        }
        System.out.println("This plugin has been develop by MaikoX");
        System.out.println("https://twitter.com/ignMaikoX");
        System.out.println("mHub finish loading....");

    }

    private void registerListener() {
        Arrays.asList(
                new PlayerListener(),
                new DoubleJumpListener()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    private void registerBungee(){
        this.bungeeHandler = new BungeeHandler(this);
    }

    private void registerScoreboard() {
        Assemble assemble = new Assemble(this, new HubProvider());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.MODERN);
    }


    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider =  Bukkit.getServicesManager().getRegistration(Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
}