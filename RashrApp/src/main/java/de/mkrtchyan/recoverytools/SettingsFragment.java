package de.mkrtchyan.recoverytools;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.io.File;

import de.mkrtchyan.utils.Common;
import de.psdev.licensesdialog.LicensesDialog;

/**
 * Copyright (c) 2016 Aschot Mkrtchyan
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class SettingsFragment extends Fragment {

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public static void showChangelog(Context AppContext) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(AppContext);
        dialog.setTitle(R.string.changelog);
        WebView changes = new WebView(AppContext);
        changes.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        changes.setWebViewClient(new WebViewClient());
        changes.loadUrl(Const.CHANGELOG_URL);
        changes.clearCache(true);
        dialog.setView(changes);
        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final AppCompatCheckBox cbShowAds = (AppCompatCheckBox) root.findViewById(R.id.cbShowAds);
        final AppCompatCheckBox cbLog = (AppCompatCheckBox) root.findViewById(R.id.cbLog);
        final AppCompatCheckBox cbDarkUI = (AppCompatCheckBox) root.findViewById(R.id.cbDarkUI);
        final AppCompatCheckBox cbCheckUpdates = (AppCompatCheckBox) root.findViewById(R.id.cbCheckUpdates);
        final AppCompatCheckBox cbHideUpToDateHint = (AppCompatCheckBox) root.findViewById(R.id.cbShowUpToDateHints);
        final AppCompatCheckBox cbSkipSizeCheck = (AppCompatCheckBox) root.findViewById(R.id.cbSkipSizeChecking);
        final AppCompatCheckBox cbSkipValidate = (AppCompatCheckBox) root.findViewById(R.id.cbSkipValidateImages);
        final AppCompatButton bShowLogs = (AppCompatButton) root.findViewById(R.id.bShowLogs);
        final AppCompatButton bReport = (AppCompatButton) root.findViewById(R.id.bReport);
        final AppCompatButton bShowChangelog = (AppCompatButton) root.findViewById(R.id.bShowChangelog);
        final AppCompatButton bReset = (AppCompatButton) root.findViewById(R.id.bReset);
        final AppCompatButton bClearCache = (AppCompatButton) root.findViewById(R.id.bClearCache);
        final AppCompatButton bShowLicences = (AppCompatButton) root.findViewById(R.id.bShowLicenses);

        cbDarkUI.setChecked(Common.getBooleanPref(root.getContext(), Const.PREF_NAME,
                Const.PREF_KEY_DARK_UI));
        cbShowAds.setChecked(Common.getBooleanPref(root.getContext(), Const.PREF_NAME,
                Const.PREF_KEY_ADS));
        cbLog.setChecked(Common.getBooleanPref(root.getContext(), Const.PREF_NAME, Const.PREF_KEY_LOG));
        cbCheckUpdates.setChecked(Common.getBooleanPref(root.getContext(), Const.PREF_NAME,
                Const.PREF_KEY_CHECK_UPDATES));
        cbShowAds.setChecked(Common.getBooleanPref(root.getContext(), Const.PREF_NAME,
                Const.PREF_KEY_ADS));
        cbHideUpToDateHint.setChecked(Common.getBooleanPref(root.getContext(), Const.PREF_NAME,
                Const.PREF_KEY_HIDE_UPDATE_HINTS));
        cbSkipSizeCheck.setChecked(Common.getBooleanPref(root.getContext(), Const.PREF_NAME,
                Const.PREF_KEY_SKIP_SIZE_CHECK));
        cbSkipValidate.setChecked(Common.getBooleanPref(root.getContext(), Const.PREF_NAME, Const.PREF_KEY_SKIP_IMAGE_CHECK));

        cbDarkUI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                Common.setBooleanPref(view.getContext(), Const.PREF_NAME,
                        Const.PREF_KEY_DARK_UI, isChecked);
                RashrActivity.isDark = isChecked;
            }
        });
        cbLog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                Common.setBooleanPref(view.getContext(), Const.PREF_NAME, Const.PREF_KEY_LOG, isChecked);
                root.findViewById(R.id.bShowLogs)
                        .setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            }
        });
        cbCheckUpdates.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                Common.setBooleanPref(view.getContext(), Const.PREF_NAME,
                        Const.PREF_KEY_CHECK_UPDATES, isChecked);
            }
        });
        cbShowAds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                Common.setBooleanPref(view.getContext(), Const.PREF_NAME, Const.PREF_KEY_ADS,
                        isChecked);
            }
        });
        cbHideUpToDateHint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                Common.setBooleanPref(view.getContext(), Const.PREF_NAME,
                        Const.PREF_KEY_HIDE_UPDATE_HINTS, isChecked);
            }
        });
        cbSkipSizeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Common.setBooleanPref(buttonView.getContext(), Const.PREF_NAME,
                        Const.PREF_KEY_SKIP_SIZE_CHECK, isChecked);
            }
        });
        cbSkipValidate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Common.setBooleanPref(buttonView.getContext(), Const.PREF_NAME,
                        Const.PREF_KEY_SKIP_IMAGE_CHECK, isChecked);
            }
        });

        final RashrActivity activity = (RashrActivity) getActivity();

        bReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportDialog dialog = new ReportDialog(activity, "");
                dialog.show();
            }
        });

        bShowLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogs();
            }
        });

        bShowLogs.setVisibility(cbLog.isChecked() ? View.VISIBLE : View.INVISIBLE);

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                SharedPreferences.Editor editor = activity.getSharedPreferences(Const.PREF_NAME,
                        Context.MODE_PRIVATE).edit();
                editor.clear().commit();
                editor = activity.getSharedPreferences(Const.PREF_NAME,
                        Context.MODE_PRIVATE).edit();
                editor.clear().commit();
                RashrActivity.firstSetup(v.getContext());
            }
        });

        bClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder ConfirmationDialog = new AlertDialog.Builder(v.getContext());
                ConfirmationDialog.setTitle(R.string.warning);
                ConfirmationDialog.setMessage(R.string.delete_confirmation);
                ConfirmationDialog.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!Common.deleteFolder(Const.PathToCWM, false)
                                || !Common.deleteFolder(Const.PathToTWRP, false)
                                || !Common.deleteFolder(Const.PathToPhilz, false)
                                || !Common.deleteFolder(Const.PathToStockRecovery, false)
                                || !Common.deleteFolder(Const.PathToStockKernel, false)) {
                            Toast
                                    .makeText(getActivity(), R.string.delete_failed, Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast
                                    .makeText(getActivity(), R.string.files_deleted, Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
                ConfirmationDialog.setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ConfirmationDialog.show();
            }
        });
        bShowChangelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment.showChangelog(v.getContext());
            }
        });
        bShowLicences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LicensesDialog.Builder(v.getContext())
                        .setNotices(R.raw.licenses_notice)
                        .setIncludeOwnLicense(true)
                        .build()
                        .show();
            }
        });
        return root;
    }

    private void showLogs() {
        final Context context = getActivity();
        final AlertDialog.Builder LogDialog = new AlertDialog.Builder(context);
        LogDialog.setTitle(R.string.logs);
        final File logs = new File(context.getFilesDir(), Const.Logs);
        try {
            final String message = Common.fileContent(logs);
            LogDialog.setMessage(message);
            LogDialog.setNeutralButton(R.string.copy, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Common.copyToClipboard(context, message);
                }
            });
        } catch (Exception ignore) {
            Toast.makeText(context, R.string.no_logs, Toast.LENGTH_SHORT).show();
        }
        LogDialog.setNegativeButton(R.string.clear_logs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logs.delete();
            }
        });
        LogDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        LogDialog.show();
    }

}
