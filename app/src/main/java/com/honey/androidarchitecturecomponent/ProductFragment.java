package com.honey.androidarchitecturecomponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProductFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView productInfo = view.findViewById(R.id.tv_product_info);
        ProductFragmentArgs productFragmentArgs = ProductFragmentArgs.fromBundle(getArguments());

        productInfo.setText(
                "Product Name: " + productFragmentArgs.getProductName() +
                "\n" +
                "Amount: " + productFragmentArgs.getAmount() +
                "\n" +
                "Item Count: " + productFragmentArgs.getItemCount()
        );
    }
}
