package ru.progrm_jarvis.contentmakers.listener.pluginmessage;

import net.md_5.bungee.api.config.ServerInfo;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.bukkit.BukkitManager;
import ru.progrm_jarvis.contentmakers.console.Tracer;
import ru.progrm_jarvis.contentmakers.data.streams.StreamManager;

import java.util.Arrays;

/**
 * Created by PROgrm_JARvis on 15.04.2017.
 */
public final class ContentMakersPacketHandler {
    public static void handle (ServerInfo serverInfo, String subchannel, int length, String type, String[] packet) {
        switch (type) {
            case ("addStream"):
                ContentMakersPlugin.getInstance().getStreamManager().addStreamFromPacket(packet);
                break;
            case ("stopStream"):
                ContentMakersPlugin.getInstance().getStreamManager().stopStreamFromPacket(packet);
                break;
            case ("loadBukkit"):
                BukkitManager.sendAllDataToBukkit(serverInfo);
                break;
            default:
                Tracer.err("Received wrong packet! Probably you're using different versions of ContentMakers for BungeeCord and Spigot.\n"+
                        "Packet info:\n" + "BungeeCord" + ":" + subchannel + ":" + type + ":" + length + ":" + Arrays.toString(packet));
                break;
        }
    }
}
