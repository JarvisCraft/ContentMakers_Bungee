package ru.progrm_jarvis.contentmakers.custom.platform;

import net.md_5.bungee.api.ChatColor;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;
import ru.progrm_jarvis.contentmakers.config.ConfigManager;

import net.md_5.bungee.config.Configuration;
import ru.progrm_jarvis.contentmakers.console.Tracer;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Manages Platforms
 */
public class PlatformManager {
    private static PlatformManager platformManager = new PlatformManager();
    private static ContentMakersPlugin plugin;
    private static ConfigManager configManager;
    public PlatformManager (ContentMakersPlugin plugin) {
        PlatformManager.plugin = plugin;
        PlatformManager.configManager = plugin.getConfigManager();
    }
    private PlatformManager () {}

    private Configuration streamPlarformsConfiguration;
    private Set<StreamPlatform> streamPlarforms = new LinkedHashSet<>();
    public void loadPlatforms () {
        loadVideoPlatforms();
        loadStreamPlatforms();
    }
    public void loadVideoPlatforms() {
        //TODO
        Tracer.msg("Loading ContentMakers' Video-Platforms");
    }
    public void loadStreamPlatforms () {
        Tracer.msg("Loading ContentMakers' Stream-Platforms");
        streamPlarformsConfiguration = configManager.cfg("platforms.yml").getSection("stream");
        Collection<String> platforms = streamPlarformsConfiguration.getKeys();
        if (platforms.size() > 0) {
            for (String platform : platforms) {
                loadStreamPlatform(platform, streamPlarformsConfiguration.getSection(platform));
            }
            Tracer.msg("Sucessfully loaded " +
                    streamPlarforms.size() + " Stream-Platforms: " + streamPlarformsConfiguration.getKeys());
        } else {
            Tracer.err("There are no working StreamPlatforms in your " +
                    ChatColor.ITALIC + "platforms.yml! Try recreating it.");
        }

    }
    private void loadStreamPlatform (String name, Configuration platformConfiguration) {
        StreamPlatform platform = new StreamPlatform(name, platformConfiguration.getString("viewers-num-url"),
                platformConfiguration.getString("viewers-num-placeholder"), platformConfiguration.getInt("viewers-num-frequency"));
        streamPlarforms.add(platform);
        Tracer.msg("Loades Stream-Platform: " + platform.toString() + ".");
    }

}
