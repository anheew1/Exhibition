package kr.ac.anheew1kookmin.exhibition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Currency;
import java.util.Locale;

import kr.ac.anheew1kookmin.exhibition.Entity.Artwork;
import kr.ac.anheew1kookmin.exhibition.Entity.User;

public class SubArtworkActivity extends AppCompatActivity {
    private ImageView artwork_img;
    private TextView artwork_name;
    private TextView artwork_artistName;
    private TextView artwork_description;
    private TextView artwork_artworkType;
    private TextView artwork_size;
    private TextView artwork_price;
    private Button btn_artwork_purchase;

    private DatabaseReference db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_artwork);
        final Artwork artwork = getArtworkFromIntent();

        artwork_img = (ImageView) findViewById(R.id.sub_artwork_img);
        artwork_name = (TextView)findViewById(R.id.text_sub_artwork_name);
        artwork_artistName = (TextView) findViewById(R.id.text_sub_artist_name);
        artwork_description = (TextView)findViewById(R.id.text_sub_artwork_description);
        artwork_artworkType = (TextView)findViewById(R.id.text_sub_artworkType);
        artwork_size = (TextView)findViewById(R.id.text_sub_artwork_size);
        artwork_price = (TextView) findViewById(R.id.text_sub_artwork_price);
        btn_artwork_purchase = (Button) findViewById(R.id.btn_sub_artwork);

        artwork_img.setImageBitmap((Bitmap)getIntent().getParcelableExtra("image"));
        artwork_name.setText(artwork.getName());

        db = FirebaseDatabase.getInstance().getReference();
        db.child("User").child(artwork.getArtist_id()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user_artist = dataSnapshot.getValue(User.class);
                artwork_artistName.setText(user_artist.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DBerror",databaseError.getMessage());
            }
        });

        if(FirebaseAuth.getInstance().getUid().equals(artwork.getArtist_id())){
            btn_artwork_purchase.setVisibility(View.GONE);
        }


        if(artwork.getDescription() != null)
        artwork_description.setText(artwork.getDescription().replaceAll("_"," "));
        artwork_artworkType.setText(artwork.getArtType());
        artwork_size.setText(artwork.getSize());
        if(artwork.getPeroid() < 0)
            artwork_price.setText(artwork.getPrice()+ ""+Currency.getInstance(Locale.KOREA).getSymbol());
        else artwork_price.setText(genPeroidalPrice(artwork.getPrice(),artwork.getPeroid()));
        btn_artwork_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PurchaseActivity.class);
                intent.putExtra("artworkId",artwork.getId());
                intent.putExtra("sellerId",artwork.getArtist_id());
                intent.putExtra("price",artwork_price.getText().toString());
                startActivity(intent);
            }
        });

    }
    private Artwork getArtworkFromIntent(){
        Bundle b = getIntent().getExtras();
        return new Artwork(b.getString("id"),
                b.getString("name"),b.getString("photoID"),b.getString("artist_id"),
                b.getString("artType"),b.getString("description"),b.getString("size"),
                b.getInt("peroid"),b.getInt("price"));
    }
    private String genPeroidalPrice(int price, int peroid){
        return price+ Currency.getInstance(Locale.KOREA).getSymbol()+" For "+
                peroid+" days";
    }
}
