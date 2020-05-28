package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // khởi tạo recyclerview
        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);

        //set layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // khởi tạo http
        OkHttpClient client = new OkHttpClient();

        // khởi tạo  mosi adapter để biến đổi json thành model java
        Moshi moshi = new Moshi.Builder().build();
        Type datatype = Types.newParameterizedType(List.class, DataCovid.class);
        final JsonAdapter<List<DataCovid>> jsonAdapter = moshi.adapter(datatype);

        // tạo request lên server
        Request request = new Request.Builder().url("https://corona.lmao.ninja/v2/gov/vn?utm_source=j2team&utm_medium=url_shortener").build();
        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Er", "Network err");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // lấy thông tin từ json trả về
                String json = response.body().string();
                final List<DataCovid> dataCovids = jsonAdapter.fromJson(json);


                // hiển thị lên ryceceler view
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(new DataAdapter(dataCovids,MainActivity.this));

                    }
                });
            }
        });
    }
}
