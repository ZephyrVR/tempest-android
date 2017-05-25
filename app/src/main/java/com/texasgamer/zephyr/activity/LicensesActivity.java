package com.texasgamer.zephyr.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.OpenSourceLicense;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.texasgamer.zephyr.R;

public class LicensesActivity extends MaterialAboutActivity {

    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        MaterialAboutList.Builder listBuilder = new MaterialAboutList.Builder();
        listBuilder.addCard(ConvenienceBuilder.createLicenseCard(context,
                new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                "Android-Iconics", "2016", "Mike Penz",
                OpenSourceLicense.APACHE_2));

        listBuilder.addCard(ConvenienceBuilder.createLicenseCard(context,
                new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                "Community Material Typeface", "2016", "Mike Penz",
                OpenSourceLicense.APACHE_2));

        listBuilder.addCard(ConvenienceBuilder.createLicenseCard(context,
                new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                "Google Material Typeface", "2016", "Mike Penz",
                OpenSourceLicense.APACHE_2));

        listBuilder.addCard(ConvenienceBuilder.createLicenseCard(context,
                new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                "material-about-library", "2016", "Daniel Stone",
                OpenSourceLicense.APACHE_2));

        listBuilder.addCard(ConvenienceBuilder.createLicenseCard(context,
                new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                "OkHttp", "2016", "Square, Inc.",
                OpenSourceLicense.APACHE_2));

        listBuilder.addCard(ConvenienceBuilder.createLicenseCard(context,
                new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                "Okio", "2013", "Square, Inc.",
                OpenSourceLicense.APACHE_2));

        listBuilder.addCard(ConvenienceBuilder.createLicenseCard(context,
                new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                "Picasso", "2013", "Square, Inc.",
                OpenSourceLicense.APACHE_2));

        listBuilder.addCard(ConvenienceBuilder.createLicenseCard(context,
                new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                "socket.io-client-java", "2013", "Naoyuki Kanezawa",
                OpenSourceLicense.MIT));

        return listBuilder.build();
    }

    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.activity_licenses_title);
    }
}
