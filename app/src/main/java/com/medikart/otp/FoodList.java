package com.medikart.otp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medikart.otp.Interface.ItemClickListener;
import com.medikart.otp.Model.Food;
import com.medikart.otp.ViewHolder.FoodViewHolder;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String CategoryId="";
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Foods");

        recyclerView = findViewById(R.id.recyler_food);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // get intent here
        if(getIntent()!=null)
            CategoryId = getIntent().getStringExtra("CategoryId");

        if(!CategoryId.isEmpty() && CategoryId !=null)
        {
            loadListFood(CategoryId);
        }

    }

    private void loadListFood(String categoryId) {
                                                                                                                                                    // like: select* from Foods Where MenuId =
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_item,FoodViewHolder.class,foodList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {

                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_image);

                final Food loacal = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public Void onClick(View view, int position, boolean isLongClick) {

                        // start new activity

                        Intent fooddetail = new Intent(FoodList.this,FoodDetail.class);
                        fooddetail.putExtra("FoodId",adapter.getRef(position).getKey());// send food id to new activity
                        startActivity(fooddetail);
                        return null;
                    }
                });

            }
        };

        // set adapter
        recyclerView.setAdapter(adapter);
    }
}
