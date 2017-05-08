package ru.progrm_jarvis.contentmakers;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import ru.progrm_jarvis.contentmakers.config.ConfigManager;
import ru.progrm_jarvis.contentmakers.custom.platform.PlatformManager;
import ru.progrm_jarvis.contentmakers.data.streams.StreamManager;
import ru.progrm_jarvis.contentmakers.listener.pluginmessage.ContentMakersChannelListener;
import ru.progrm_jarvis.contentmakers.listener.proxy.ContentMakersEventListener;

/**
 * Created by PROgrm_JARvis on 26.03.2017.
 */
public class ContentMakersPlugin extends Plugin {
    private static ContentMakersPlugin contentMakersPlugin;
    public static ContentMakersPlugin getInstance () {
        return contentMakersPlugin;
    }

    @Override
    public void onEnable() {
        contentMakersPlugin = this;

        getProxy().getPluginManager().registerListener(this, new ContentMakersEventListener());
        enableMessagingChannels();
        enableManagers();
    }

    //Переменные
    private ConfigManager configManager;
    private StreamManager streamManager;
    private PlatformManager platformManager;

    private void enableMessagingChannels () {
        BungeeCord.getInstance().getPluginManager().registerListener(this, new ContentMakersChannelListener());
        BungeeCord.getInstance().registerChannel("Return");
        BungeeCord.getInstance().registerChannels();
    }
    private void enableManagers () {
        configManager = new ConfigManager(this);
        streamManager = new StreamManager(this);
        platformManager = new PlatformManager(this);

        configManager.generateDefaultFiles();
        configManager.loadConfigs();

        streamManager.loadConfigurableDataFromConfig();

        platformManager.loadPlatforms();
    }

    public ConfigManager getConfigManager () {
        return configManager;
    }
    public StreamManager getStreamManager() {
        return streamManager;
    }
}
