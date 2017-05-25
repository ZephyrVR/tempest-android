package com.texasgamer.zephyr.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.LoginEvent;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.texasgamer.zephyr.Constants;
import com.texasgamer.zephyr.R;

import java.util.UUID;

import io.fabric.sdk.android.Fabric;

public class MetricsManager {

    private final String TAG = this.getClass().getSimpleName();

    private FirebaseAnalytics mFirebaseAnalytics;
    private Context mContext;

    public MetricsManager(Context context) {
        mContext = context;

        String uuid = getUuid();

        if (Constants.FIREBASE_ANALYTICS_ENABLED) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
            mFirebaseAnalytics.setUserId(uuid);
        }


        if (Constants.FABRIC_CRASHLYITCS_ENABLED) {
            if (!Fabric.isInitialized()) {
                Fabric.with(context, new Crashlytics());
            }
            Crashlytics.setUserIdentifier(uuid);
        }
    }

    public void logEvent(@StringRes int iri, Bundle extras) {
        if (isUsageDataEnabled()) {
            Log.v(TAG, mContext.getString(iri) + ": " + (extras != null ? extras.toString() : "null"));
            firebaseEvent(iri, extras);
            fabricEvent(iri, extras);
        }
    }

    public void resetUuid() {
        Log.i(TAG, "Resetting UUID.");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        preferences.edit().putString(mContext.getString(R.string.pref_uuid), "").apply();
    }

    private void firebaseEvent(@StringRes int iri, Bundle extras) {
        if (Constants.FIREBASE_ANALYTICS_ENABLED) {
            mFirebaseAnalytics.logEvent(mContext.getString(iri), extras);
        }
    }

    private void fabricEvent(@StringRes int iri, Bundle extras) {
        if (!Constants.FABRIC_ANSWERS_ENABLED) {
            return;
        }

        if (!Fabric.isInitialized()) {
            Fabric.with(mContext, new Crashlytics());
        }

        CustomEvent event = new CustomEvent(mContext.getString(iri));
        if(extras != null) {
            for (String key : extras.keySet()) {
                event.putCustomAttribute(key, extras.get(key).toString());
            }
        }

        Answers.getInstance().logCustom(event);
    }

    private String getUuid() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String uuid = preferences.getString(mContext.getString(R.string.pref_uuid), "");

        if (uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString();
            Log.i(TAG, "Generating new UUID: " + uuid);
            preferences.edit().putString(mContext.getString(R.string.pref_uuid), uuid).apply();
        }

        return uuid;
    }

    private boolean isUsageDataEnabled() {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getBoolean(mContext.getString(R.string.pref_usage_data), true);
    }
}
