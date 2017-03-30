package com.edccorp.pokedex.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.edccorp.pokedex.BuilderManager;
import com.edccorp.pokedex.R;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class MainActivity extends AppCompatActivity {

    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_2);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_4_2);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            bmb.addBuilder(BuilderManager.getSimpleCircleButtonBuilder().listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    if (index == 0) {
                        startActivity(new Intent(MainActivity.this, PokedexActivity.class));
                    } else if (index == 1) {
                        startActivity(new Intent(MainActivity.this, ItemsActivity.class));
                    } else if (index == 2) {
                        startActivity(new Intent(MainActivity.this, RegionActivity.class));
                    } else if (index == 3) {
                        startActivity(new Intent(MainActivity.this, EggActivity.class));
                    }
                }
            }));
        }
    }
}
