package app.start.lonewolf.shopapp;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Enter_Sales extends AppCompatActivity {

    private Spinner spinCategory, spinItem;
    private TextView item, price, total;
    private EditText quantity;
    private DatabaseReference enterSales, getNum, categoryRef, itemRef;
    private List<String> categoryList, itemList, pricelist;
    private ProgressDialog progressDialog;
    private int  priz=0;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter__sales);

        progressDialog=new ProgressDialog(this);
        spinCategory = (Spinner)findViewById(R.id.spinCategory);
        spinItem = (Spinner)findViewById(R.id.spinItem);
        item = (TextView)findViewById(R.id.textView);
        total = (TextView)findViewById(R.id.txtSalesTotal);
        price = (TextView)findViewById(R.id.textView) ;
        quantity = (EditText)findViewById(R.id.editText);
        enterSales = FirebaseDatabase.getInstance().getReference().child("recordSales");
        getNum = FirebaseDatabase.getInstance().getReference().child("identifiers").child("salesId").child("number");
        categoryRef = FirebaseDatabase.getInstance().getReference().child("Categories");
        submit = (Button)findViewById(R.id.btnSalesSubmit);
      //  itemRef = FirebaseDatabase.getInstance().getReference().child("inventory").child(spinCategory.getSelectedItem().toString());
        categoryList = new ArrayList<>();
        itemList = new ArrayList<>();
        pricelist = new ArrayList<>();


        getCategory();

        getButtons();


    }

    private void getButtons() {

        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(!spinCategory.getSelectedItem().equals("Select Category") && !spinCategory.getSelectedItem().equals(null)) {

                    progressDialog.setMessage("Please Wait. Getting Items......");
                    progressDialog.show();
                    itemList.clear();
                    itemList.add(0, "Select Item");

                    pricelist.clear();
                    pricelist.add(0, "Item - 0.00");


                    getItem();

                    progressDialog.dismiss();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for(int x=0; x<itemList.size(); x++) {
                    if (x > 0 && !pricelist.get(i).equals("Item - 0.00")) {
                        item.setText(spinItem.getSelectedItem().toString() + " - " + pricelist.get(i));
                        priz = Integer.parseInt(pricelist.get(i));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Working");
                progressDialog.setTitle("Stuff");
                //progressDialog.setMax(101);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progressDialog.getProgress()<=progressDialog.getMax()){
                                Thread.sleep(200);
                                handle.sendMessage(handle.obtainMessage());
                                if(progressDialog.getProgress()==progressDialog.getMax()){
                                    progressDialog.dismiss();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            Handler handle = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressDialog.incrementProgressBy(1);
                }
            };
        });

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!quantity.getText().toString().isEmpty()) {
                    total.setText(String.valueOf(Integer.parseInt(quantity.getText().toString()) * priz));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(spinCategory.getSelectedItem().equals("Select Category")){
                    Toast.makeText(Enter_Sales.this, "Please Select A Category", Toast.LENGTH_SHORT).show();
                }else if(spinItem.getSelectedItem().equals("Select Item")){
                    Toast.makeText(Enter_Sales.this, "Please Select An Item", Toast.LENGTH_SHORT).show();
                }else {
                    sendSales();

                }


            }
        });


    }

    private void sendSales(){

       progressDialog.setMessage("Sending.....");
        progressDialog.show();

        getNum.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               final int saleId = Integer.parseInt(dataSnapshot.getValue().toString())+1;


               final DatabaseReference itemAmount= FirebaseDatabase.getInstance().getReference().child("inventory").child(spinCategory.getSelectedItem().toString()).child(spinItem.getSelectedItem().toString()).child("quantity");
               itemAmount.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {

                       Integer newQuantity = Integer.parseInt(dataSnapshot.getValue().toString()) - Integer.parseInt(quantity.getText().toString());

                       if(newQuantity>=0){
                           itemAmount.setValue(newQuantity);

                           Calendar c = Calendar.getInstance();
                           System.out.println("Current time => " + c.getTime());

                           SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                           String formattedDate = df.format(c.getTime());

                           enterSales.child(String.valueOf(saleId)).child("item").setValue(spinItem.getSelectedItem().toString());
                           enterSales.child(String.valueOf(saleId)).child("price").setValue(String.valueOf(priz));
                           enterSales.child(String.valueOf(saleId)).child("quantity").setValue(quantity.getText().toString());
                           enterSales.child(String.valueOf(saleId)).child("salesId").setValue(String.valueOf(saleId));
                           enterSales.child(String.valueOf(saleId)).child("date").setValue(formattedDate);


                           getNum.setValue(saleId);

                           pricelist.clear();
                           spinCategory.setSelection(0);
                           spinItem.setSelection(0);
                           spinItem.setVisibility(View.GONE);
                           price.setText("Item - 0.00");
                           quantity.setText("");
                           total.setText("0.00");
                       }
                       else {
                           Toast.makeText(Enter_Sales.this, "Not enough goods", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
               progressDialog.dismiss();
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }

    private void getCategory() {
        categoryList.add(0, "Select Category");
        progressDialog.setMessage("Please Wait. Loading Categories.......");
        progressDialog.show();
        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 for(DataSnapshot child : dataSnapshot.getChildren()){
                     for(DataSnapshot grandChild : child.getChildren()){

                         categoryList.add(grandChild.getValue().toString());

                     }
                 }

                 setCategory();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setCategory() {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categoryList);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinCategory.setAdapter(arrayAdapter);
    }

    private void getItem(){
        itemRef = FirebaseDatabase.getInstance().getReference().child("inventory").child(spinCategory.getSelectedItem().toString());
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    for(DataSnapshot grandChild : child.getChildren()){
                        if(grandChild.getKey().toString().equals("item")){
                            itemList.add(grandChild.getValue().toString());
                        }
                        if(grandChild.getKey().toString().equals("price")){
                            pricelist.add(grandChild.getValue().toString());
                        }
                    }
                }
                spinItem.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, itemList);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinItem.setAdapter(arrayAdapter);



    }
}
