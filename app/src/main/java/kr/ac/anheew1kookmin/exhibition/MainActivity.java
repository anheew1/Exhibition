package kr.ac.anheew1kookmin.exhibition;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_artwork) {

                }
                if (tabId == R.id.tab_place) {

                }
                if (tabId == R.id.tab_upload) {

                }
                if (tabId == R.id.tab_message) {

                }
                if (tabId == R.id.tab_mypage) {

                }

            }
        });
    }
}
