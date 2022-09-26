package com.example.androidassignment.view

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.androidassignment.R
import com.example.androidassignment.databinding.ActivityDisplayBinding
import com.example.androidassignment.utils.AppConstants
import com.example.lib_network.Models.CustomUiResponse
import com.example.lib_network.Models.Uidata
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DisplayActivity : AppCompatActivity() {
    private lateinit  var binding: ActivityDisplayBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userData = intent.getSerializableExtra(AppConstants.INTENT_TAG) as HashMap<*, *>

        //Input from the HomeActivity is shown here
        binding.userInputData.text = """
            USERNAME    -> ${userData.get("text_name")}
            PHONENUMBER -> ${userData.get("text_phone")}
            CITY        -> ${userData.get("text_city")}
        """.trimIndent()
        
    }
}


/*
private fun createViewElement(lable: HomeActivity.ViewType, ui1: Uidata, ui2: Uidata?): View {
    val layoutParamter = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    if (lable.equals(HomeActivity.ViewType.LABEL)) {
        return TextInputLayout(
            context
        ).apply {
            layoutParams = layoutParamter
            boxBackgroundColor = Color.WHITE
            boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
            setBoxCornerRadii(4f, 4f, 4f, 4f)
            hint = ui1.value
            isExpandedHintEnabled = false
            val editText = TextInputEditText(this.context)
            editText.layoutParams = layoutParamter
            editText.hint = ui2?.hint
            editText.tag=ui2?.key
            editText.setHintTextColor(ContextCompat.getColor(context, R.color.hint_color))
            addView(editText)
        }
    } else if (lable.equals(HomeActivity.ViewType.BUTTON)) {
        val btn =  MaterialButton(context)
        btn.text = ui1.value
        btn.cornerRadius = 40
        btn.layoutParams = layoutParamter
        return btn

    }
    else
        return View(context)
}*/
