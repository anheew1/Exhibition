package kr.ac.anheew1kookmin.exhibition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import kr.ac.anheew1kookmin.exhibition.Entity.Transaction;

public class PurchaseActivity extends AppCompatActivity {
    private Button returnToMainButton;
    private TextView purchase_success;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        returnToMainButton = (Button)findViewById(R.id.btn_purchase_returnToMain);
        purchase_success = (TextView) findViewById(R.id.text_purchaseComplete);
        Bundle b = getIntent().getExtras();
        String artworkId = b.getString("artworkId");
        String sellerId = b.getString("sellerId");
        String peroidical_price = b.getString("price");
        String buyerId = FirebaseAuth.getInstance().getUid();
        String id = "temp";
        Date purchaseDate = new Date();

        Transaction transaction = new Transaction(id,buyerId,sellerId,purchaseDate,artworkId,peroidical_price);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        String transId =db.child("Transaction").push().getKey();
        transaction.setId(transId);
        db.child("Transaction").child(transId).setValue(transaction).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isCanceled()){
                    purchase_success.setText("purchase fail");
                }
            }
        });

        returnToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnToMainIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(returnToMainIntent);
            }
        });



    }
}
