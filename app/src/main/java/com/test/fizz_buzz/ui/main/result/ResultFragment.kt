package com.test.fizz_buzz.ui.main.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.test.fizz_buzz.R
import com.test.fizz_buzz.ui.main.ComponentListener
import com.test.fizz_buzz.model.ResultGame

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel
    protected lateinit var listener : ComponentListener
    protected lateinit var resultData: ResultGame
    private lateinit var resultRv : RecyclerView
    private lateinit var restbtn : Button

    private val adapterResult = FastItemAdapter<IItem<*, *>>()


    companion object {
        fun newInstance(listener: ComponentListener, resultData : ResultGame): ResultFragment {
            return ResultFragment().apply { this.listener = listener
            this.resultData = resultData}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        resultRv = requireActivity().findViewById(R.id.rvData)
        restbtn = requireActivity().findViewById(R.id.reset)
        initList()
        setupListResult()
    }

    private fun initList(){
        viewModel.changeMultiples(resultData.entiers.entier1,resultData.entiers.entier2,resultData.limit,resultData.Strings.str1,resultData.Strings.str2)
        adapterResult.clear()
        adapterResult.add(viewModel.listFinal .map { ResultatItem(it) })
    }

    private fun setupListResult(){
        resultRv.layoutManager = LinearLayoutManager(context)
        resultRv.adapter = adapterResult

        adapterResult.withOnPreClickListener { _, _, _, _ ->
            false
        }

        restbtn.setOnClickListener {
            listener.onNext()
        }
    }
}
