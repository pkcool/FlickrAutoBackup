package com.smaxll.apps.android.flickrautobackup;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.smaxll.apps.android.flickrautobackup.FlickrApi.PRIVACY;

import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class MyBroadcastReceiver extends BroadcastReceiver {
	static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MyBroadcastReceiver.class);

	@Override
	public void onReceive(Context context, Intent intent) {
		LOG.info("intent : " + intent);
		if ("com.smaxll.intent.CANCEL_UPLOAD".equals(intent.getAction())) {
//			Mixpanel.track("Cancel in notification");
			LOG.debug( "canceling uploads");
			UploadService.cancel(true);
		} else if ("com.smaxll.intent.SHARE_PHOTO".equals(intent.getAction())) {
//			Mixpanel.track("Share in notification");
			LOG.debug( "share intent : " + intent);
			int imageId = intent.getIntExtra("imageId", -1);
			if (imageId > 0) {
				Media image = Utils.getImage(imageId);
				final String photoId = FlickrApi.getPhotoId(image);
				if (photoId != null) {
					Toast.makeText(context, "Sharing photo", Toast.LENGTH_LONG).show();
					String url = FlickrApi.getShortUrl(photoId);
					Intent sharingIntent = new Intent(Intent.ACTION_SEND);
					sharingIntent.setType("text/plain");
					sharingIntent.putExtra(Intent.EXTRA_TEXT, url);
					Intent createChooser = Intent.createChooser(sharingIntent, "Share via");
					createChooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(createChooser);
					LOG.debug( "url : " + url);
					FlickrApi.setPrivacy(PRIVACY.PUBLIC, Arrays.asList(photoId));
					NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
					mNotificationManager.cancelAll();
				}
			}
		} else {
			LOG.debug( "action : " + intent.getAction());
			context.startService(new Intent(context, UploadService.class));
			UploadService.wake();
		}
	}
}
