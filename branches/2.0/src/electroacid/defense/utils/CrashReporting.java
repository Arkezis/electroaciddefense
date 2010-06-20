package electroacid.defense.utils;

import org.acra.CrashReportingApplication;

import electroacid.defense.R;
import electroacid.defense.R.string;

import android.os.Bundle;

public class CrashReporting extends CrashReportingApplication {

	@Override
	public String getFormId() {
		//return "dFo4a0VCcnJyWFh4SXhvSThUdURMOUE6MQ";//for apk in market
		return "";//for debug in eclipse
	}

	@Override
	public Bundle getCrashResources() {
		Bundle result = new Bundle();
		result.putInt(RES_TOAST_TEXT, R.string.crash_toast_text);
		return result;
	}

}
