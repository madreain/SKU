package com.madreain.sku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.madreain.sku.bean.ProductData;
import com.madreain.sku.bean.Sku;

import butterknife.ButterKnife;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：
 */
public class MainActivity extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSkuDialog();
            }
        });
    }

    //商品sku
    private ProductSkuDialog dialog;

    private void showSkuDialog() {
        if (dialog == null) {
            dialog = new ProductSkuDialog(this);
            dialog.setData(ProductData.get(this), new ProductSkuDialog.Callback() {
                @Override
                public void onAdded(Sku sku, int quantity) {
                    //立即购买再跳转到

                }

                @Override
                public void onSelect(String selected) {
                    //默认设置 有选的
                    tv.setText(selected);
                }

                @Override
                public void reUnSelect() {


                }
            });
        }
        dialog.show();
    }

}
