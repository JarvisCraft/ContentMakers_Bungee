package ru.progrm_jarvis.contentmakers.custom.platform;

/**
 * Created by PROgrm_JARvis on 14.04.2017.
 */
public class StreamPlatform {
    private String name;
    private String viewersNumUrl;
    private String viewersNumPlaceholder;
    private int viewersNumFrequency;
    public StreamPlatform (String name, String viewersNumUrl, String viewersNumPlaceholder, int viewersNumFrequency) {
        setName(name);
        setViewersNumUrl(viewersNumUrl);
        setViewersNumPlaceholder(viewersNumPlaceholder);
        setViewersNumFrequency(viewersNumFrequency);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setViewersNumUrl(String viewersNumUrl) {
        this.viewersNumUrl = viewersNumUrl;
    }
    public void setViewersNumPlaceholder(String viewersNumPlaceholder) {
        this.viewersNumPlaceholder = viewersNumPlaceholder;
    }
    public void setViewersNumFrequency(int viewersNumFrequency) {
        this.viewersNumFrequency = viewersNumFrequency;
    }

    public String getName() {
        return name;
    }
    public String getViewersNumUrl() {
        return viewersNumUrl;
    }
    public String getViewersNumPlaceholder() {
        return viewersNumPlaceholder;
    }
    public int getViewersNumFrequency() {
        return viewersNumFrequency;
    }

    @Override
    public String toString() {
        return getName().concat(" ").concat(getViewersNumUrl()).concat(" ").concat(getViewersNumPlaceholder()).concat(" ").concat(String.valueOf(getViewersNumFrequency()));
    }
}
