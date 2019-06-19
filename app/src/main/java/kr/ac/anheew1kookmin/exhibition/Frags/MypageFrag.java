package kr.ac.anheew1kookmin.exhibition.Frags;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import kr.ac.anheew1kookmin.exhibition.Entity.Transaction;
import kr.ac.anheew1kookmin.exhibition.Entity.User;
import kr.ac.anheew1kookmin.exhibition.R;

public class MypageFrag extends Fragment {
    private View view;
    private LinearLayout rootLayout;
    private Context mContext;
    private ImageView profileImgView;
    private FirebaseUser fb_curr_user;
    private FirebaseDatabase db;
    private User curr_user;
    private ArrayList<Transaction> transactionArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mypage,container,false);
        fb_curr_user = FirebaseAuth.getInstance().getCurrentUser();
        rootLayout = (LinearLayout) view.findViewById(R.id.rootLayout_transaction);
        mContext = view.getContext();
        profileImgView = view.findViewById(R.id.imageView_profile);

        setProfileImg();


        // to-do
        // use loop to add transaction layout

        db.getReference().child("User").child(fb_curr_user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                curr_user = (User) dataSnapshot.getValue(User.class);
                db.getReference().child("Transaction").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot child: dataSnapshot.getChildren()){
                            Transaction trans = (Transaction) child.getValue(Transaction.class);
                            for( String id: curr_user.getTransactionIDList()){
                                if(id.equals(trans.getId())){
                                    transactionArrayList.add(trans);
                                }
                            }
                        }

                        for(Transaction trans: transactionArrayList) {
                            RelativeLayout sub_layout = new RelativeLayout(mContext);
                            LayoutInflater sub_inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            sub_inflater.inflate(R.layout.frag_mypage_trans, sub_layout, true);
                            sub_layout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            rootLayout.addView(sub_layout);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("DBError",databaseError.getMessage());
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DBError",databaseError.getMessage());
            }
        });


        return view;
    }
    private void setProfileImg(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference profileRef = storage.getReference().child("User/"+curr_user.getUid()+".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        profileRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                profileImgView.setImageBitmap(bitmap);
                // Data for "images/island.jpg" is returns, use this as needed
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                exception.printStackTrace();
                Log.e("Tag","Get profile Image fail");
            }
        });




    }
}
