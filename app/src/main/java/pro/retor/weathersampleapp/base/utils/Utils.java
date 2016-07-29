package pro.retor.weathersampleapp.base.utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by retor on 16.03.2016.
 */
public class Utils {
/*    public static AlertDialog errorShow(Activity activity) {
        return new AlertDialog.Builder(activity).setTitle("Error")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNeutralButton("Ok", (dialog, which) -> {
                    dialog.dismiss();
                }).create();
    }*/

    public static ProgressDialog progressShow(Activity activity) {
        ProgressDialog pd = new ProgressDialog(activity);
        pd.setMessage("Loading...");
        pd.setCanceledOnTouchOutside(false);
        pd.setCancelable(false);
        return pd;
    }
}
