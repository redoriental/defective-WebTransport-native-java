package top.redoriental.webtransport;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class WebTransportOnEvent {

    public static Map<String,Class<? extends WebTransportHandle>> pathHandleMap = new ConcurrentHashMap<>(512);

    public static Map<Long,WebTransportHandle> sessionIdWebTransportHandleMap = new ConcurrentHashMap<>(512);

    public static Map<Long, StreamEvent> streamIdStreamEventMap = new ConcurrentHashMap<>(1024);

    public static Map<Long,Long> sessionIdStreamIdMap = new ConcurrentHashMap<>(512);

    public void onOpen(String path, String remoteIp, long sessionId) throws Exception{
        WebTransportHandle webTransportHandle = pathHandleMap.get(path).getConstructor().newInstance();
        sessionIdWebTransportHandleMap.put(sessionId,webTransportHandle);
        webTransportHandle.onSessionEvent(remoteIp);
    }

    public void onStream(long streamId, int streamType, long sessionId) {
        StreamEvent streamEvent = new StreamEvent(streamId);
        sessionIdWebTransportHandleMap.get(sessionId).onStreamEvent(streamEvent);
        streamIdStreamEventMap.put(streamId,streamEvent);
        sessionIdStreamIdMap.put(sessionId,streamId);
    }

    public void onMessage(long streamId, byte[] message) {
//        streamIdStreamEventMap.get(streamId).getMessageEvent().onMessage(message);
        WebTransportFactory.writeMessage(streamId,message,message.length);
    }


    public void onStreamClose(long streamId) {
        StreamEvent streamEvent = streamIdStreamEventMap.get(streamId);
        streamEvent.getMessageEvent().onClose();
        streamEvent.onClose();
        streamIdStreamEventMap.remove(streamId);
    }


    public void onSessionClose(long sessionId) {
        System.out.println("onSessionClose");
        sessionIdWebTransportHandleMap.get(sessionId).onCloseEvent();
        sessionIdWebTransportHandleMap.remove(sessionId);
    }

    public void putPathHandleClass(String path,Class<? extends WebTransportHandle> handleClass){
        pathHandleMap.put(path,handleClass);
    }
}
