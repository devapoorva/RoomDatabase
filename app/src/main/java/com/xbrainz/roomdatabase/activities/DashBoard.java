package com.xbrainz.roomdatabase.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.xbrainz.roomdatabase.R;
import com.xbrainz.roomdatabase.adapter.UserAdapter;
import com.xbrainz.roomdatabase.dao.UserDao;
import com.xbrainz.roomdatabase.database.AppDatabase;
import com.xbrainz.roomdatabase.models.UserModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoard extends AppCompatActivity {

    private UserAdapter userAdapter;

    @BindView(R.id.recycle_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        ButterKnife.bind(this);
        new LoadData().execute();
    }

    public class LoadData extends AsyncTask<Void,Void, List<UserModel>>
    {

        @Override
        protected List<UserModel> doInBackground(Void... voids) {
            AppDatabase appDatabase = AppDatabase.getInstance(DashBoard.this);
            UserDao userDao = appDatabase.userDao();
            return userDao.getAllUser();
        }

        @Override
        protected void onPostExecute(List<UserModel> userModels) {
            super.onPostExecute(userModels);
            userAdapter = new UserAdapter(DashBoard.this,userModels);
            recyclerView.setLayoutManager(new LinearLayoutManager(DashBoard.this));
            recyclerView.setAdapter(userAdapter);
        }
    }
}
