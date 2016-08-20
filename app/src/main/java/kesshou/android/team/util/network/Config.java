package kesshou.android.team.util.network;

/**
 * Created by lienching on 8/20/16.
 */
public class Config {

    public enum Env {

        PROD("kesshou.org"),
        DEV("dev.kesshou.org"); // PROD: Production website ,DEV: Developement website


        public final String host;

        Env(String host) {
            this.host = host;
        }
    }

    // API Version
    public final static double VERSION = 0.1;

    // Using Env
    public final static Env env = Env.DEV;

    public static String getAPIPath() {
        return getAPIPath( Config.env, VERSION);
    }

    private static String getAPIPath( Env env, double version ) {
        return String.format("https://%1$s/api%2$f", env.host, version);
    }

}
