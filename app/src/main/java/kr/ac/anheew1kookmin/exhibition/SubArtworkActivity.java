package kr.ac.anheew1kookmin.exhibition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.anheew1kookmin.exhibition.Entity.Artwork;

public class SubArtworkActivity extends AppCompatActivity {
    private ImageView artwork_img;
    private TextView artwork_name;
    private TextView artwork_description;
    private TextView artwork_artworkType;
    private TextView artwork_size;
    private TextView artwork_price;
    private Button btn_artwork_purchase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_artwork);
        final Artwork artwork = getArtworkFromIntent();

        artwork_img = (ImageView) findViewById(R.id.sub_artwork_img);
        artwork_name = (TextView)findViewById(R.id.text_sub_artwork_name);
        artwork_description = (TextView)findViewById(R.id.text_sub_artwork_description);
        artwork_artworkType = (TextView)findViewById(R.id.text_sub_artworkType);
        artwork_size = (TextView)findViewById(R.id.text_sub_artwork_size);
        artwork_price = (TextView) findViewById(R.id.text_sub_artwork_price);
        btn_artwork_purchase = (Button) findViewById(R.id.btn_sub_artwork);

        artwork_img.setImageBitmap((Bitmap)getIntent().getParcelableExtra("image"));
        artwork_name.setText(artwork.getName());
        artwork_description.setText(artwork.getDescription());
        artwork_artworkType.setText(artwork.getArtType());
        artwork_size.setText(artwork.getSize());
        artwork_price.setText(artwork.getPrice() +" For "+artwork.getPeroid()+" days");
        btn_artwork_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PurchaseActivity.class);
                intent.putExtra("artworkId",artwork.getId());
                intent.putExtra("sellerId",artwork.getArtist_id());
                intent.putExtra("price",artwork.getPrice() +" For "+artwork.getPeroid()+" days");
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
}
