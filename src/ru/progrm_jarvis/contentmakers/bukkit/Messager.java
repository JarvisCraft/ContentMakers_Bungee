package ru.progrm_jarvis.contentmakers.bukkit;

import net.md_5.bungee.BungeeCord;

/**
 * Created by PROgrm_JARvis on 14.04.2017.
 */
public final class Messager {
    public static void message (String player, String messageIdentifier) {
        String[] packet = {player, messageIdentifier};
        BukkitMessager.sendToBukkit(BungeeCord.getInstance().getPlayer(player).getServer().getInfo(), "message", packet);
    }
    public static void broadcast (String messageIdentifier) {
        String[] packet = {messageIdentifier};
        BukkitMessager.sendToAllBukkit("broadcast", packet);
    }
}
