package com.honey.androidarchitecturecomponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

public class CartFragment extends Fragment {

    private TextInputEditText tieProductName;
    private TextInputEditText tieAmount;
    private TextInputEditText tieItemCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button productDetails = view.findViewById(R.id.btn_product_details);
        tieProductName = view.findViewById(R.id.ti_product_name);
        tieAmount = view.findViewById(R.id.ti_amount);
        tieItemCount = view.findViewById(R.id.ti_item_count);

        productDetails.setOnClickListener(new View.OnClickListener() {

            String productName = null;
            float amount = 0.0f;
            int itemCount = 0;

            @Override
            public void onClick(View v) {
                if (tieProductName.getText() != null) {
                    productName = tieProductName.getText().toString().trim();
                }
                if (tieAmount.getText() != null) {
                    amount = Float.parseFloat(tieAmount.getText().toString().trim());
                }

                if (tieItemCount.getText() != null) {
                    itemCount = Integer.parseInt(tieItemCount.getText().toString().trim());
                }
                CartFragmentDirections.NextAction nextAction = CartFragmentDirections
                        .nextAction(productName == null ? "This is a sample product" : productName)
                        .setAmount(amount)
                        .setItemCount(100);
                Navigation.findNavController(v).navigate(nextAction);
            }
        });
    }
}
