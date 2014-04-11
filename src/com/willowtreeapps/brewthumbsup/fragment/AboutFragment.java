package com.willowtreeapps.brewthumbsup.fragment;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockDialogFragment;
import com.willowtreeapps.brewthumbsup.R;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import roboguice.inject.InjectView;

/**
 * User: derek Date: 11/27/13 Time: 3:37 PM
 */
public class AboutFragment extends RoboSherlockDialogFragment {

    private static final String ATTRIBUTION_URL
            = "http://www.willowtreeapps.com/?utm_source=com.willowtreeapps.brewthumbsup&utm_medium=android&utm_campaign=attribution";

    @InjectView(R.id.attribution) private ImageView attribution;
    @InjectView(R.id.version_text) private TextView versionText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle(R.string.about_brew_thumbs_up);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String appVersionName = "x.x";
        int appVersionCode = 0;
        try {
            appVersionName = getActivity().getApplication().getPackageManager()
                    .getPackageInfo(getActivity().getApplication().getPackageName(), 0).versionName;
            appVersionCode = getActivity().getApplication().getPackageManager()
                    .getPackageInfo(getActivity().getApplication().getPackageName(), 0).versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            //Failed
        }
        versionText.setText("\nVersion: " + appVersionName + " b" + appVersionCode + "\n");
        versionText.append(getString(R.string.popcorn_attribution) + "\n");
        versionText.append(getString(R.string.tomato_attribution) + "\n");

        attribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attributionIntent = new Intent(Intent.ACTION_VIEW);
                attributionIntent.setData(Uri.parse(ATTRIBUTION_URL));
                startActivity(attributionIntent);
            }
        });
    }
}
