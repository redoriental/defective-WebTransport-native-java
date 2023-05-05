package top.redoriental.webtransport;

public class StreamEvent {
    private MessageEvent messageEvent;

    private final long streamId;

    private int streamType;//0 关闭 1开启

    public StreamEvent(long streamId) {
        this.streamId = streamId;
    }

    public MessageEvent getMessageEvent() {
        return messageEvent;
    }

    public void setMessageEvent(MessageEvent messageEvent) {
        this.messageEvent = messageEvent;
    }

    public void setStreamType(int type){
        streamType = type;
    }

    public void writeMessage(byte[] bytes){
        writeMessage(bytes, bytes.length);
    }

    public void writeMessage(byte[] bytes,int len){
        WebTransportFactory.writeMessage(streamId,bytes,len);
    }

    public void onClose(){
        messageEvent.onClose();
    }
}
