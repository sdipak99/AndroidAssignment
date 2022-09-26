package com.example.androidassignment.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.lifecycle.ViewModelProvider
import com.example.androidassignment.viewmodel.HomeViewModel
import com.example.androidassignment.R
import com.example.androidassignment.databinding.ActivityHomeBinding
import com.example.androidassignment.databinding.ButtonBinding
import com.example.androidassignment.databinding.TextInputEtBinding
import com.example.androidassignment.utils.AppConstants
import com.example.lib_network.Models.Uidata
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var context: Context
    private val userInput = HashMap<String,String>()
    private var viewID = 1
    private var previousViewId = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.fetchCustomUI(AppConstants.UI_URL)

        viewModel.customUiObject.observe(this) {
            renderUi(binding.layoutcontainer, it.uidata)
            setSubmitClickListener()
        }

        viewModel.errorResponse.observe(this) {
            Toast.makeText(this, it ?: AppConstants.APIERROR, Toast.LENGTH_SHORT).show()
        }



    }

    //add click listener to the submit button that is dynamically added
    private fun setSubmitClickListener() {

        //SUBMIT is the last button in the layout container
        if(binding.layoutcontainer.getChildAt(binding.layoutcontainer.childCount-1)==null)
            return

        //get all the texts from TextInputEditTexts
        binding.layoutcontainer.getChildAt(binding.layoutcontainer.childCount-1).setOnClickListener{
                for(i in 0..binding.layoutcontainer.childCount){
                    val currentView = binding.layoutcontainer.getChildAt(i)
                    if( currentView is TextInputLayout)
                        userInput[currentView.editText?.tag.toString()] = currentView.editText?.text.toString()

                }

                //navigate to the next activity
                navigateToNextActivity(userInput)
            }

    }

    //the layoutContainer(LinearLayout) will be added with new UIs
    fun renderUi(layoutcontainer: ConstraintLayout, uidata: List<Uidata>) {
        val set = ConstraintSet()
        set.clone(binding.layoutcontainer)
        var index = 0
        while (index < uidata.size) {
            val mIndex = index + 1

            //whenever a lable is found in the data, the next element is the edit text so combine them inside a textinputedittext
            when (uidata.get(index).uitype.uppercase()) {
                ViewType.LABEL.name -> {
                    if ((mIndex < uidata.size - 1) && (uidata.get(mIndex).uitype.uppercase()
                            .equals(ViewType.EDITTEXT.name))
                    ) {
                        val view = createViewElement(
                            ViewType.LABEL,
                            uidata.get(index),
                            uidata.get(mIndex)
                        )
                        view.id = viewID
                        layoutcontainer.addView(view)
                        setConstrainsForViews(set,view)
                        index++;
                    }
                }
                //for the submit button
                ViewType.BUTTON.name -> {
                    val view =  createViewElement(
                        ViewType.BUTTON,
                        uidata.get(index),
                        null
                    )
                    view.id = viewID
                    layoutcontainer.addView(view)
                    setConstrainsForViews(set,view)
                    set.applyTo(binding.layoutcontainer)
                }

            }
            viewID++;
            index++
        }
        set.applyTo(binding.layoutcontainer)


    }

    private fun setConstrainsForViews(set:ConstraintSet,view:View) {
        if(view is Button){
            set.constrainPercentWidth(view.id, 0.3F);
            set.constrainHeight(view.id,ConstraintSet.WRAP_CONTENT)
        }
        else {
            set.constrainWidth(view.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
            set.constrainHeight(view.id, ConstraintSet.WRAP_CONTENT)
        }
        set.connect(view.id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START)
        set.connect(view.id,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END)
        if(previousViewId == 0) {
            set.connect(view.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        }
        else {
            set.connect(view.id, ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM,24)
        }
        previousViewId = view.id
    }

    //this will actually create the new view-elements
    private fun createViewElement(lable: ViewType, ui1: Uidata, ui2: Uidata?): View {
        if (lable.equals(ViewType.LABEL)) {
            val textInputLayout = TextInputEtBinding.inflate(layoutInflater).til
            textInputLayout.hint = ui1.value
            val editText = textInputLayout.editText
            editText?.hint = ui2?.hint
            editText?.tag=ui2?.key
            return textInputLayout

        } else if (lable.equals(ViewType.BUTTON)) {
            val btn = ButtonBinding.inflate(layoutInflater).mbutton
            btn.text = ui1.value
            return btn

        }
        else
            return View(context)
    }


    //enum class to determine the view type
    enum class ViewType {
        LABEL,
        EDITTEXT,
        BUTTON
    }

    fun navigateToNextActivity(userInput:HashMap<String,String>) {
        val intent = Intent(this, DisplayActivity::class.java)
        intent.putExtra(AppConstants.INTENT_TAG,userInput)
        startActivity(intent)
    }
}