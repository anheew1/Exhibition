package kr.ac.anheew1kookmin.exhibition.Frags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import kr.ac.anheew1kookmin.exhibition.Adapter.GridImageAdapter;
import kr.ac.anheew1kookmin.exhibition.R;

public class PlaceFrag extends Fragment {
    private View view;
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_place, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_place);
        gridView.setAdapter(new GridImageAdapter(view.getContext()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
