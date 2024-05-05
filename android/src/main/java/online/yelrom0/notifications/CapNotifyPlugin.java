package online.yelrom0.notifications;

import android.content.Context;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.ArrayList;
import java.util.List;

@CapacitorPlugin(name = "CapNotify")
public class CapNotifyPlugin extends Plugin {

    private final CapNotify implementation = new CapNotify();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void listApps(PluginCall call) {
        Boolean showSystemApps = call.getBoolean("showSystemApps");
        Context ctx = this.getActivity().getApplicationContext();

        List<ProcessedPackageInfo> infoList = implementation.listApps(ctx, showSystemApps);
        List<JSObject> appInfoList = new ArrayList<>();


        for (ProcessedPackageInfo info : infoList) {
            JSObject appInfo = new JSObject();
            appInfo.put("packageName", info.packageName);
            appInfo.put("name", info.name);
            // below is the template for the converting the icon to bmp
            // final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            // below is my attempt to get the icon
            // final Bitmap bmp = Bitmap.createBitmap(info.icon.getIntrinsicWidth(), info.icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            // appInfo.put("icon", bmp);
            // below is the old line
            appInfo.put("icon", info.icon);
            appInfoList.add(appInfo);
        }

        JSObject ret = new JSObject();
        ret.put("appList", appInfoList);
        call.resolve(ret);
    }
}
