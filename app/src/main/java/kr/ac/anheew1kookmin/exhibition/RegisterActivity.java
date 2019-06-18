package kr.ac.anheew1kookmin.exhibition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import kr.ac.anheew1kookmin.exhibition.Entity.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText edit_id;
    private EditText edit_name;
    private EditText edit_pass;
    private EditText edit_confirmPass;

    private Button btn_register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edit_id = (EditText) findViewById(R.id.editText_register_ID);
        edit_name = (EditText) findViewById(R.id.editText_register_name) ;
        edit_pass = (EditText) findViewById(R.id.editText_register_pass);
        edit_confirmPass = (EditText) findViewById(R.id.editText_register_confirmPass);

        btn_register = (Button) findViewById(R.id.btn_register_submit);
        mAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(edit_pass.getText().toString().equals(edit_confirmPass.getText().toString()))){
                    Toast toast = Toast.makeText(getApplicationContext(),"Passwords are not equal",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(edit_pass.getText().toString().length() <6){
                    Toast.makeText(getApplicationContext(),"Passwords must set over 6",Toast.LENGTH_SHORT);
                }
                else if(edit_id.getText().toString().equals("") || edit_name.getText().toString().equals("")
                        || edit_pass.getText().toString().equals("")
                        ||edit_confirmPass.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"All blank field must filled",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    register(edit_id.getText().toString(),edit_pass.getText().toString());
                    Log.d("Login","w");
                }
            }
        });
    }
    private void register(String id , String pass){
        mAuth.createUserWithEmailAndPassword(id,pass).addOnCompleteListener(
                this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("ad","Succes");
                    registerToFirebaseDB();
                    Toast.makeText(getApplicationContext(), "Register Complete", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Log.w("not","fail",task.getException());
                    Toast.makeText(getApplicationContext(), "Register Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void registerToFirebaseDB(){
        String id = edit_id.getText().toString();
        String name = edit_name.getText().toString();
        // id is email
        User user = new User("uid",id,name);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        String uid = ref.child("/User").push().getKey();
        user.setUid(uid);
        ref.child("/User").child(uid).setValue(user);
    }
}
