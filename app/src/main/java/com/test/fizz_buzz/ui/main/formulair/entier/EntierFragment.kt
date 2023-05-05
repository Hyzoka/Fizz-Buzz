package com.test.fizz_buzz.ui.main.formulair.entier

import android.content.Context
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
import com.test.fizz_buzz.model.Entiers
import com.test.fizz_buzz.ui.main.base.BaseFragment

class EntierFragment : BaseFragment<EntierViewModel>() {

    private lateinit var confirmBtn: LinearLayout
    private lateinit var entier1: EditText
    private lateinit var entier2: EditText
    protected lateinit var listener: ComponentListener

    companion object {
        fun newInstance(listener: ComponentListener): EntierFragment {
            return EntierFragment().apply { this.listener = listener }
        }
    }

    override fun layoutId() = R.layout.fragment_entier

    override fun viewModel() = ViewModelProvider(this).get(EntierViewModel::class.java)

    override fun setupView() {
        confirmBtn = requireView().findViewById(R.id.confirm_button)
        entier1 = requireView().findViewById(R.id.ent1)
        entier2 = requireView().findViewById(R.id.ent2)
        setupListener()
    }

    override fun setupViewModel() {
        entier1.addTextChangedListener(
            GenericTextWatcher(
                requireContext(),
                entier1,
                confirmBtn,
                viewModel
            )
        )
        entier2.addTextChangedListener(
            GenericTextWatcher(
                requireContext(),
                entier2,
                confirmBtn,
                viewModel
            )
        )
    }

    private fun setupListener() {
        confirmBtn.setOnClickListener {
            if (viewModel.isFormValid.value) {
                closeKeyboard()
                Log.i("DATAEDZA","ENTIER DATAEDZA = ${viewModel.entier1.value.toInt()} :  ${viewModel.entier2.value.toInt()}")
                listener.onNext(
                    Entiers(
                        viewModel.entier1.value.toInt(),
                        viewModel.entier2.value.toInt()
                    )
                )
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
        var viewModel: EntierViewModel
    ) :
        TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view.id) {
                R.id.ent1 -> viewModel.setEntier1(text)
                R.id.ent2 -> viewModel.setEntier2(text)
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