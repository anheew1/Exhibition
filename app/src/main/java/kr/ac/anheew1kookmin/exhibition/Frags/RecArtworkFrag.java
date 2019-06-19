package kr.ac.anheew1kookmin.exhibition.Frags;

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
import android.widget.Toast;

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

import kr.ac.anheew1kookmin.exhibition.Entity.Artwork;
import kr.ac.anheew1kookmin.exhibition.LoginActivity;
import kr.ac.anheew1kookmin.exhibition.R;
import kr.ac.anheew1kookmin.exhibition.Adapter.GridImageAdapter;

public class RecArtworkFrag extends Fragment {
    private View view;
    private GridView gridView;
    private ArrayList<Artwork> artworkArrayList;
    private ArrayList<Bitmap> bitmapArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_artwork,container,false);
        gridView = (GridView) view.findViewById(R.id.grid_artwork);
        artworkArrayList = new ArrayList<>();
        bitmapArrayList = new ArrayList<>();
        final GridImageAdapter adapter = new GridImageAdapter(view.getContext());

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("Artwork").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Artwork artwork = child.getValue(Artwork.class);
                    artworkArrayList.add(artwork);
                }
                for( Artwork artwork: artworkArrayList) {
                    Log.d("Artwork",artwork.getId());
                    StorageReference storage = FirebaseStorage.getInstance().getReference().child("Artwork/" + artwork.getId()+".jpg");
                    final long ONE_MEGABYTE = 1024 * 1024;
                    storage.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            adapter.addImageBitmap(bitmap);
                            gridView.setAdapter(adapter);
                            Log.d("OK","Add BItmap ");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            exception.printStackTrace();
                            Log.e("ST","Can't get image source");
                        }
                    });
                }
                // not adding bitmapArrayList
                Log.d("Wait","now Wating");
                Log.d("SET","Suceess setting adapter");

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(view.getContext(),""+position,Toast.LENGTH_SHORT).show();
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
