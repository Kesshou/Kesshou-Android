package kesshou.android.team.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yoyoIU on 2016/12/6.
 */

public class Strings extends RealmObject {

	@PrimaryKey
	public int index;

	public String string;
}
