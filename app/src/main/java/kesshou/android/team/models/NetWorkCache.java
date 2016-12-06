package kesshou.android.team.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by yoyoIU on 2016/11/28.
 */

public class NetWorkCache extends RealmObject {
	public String sectionalexamscore1;
	public String sectionalexamscore2;
	public String timeTable;
	public String record;
	public String prize;
	public RealmList<Strings> histroyScore1;
	public RealmList<Strings> histroyScore2;
}
