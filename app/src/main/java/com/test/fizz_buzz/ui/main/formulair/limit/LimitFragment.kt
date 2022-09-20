package com.test.fizz_buzz.ui.main.formulair.limit

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
import com.test.fizz_buzz.ui.main.formulair.entier.EntierViewModel

class LimitFragment : Fragment(){

    private lateinit var confirmBtn : LinearLayout
    private lateinit var limit : EditText

    protected lateinit var listener : ComponentListener
    private lateinit var viewModel: LimitViewModel

    companion object {
        fun newInstance(listener: ComponentListener): LimitFragment {
            return LimitFragment().apply { this.listener = listener }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_limit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LimitViewModel::class.java)
        confirmBtn = requireView().findViewById(R.id.confirm_button)
        limit = requireView().findViewById(R.id.limit)
        setupListener()
    }

    private fun setupListener(){
        confirmBtn.setOnClickListener {
            if (viewModel.checkIsNotEmpty()){
                listener.onNext(viewModel.limit!!.toInt())
            }
            else{
                Toast.makeText(context,getText(R.string.empty_data), Toast.LENGTH_SHORT).show()
            }
        }
        limit.addTextChangedListener(GenericTextWatcher(requireContext(),limit,confirmBtn,viewModel))

    }

    private class GenericTextWatcher(var context : Context, private val view: View, private val btn: View, var viewModel: LimitViewModel) :
        TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view.id) {
                R.id.limit -> viewModel.limit = text
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
