package com.woowa.accountbook.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.woowa.accountbook.R
import com.woowa.accountbook.databinding.ActivityMainBinding
import com.woowa.accountbook.ui.calendar.CalendarFragment
import com.woowa.accountbook.ui.history.HistoryFragment
import com.woowa.accountbook.ui.setting.SettingFragment
import com.woowa.accountbook.ui.statistic.StatisticFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val accountBookViewModel by viewModels<AccountBookViewModel>()

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
            when (menu.itemId) {
                R.id.item_history -> changeFragment(
                    HistoryFragment(),
                    getString(R.string.fragment_history),
                    false
                )
                R.id.item_calendar -> changeFragment(
                    CalendarFragment(),
                    getString(R.string.fragment_calendar),
                    false
                )
                R.id.item_statistic -> changeFragment(
                    StatisticFragment(),
                    getString(R.string.fragment_statistic),
                    false
                )
                R.id.item_setting -> changeFragment(
                    SettingFragment(),
                    getString(R.string.fragment_setting),
                    false
                )
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String, addBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment, tag)
            .let {
                if (addBackStack) it.addToBackStack(tag)
                it.commitAllowingStateLoss()
            }
    }
}