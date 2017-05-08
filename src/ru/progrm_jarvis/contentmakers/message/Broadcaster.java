package ru.progrm_jarvis.contentmakers.message;

import net.md_5.bungee.BungeeCord;

/**
 * Created by PROgrm_JARvis on 27.03.2017.
 */
public final class Broadcaster {
    private Broadcaster () {}
    public static void broadcast (String message) {
        BungeeCord.getInstance().broadcast(message);
    }
}
