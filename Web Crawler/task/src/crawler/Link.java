package crawler;

public class Link {
    private final String url;
    private final String title;
    private final int depth;

    public Link(String url, String title, int depth) {
        this.url = url;
        this.title = title;
        this.depth = depth;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getDepth() {
        return depth;
    }
}
