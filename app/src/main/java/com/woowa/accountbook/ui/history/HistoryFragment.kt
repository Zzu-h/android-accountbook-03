package com.woowa.accountbook.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.theme.AccountbookTheme

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_history, container, false)

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent {
                AccountbookTheme() {
                    MainAppBar(
                        title = "Hi",
                    )
                }
            }
        }
        return rootView
    }
}