package com.edccorp.pokedex.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.edccorp.pokedex.Adapters.Adapter;
import com.edccorp.pokedex.Models.ItemModel;
import com.edccorp.pokedex.Models.ItemResponse;
import com.edccorp.pokedex.PokeApi.PokeEndpoint;
import com.edccorp.pokedex.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edccorp.pokedex.PokeApplication.POKE_API;

public class ItemsActivity extends AppCompatActivity {

    private static final String TAG = "";
    private Adapter itemAdapter;
    private int offset;

    private boolean fitLoad;
    private PokeEndpoint pokeEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        pokeEndpoint = (PokeEndpoint) getApplication().getSystemService(POKE_API);

        RecyclerView recyclerViewItem = (RecyclerView) findViewById(R.id.recyclerViewItem);
        itemAdapter = new Adapter<>(this);
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
        fitLoad = true;
        offset = 0;
        getData(offset);
    }

    private void getData(int offset) {
        Call<ItemResponse> itemResponseCall = pokeEndpoint.listItems(150, offset);
        itemResponseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                fitLoad = true;
                if (response.isSuccessful()) {
                    ItemResponse itemResponse = response.body();
                    List<ItemModel> listItem = itemResponse.getResults();
                    itemAdapter.addList(listItem);
                } else {
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
