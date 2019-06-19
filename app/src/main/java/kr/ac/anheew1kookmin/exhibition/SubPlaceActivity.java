package kr.ac.anheew1kookmin.exhibition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.anheew1kookmin.exhibition.Entity.Place;

public class SubPlaceActivity extends AppCompatActivity {
    private ImageView place_img;
    private TextView place_name;
    private TextView place_description;
    private TextView place_artworkType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_place);
        Place currPlace = getPlaceFromIntent();
        place_img = (ImageView) findViewById(R.id.sub_place_image);
        place_name = (TextView) findViewById(R.id.text_sub_place_name);
        place_description = (TextView) findViewById(R.id.text_sub_place_description);
        place_artworkType = (TextView) findViewById(R.id.text_sub_place_ArtworkType);

        place_img.setImageBitmap((Bitmap)getIntent().getParcelableExtra("image"));
        place_name.setText(currPlace.getName());
        place_description.setText(currPlace.getDescription());
        place_artworkType.setText(currPlace.getArtType());

        /*btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create apply
            }
        });*/

    }

    private Place getPlaceFromIntent(){
        Bundle b = getIntent().getExtras();
        return new Place(b.getString("id"),b.getString("name"),b.getString("PhotoId"),
                b.getString("provider_id"),b.getString("artType"),b.getString("descrtiption"),
                b.getString("size"));
    }
}
