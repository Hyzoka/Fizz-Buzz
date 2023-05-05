package com.test.fizz_buzz.ui.main.formulair.limit

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.test.fizz_buzz.R
import com.test.fizz_buzz.ui.main.ComponentListener
import com.test.fizz_buzz.ui.main.base.BaseFragment

class LimitFragment : BaseFragment<LimitViewModel>() {

    private lateinit var confirmBtn: LinearLayout
    private lateinit var limit: EditText

    protected lateinit var listener: ComponentListener

    override fun layoutId() = R.layout.fragment_limit

    override fun viewModel() = ViewModelProvider(this).get(LimitViewModel::class.java)

    override fun setupView() {
        confirmBtn = requireView().findViewById(R.id.confirm_button)
        limit = requireView().findViewById(R.id.limit)
        setupListener()
    }

    override fun setupViewModel() {
        limit.addTextChangedListener(
            GenericTextWatcher(
                requireContext(), limit, confirmBtn, viewModel
            )
        )
    }

    companion object {
        fun newInstance(listener: ComponentListener): LimitFragment {
            return LimitFragment().apply { this.listener = listener }
        }
    }

    private fun setupListener() {
        confirmBtn.setOnClickListener {
            if (viewModel.isFormValid.value) {
                closeKeyboard()
                Log.i("DATAEDZA","LIMIT DATAEDZA = ${viewModel.limit.value.toInt()}")
                listener.onNext(viewModel.limit.value.toInt())
            } else {
                view?.let { it1 ->
                    Snackbar.make(
                        it1, getString(R.string.empty_data), Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private class GenericTextWatcher(
        var context: Context,
        private val view: View,
        private val btn: View,
        var viewModel: LimitViewModel
    ) : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view.id) {
                R.id.limit -> viewModel.setLimit(text)
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
