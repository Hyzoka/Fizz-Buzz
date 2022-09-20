package com.test.fizz_buzz.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.fizz_buzz.R
import com.test.fizz_buzz.ui.main.formulair.entier.EntierFragment
import com.test.fizz_buzz.ui.main.formulair.limit.LimitFragment
import com.test.fizz_buzz.ui.main.formulair.string.StringFragment
import com.test.fizz_buzz.model.Entiers
import com.test.fizz_buzz.model.ResultGame
import com.test.fizz_buzz.model.Strings
import com.test.fizz_buzz.ui.main.result.ResultFragment

class MainActivity : AppCompatActivity() , ComponentListener{

    private lateinit var viewModel: MainViewModel

    private lateinit var entierFragment : EntierFragment
    private lateinit var stringFragment : StringFragment
    private lateinit var resultFragment : ResultFragment
    private lateinit var limitFragment : LimitFragment
    private var currentFragment : Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initFragments()
    }

    private fun initFragments(){
        entierFragment = EntierFragment.newInstance(this)
        stringFragment = StringFragment.newInstance(this)
        limitFragment = LimitFragment.newInstance(this)
        initFragmentsFlow()
    }

    private fun initFragmentsFlow(){
        currentFragment = entierFragment
        showCurrentFragment()
    }

    private fun showCurrentFragment(){
        currentFragment?.let { fragment ->
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter_from_left, android.R.anim.slide_out_right,
                R.anim.enter_from_left, android.R.anim.fade_out)
            if(!fragment.isAdded){
                transaction.add(R.id.container, fragment)
                transaction.addToBackStack(null)
            }else{
                transaction.replace(R.id.container, fragment)
            }
            transaction.commit()
        }
    }


    override fun onNext(data: Any?) {
        when(currentFragment) {
            is EntierFragment -> {
                data.let {
                    (it as Entiers)
                    viewModel.entier1 = it.entier1
                    viewModel.entier2 = it.entier2
                }
                currentFragment = stringFragment
                showCurrentFragment()
            }
            is StringFragment -> {
                data.let {
                    (it as Strings)
                    viewModel.str1 = it.str1
                    viewModel.str2 = it.str2

                }
                currentFragment = limitFragment
                showCurrentFragment()
            }
            is LimitFragment -> {
                data.let {
                    (it as Int)
                    viewModel.limit = it
                }
                resultFragment = ResultFragment.newInstance(this, ResultGame(viewModel.getEntiers(),viewModel.getStrings(),viewModel.getRange()))
                currentFragment = resultFragment
                showCurrentFragment()
            }
            is ResultFragment -> {
                currentFragment = entierFragment
                showCurrentFragment()
            }
        }
    }

    override fun onBackPressed() {
        when(currentFragment){
            is EntierFragment -> finish()

            is StringFragment -> {
                currentFragment = entierFragment
                backFragment()
            }
            is LimitFragment -> {
                currentFragment = stringFragment
                backFragment()
            }
            is ResultFragment -> {
               finish()
            }
        }
    }

    private fun backFragment(){
        supportFragmentManager.popBackStack()
    }
}