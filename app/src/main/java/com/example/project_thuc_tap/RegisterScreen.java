package com.example.project_thuc_tap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;



    Button  btnRegister;
    TextInputEditText edtEmailPhone, edtPassWord, getEdtPassWordConfirm;
    TextView tvWarnEmail, tvWarnPass, tvWarnPassConfirm;


    String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Map();


        btnRegister.setOnClickListener(v -> {
            String email = edtEmailPhone.getText().toString();
            String pass = edtPassWord.getText().toString();
            String passConfirm = getEdtPassWordConfirm.getText().toString();

            //validate Email
            if(!TextUtils.isEmpty(email))
            {
                if(isValidEmail(email)){
                    //email hop le
                    tvWarnEmail.setTextColor(Color.GREEN);
                    tvWarnEmail.setText("✓");
                }
                else{
                    tvWarnEmail.setTextColor(Color.RED);
                    tvWarnEmail.setText("Email is Invalid!");
                }
            }
            else {
                tvWarnEmail.setTextColor(Color.RED);
                tvWarnEmail.setText("This Field can't Empty");
            }

            //xu li password
            //neeu pass va confirm pass khong de null
            if (!TextUtils.isEmpty(pass) && !TextUtils.isEmpty(passConfirm))
            {
                if(pass.equals(passConfirm))
                {
                    if(isValidPassword(pass, regex)){
                        tvWarnPass.setTextColor(Color.GREEN);
                        tvWarnPass.setText("✓");
                        tvWarnPassConfirm.setTextColor(Color.GREEN);
                        tvWarnPassConfirm.setText("✓");
                    }
                    else {
//                        Toast.makeText(this, "chưa đủ mạnh", Toast.LENGTH_LONG).show();
                        tvWarnPass.setTextColor(Color.RED);
                        tvWarnPass.setText("Your Password Invalid!");
                    }
                }
                else {
//                    Toast.makeText(this, "khác nhau", Toast.LENGTH_LONG).show();
                    tvWarnPassConfirm.setTextColor(Color.RED);
                    tvWarnPassConfirm.setText("Your Password not match!");
                }
            }
            else {
                tvWarnPass.setTextColor(Color.RED);
                tvWarnPass.setText("This Field can't Empty");
            }

            //dang ky tai khoan len firebase
            if(tvWarnPass.getText().toString().equals("✓") && tvWarnEmail.getText().toString().equals("✓")){
                CreateAccount(email, pass);
            }

        });

    }

    public void CreateAccount(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterScreen.this, "Create Account successfully!", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterScreen.this, "Create Account Failure", Toast.LENGTH_LONG).show();

                        }
                    }
                });
        startActivity(new Intent(this, LoginScreen.class));
    }

    //validate email
    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    //validate passWord have to inclucde @#%$, upcase, lowcase
    public static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    void Map(){
        btnRegister = (Button) findViewById(R.id.btnRegisterRes);
        edtEmailPhone = (TextInputEditText) findViewById(R.id.edtEmailPhoneRegis);
        edtPassWord = (TextInputEditText) findViewById(R.id.edtPassWordRegis);
        getEdtPassWordConfirm = (TextInputEditText) findViewById(R.id.edtPassWordConfirm);
        tvWarnEmail = (TextView) findViewById(R.id.tvWarnEmail);
        tvWarnPass = (TextView) findViewById(R.id.tvWarnPassWord);
        tvWarnPassConfirm = (TextView) findViewById(R.id.tvWarnConfirm);

    }



}