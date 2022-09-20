package com.test.fizz_buzz.ui.main.formulair.string

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.fizz_buzz.R
import com.test.fizz_buzz.ui.main.ComponentListener
import com.test.fizz_buzz.model.Strings
import com.test.fizz_buzz.ui.main.formulair.entier.EntierViewModel

class StringFragment : Fragment(){

    private lateinit var confirmBtn : LinearLayout
    private lateinit var str1 : EditText
    private lateinit var str2 : EditText
    protected lateinit var listener : ComponentListener
    private lateinit var viewModel: StringViewModel

    companion object {
        fun newInstance(listener: ComponentListener): StringFragment {
            return StringFragment().apply { this.listener = listener }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_string, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StringViewModel::class.java)
        confirmBtn = requireView().findViewById(R.id.confirm_button)
        str1 = requireView().findViewById(R.id.str1)
        str2 = requireView().findViewById(R.id.str2)
        setupListener()
    }

    private fun setupListener(){
        confirmBtn.setOnClickListener {
            if (viewModel.checkIsNotEmpty()){
                listener.onNext(Strings(viewModel.str1!!,viewModel.str2!!))
            }
            else{
                Toast.makeText(context,getText(R.string.empty_data), Toast.LENGTH_SHORT).show()
            }
        }

        str1.addTextChangedListener(GenericTextWatcher(requireContext(),str1,confirmBtn,viewModel))
        str2.addTextChangedListener(GenericTextWatcher(requireContext(),str2,confirmBtn,viewModel))

    }

    private class GenericTextWatcher(var context : Context, private val view: View, private val btn: View, var viewModel: StringViewModel) :
        TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view.id) {
                R.id.str1 -> viewModel.str1 = text
                R.id.str2 -> viewModel.str2 = text
            }
            if (viewModel.checkIsNotEmpty()){
                btn.background = ContextCompat.getDrawable(context, R.drawable.round_courner_36_black)
            }
            else{
                btn.background = ContextCompat.getDrawable(context, R.drawable.round_courner_36_grey)
            }
        }
    }
}
