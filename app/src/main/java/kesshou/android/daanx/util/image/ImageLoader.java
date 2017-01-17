package kesshou.android.daanx.util.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import kesshou.android.daanx.R;

/**
 * Created by yoyoIU on 2016/9/20.
 */
class ImageLoader {

	private static final String ANDROID_RESOURCE = "android.resource://";
	private static final String FOREWARD_SLASH = "/";

	private static class ImageLoaderHolder {
		private static final ImageLoader INSTANCE = new ImageLoader();
	}

	private ImageLoader() {
	}

	public static ImageLoader getInstance() {
		return ImageLoaderHolder.INSTANCE;
	}

	public void displayImage(Activity activity, String url, ImageView imageView) {
		Glide
			.with(activity)
			.load(url)
			.fitCenter()
			.crossFade()
			.placeholder(R.mipmap.ic_launcher)
			.error(R.mipmap.ic_launcher)
			.listener(requestListener)
			.into(imageView);
	}


	public void displayImage(Activity activity, int resId, ImageView imageView) {
		Glide.with(activity)
			.load(resourceIdToUri(activity, resId))
			.centerCrop()
			.crossFade()
			.into(imageView);
	}

	public void displayCricleImage(Activity activity, String url, ImageView imageView) {
		Glide
			.with(activity)
			.load(url)
			.centerCrop()
			.placeholder(R.mipmap.ic_launcher)
			.error(R.mipmap.ic_launcher)
			.listener(requestListener)
			.transform(new CircleTransform(activity.getApplicationContext()))
			.crossFade()
			.into(imageView);
	}

	public void displayCricleImage(Activity activity, int resId, ImageView imageView) {
		Glide.with(activity)
			.load(resourceIdToUri(activity, resId))
			.centerCrop()
			.crossFade()
			.transform(new CircleTransform(activity.getApplicationContext()))
			.into(imageView);
	}

	private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
		@Override
		public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
			// todo log exception
			e.printStackTrace();
			// important to return false so the error placeholder can be placed
			return false;
		}

		@Override
		public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
			return false;
		}
	};

	private Uri resourceIdToUri(Activity activity, int resourceId) {
		return Uri.parse(ANDROID_RESOURCE + activity.getPackageName() + FOREWARD_SLASH + resourceId);
	}


	private static class CircleTransform extends BitmapTransformation {
		CircleTransform(Context context) {
			super(context);
		}

		@Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
			return circleCrop(pool, toTransform);
		}

		private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
			if (source == null) return null;

			int size = Math.min(source.getWidth(), source.getHeight());
			int x = (source.getWidth() - size) / 2;
			int y = (source.getHeight() - size) / 2;

			// TODO this could be acquired from the pool too
			Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

			Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
			if (result == null) {
				result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
			}

			Canvas canvas = new Canvas(result);
			Paint paint = new Paint();
			paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
			paint.setAntiAlias(true);
			float r = size / 2f;
			canvas.drawCircle(r, r, r, paint);
			return result;
		}

		@Override public String getId() {
			return getClass().getName();
		}
	}

}
