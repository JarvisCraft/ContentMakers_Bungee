package ru.progrm_jarvis.contentmakers.bukkit;

import net.md_5.bungee.api.config.ServerInfo;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.console.Tracer;

import java.util.Arrays;

/**
 * Created by PROgrm_JARvis on 24.04.2017.
 */
public final class BukkitManager {
    public static void sendAllDataToBukkit (ServerInfo serverInfo) {
        Tracer.msg(Arrays.toString(ContentMakersPlugin.getInstance().getStreamManager().getStreamsAsStringArray()));
        for (String player : ContentMakersPlugin.getInstance().getStreamManager().getStreamsAsStringArray()) {
            BukkitMessager.sendToBukkit(serverInfo, "addStream", ContentMakersPlugin.getInstance().getStreamManager().generateStreamDataPacket(player));
        }
        BukkitMessager.sendToBukkit(serverInfo, "dataLoaded", null);
    }
}
