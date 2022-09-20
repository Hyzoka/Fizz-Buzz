package com.test.fizz_buzz.ui.main.formulair.entier

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.test.fizz_buzz.model.Entiers

class EntierFragment : Fragment() {

    private lateinit var confirmBtn : LinearLayout
    private lateinit var entier1 : EditText
    private lateinit var entier2 : EditText
    protected lateinit var listener : ComponentListener

    companion object {
        fun newInstance(listener: ComponentListener): EntierFragment {
            return EntierFragment().apply { this.listener = listener }
        }
    }

    private lateinit var viewModel: EntierViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_entier, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        confirmBtn = requireView().findViewById(R.id.confirm_button)
        entier1 = requireView().findViewById(R.id.ent1)
        entier2 = requireView().findViewById(R.id.ent2)
        viewModel = ViewModelProvider(this).get(EntierViewModel::class.java)
        viewModel.checkIsNotEmpty()
        setupListener()
    }

    private fun setupListener(){
        confirmBtn.setOnClickListener {
            if (viewModel.checkIsNotEmpty()){
                listener.onNext(Entiers(viewModel.entier1!!.toInt(),viewModel.entier2!!.toInt()))
            }
            else{
                Toast.makeText(context,getText(R.string.empty_data),Toast.LENGTH_SHORT).show()
            }
        }

        entier1.addTextChangedListener(GenericTextWatcher(requireContext(),entier1,confirmBtn,viewModel))
        entier2.addTextChangedListener(GenericTextWatcher(requireContext(),entier2,confirmBtn,viewModel))

    }

    private class GenericTextWatcher(var context : Context, private val view: View,private val btn: View, var viewModel: EntierViewModel) :
        TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view.id) {
                R.id.ent1 -> viewModel.entier1 = text
                R.id.ent2 -> viewModel.entier2 = text
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