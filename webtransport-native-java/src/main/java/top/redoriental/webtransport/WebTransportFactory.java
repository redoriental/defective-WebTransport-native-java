package top.redoriental.webtransport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class WebTransportFactory {

    static {
        load("libWebTransport_c_native_java.dll");
    }

    public static void load(String dll){
        try {
            InputStream in = WebTransportFactory.class.getClassLoader().getResourceAsStream(dll);
            File fileOut = File.createTempFile("libMyNativeLibrary", ".dll");
            FileOutputStream out = new FileOutputStream(fileOut);

            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            while (len != -1) {
                out.write(buffer, 0, len);
                len = in.read(buffer);
            }
            out.close();
            in.close();

            System.load(fileOut.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected static native void createServer(String dllpath,int port, String sslKey, String sslCert, String urls);

    protected static native void writeMessage(long streamId,byte[] message,int len);


}
