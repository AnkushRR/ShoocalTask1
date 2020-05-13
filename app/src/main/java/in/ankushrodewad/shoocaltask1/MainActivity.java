package in.ankushrodewad.shoocaltask1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);

                ButterKnife.bind(MainActivity.this);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                Api api = retrofit.create(Api.class);

                Call<List<RepoObject>> call = api.getRepoObjects();


                call.enqueue(new Callback<List<RepoObject>>() {
                    @Override
                    public void onResponse(Call<List<RepoObject>> call, Response<List<RepoObject>> response) {

                        List<RepoObject> repoObjectList = response.body();
                        listView.setAdapter(new CustomAdapter((ArrayList<RepoObject>) repoObjectList,getApplicationContext()));

                    }

                    @Override
                    public void onFailure(Call<List<RepoObject>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        },3000);


    }
}
