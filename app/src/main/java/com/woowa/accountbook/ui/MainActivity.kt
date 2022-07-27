package com.woowa.accountbook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.woowa.accountbook.R
import com.woowa.accountbook.databinding.ActivityMainBinding
import com.woowa.accountbook.ui.history.HistoryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        bnvSetting()
        changeFragment(HistoryFragment(), getString(R.string.fragment_history), false)
    }

    private fun bnvSetting() {
        binding.bnvMain.setOnItemSelectedListener { menu ->
            when(menu.itemId){
                R.id.item_history -> changeFragment(HistoryFragment(), getString(R.string.fragment_history), false)
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String, addBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment, tag)
            .let {
                if(addBackStack) it.addToBackStack(tag)
                it.commitAllowingStateLoss()
            }
    }
}