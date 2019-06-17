package kr.ac.anheew1kookmin.exhibition.Frags;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.io.FileNotFoundException;
import java.io.InputStream;

import kr.ac.anheew1kookmin.exhibition.R;

import static android.app.Activity.RESULT_OK;

public class UploadFrag extends Fragment {
    int REQUEST_IMAGE_CAPTURE = 1;
    int REQUEST_IMAGE_LOAD = 2;
    private View view;
    private RadioGroup radio_selectType;

    private EditText edit_name;
    private RadioGroup radio_artType;

    private TextView text_setPeroidPrice;

    private ImageView img_photo;


    private ImageButton btn_addPhoto;
    private ImageButton btn_insertPhoto;

    private LinearLayout layout_setPeroid;
    private CheckBox check_noRental;
    private EditText edit_setPeroid;
    private EditText edit_setPrice;
    private Button btn_upload;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_upload,container,false);

        radio_selectType = view.findViewById(R.id.radio_upload_selectType);
        //radio_artwork = view.findViewById(R.id.radio_btn_artwork);
        //radio_place = view.findViewById(R.id.radio_btn_place);

        edit_name = view.findViewById(R.id.editText_upload_name);

        radio_artType = view.findViewById(R.id.radio_upload_artType);

        btn_addPhoto = view.findViewById(R.id.btn_add_photo);
        btn_insertPhoto = view.findViewById(R.id.btn_insert_photo);

        text_setPeroidPrice = view.findViewById(R.id.text_upload_peroid_price);


        check_noRental = view.findViewById(R.id.check_notRental);
        layout_setPeroid = view.findViewById(R.id.layout_upload_peroid);
        edit_setPeroid = view.findViewById(R.id.editText_upload_setPeroid);

        edit_setPrice = view.findViewById(R.id.editText_upload_price);

        btn_upload = view.findViewById(R.id.btn_upload);

        radio_selectType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_btn_place){
                    layout_setPeroid.setVisibility(View.GONE);
                }
                else if (checkedId == R.id.radio_btn_artwork){
                    layout_setPeroid.setVisibility(View.VISIBLE);
                }
            }
        });
        radio_artType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_btn_painting){
                    view.findViewById(R.id.size_z_t).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.size_z).setVisibility(View.VISIBLE);
                }
                else if (checkedId == R.id.radio_btn_sculpture){
                    view.findViewById(R.id.size_z_t).setVisibility(View.GONE);
                    view.findViewById(R.id.size_z).setVisibility(View.GONE);
                }
            }
        });
        btn_addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhoto();
            }
        });
        btn_insertPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPhoto();
            }
        });
        check_noRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_noRental.isChecked()){
                    edit_setPeroid.setVisibility(View.GONE);
                }else{
                    edit_setPeroid.setVisibility(View.VISIBLE);
                }
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        return view;
    }
    private void addPhoto(){
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(photoIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(photoIntent,REQUEST_IMAGE_CAPTURE);
        }
    }
    private void insertPhoto(){
        Intent photoIntent =new Intent(Intent.ACTION_PICK);
        photoIntent.setType("image/*");
        startActivityForResult(photoIntent, REQUEST_IMAGE_LOAD);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE || requestCode == REQUEST_IMAGE_LOAD){
            if(resultCode == RESULT_OK) {
                try {
                    Uri imgUri = data.getData();
                    InputStream imageStream = getActivity().getContentResolver().openInputStream(imgUri);
                    Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
                    img_photo = (ImageView) view.findViewById(R.id.imageView_photo);
                    img_photo.setImageBitmap(imageBitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void upload(){
        //to-do
    }
}
