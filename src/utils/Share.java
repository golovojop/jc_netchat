package utils;

import java.io.Closeable;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Share {
    public static final int PORT = 9191;
    public static final String HOST = "127.0.0.1";

    public static final String REGEX_AUTH = "^/auth\\s.+";
    public static final String SEPARATOR = "@@";


    public static final String PROT_MSG_AUTH_OK = "/authok";
    public static final String PROT_MSG_AUTH_ERROR = "Login or password incorrect";
    public static final String PROT_MSG_AUTH = "/auth";
    public static final String PROT_MSG_END = "/end";
    public static final String PROT_MSG_SERVER_CLOSED = "/serverClosed";

    /**
     * Цвета оформления сообщений
     */
    public static final String[] colors = {"#efe4b0;", "#d2d2d2;", "#99d9ea;", "#7092be;", "#ffaec9;", "#b5e61d;", "#ffc90e;"};

    public static String currentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static void closeResources(Closeable... obj){
        for (Closeable o: obj) {
            try {
                if(o != null) o.close();
            } catch (Exception e) {e.printStackTrace();}
        }
    }

}
