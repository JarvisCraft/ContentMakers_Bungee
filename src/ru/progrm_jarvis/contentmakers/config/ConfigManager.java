package ru.progrm_jarvis.contentmakers.config;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import ru.progrm_jarvis.contentmakers.ContentMakersPlugin;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PROgrm_JARvis on 27.03.2017.
 */
public class ConfigManager {
    private static ConfigManager streamManager = new ConfigManager();
    private static ContentMakersPlugin plugin;
    public ConfigManager (ContentMakersPlugin plugin) {
        ConfigManager.plugin = plugin;
    }
    private ConfigManager () {}

    //Основная часть
    private String[] configNames = {"config.yml", "platforms.yml"};
    private String[] fileNames = {"README.txt"};
    private Map<String, Configuration> configs = new HashMap<String, Configuration>();
    private void generateDefaultFile(String name) {
        if (!plugin.getDataFolder().exists()) {
            System.out.println(plugin.getDataFolder().mkdir() ? "Default file " + name + " directory generated." : "ERROR generating default file " + name + " directory (maybe it already exists(?).");
        }
        File file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            try (InputStream inputStream = plugin.getResourceAsStream(name)) {
                Files.copy(inputStream, file.toPath());
            } catch (IOException e) {
                throw new RuntimeException("ERROR generating default file " + name + " for YouTubers-plugin: ", e);
            }
        }
    }
    public void generateDefaultFiles () {
        for (String fileName : fileNames) {
            generateDefaultFile(fileName);
        }
    }
    public void loadConfigs () {
        for (String configName  : configNames) {
            if (!configs.containsKey(configName)) {
                System.out.println(configName);
                generateDefaultFile(configName);
                Configuration config = null;
                try {
                    config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), configName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                configs.put(configName, config);
                System.out.println(config.getString("messages.new_stream.youtube"));
                System.out.println(config.getSection("video").getKeys());
            }
        }
    }
    private Configuration getConfigByName (String configName) {
        if (configs.keySet().contains(configName)) {
            return configs.get(configName);
        } else {
            return null;
        }
    }
    public Configuration cfg (String configName) {
        return getConfigByName(configName);
    }
}
