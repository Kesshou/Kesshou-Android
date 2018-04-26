package kesshou.android.daanx.models;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by yoyo930021 on 2017/1/16.
 */

public class Migration implements RealmMigration {
	@Override
	public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
		// During a migration, a DynamicRealm is exposed. A DynamicRealm is an untyped variant of a normal Realm, but
		// with the same object creation and query capabilities.
		// A DynamicRealm uses Strings instead of Class references because the Classes might not even exist or have been
		// renamed.

		// Access the Realm schema in order to create, modify or delete classes and their fields.
		RealmSchema schema = realm.getSchema();

		if(oldVersion==1){

			RealmObjectSchema settingSchema = schema.get("Setting");

			settingSchema.addField("locale",String.class)
					.transform(new RealmObjectSchema.Function() {
						@Override
						public void apply(DynamicRealmObject obj) {
							obj.set("locale","Auto");
						}
					});

			oldVersion++;
		}

		if(oldVersion==2){

			RealmObjectSchema cacheSchema = schema.get("NetWorkCache");

			cacheSchema.removeField("record").addField("record",String.class);

			oldVersion++;
		}
	}
}
