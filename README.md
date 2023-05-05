# WebTransport-native-java
This will be the first Java project about webtransport

package com.example.demo;

import top.redoriental.webtransport.MessageEvent;
import top.redoriental.webtransport.StreamEvent;
import top.redoriental.webtransport.WebTransportHandle;

public class HandleImpl implements WebTransportHandle {
    @Override
    public void onSessionEvent(String remoteIp) {

    }

    @Override
    public void onStreamEvent(StreamEvent streamEvent) {
        streamEvent.setMessageEvent(new MessageEvent() {
            @Override
            public void onMessage(byte[] bytes) {
                streamEvent.writeMessage(bytes);
            }

            @Override
            public void onClose() {

            }
        });
    }

    @Override
    public void onCloseEvent() {

    }
}


//test
main(){
WebTransportEngine
                .create()
                .dllPath("E:\\demo\\src\\main\\resources\\tt.dll")
                .pathHandle("/path", HandleImpl.class)
                .setPort(10001)
                .ssl("E:\\demo\\src\\main\\resources\\redoriental.top8.key","E:\\demo\\src\\main\\resources\\redoriental.top.crt")
                .init();
                }
