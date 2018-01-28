package app.start.lonewolf.shopapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.zelory.compressor.Compressor;

public class Record_Goods extends AppCompatActivity {

    private EditText item, price, quantity;
    private Spinner category;
    private Button submit;
    private ImageView image;
    private DatabaseReference databaseReference, categoryRef, picRef;
    private  List<String> newArray, newArray2;
    private SavedPreferences userPreference;
    private int picId;

    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record__goods);

        userPreference = new SavedPreferences(this);
        item = (EditText)findViewById(R.id.edtRecordItemName);
        price = (EditText)findViewById(R.id.edtRecordPrice);
        quantity = (EditText)findViewById(R.id.edtRecordQty);
        category = (Spinner) findViewById(R.id.spinCategory);
        submit = (Button)findViewById(R.id.btnRecordSubmit);
        image = (ImageView) findViewById(R.id.imgRecordPic);
        category =(Spinner)findViewById(R.id.spinCategory);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("inventory");

        newArray = new ArrayList<String>();
        newArray2 = new ArrayList<String>();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordGoods();
            }
        });
        newArray2.clear();
        getCategory();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPic addPic = new AddPic();
                addPic.show(getFragmentManager(), null);
               // finish();

            }
        });

        if(!userPreference.getPicture().isEmpty()){
            String strBase64 = userPreference.getPicture();
            Bitmap bmp = Constant.decodeBase64(strBase64);
            if (bmp != null) {

                image.setImageBitmap(bmp);
            }
        }

    }

    private void recordGoods() {
        databaseReference.child(category.getSelectedItem().toString()).child(item.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Toast.makeText(Record_Goods.this, "This Item already exist", Toast.LENGTH_SHORT).show();

                    Dialog dialogue = new Dialog(Record_Goods.this);
                    dialogue.setContentView(R.layout.layout_all_sales);
                    dialogue.setTitle("Hero");
                    dialogue.show();
                }else {
                    databaseReference.child(category.getSelectedItem().toString()).child(item.getText().toString()).child("item").setValue(item.getText().toString());
                    databaseReference.child(category.getSelectedItem().toString()).child(item.getText().toString()).child("price").setValue(price.getText().toString());
                    databaseReference.child(category.getSelectedItem().toString()).child(item.getText().toString()).child("quantity").setValue(quantity.getText().toString());
                    //databaseReference.child(category.getSelectedItem().toString()).child(item.getText().toString()).child("image").setValue(userPreference.getPicture());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Record_Goods.this, "Does not", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void checkExist(){
        //.child(item.getText().toString()).child("item");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //List<String> list = dataSnapshot.getValue(2)
                try {

                    for (DataSnapshot child : dataSnapshot.getChildren())
                    {

                        for (DataSnapshot grandChild : child.getChildren())
                        {
                            if(grandChild.getKey().toString().equals("item")) {
                                newArray.add(grandChild.getValue().toString());
                            }

                        }
                    }
                  //  Toast.makeText(Record_Goods.this, "hereok "+newArray.toString() , Toast.LENGTH_SHORT).show();
                    //Log.d("oneok", newArray.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
                //Toast.makeText(Record_Goods.this, "here "+newArray.toString() , Toast.LENGTH_SHORT).show();
                Log.d("one", newArray.toString());

               getDrop();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        categoryRef = FirebaseDatabase.getInstance().getReference().child("Categories");

    }


    private void getCategory(){

        categoryRef = FirebaseDatabase.getInstance().getReference().child("Categories");
        newArray2.add(0, "Select Category");
        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for( DataSnapshot child : dataSnapshot.getChildren()){
                    for(DataSnapshot grandchild : child.getChildren()){
                        newArray2.add(grandchild.getValue().toString());
                    }
                }

                if(newArray2.size()>0){
                    getDrop();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDrop() {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, newArray2);
        arrayAdapter.setDropDownViewResource(R.layout.drop_down_layout);
        category.setAdapter(arrayAdapter);
    }

    private void sendPic() throws IOException {

        final Uri uri = Uri.parse(userPreference.getPicture());


        File actualImage = new File(uri.getPath());
        Bitmap compressedImage = new Compressor(Record_Goods.this)
                .setMaxWidth(200)
                .setMaxHeight(200)
                .setQuality(75)
                .compressToBitmap(actualImage);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        compressedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        final byte[] thumbByte = baos.toByteArray();



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
