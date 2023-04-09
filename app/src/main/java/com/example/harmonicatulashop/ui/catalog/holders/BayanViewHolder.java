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

public class BayanViewHolder extends RecyclerView.ViewHolder {

    private final TextView bayanNameView;

    private final TextView bayanPriceView;

    private final ImageView bayanImageView;

    public BayanViewHolder(View itemView) {
        super(itemView);

        bayanNameView = itemView.findViewById(R.id.bayan_name);
        bayanPriceView = itemView.findViewById(R.id.bayan_price);
        bayanImageView = itemView.findViewById(R.id.bayan_icon);

    }

    public void bindName(String name) {
        bayanNameView.setText(name);
    }

    public void bindPrice(String price) {
        bayanPriceView.setText(price);
    }

    public void bindImage(byte[] image) {

        new Thread(() -> {

            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            bayanImageView.setImageBitmap(bitmap);

        }).start();
    }

    public static BayanViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bayan, parent, false);
        return new BayanViewHolder(view);
    }
}
