
This sample app contains the bottom navigation bar and toolbar menu with navigation components.

## Navigate the project build.gradle and add below classpath

Let’s navigate project level build.gradle and below line.
``` 
def nav_version = "2.2.0"
classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
 ```
 
 ## Apply Navigation Safeargs Plugin.

open the app level project build.gradle and this line on top of the file. This plug is helped us a lot in navigation, action and sending argument as well.

```
apply plugin: "androidx.navigation.safeargs"
```

## Add below dependencies

```
def nav_version = "1.0.0"
  def material_version = "1.2.0-alpha03"
  implementation "android.arch.navigation:navigation-fragment:$nav_version"
  implementation "android.arch.navigation:navigation-ui:$nav_version"
  implementation "com.google.android.material:material:$material_version"
```

Check out the latest version of [android.arch.navigation](https://mvnrepository.com/artifact/android.arch.navigation)

## Open main activity layout file and add below code

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <fragment
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toTopOf="@+id/bottom_nav"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/nav_graph"/>

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_nav"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:backgroundTint="@color/colorAccent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        android:background="@color/colorPrimary"
        app:itemIconTint="@color/white"
        app:itemTextColor="@color/white"
        app:menu="@menu/bottom_nav_menu"
        />
</androidx.constraintlayout.widget.ConstraintLayout>
```

## Navigation Resource file

in res create a resource folder name is navigation and create navigation graph file. In file name, I’m taken here nav_graph.xml.
Basically, followings things we are doing here
* Setting up navigation Id
* Set start destination here is a home fragment

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/home_destination">
</navigation>
```
* Declear fragments here

```
<fragment
      android:id="@+id/home_destination"
      android:name="com.navigation.example.fragments.HomeFragment"
      android:label="Home"
      tools:layout="@layout/fragment_home">
</fragment>
```

* Setup action and action destination

```
 <action
        android:id="@+id/next_action"
        app:destination="@+id/profile_destination" />
```

* Setup safe argument as well

```
<android:name="com.navigation.example.fragments.ProductFragment"
      android:label="Product"
      tools:layout="@layout/fragment_product">
    <argument
        android:name="product_name"
        app:argType="string"
        app:nullable="true" />
    <argument
        android:name="amount"
        app:argType="float"
        android:defaultValue="0.0" />
    <argument
        android:name="item_count"
        app:argType="integer"
        android:defaultValue="0" />
  </fragment>
```

## Finally, your nav_graph.xml code looks like this.

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/home_destination">
  <fragment
      android:id="@+id/home_destination"
      android:name="com.navigation.example.fragments.HomeFragment"
      android:label="Home"
      tools:layout="@layout/fragment_home">
    <action
        android:id="@+id/next_action"
        app:destination="@+id/profile_destination" />
  </fragment>
  <fragment
      android:id="@+id/notification_destination"
      android:name="com.navigation.example.fragments.NotificationFragment"
      android:label="Notification"
      tools:layout="@layout/fragment_notification" />
  <fragment
      android:id="@+id/profile_destination"
      android:name="com.navigation.example.fragments.ProfileFragment"
      android:label="Home"
      tools:layout="@layout/fragment_profile">
    <argument
        android:name="productCount"
        android:defaultValue="0"
        app:argType="integer" />
  </fragment>
  <fragment
      android:id="@+id/cart_destination"
      android:name="com.navigation.example.fragments.CartFragment"
      android:label="Cart"
      tools:layout="@layout/fragment_cart">
    <action
        android:id="@+id/next_action"
        app:destination="@+id/product_destination" />
  </fragment>
  <fragment
      android:id="@+id/product_destination"
      android:name="com.navigation.example.fragments.ProductFragment"
      android:label="Product"
      tools:layout="@layout/fragment_product">
    <argument
        android:name="product_name"
        app:argType="string"
        app:nullable="true" />
    <argument
        android:name="amount"
        app:argType="float"
        android:defaultValue="0.0" />
    <argument
        android:name="item_count"
        app:argType="integer"
        android:defaultValue="0" />
  </fragment>
</navigation>
```

## Open HomeFragment and on button click navigate another fragment.

On button click, we are fetching the direction and next action.


```
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonViewProfile = view.findViewById(R.id.buttonViewProfile);
        buttonViewProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                HomeFragmentDirections.NextAction nextAction = HomeFragmentDirections.nextAction();
                nextAction.setProductCount(100);
                navController.navigate(nextAction);
            }
        });
    }
}

```

## Open CartFragment and add below code

On view product button click will move to ProductFragment as pass some arguments.

```
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

```

## Get argument using safe argument

```
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

```


