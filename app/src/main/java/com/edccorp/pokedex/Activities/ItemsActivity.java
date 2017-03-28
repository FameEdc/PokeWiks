package com.edccorp.pokedex.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.edccorp.pokedex.Adapters.ItemAdapter;
import com.edccorp.pokedex.Models.ItemModel;
import com.edccorp.pokedex.Models.ItemResponse;
import com.edccorp.pokedex.PokeApi.ApiService;
import com.edccorp.pokedex.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemsActivity extends AppCompatActivity {

    private static final String TAG = "";
    private Retrofit retrofit;
    private RecyclerView recyclerViewItem;
    private ItemAdapter itemAdapter;
    private int offset;

    private boolean fitLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerViewItem = (RecyclerView) findViewById(R.id.recyclerViewItem);
        itemAdapter = new ItemAdapter(this);
        recyclerViewItem.setAdapter(itemAdapter);
        recyclerViewItem.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerViewItem.setLayoutManager(layoutManager);


        recyclerViewItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (fitLoad) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Final.");

                            fitLoad = false;
                            offset += 20;
                            getData(offset);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fitLoad = true;
        offset = 0;

        getData(offset);

    }


    private void getData(int offset) {

        ApiService service = retrofit.create(ApiService.class);
        Call<ItemResponse> itemResponseCall = service.getListItem(150, offset);

        itemResponseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {

                fitLoad = true;

                if (response.isSuccessful()) {

                    ItemResponse itemResponse = response.body();
                    ArrayList<ItemModel> listItem = itemResponse.getResults();

                    itemAdapter.addList(listItem);

                }

                else {
                    Log.e(TAG, " onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {

                fitLoad = true;

                Log.e(TAG, " onFailure " + t.getMessage());

            }
        });

    }
}
