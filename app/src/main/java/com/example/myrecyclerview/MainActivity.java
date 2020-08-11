package com.example.myrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHero;
    private ArrayList<Hero> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvHero = findViewById(R.id.rv_hero);
        rvHero.setHasFixedSize(true);

        list.addAll(HeroesData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvHero.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHero.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListHeroAdapter.onItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });

        rvHero.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHero.setAdapter(gridHeroAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode){
        switch (selectedMode){
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
            break;

            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
            break;

            case R.id.action_cardView:
                title = "Mode CardView";
                showRecyclerCardView();
            break;
        }
    }


    private void showRecyclerGrid() {
        rvHero.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHero.setAdapter(gridHeroAdapter);
    }

    private void showRecyclerCardView(){
        rvHero.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter cardViewHeroAdapter = new CardViewHeroAdapter(list);
        rvHero.setAdapter(cardViewHeroAdapter);
    }

    private void setActionBarTitle(String title){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }
    private void showSelectedHero (Hero hero){
        Toast.makeText(this, "Kamu Memilih"+ hero.getName(), Toast.LENGTH_SHORT).show();
    }


}
