package ru.progrm_jarvis.contentmakers.data.streams;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.console.Tracer;

import java.util.concurrent.TimeUnit;

/**
 * Created by PROgrm_JARvis on 26.03.2017.
 */
public class Stream {
    private Stream instance;
    public Stream getInstance() {
        return instance;
    }

    private String player;
    private String platform;
    private String id;
    private String name;
    private ScheduledTask updatePacketTask;
    public Stream (String player, String platform, String id, String name) {
        this.instance = this;
        this.player = player;
        this.platform = platform;
        this.id = id;
        this.name = name;

        scheduleUpdatePacketTask();
    }

    //Packet-Tasking
    public ScheduledTask getUpdatePacketTask() {
        return updatePacketTask;
    }
    private void scheduleUpdatePacketTask () {
        Tracer.msg("scheduleUpdatePacketTask()");
        updatePacketTask = BungeeCord.getInstance().getScheduler().schedule(ContentMakersPlugin.getInstance(), () -> {
            Tracer.msg(String.valueOf(ContentMakersPlugin.getInstance().getStreamManager().getUpdatePacketInterval()));
            ContentMakersPlugin.getInstance().getStreamManager().sendStreamUpdatePacket(instance);
            scheduleUpdatePacketTask();


        }, ContentMakersPlugin.getInstance().getStreamManager().getUpdatePacketInterval(), TimeUnit.SECONDS);
    }
    public void cancelUpdatePacketTask () {
        updatePacketTask.cancel();
    }

    public String getPlayer() {
        return player;
    }
    public String getPlatform() {
        return platform;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
