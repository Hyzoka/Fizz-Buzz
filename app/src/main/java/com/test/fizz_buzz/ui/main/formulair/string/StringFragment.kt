package com.test.fizz_buzz.ui.main.formulair.string

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.test.fizz_buzz.R
import com.test.fizz_buzz.model.Strings
import com.test.fizz_buzz.ui.main.ComponentListener
import com.test.fizz_buzz.ui.main.base.BaseFragment

class StringFragment : BaseFragment<StringViewModel>() {

    private lateinit var confirmBtn: LinearLayout
    private lateinit var str1: EditText
    private lateinit var str2: EditText
    protected lateinit var listener: ComponentListener

    companion object {
        fun newInstance(listener: ComponentListener): StringFragment {
            return StringFragment().apply { this.listener = listener }
        }
    }

    override fun layoutId() = R.layout.fragment_string

    override fun viewModel() = ViewModelProvider(this).get(StringViewModel::class.java)

    override fun setupView() {
        viewModel = ViewModelProvider(this).get(StringViewModel::class.java)
        confirmBtn = requireView().findViewById(R.id.confirm_button)
        str1 = requireView().findViewById(R.id.str1)
        str2 = requireView().findViewById(R.id.str2)
        setupListener()
    }

    override fun setupViewModel() {
        str1.addTextChangedListener(
            GenericTextWatcher(
                requireContext(),
                str1,
                confirmBtn,
                viewModel
            )
        )
        str2.addTextChangedListener(
            GenericTextWatcher(
                requireContext(),
                str2,
                confirmBtn,
                viewModel
            )
        )
    }

    private fun setupListener() {
        confirmBtn.setOnClickListener {
            if (viewModel.isFormValid.value) {
                closeKeyboard()
                listener.onNext(Strings(viewModel.string1.value, viewModel.string2.value))
            } else {
                view?.let { it1 ->
                    Snackbar.make(
                        it1,
                        getString(R.string.empty_data),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private class GenericTextWatcher(
        var context: Context,
        private val view: View,
        private val btn: View,
        var viewModel: StringViewModel
    ) :
        TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view.id) {
                R.id.str1 -> viewModel.setString1(text)
                R.id.str2 -> viewModel.setString2(text)
            }
            if (viewModel.isFormValid.value) {
                btn.background =
                    ContextCompat.getDrawable(context, R.drawable.round_courner_36_black)
            } else {
                btn.background =
                    ContextCompat.getDrawable(context, R.drawable.round_courner_36_grey)
            }
        }
    }
}
