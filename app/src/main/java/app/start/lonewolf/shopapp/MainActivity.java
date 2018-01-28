package app.start.lonewolf.shopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Record, Calculate, Inventory, viewSales;
    private SavedPreferences savedPreferences;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedPreferences= new SavedPreferences(this);

       // toolbar = (Toolbar)findViewById(R.id.mainPageTab_layout);
       // setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Here");
        setTitle("Main Page");

        Record = (Button)findViewById(R.id.btnRecord);
        Calculate = (Button)findViewById(R.id.btnCalculator);
        Inventory = (Button)findViewById(R.id.btnInventory);
        viewSales = (Button)findViewById(R.id.btnViewSales);
        savedPreferences.setPicture("");

        getButtons();
    }

    private void getButtons() {

        Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Enter_Sales.class);
                startActivity(intent);
            }
        });

        Inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Record_Goods.class);
                startActivity(intent);
            }
        });

        viewSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, All_Sales.class);
                startActivity(intent);
            }
        });
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calculator.class);
                startActivity(intent);
            }
        });
    }
}
