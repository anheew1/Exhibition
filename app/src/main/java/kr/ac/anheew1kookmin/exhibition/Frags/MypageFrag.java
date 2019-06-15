package kr.ac.anheew1kookmin.exhibition.Frags;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import kr.ac.anheew1kookmin.exhibition.R;

public class MypageFrag extends Fragment {
    private View view;
    private LinearLayout rootLayout;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mypage,container,false);
        rootLayout = (LinearLayout) view.findViewById(R.id.rootLayout_transaction);
        mContext = view.getContext();

        // to-do
        // use loop to add transaction layout

        RelativeLayout sub_layout = new RelativeLayout(mContext);
        LayoutInflater  sub_inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sub_inflater.inflate(R.layout.frag_mypage_trans,sub_layout,true);
        sub_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rootLayout.addView(sub_layout);
        return view;
    }
}
