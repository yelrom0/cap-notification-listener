package online.yelrom0.notifications;

import android.content.Context;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapNotify")
public class CapNotifyPlugin extends Plugin {

    private CapNotify implementation = new CapNotify();

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
        JSObject ret = new JSObject();
        ret.put("appList", implementation.listApps(ctx, showSystemApps));
        call.resolve(ret);
    }
}
