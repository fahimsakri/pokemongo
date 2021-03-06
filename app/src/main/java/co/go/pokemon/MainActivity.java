package co.go.pokemon;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.google.firebase.analytics.FirebaseAnalytics;

import co.go.pokemon.common.Common;
import co.go.pokemon.fragments.MainFragment;
import co.go.pokemon.fragments.NotificationFragment;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFireBaseAnalytics;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        if (Common.isNetworkAvailable(getBaseContext())) {
            fragmentTransaction.replace(R.id.activity_main, new MainFragment());
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.replace(R.id.activity_main, new NotificationFragment());
            fragmentTransaction.commit();
        }


    }

    public FirebaseAnalytics getAnalytics() {
        if (mFireBaseAnalytics == null)
            mFireBaseAnalytics = FirebaseAnalytics.getInstance(this);
        return mFireBaseAnalytics;
    }
}
