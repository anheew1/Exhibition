package kr.ac.anheew1kookmin.exhibition.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import kr.ac.anheew1kookmin.exhibition.Entity.Artwork;
import kr.ac.anheew1kookmin.exhibition.Entity.Transaction;
import kr.ac.anheew1kookmin.exhibition.R;

public class MypageTransLayout extends LinearLayout {
    private Transaction trans;
    private TextView text_trans_id;
    private TextView text_artwork_name;
    private TextView text_trans_date;
    private TextView text_peroidial_price;
    private ImageView imageView;
    private DatabaseReference db;
    private StorageReference storage;

    public MypageTransLayout(Context context) {
        super(context);
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.frag_mypage_trans,this,true);
    }

    public MypageTransLayout(Context context , Transaction trans) {
        super(context);
        this.trans = trans;
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.frag_mypage_trans,this,true);
        text_trans_id = (TextView)findViewById(R.id.text_trans_Id);
        text_artwork_name = (TextView) findViewById(R.id.text_trans_ArtworkName);
        text_trans_date = (TextView)findViewById(R.id.text_transDate);
        text_peroidial_price = (TextView) findViewById(R.id.text_trans_peroidialPrice);
        imageView = (ImageView) findViewById(R.id.trans_image);

        text_trans_id.setText("ID:" +trans.getId());
        text_peroidial_price.setText(trans.getPeroidical_price());
        text_trans_date.setText(trans.getPurchase_date().toString());
        db = FirebaseDatabase.getInstance().getReference();
        db.child("Artwork").child(trans.getArtwork_id()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Artwork artwork = dataSnapshot.getValue(Artwork.class);
                text_artwork_name.setText(artwork.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("dbError",databaseError.getMessage());
            }
        });
        storage = FirebaseStorage.getInstance().getReference().child("Artwork/" + trans.getArtwork_id()+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        storage.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imageView.setImageBitmap(bitmap);
            }
        });



    }
}
