public class URLDepthPair {
    public static final String URL_PREFIX = "http://";
    public static final String URL_PREFIX_S = "https://";

    private String URL_Address;
    private int depth;

    URLDepthPair(String URL_Address, int depth) {
        this.URL_Address = URL_Address;
        this.depth = depth;
    }

    public String getStringFormat(){
        return "URL = " + URL_Address + ", depth = " + depth;
    }


    public void setURL_Address(String URL_Address){
        this.URL_Address = URL_Address;
    }
    public void setDepth(int depth){
        this.depth = depth;
    }
    public int getDepth(){
        return depth;
    }
    public String getURL_Address(){
        return URL_Address;
    }
}
