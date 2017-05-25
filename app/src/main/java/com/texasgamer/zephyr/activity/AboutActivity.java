package com.texasgamer.zephyr.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.texasgamer.zephyr.Constants;
import com.texasgamer.zephyr.R;
import com.texasgamer.zephyr.manager.MetricsManager;

public class AboutActivity extends MaterialAboutActivity {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        new MetricsManager(this).logEvent(R.string.analytics_tap_about_prefs, null);
    }

    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull final Context context) {
        MaterialAboutList.Builder listBuilder = new MaterialAboutList.Builder();
        MaterialAboutCard.Builder titleCardBuilder = new MaterialAboutCard.Builder();
        titleCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(R.string.app_name)
                .icon(R.mipmap.ic_launcher)
                .build());

        try {
            titleCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(context,
                    new IconicsDrawable(context)
                            .icon(GoogleMaterial.Icon.gmd_info_outline)
                            .color(ContextCompat.getColor(context, R.color.colorPrimary))
                            .sizeDp(18),
                    "Version",
                    false));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        titleCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Licenses")
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_book)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18))
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent(context, LicensesActivity.class);
                        context.startActivity(intent);
                    }
                })
                .build());
        listBuilder.addCard(titleCardBuilder.build());

        MaterialAboutCard.Builder linksCardBuilder = new MaterialAboutCard.Builder();
        linksCardBuilder.title(R.string.about_links_title);
        linksCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_website)
                .subText(Constants.ZEPHYR_BASE_WEB_URL)
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_web)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse(Constants.ZEPHYR_BASE_WEB_URL)))
                .build());

        linksCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.about_github)
                .icon(new IconicsDrawable(context)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(context, Uri.parse("https://github.com/ZephyrVR")))
                .build());

        linksCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(context,
                new IconicsDrawable(context)
                        .icon(CommunityMaterial.Icon.cmd_star)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18),
                getString(R.string.about_rate),
                null
        ));

        listBuilder.addCard(linksCardBuilder.build());

        return listBuilder.build();
    }

    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.about_title);
    }
}
