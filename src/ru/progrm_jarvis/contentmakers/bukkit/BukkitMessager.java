package ru.progrm_jarvis.contentmakers.bukkit;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import ru.progrm_jarvis.contentmakers.console.Tracer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * Final Class used for sending data to Spigot
 */
public final class BukkitMessager {
    private BukkitMessager() {}
    public static void sendToBukkit (ServerInfo server, String subchannel, String type, String[] packet) {
        Tracer.msg("stb");
        if (packet == null) {
            packet = new String[0];
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(subchannel);
            out.writeUTF(type);
            out.writeUTF(Integer.toString(packet.length));
            for (String field : packet) {
                out.writeUTF(field);
                System.out.println(field);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Tracer.msg("stb1");
        server.sendData("Return", stream.toByteArray());
    }
    public static void sendToBukkit (Collection<ServerInfo> servers, String subchannel, String type, String[] data) {
        for (ServerInfo server : servers) {
            sendToBukkit(server, subchannel, type, data);
        }
    }
    public static void sendToAllBukkit (String subchannel, String type, String[] data) {
        Collection<ServerInfo> servers = BungeeCord.getInstance().getServers().values();
        for (ServerInfo server : servers) {
            sendToBukkit(server, subchannel, type, data);
        }
    }
    public static void sendToBukkit (ServerInfo server, String type, String[] data) {
        sendToBukkit(server, "ru.progrm_jarvis.contentmakers", type, data);
    }
    public static void sendToBukkit (Collection<ServerInfo> servers, String type, String[] data) {
        sendToBukkit(servers, "ru.progrm_jarvis.contentmakers", type, data);
    }
    public static void sendToAllBukkit (String type, String[] data) {
        sendToAllBukkit("ru.progrm_jarvis.contentmakers", type, data);
    }
}
