package com.xbrainz.roomdatabase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xbrainz.roomdatabase.R;
import com.xbrainz.roomdatabase.dao.UserDao;
import com.xbrainz.roomdatabase.database.AppDatabase;
import com.xbrainz.roomdatabase.models.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Register extends AppCompatActivity {

    // initialize view
    @BindView(R.id.edt_email) EditText edtEmail;
    @BindView(R.id.edt_name) EditText edtName;
    @BindView(R.id.edt_password) EditText edtPassword;

    private String name,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    public void register(View view) {
        name = edtName.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString();
        new RegisterUser(new UserModel(name,email,password)).execute();
        startActivity(new Intent(Register.this,DashBoard.class));
    }


    public class RegisterUser extends AsyncTask<Void,Void,Void>
    {
        private UserModel userModel;

        public RegisterUser(UserModel userModel) {
            this.userModel = userModel;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase appDatabase = AppDatabase.getInstance(Register.this);
            UserDao userDao = appDatabase.userDao();
            userDao.insert(this.userModel);
            return null;
        }
    }
}
