package kesshou.android.daanx.util.network;

/**
 * Created by lienching on 8/20/16.
 */
public class Config {

    private enum Env {

        PROD("kesshou.dacsc.club"),
        DEV("dev.dacsc.club"); // PROD: Production website ,DEV: Developement website


        public final String host;

        Env(String host) {
            this.host = host;
        }
    }

    // API Version
    private final static String VERSION = "1";

    // Using Env
    private final static Env env = Env.PROD;

    public static String getAPIPath() {
        return getAPIPath( Config.env, Config.VERSION);
    }

    private static String getAPIPath( Env env, String version ) {
        return "https://"+env.host+"/v"+version+"/";
    }

}
