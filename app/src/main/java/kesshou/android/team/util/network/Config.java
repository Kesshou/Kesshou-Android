package kesshou.android.team.util.network;

/**
 * Created by lienching on 8/20/16.
 */
public class Config {

    public enum Env {

        PROD("kesshou.org"),
        DEV("dev.dacsc.club"); // PROD: Production website ,DEV: Developement website


        public final String host;

        Env(String host) {
            this.host = host;
        }
    }

    // API Version
    private final static String VERSION = "1";

    // Using Env
    private final static Env env = Env.DEV;

    public static String getAPIPath() {
        return getAPIPath( Config.env, Config.VERSION);
    }

    private static String getAPIPath( Env env, String version ) {
        return "https://"+env.host+"/v"+version+"/";
    }

}
