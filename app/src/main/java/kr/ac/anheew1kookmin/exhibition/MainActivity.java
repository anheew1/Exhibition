package kr.ac.anheew1kookmin.exhibition;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import kr.ac.anheew1kookmin.exhibition.Frags.*;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private ArtworkFrag artworkFrag;
    private PlaceFrag placeFrag;
    private FragmentTransaction tran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Fragment
        fm = getSupportFragmentManager();
        artworkFrag = new ArtworkFrag();
        placeFrag = new PlaceFrag();
        tran = fm.beginTransaction();
        //set default as artwork fragment
        tran.replace(R.id.main_layout,artworkFrag);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                tran = fm.beginTransaction();

                if (tabId == R.id.tab_artwork) {
                    tran.replace(R.id.main_layout,artworkFrag);
                }
                if (tabId == R.id.tab_place) {
                    tran.replace(R.id.main_layout,placeFrag);
                }
                if (tabId == R.id.tab_upload) {

                }
                if (tabId == R.id.tab_message) {

                }
                if (tabId == R.id.tab_mypage) {

                }
                tran.commit();

            }
        });
    }
}
