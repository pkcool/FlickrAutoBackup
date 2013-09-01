package com.smaxll.apps.android.flickrautobackup;

import android.content.pm.ApplicationInfo;

import org.slf4j.LoggerFactory;

public class Config {
	static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Config.class);

	public static final int VERSION = getVersion();
	public static final long MAX_FILE_SIZE = 200 * 1024 * 1024L;
	public static final String VERSION_NAME = getVersionName();
	public static final String FULL_VERSION_NAME = VERSION_NAME + "-" + VERSION;

	private static Boolean DEBUG = null;

	public static boolean isDebug() {
		if (DEBUG == null) {
			DEBUG = (FlickrAutoBackup.getAppContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		}
		return DEBUG;
	}

	private static int getVersion() {
		try {
			return FlickrAutoBackup.getAppContext().getPackageManager().getPackageInfo(FlickrAutoBackup.getAppContext().getPackageName(), 0).versionCode;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return 0;
	}

	private static String getVersionName() {
		try {
			return "" + FlickrAutoBackup.getAppContext().getPackageManager().getPackageInfo(FlickrAutoBackup.getAppContext().getPackageName(), 0).versionName;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return "0";
	}
}
