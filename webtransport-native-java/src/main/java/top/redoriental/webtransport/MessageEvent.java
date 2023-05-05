package top.redoriental.webtransport;

public interface MessageEvent {

    void onMessage(byte[] message);

    void onClose();

}
