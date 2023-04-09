package com.example.harmonicatulashop.ui.catalog.holders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.harmonicatulashop.R;

public class AccordionViewHolder extends RecyclerView.ViewHolder {

    private final TextView accordionNameView;

    private final TextView accordionPriceView;

    private final ImageView accordionImageView;

    public AccordionViewHolder(View itemView) {
        super(itemView);

        accordionNameView = itemView.findViewById(R.id.accordion_name);
        accordionPriceView = itemView.findViewById(R.id.accordion_price);
        accordionImageView = itemView.findViewById(R.id.accordion_icon);

    }

    public void bindName(String name) {
        accordionNameView.setText(name);
    }

    public void bindPrice(String price) {
        accordionPriceView.setText(price);
    }

    public void bindImage(byte[] image) {

        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        accordionImageView.setImageBitmap(bitmap);

    }

    public static AccordionViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_accordion, parent, false);
        return new AccordionViewHolder(view);
    }
}
