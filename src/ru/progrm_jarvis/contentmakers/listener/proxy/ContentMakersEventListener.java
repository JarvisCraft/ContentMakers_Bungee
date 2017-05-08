package ru.progrm_jarvis.contentmakers.listener.proxy;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import ru.progrm_jarvis.contentmakers.bukkit.BukkitMessager;

/**
 * Created by PROgrm_JARvis on 26.03.2017.
 */
public class ContentMakersEventListener implements Listener {
    @EventHandler
    public void onServerConnected (ServerConnectedEvent event) {
        event.getPlayer().sendMessage(new ComponentBuilder("QQ, Bro, " + event.getServer().getInfo().getName() + "!").color(ChatColor.GREEN).create());
        BukkitMessager.sendToBukkit(event.getServer().getInfo(), "doUpdateData", null);
    }
}
