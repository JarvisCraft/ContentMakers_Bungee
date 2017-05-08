package ru.progrm_jarvis.contentmakers.data.streams;

import com.sun.istack.internal.Nullable;
import net.md_5.bungee.BungeeCord;
import ru.progrm_jarvis.contentmakers.bukkit.BukkitMessager;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.bukkit.Messager;
import ru.progrm_jarvis.contentmakers.console.Tracer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Manager for active Streams
 */
public class StreamManager {

    private static StreamManager streamManager = new StreamManager();
    private static ContentMakersPlugin plugin;
    public StreamManager (ContentMakersPlugin plugin) {
        StreamManager.plugin = plugin;
    }
    private StreamManager () {}
    public static StreamManager get () {
        return streamManager;
    }
    private Map<String, Stream> streams = new HashMap<>();
    public Map<String, Stream> getStreams () {
        return streams;
    }


    //Configurable Data

    private int updatePacketInterval;
    public void loadConfigurableDataFromConfig () {
        updatePacketInterval = ContentMakersPlugin.getInstance().getConfigManager().cfg("config.yml").getInt("stream.update-packet-interval");
    }
    public int getUpdatePacketInterval() {
        return updatePacketInterval;
    }



    public void addStream (String player, Stream stream) {
        System.out.println(streams.keySet().toString());
        System.out.println(streams.containsKey(player));
        if (!streams.containsKey(player)) {
            streams.put(player, stream);

            //Generating packet
            String[] packet = generateStreamDataPacket(stream);
            Tracer.msg(Arrays.toString(packet));

            BungeeCord.getInstance().getLogger().info("Added stream and sending it to all servers");
            BukkitMessager.sendToAllBukkit("addStream", packet);
            if (stream.getName().equals("")) {
                Messager.message(player, "StreamCreatedSucess");
            } else {
                Messager.message(player, "StreamCreatedSucessNamed");
            }
        } else {
            Messager.message(player, "AlreadyStreamingError");
        }
    }
    public boolean removeStream (String player) {
        if (streams.containsKey(player)) {
            streams.remove(player);
            return true;
        }
        return false;
    }
    public void stopAllStreams () {
        for (String streamName : streams.keySet()) {
            stopStream(streamName);
        }
    }
    public void stopStream (String name) {
        if (removeStream(name)) {
            Messager.broadcast("StreamStopped");
        }
    }

    //Добавление стрима из запроса к Банжи
    public void addStreamFromPacket(String[] packet) {
        Stream stream;
        if (!streams.containsKey(packet[0])) {
            if (packet.length == 3) {
                stream = new Stream(packet[0], packet[1], packet[2], "");
            } else {
                stream = new Stream(packet[0], packet[1], packet[2], packet[3]);
            }
            addStream(stream.getPlayer(), stream);
        }
    }
    //Добавление стрима из запроса к Банжи
    public void stopStreamFromPacket(String[] packet) {
        switch (packet[0]) {
            case ("player"):
                removeStream(packet[1]);
                break;
            case ("all"):
                stopAllStreams();
                break;
        }
    }
    public Stream getStreamByPlayer (String player) {
        return streams.getOrDefault(player, null);
    }

    public void sendStreamUpdatePacket (Stream stream) {
        Tracer.msg("Sending Stream-Update packet for stream " + stream.getName());
        String[] packet = {stream.getPlayer(), stream.getPlatform(), stream.getId(), stream.getName(), "-1"};
        BukkitMessager.sendToAllBukkit("streamUpdate", packet);
    }

    @Nullable
    public String[] getStreamsAsStringArray () {
        Tracer.msg(streams.size() + "");
        return streams.keySet().toArray(new String[streams.keySet().size()]);
    }
    @Nullable
    public String[] generateStreamDataPacket (Stream stream) {
        if (streams.containsValue(stream)) {
            Tracer.msg("exists");
            return new String[]{stream.getPlayer(), stream.getPlatform(), stream.getId(), stream.getName()};
        } else {
            Tracer.msg("not exists");
            return null;
        }
    }
    @Nullable
    public String[] generateStreamDataPacket (String streamName) {
        return generateStreamDataPacket(getStreamByPlayer(streamName));
    }
}
