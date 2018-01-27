package app.start.lonewolf.shopapp.viewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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

}
