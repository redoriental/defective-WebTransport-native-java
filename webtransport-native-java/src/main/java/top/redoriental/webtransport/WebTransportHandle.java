package top.redoriental.webtransport;

public interface WebTransportHandle {

    void onSessionEvent(String remoteIpPort);

    void onStreamEvent(StreamEvent streamEvent);

    void onCloseEvent();
}
