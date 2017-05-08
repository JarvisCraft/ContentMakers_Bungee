package ru.progrm_jarvis.contentmakers.listener.pluginmessage;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.*;

/**
 * Created by PROgrm_JARvis on 26.03.2017.
 */
public class ContentMakersChannelListener implements Listener {
    @EventHandler
    public void onPluginMessage (PluginMessageEvent event) {
        System.out.println("Received some Plugin-Message Packet");
        if (event.getTag().equalsIgnoreCase("BungeeCord")) {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
            try {
                String subchannel = in.readUTF();
                if (subchannel.equals("ru.progrm_jarvis.contentmakers")) {
                    System.out.println("PJACs channel");
                    ServerInfo serverInfo = BungeeCord.getInstance().getPlayer(event.getReceiver().toString()).getServer().getInfo();
                    String type = in.readUTF();
                    Integer length = Integer.parseInt(in.readUTF());
                    String[] packet = new String[length];
                    for (int i = 0; i < length; i++) {
                        packet[i] = in.readUTF();
                    }
                    ContentMakersPacketHandler.handle(serverInfo, subchannel, length, type, packet);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
