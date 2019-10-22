package com.example.sms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SplashActivity extends AppCompatActivity {

    /**
     * The time that the splash screen will be on the screen in milliseconds.
     */
    private int timeoutMillis = 3000;

    /**
     * The time when this {@link SplashActivity} was created.
     */
    private long startTimeMillis = 0;

    /**
     * The code used when requesting permissions
     */
    private int PERMISSIONS_REQUEST = 1;
    /**
     * The code used when requesting permissions
     */
    private int SETTINGS_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        /**
         * Save the start time of this Activity, which will be used to determine
         * when the splash screen should timeout.
         */
        startTimeMillis = System.currentTimeMillis();

        /**
         * On a post-Android 6.0 devices, check if the required permissions have
         * been granted.
         */
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        } else {
            getNextActivityClass();
        }


    }

    /**
     * See if we now have all of the required dangerous permissions. Otherwise,
     * tell the user that they cannot continue without granting the permissions,
     * and then request the permissions again.
     */
    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST) {
            checkPermissions();
        }
    }

    /**
     * After the timeout, start the {@link SplashActivity} as specified by
     * {@link #getNextActivityClass()}, and remove the splash screen from the
     * backstack. Also, we can change the message shown to the user to tell them
     * we now have the requisite permissions.
     */
    private void startNextActivity() {
        long delayMillis = getTimeoutMillis() - (System.currentTimeMillis() - startTimeMillis);
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, getNextActivityClass()));
                finish();
            }
        }, delayMillis);
    }

    /**
     * Get the time (in milliseconds) that the splash screen will be on the
     * screen before starting the {@link SplashActivity} who's class is returned by
     * {@link #getNextActivityClass()}.
     */
    public int getTimeoutMillis() {
        return timeoutMillis;
    }

    /**
     * Get the {@link SplashActivity} to start when the splash screen times out.
     */
    @SuppressWarnings("rawtypes")
    public Class getNextActivityClass() {
       return MyTabLayout.class;
    }

    /**
     * Get the list of required permissions by searching the manifest. If you
     * don't think the default behavior is working, then you could try
     * overriding this function to return something like:
     *
     * <pre>
     * <code>
     * return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
     * </code>
     * </pre>
     */
    public String[] getRequiredPermissions() {
        String[] permissions = null;
        try {
            permissions = getPackageManager().getPackageInfo(getPackageName(),
                    PackageManager.GET_PERMISSIONS).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (permissions == null) {
            return new String[0];
        } else {
            return permissions.clone();
        }
    }

    /**
     * Check if the required permissions have been granted, and
     * {@link #startNextActivity()} if they have. Otherwise
     * {@link #requestPermissions(String[], int)}.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        String[] ungrantedPermissions = requiredPermissionsStillNeeded();
        if (ungrantedPermissions.length == 0) {
            startNextActivity();
        } else {

            if (ungrantedPermissions.length == 1 && ungrantedPermissions[0].equalsIgnoreCase("android.permission.ANSWER_PHONE_CALLS")) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    startNextActivity();
                    return;
                }
            }


            for (String permission : ungrantedPermissions) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, permission)) {
                    requestPermissions(ungrantedPermissions, PERMISSIONS_REQUEST);
                    break;
                } else {
                    showDialogOK("Permissions are required for this app," +
                                    " please unable the permissions in order to use the app.",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            intent.setData(uri);
                                            startActivityForResult(intent, SETTINGS_REQUEST);
                                            break;
                                        case DialogInterface.BUTTON_NEGATIVE:
                                            // proceed with logic by disabling the related features or quit the app.
                                            SplashActivity.this.finish();
                                            break;
                                    }
                                }
                            });
                }
            }

        }
    }

    /**
     * A overridden method to capture the event when user has return from Settings screen.
     * Over here checkPermissions method will called to check if user has granted all permissions are not.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQUEST) {
            checkPermissions();
        }
    }

    /**
     * A custom method to generate a dialog to ask user to unable permissions in setting screen.
     */
    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("Settings", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    /**
     * Convert the array of required permissions to a {@link Set} to remove
     * redundant elements. Then remove already granted permissions, and return
     * an array of ungranted permissions.
     */
    @TargetApi(23)
    private String[] requiredPermissionsStillNeeded() {

        Set<String> permissions = new HashSet<String>();
        for (String permission : getRequiredPermissions()) {
            permissions.add(permission);
        }
        for (Iterator<String> i = permissions.iterator(); i.hasNext(); ) {
            String permission = i.next();
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                Log.d(SplashActivity.class.getSimpleName(),
                        "Permission: " + permission + " already granted.");
                i.remove();
            } else {
                Log.d(SplashActivity.class.getSimpleName(),
                        "Permission: " + permission + " not yet granted.");
            }
        }
        return permissions.toArray(new String[permissions.size()]);
    }


}
