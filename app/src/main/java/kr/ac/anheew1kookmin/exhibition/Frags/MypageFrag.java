package kr.ac.anheew1kookmin.exhibition.Frags;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kr.ac.anheew1kookmin.exhibition.Entity.Transaction;
import kr.ac.anheew1kookmin.exhibition.Entity.User;
import kr.ac.anheew1kookmin.exhibition.LoginActivity;
import kr.ac.anheew1kookmin.exhibition.R;
import kr.ac.anheew1kookmin.exhibition.layout.MypageTransLayout;

public class MypageFrag extends Fragment {
    private View view;
    private Button btn_logout;
    private LinearLayout rootLayout;
    private TextView text_email;
    private TextView text_name;
    private FirebaseUser fb_curr_user;
    private FirebaseAuth mAuth;
    private DatabaseReference db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_mypage,container,false);
        db = FirebaseDatabase.getInstance().getReference();
        fb_curr_user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        rootLayout = (LinearLayout) view.findViewById(R.id.rootLayout_transaction);
        btn_logout = (Button) view.findViewById(R.id.btn_logout);
        text_email = (TextView) view.findViewById(R.id.text_mypage_email);
        text_name = (TextView) view.findViewById(R.id.text_mypage_name);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        // to-do
        // use loop to add transaction layout
        Log.d("T",fb_curr_user.getUid());
        db.child("User").child(fb_curr_user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                text_email.setText(user.getEmail());
                text_name.setText(user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DBerror",databaseError.getMessage());

            }
        });

        db.child("Transaction").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    final Transaction transaction = child.getValue(Transaction.class);
                    if (fb_curr_user.getUid().equals(transaction.getBuyer_id())
                            || fb_curr_user.getUid().equals(transaction.getSeller_id())) {
                        MypageTransLayout transactionLayout = new MypageTransLayout(getContext(),transaction);
                        rootLayout.addView(transactionLayout);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DBerror",databaseError.getMessage());
            }
        });


        return view;
    }
    private void logout(){
        mAuth.signOut();
        Intent loginIntent = new Intent(getContext(), LoginActivity.class);
        startActivity(loginIntent);
    }

}
