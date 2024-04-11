package online.yelrom0.notifications;

import android.graphics.drawable.Drawable;

public class ProcessedPackageInfo {
    public final String packageName;
    public final String name;
    public final Drawable icon;

    public ProcessedPackageInfo(String packageName, String name, Drawable icon) {
        this.packageName = packageName;
        this.name = name;
        this.icon = icon;
    }


}
