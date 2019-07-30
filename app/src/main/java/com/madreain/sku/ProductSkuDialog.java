package com.madreain.sku;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.madreain.sku.bean.ProductData;
import com.madreain.sku.bean.Sku;
import com.madreain.sku.bean.SkuAttribute;
import com.madreain.sku.view.OnSkuListener;
import com.madreain.sku.view.SkuSelectScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：
 */
public class ProductSkuDialog extends Dialog {

    @BindView(R.id.ib_sku_close)
    ImageButton ibSkuClose;
    @BindView(R.id.tv_sku_info)
    TextView tvSkuInfo;
    @BindView(R.id.tv_sku_selling_price)
    TextView tvSkuSellingPrice;
    @BindView(R.id.scroll_sku_list)
    SkuSelectScrollView scrollSkuList;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.iv_sku_logo)
    ImageView ivSkuLogo;
    @BindView(R.id.tv_sku_quantity_label)
    TextView tvSkuQuantityLabel;
    @BindView(R.id.tv_sku_quantity)
    TextView tvSkuQuantity;
    @BindView(R.id.btn_sku_quantity_minus)
    TextView btnSkuQuantityMinus;
    @BindView(R.id.et_sku_quantity_input)
    EditText etSkuQuantityInput;
    @BindView(R.id.btn_sku_quantity_plus)
    TextView btnSkuQuantityPlus;


    private Context context;
    private ProductData product;
    private List<Sku> skuList;
    private Callback callback;
    private Sku selectedSku;
    private String priceFormat;
    private String stockQuantityFormat;

    public ProductSkuDialog(@NonNull Context context) {
        this(context, R.style.CommonBottomDialogStyle);
    }

    public ProductSkuDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.dialog_sku, null);
        Window window = this.getWindow();
        window.setContentView(view);
        ButterKnife.bind(this, view);
        //关闭
        ibSkuClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnSkuQuantityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = etSkuQuantityInput.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt > 1) {
                    String newQuantity = String.valueOf(quantityInt - 1);
                    etSkuQuantityInput.setText(newQuantity);
                    etSkuQuantityInput.setSelection(newQuantity.length());
                    updateQuantityOperator(quantityInt - 1);
                }
            }
        });
        btnSkuQuantityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = etSkuQuantityInput.getText().toString();
                if (TextUtils.isEmpty(quantity) || selectedSku == null) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt < selectedSku.getStockQuantity()) {
                    String newQuantity = String.valueOf(quantityInt + 1);
                    etSkuQuantityInput.setText(newQuantity);
                    etSkuQuantityInput.setSelection(newQuantity.length());
                    updateQuantityOperator(quantityInt + 1);
                }
            }
        });
        etSkuQuantityInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != EditorInfo.IME_ACTION_DONE || selectedSku == null) {
                    return false;
                }
                String quantity = etSkuQuantityInput.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return false;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt <= 1) {
                    etSkuQuantityInput.setText("1");
                    etSkuQuantityInput.setSelection(1);
                    updateQuantityOperator(1);
                } else if (quantityInt >= selectedSku.getStockQuantity()) {
                    String newQuantity = String.valueOf(selectedSku.getStockQuantity());
                    etSkuQuantityInput.setText(newQuantity);
                    etSkuQuantityInput.setSelection(newQuantity.length());
                    updateQuantityOperator(selectedSku.getStockQuantity());
                } else {
                    etSkuQuantityInput.setSelection(quantity.length());
                    updateQuantityOperator(quantityInt);
                }
                return false;
            }
        });
        scrollSkuList.setListener(new OnSkuListener() {
            @Override
            public void onUnselected(SkuAttribute unselectedAttribute) {
                selectedSku = null;
                //默认第一张照片
                GlideApp.with(context).load(product.getPictureUrl()).into(ivSkuLogo);
                tvSkuQuantity.setText(String.format(stockQuantityFormat, product.getStockQuantity()));
                String selected = getSelected();
                //设置回掉
                callback.onSelect(selected);
                //增加前缀
                if (isHaveSelect()) {
                    selected = "已选： " + selected;
                } else {
                    selected = "请选择： " + selected;
                    tvSkuSellingPrice.setText(String.format(priceFormat, product.getMinPrice()));
                }
                tvSkuInfo.setText(selected);
                btnSubmit.setEnabled(false);
                String quantity = etSkuQuantityInput.getText().toString();
                if (!TextUtils.isEmpty(quantity)) {
                    updateQuantityOperator(Integer.valueOf(quantity));
                } else {
                    updateQuantityOperator(0);
                }
            }

            @Override
            public void onSelect(SkuAttribute selectAttribute) {
                String selected = getSelected();
                //设置回掉
                callback.onSelect(selected);
                //增加前缀
                if (isHaveSelect()) {
                    selected = "已选： " + selected;
                } else {
                    selected = "请选择： " + selected;
                    tvSkuSellingPrice.setText(String.format(priceFormat, product.getMinPrice()));
                }
                tvSkuInfo.setText(selected);
            }

            @Override
            public void onSkuSelected(Sku sku) {
                selectedSku = sku;
                GlideApp.with(context).load(selectedSku.getPictureUrl()).into(ivSkuLogo);
                List<SkuAttribute> attributeList = selectedSku.getAttributes();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < attributeList.size(); i++) {
                    if (i != 0) {
                        builder.append("　");
                    }
                    SkuAttribute attribute = attributeList.get(i);
                    builder.append("\"" + attribute.getValue() + "\"");
                }
                tvSkuInfo.setText("已选：" + builder.toString());
                setHaveSelect(true);
                //设置价格
                tvSkuSellingPrice.setText(String.format(priceFormat, sku.getPrice()));
                callback.onSelect(builder.toString());
                tvSkuQuantity.setText(String.format(stockQuantityFormat, selectedSku.getStockQuantity()));
                btnSubmit.setEnabled(true);
                String quantity = etSkuQuantityInput.getText().toString();
                if (!TextUtils.isEmpty(quantity)) {
                    updateQuantityOperator(Integer.valueOf(quantity));
                } else {
                    updateQuantityOperator(0);
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = etSkuQuantityInput.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt > 0 && quantityInt <= selectedSku.getStockQuantity()) {
                    callback.onAdded(selectedSku, quantityInt);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "商品数量超出库存，请修改数量", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setData(final ProductData product, Callback callback) {
        this.product = product;
        this.skuList = product.getSkus();
        this.callback = callback;
        priceFormat = context.getString(R.string.comm_price_format);
        stockQuantityFormat = context.getString(R.string.sku_stock);
        updateSkuData();
        updateQuantityOperator(1);
    }

    private void updateSkuData() {
        scrollSkuList.setSkuList(product.getSkus());
        //默认选择第一个
        Sku firstSku = product.getSkus().get(0);
        if (firstSku.getStockQuantity() > 0) {
            selectedSku = firstSku;
            // 选中第一个sku
            scrollSkuList.setSelectedSku(selectedSku);
            GlideApp.with(context).load(product.getPictureUrl()).into(ivSkuLogo);
            tvSkuSellingPrice.setText(String.format(priceFormat, selectedSku.getPrice()));
            tvSkuQuantity.setText(String.format(stockQuantityFormat, selectedSku.getStockQuantity()));
            btnSubmit.setEnabled(selectedSku.getStockQuantity() > 0);
            List<SkuAttribute> attributeList = selectedSku.getAttributes();
            StringBuilder builder = new StringBuilder();
            int attributeListSize = attributeList.size();
            for (int i = 0; i < attributeListSize; i++) {
                if (i != 0) {
                    builder.append("　");
                }
                SkuAttribute attribute = attributeList.get(i);
                builder.append("\"" + attribute.getValue() + "\"");
            }
            tvSkuInfo.setText("已选：" + builder.toString());
            setHaveSelect(true);
            callback.onSelect(builder.toString());
        } else {
            GlideApp.with(context).load(product.getPictureUrl()).into(ivSkuLogo);
            tvSkuSellingPrice.setText(String.format(priceFormat, product.getMinPrice()));
            tvSkuQuantity.setText(String.format(stockQuantityFormat, product.getStockQuantity()));
            btnSubmit.setEnabled(false);
            tvSkuInfo.setText("请选择：" + skuList.get(0).getAttributes().get(0).getKey());
            setHaveSelect(false);
            callback.reUnSelect();
        }
    }

    /**
     * 获取已选中的
     *
     * @return
     */
    public boolean isHaveSelect;

    public boolean isHaveSelect() {
        return isHaveSelect;
    }

    public void setHaveSelect(boolean haveSelect) {
        isHaveSelect = haveSelect;
    }

    /**
     * 获取显示的内容
     *
     * @return
     */
    private String getSelected() {
        List<SkuAttribute> skuAttributeList = scrollSkuList.getSelectedAttributeList();
        if (skuAttributeList != null) {
            //是否有属性是选中的
            setHaveSelect(false);
            StringBuilder stringBuilder = new StringBuilder();
            int skuAttributeListSize = skuAttributeList.size();
            for (int i = 0; i < skuAttributeListSize; i++) {
                SkuAttribute skuAttribute = skuAttributeList.get(i);
                if (i != 0) {
                    stringBuilder.append("  ");
                }
                if (TextUtils.isEmpty(skuAttribute.getValue())) {
                    stringBuilder.append(skuAttribute.getKey());
                } else {
                    stringBuilder.append(skuAttribute.getValue());
                    setHaveSelect(true);
                }
            }
            return stringBuilder.toString();
        } else
            return null;
    }

    private void updateQuantityOperator(int newQuantity) {
        if (selectedSku == null) {
            btnSkuQuantityMinus.setEnabled(false);
            btnSkuQuantityPlus.setEnabled(false);
            etSkuQuantityInput.setEnabled(false);
        } else {
            if (newQuantity <= 1) {
                btnSkuQuantityMinus.setEnabled(false);
                btnSkuQuantityPlus.setEnabled(true);
            } else if (newQuantity >= selectedSku.getStockQuantity()) {
                btnSkuQuantityMinus.setEnabled(true);
                btnSkuQuantityPlus.setEnabled(false);
            } else {
                btnSkuQuantityMinus.setEnabled(true);
                btnSkuQuantityPlus.setEnabled(true);
            }
            etSkuQuantityInput.setEnabled(true);
        }

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 解决键盘遮挡输入框问题
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.getDecorView().setPadding(0, 0, 0, 0);
    }

    public interface Callback {
        //添加购物车
        void onAdded(Sku sku, int quantity);

        //已选
        void onSelect(String selected);

        //恢复默认未选
        void reUnSelect();
    }

}
