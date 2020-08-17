package com.yacodings.imgtextview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.yacodings.imgtextview.R
import kotlinx.android.synthetic.main.component_imgtextview.view.*

class ImgTextView : LinearLayout {
    var imgSize = resources.getDimensionPixelSize(R.dimen.default_imgSize)
    var imgSrc = ResourcesCompat.getDrawable(resources, android.R.drawable.alert_dark_frame, null)
    var mainColor: Int = ResourcesCompat.getColor(resources, android.R.color.black, null)
    var spacing: Int = 0
    var txtString: String = "預設字串"
    var txtSize = resources.getDimension(R.dimen.default_txtSize)
    constructor(context: Context) : super(context) {
        initViews()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImgTextView)
        getValues(typedArray)
        initViews()
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImgTextView, defStyle, 0)
        getValues(typedArray)
        initViews()
    }
    private fun getValues(typedArray: TypedArray) {
        imgSize = typedArray.getDimensionPixelOffset(
            R.styleable.ImgTextView_imgSize, resources.getDimensionPixelSize(
                R.dimen.default_imgSize))
        imgSrc = typedArray.getDrawable(R.styleable.ImgTextView_imgSrc) ?: ResourcesCompat.getDrawable(resources, android.R.drawable.alert_dark_frame, null)
        mainColor = typedArray.getColor(R.styleable.ImgTextView_mainColor, ResourcesCompat.getColor(resources, android.R.color.black, null))
        spacing = typedArray.getDimensionPixelSize(R.styleable.ImgTextView_spacing, 0)
        txtString = typedArray.getString(R.styleable.ImgTextView_txtString) ?: "預設文字"
        txtSize = typedArray.getDimension(
            R.styleable.ImgTextView_txtSize, resources.getDimension(
                R.dimen.default_txtSize))
        typedArray.recycle()
    }
    private fun initViews() {
        inflate(context, R.layout.component_imgtextview, this)
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val imgLayoutParams = LinearLayout.LayoutParams(imgSize, imgSize)
        img.layoutParams = imgLayoutParams
        img.setImageDrawable(imgSrc)
        img.setColorFilter(mainColor)

        text.text = txtString
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize)
        text.setTextColor(mainColor)
        val txtLayoutParams = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        txtLayoutParams.setMargins(0, spacing, 0, 0)
        text.layoutParams = txtLayoutParams
    }
}