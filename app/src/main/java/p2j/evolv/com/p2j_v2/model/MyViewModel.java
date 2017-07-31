package p2j.evolv.com.p2j_v2.model;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import p2j.evolv.com.p2j_v2.R;

public class MyViewModel extends ViewModel {
  public final ObservableList<String> items = new ObservableArrayList<>();
  public final ItemBinding<String> itemBinding = ItemBinding.of(R.id.list_view, R.layout.list_element);
}