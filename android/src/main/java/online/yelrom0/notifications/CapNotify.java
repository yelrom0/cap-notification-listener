package online.yelrom0.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CapNotify {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    private List<ProcessedPackageInfo> listAllApps(PackageManager pm) {
        List<ApplicationInfo> appInfos = new ArrayList<ApplicationInfo>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            appInfos = pm.getInstalledApplications(PackageManager.ApplicationInfoFlags.of(0L));
        } else {
            appInfos = pm.getInstalledApplications(0);
        }

        List<ProcessedPackageInfo> packageInfos = new ArrayList<ProcessedPackageInfo>();
        for (ApplicationInfo applicationInfo : appInfos) {
            try {
                Resources resources = pm.getResourcesForApplication(applicationInfo.packageName);
                String packageName = applicationInfo.packageName;
                String appName = pm.getApplicationLabel(applicationInfo).toString();
                Drawable iconDrawable = pm.getDrawable(packageName, 0, applicationInfo);

                packageInfos.add(new ProcessedPackageInfo(packageName, appName, iconDrawable));

            } catch (NameNotFoundException e) {
                Log.e("listNonSystemApps", String.format("Error getting info for app %s", e));
            }
        }

        return packageInfos;
    }

    private List<ProcessedPackageInfo> listNonSystemApps(PackageManager pm) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolvedInfos = new ArrayList<ResolveInfo>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            resolvedInfos = pm.queryIntentActivities(
                    mainIntent,
                    PackageManager.ResolveInfoFlags.of(0L)
            );
        } else {
            resolvedInfos = pm.queryIntentActivities(mainIntent, 0);
        }

        List<ProcessedPackageInfo> packageInfos = new ArrayList<ProcessedPackageInfo>();
        for (ResolveInfo resolveInfo : resolvedInfos) {
            try {
                Resources resources = pm.getResourcesForApplication(resolveInfo.activityInfo.applicationInfo);

                String appName = "";
                if (resolveInfo.activityInfo.labelRes != 0) {
                    // getting proper label from resources
                    appName = resources.getString(resolveInfo.activityInfo.labelRes);
                } else {
                    // getting it out of app info - equivalent to context.packageManager.getApplicationInfo
                    appName = resolveInfo.activityInfo.applicationInfo.loadLabel(pm).toString();
                }

                String packageName = resolveInfo.activityInfo.packageName;
                Drawable iconDrawable = resolveInfo.activityInfo.loadIcon(pm);
                packageInfos.add(new ProcessedPackageInfo(packageName, appName, iconDrawable));
            } catch (NameNotFoundException e) {
                Log.e("listNonSystemApps", String.format("Error getting info for app %s", e));
            }

        }

        return packageInfos;
    }

    public List<ProcessedPackageInfo> listApps(Context ctx, Boolean showSystemApps) {

        if (showSystemApps == null) {
            showSystemApps = false;
        }

        PackageManager packageManager = ctx.getPackageManager();

        if (!showSystemApps) {
            return listNonSystemApps(packageManager);
        }

        return listAllApps(packageManager);
    }
}
