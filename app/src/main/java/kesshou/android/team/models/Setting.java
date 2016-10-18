package kesshou.android.team.models;

import io.realm.RealmObject;

/**
 * Created by yoyoIU on 2016/9/17.
 */
public class Setting extends RealmObject {
	public boolean logined;
	public String token;
	public String nick;
	public String email;
	public String password;
	public String usr_group;
}
