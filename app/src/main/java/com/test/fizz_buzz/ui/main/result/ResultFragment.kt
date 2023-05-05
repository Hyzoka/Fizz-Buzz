package com.test.fizz_buzz.ui.main.result

import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.test.fizz_buzz.R
import com.test.fizz_buzz.ui.main.ComponentListener
import com.test.fizz_buzz.model.ResultGame
import com.test.fizz_buzz.ui.main.base.BaseFragment

class ResultFragment : BaseFragment<ResultViewModel>() {

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

    override fun layoutId() = R.layout.fragment_result

    override fun viewModel() = ViewModelProvider(this).get(ResultViewModel::class.java)

    override fun setupView() {
        resultRv = requireActivity().findViewById(R.id.rvData)
        restbtn = requireActivity().findViewById(R.id.reset)
        setupListResult()
    }

    override fun setupViewModel() {
        initList()
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