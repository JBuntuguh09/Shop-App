package app.start.lonewolf.shopapp.viewHolders;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.start.lonewolf.shopapp.Constant;
import app.start.lonewolf.shopapp.R;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by Infoview on 11/16/2017.
 */

public class Goods_View_Holder extends RecyclerView.ViewHolder {

    public static View view;
    public Goods_View_Holder(View itemView) {
        super(itemView);
        view=itemView;
    }

    public void setItemName(String item){
        TextView Item = (TextView)view.findViewById(R.id.txtAllSalesItem);
        Item.setText(item);
    }

    public void setQuantity(String quantity){
        TextView Quantity = (TextView)view.findViewById(R.id.txtAllSalesQuantity);
        Quantity.setText(quantity);
    }

    public void setPrice(String price){
        TextView Price = (TextView)view.findViewById(R.id.txtAllSalesPrice);
        Price.setText(price);
    }

    public void setDate(String date){
        TextView Date = (TextView)view.findViewById(R.id.txtAllSalesDate);
        Date.setText(date);
    }
/*
    public void setImage(String image){
        ImageView Image = (ImageView)view.findViewById(R.id.imgSales);
        if(image.isEmpty()){
            String strBase64 = image;
            Bitmap bmp = Constant.decodeBase64(strBase64);
            if (bmp != null) {

                Image.setImageBitmap(bmp);
            }
        }
    }
*/
}
