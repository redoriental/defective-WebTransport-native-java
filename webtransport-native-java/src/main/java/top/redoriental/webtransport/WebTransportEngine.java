package top.redoriental.webtransport;

import static top.redoriental.webtransport.WebTransportOnEvent.pathHandleMap;

public class WebTransportEngine {

    private int port;

    private String sslKey;

    private String sslCert;

    private String dllPath;


    private WebTransportEngine(){}

    public static WebTransportEngine create(){

        return new WebTransportEngine();
    }

    public WebTransportEngine setPort(int port){
        this.port = port;
        return this;
    }


    public WebTransportEngine pathHandle(String path, Class<? extends WebTransportHandle> handleClass){
        pathHandleMap.put(path, handleClass);
        return this;
    }

    public WebTransportEngine ssl(String sslKey,String sslCert){
        this.sslKey = sslKey;
        this.sslCert = sslCert;
        return this;
    }

    public WebTransportEngine dllPath(String dllPath){
        this.dllPath = dllPath;
        return this;
    }


    public void init(){
        StringBuilder urls = new StringBuilder();
        pathHandleMap.keySet().forEach(path->{
            urls.append(path);
            urls.append(",");
        });
        int length = urls.length();
        if (length > 0) {
            urls.deleteCharAt(length - 1);
        }
        WebTransportFactory.createServer(dllPath,port,sslKey,sslCert,urls.toString());
    }

}
