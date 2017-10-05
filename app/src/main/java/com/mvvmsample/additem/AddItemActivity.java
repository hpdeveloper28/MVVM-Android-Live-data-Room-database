package com.mvvmsample.additem;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.mvvmsample.AppCompatLifecycleActivity;
import com.mvvmsample.MyApplication;
import com.mvvmsample.R;
import com.mvvmsample.db.ItemModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddItemActivity extends AppCompatLifecycleActivity {

    private AddItemModel addItemModel;

    @BindView(R.id.edtItem)
    AppCompatEditText edtItem;
    @OnClick(R.id.btnAddItem)
    public void onAddItem(View view) {
        MyApplication.runInBackground(new Runnable() {
            @Override
            public void run() {
                addItemModel.addItem(new ItemModel(edtItem.getText().toString().trim()));
                MyApplication.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        addItemModel = ViewModelProviders.of(this).get(AddItemModel.class);

    }
}
