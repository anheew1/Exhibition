package kr.ac.anheew1kookmin.exhibition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText edit_id;
    private EditText edit_name;
    private EditText edit_pass;
    private EditText edit_confirmPass;

    private Button btn_register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_id = (EditText) findViewById(R.id.editText_register_ID);
        edit_name = (EditText) findViewById(R.id.editText_register_name) ;
        edit_pass = (EditText) findViewById(R.id.editText_register_pass);
        edit_confirmPass = (EditText) findViewById(R.id.editText_register_confirmPass);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_pass.getText().toString().equals(edit_confirmPass.toString())){
                    Toast toast = Toast.makeText(getApplicationContext(),"Passwords are not equal",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(edit_id.toString() == "" || edit_name.toString() == "" || edit_pass.toString() == ""
                        ||edit_confirmPass.toString()==""){
                    Toast toast = Toast.makeText(getApplicationContext(),"All blank field must filled",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    //register!
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
