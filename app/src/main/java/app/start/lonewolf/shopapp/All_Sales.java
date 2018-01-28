package app.start.lonewolf.shopapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.start.lonewolf.shopapp.models.SalesModel;
import app.start.lonewolf.shopapp.viewHolders.Goods_View_Holder;

public class All_Sales extends AppCompatActivity {

    private DatabaseReference databaseRef;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__sales);
        setTitle("jump");
        progressDialog = new ProgressDialog(this);
        recyclerView = (RecyclerView)findViewById(R.id.recycleListAllSales);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseRef= FirebaseDatabase.getInstance().getReference().child("recordSales");

        progressDialog.setTitle("Category");
        progressDialog.show();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //progressDialog.onStart();
        getList();
    }

    private void getList() {
        FirebaseRecyclerAdapter<SalesModel, Goods_View_Holder> firebaseRecycleAdapter = new FirebaseRecyclerAdapter<SalesModel, Goods_View_Holder>(

                SalesModel.class,
                R.layout.layout_all_sales,
                Goods_View_Holder.class,
                databaseRef


        ) {
            @Override
            protected void populateViewHolder(Goods_View_Holder viewHolder, SalesModel model, int position) {
                viewHolder.setItemName(model.getItem());
                viewHolder.setQuantity(model.getQuantity());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setDate(model.getDate());
                //viewHolder.setImage(model.getImage());
                progressDialog.dismiss();
            }
        };

        recyclerView.setAdapter(firebaseRecycleAdapter);
    }


}
