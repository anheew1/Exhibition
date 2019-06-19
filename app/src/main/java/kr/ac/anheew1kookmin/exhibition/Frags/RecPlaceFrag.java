package kr.ac.anheew1kookmin.exhibition.Frags;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import kr.ac.anheew1kookmin.exhibition.Adapter.GridImageAdapter;
import kr.ac.anheew1kookmin.exhibition.SubPlaceActivity;
import kr.ac.anheew1kookmin.exhibition.Entity.Place;
import kr.ac.anheew1kookmin.exhibition.R;

public class RecPlaceFrag extends Fragment {
    private View view;
    private GridView gridView;
    private ArrayList<Place>  placeArrayList;
    private ArrayList<Bitmap> bitmapArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_place, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_place);
        placeArrayList = new ArrayList<>();
        bitmapArrayList = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        final GridImageAdapter adapter = new GridImageAdapter(view.getContext());

        db.child("Place").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Place place = child.getValue(Place.class);
                    placeArrayList.add(place);
                }
                for( Place place: placeArrayList) {
                    StorageReference storage = FirebaseStorage.getInstance().getReference().child("Place/" + place.getId()+".jpg");
                    final long ONE_MEGABYTE = 1024 * 1024;
                    storage.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            adapter.addImageBitmap(bitmap);
                            gridView.setAdapter(adapter);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                }
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent subIntent = new Intent(getContext(), SubPlaceActivity.class);
                        Place currPlace = placeArrayList.get(position);
                        subIntent.putExtra("id",currPlace.getId());
                        subIntent.putExtra("name",currPlace.getName());
                        subIntent.putExtra("photoId",currPlace.getPhotoId());
                        subIntent.putExtra("provider_id",currPlace.getProvider_id());
                        subIntent.putExtra("artType",currPlace.getArtType());
                        subIntent.putExtra("description",currPlace.getDescription());
                        subIntent.putExtra("size",currPlace.getSize());
                        subIntent.putExtra("image",(Bitmap) adapter.getItem(position));
                        startActivity(subIntent);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DBERROR",databaseError.getMessage());
            }
        });
        return view;
    }
}
