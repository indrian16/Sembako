package io.indrian16.sembako.ui.createitem.view

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jakewharton.rxbinding3.widget.textChanges
import io.indrian16.sembako.R
import io.indrian16.sembako.database.repository.sembako.Sembako
import io.indrian16.sembako.ui.base.view.BaseActivity
import io.indrian16.sembako.ui.createitem.presenter.CreateItemPresenter
import io.indrian16.sembako.util.CommonUtil
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class CreateItemActivity : BaseActivity(), CreateItemView {

    @Inject
    lateinit var presenter: CreateItemPresenter

    private lateinit var mBarcode: EditText
    private lateinit var mTitle: EditText
    private lateinit var mPrice: EditText
    private lateinit var mStock: EditText

    private lateinit var mBarcodeTIL: TextInputLayout
    private lateinit var mTitleTIL: TextInputLayout
    private lateinit var mPriceTIL: TextInputLayout
    private lateinit var mStockTIL: TextInputLayout

    private lateinit var mCreateProduct: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_item)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
        presenter.startValidation()
    }

    private fun initView() {

        mBarcode = findViewById(R.id.edt_barcode)
        mTitle = findViewById(R.id.edt_title)
        mPrice = findViewById(R.id.edt_price)
        mStock = findViewById(R.id.edt_stock)

        mBarcodeTIL = findViewById(R.id.til_barcode)
        mTitleTIL = findViewById(R.id.til_title)
        mPriceTIL = findViewById(R.id.til_price)
        mStockTIL = findViewById(R.id.til_stock)

        mCreateProduct = findViewById(R.id.btn_create)
        mCreateProduct.setOnClickListener { createItem() }
    }

    private fun createItem() {

        val sembako = Sembako()
        sembako.barcode = mBarcode.fastEdtToInt()
        sembako.title = mTitle.text.toString()
        sembako.price = mPrice.fastEdtToInt()
        sembako.stock = mStock.fastEdtToInt()

        presenter.insertItem(sembako)

    }

    private fun EditText.fastEdtToInt(): Int {

        return Integer.parseInt(this.text.toString())
    }

    override fun barcodeChange(): Observable<CharSequence> = mBarcode.textChanges()

    override fun titleChange(): Observable<CharSequence> = mTitle.textChanges()

    override fun priceChange(): Observable<CharSequence> = mPrice.textChanges()

    override fun stockChange(): Observable<CharSequence> = mStock.textChanges()

    override fun showError(throwable: Throwable) {

        Toast.makeText(baseContext, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun showBarcodeError(error: String) {

        mBarcodeTIL.helperText = error
    }

    override fun showTitleError(error: String) {

        mTitleTIL.helperText = error
    }

    override fun showPriceError(error: String) {

        mPriceTIL.helperText = error
    }

    override fun showStockError(error: String) {

        mStockTIL.helperText = error
    }

    override fun hideErrorBarcode() {

        mBarcodeTIL.helperText = ""
    }

    override fun hideErrorTitle() {

        mTitleTIL.helperText = ""
    }

    override fun hideErrorPrice() {

        mPriceTIL.helperText = ""
    }

    override fun hideErrorStock() {

        mStockTIL.helperText = ""
    }

    override fun enableCreateButton() {

        CommonUtil.enableCreateButton(mCreateProduct)
    }

    override fun disableCreateButton() {

        CommonUtil.disableCreateButton(mCreateProduct)
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stopValidation()
    }
}
