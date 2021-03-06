package co.go.pokemon.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.List;

import co.go.pokemon.R;
import co.go.pokemon.model.Offers;
import co.go.pokemon.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fahim on 7/16/16.
 */

public class Common {

    public static final String APPSFLYER_DEV_KEY = "zD9EQSqJtNMADxmqC3AiNQ";
    public static final String DEEPLINK_URL_BANNER = "http://go.fyndi.ng/iSyg/9HWwLNte3u";
    public static final String DEEPLINK_URL_LIST = "http://go.fyndi.ng/iSyg/uj5uKbwe3u";
    public static final String REFER_BANNER = "http://cdn3.gofynd.com/inapp_banners/fynd_credits_banner_small.jpg";
    //Bottom Banner - http://go.fyndi.ng/iSyg/pLiBwWFe3u

    public interface OfferService {
        @GET("api/v2/web/inventory/get-sale/")
        Call<Offers> listOffers();
    }

    public interface PokemonService {
        @GET("https://d2gwzh2hqto90s.cloudfront.net/metadata/metadata.json")
        Call<List<Pokemon>> listPokemons();
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int DIME_TYPE_WIDTH = 1;
    public static int DIME_TYPE_HEIGHT = 2;
    public static int deviceWidth = 0;
    private static int deviceHeight = 0;

    public static int getDeviceSize(int type, Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
        }
        deviceWidth = size.x;
        deviceHeight = size.y;
        if (type == DIME_TYPE_WIDTH) {
            return deviceWidth;
        } else {
            return deviceHeight;
        }
    }

    public static void openDeepLink(Context context, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    public static int getDeviceWidth(Context context) {
        if (deviceWidth > 0)
            return deviceWidth;
        return getDeviceSize(DIME_TYPE_WIDTH, context);
    }

    public static int getDeviceHeight(Context context) {
        if (deviceHeight > 0)
            return deviceHeight;
        return getDeviceSize(DIME_TYPE_HEIGHT, context);
    }

    public static String getDeviceAndriod_Id(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static void hideKeyboard(Context ctx) {
        if (null == ctx)
            return;
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void noNetwork(final Context context) {
        Toast.makeText(context, context.getString(R.string.error_no_internet), Toast.LENGTH_LONG).show();
    }

    public static void showDefaultError(final Context context) {
        Toast.makeText(context, context.getString(R.string.error_default_error_text), Toast.LENGTH_LONG).show();
    }
}
