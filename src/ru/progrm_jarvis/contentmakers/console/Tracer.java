package ru.progrm_jarvis.contentmakers.console;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;

/**
 * Created by PROgrm_JARvis on 14.04.2017.
 */
public final class Tracer {
    private final static String prefix = "[ContentMakers] ";
    public static void msg (String message) {
        BungeeCord.getInstance().getLogger().info(prefix + message);
    }
    public static void warn (String message) {
        BungeeCord.getInstance().getLogger().warning(prefix + message);
    }
    public static void err (String message) {
        BungeeCord.getInstance().getLogger().warning(prefix + ChatColor.DARK_RED + message);
    }
}
