package com.woowa.accountbook.ui.common.popup

import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.woowa.accountbook.R
import com.woowa.accountbook.databinding.DatePickerDialogBinding

class MonthYearPickerDialog(private val year: Int, private val month: Int) : DialogFragment() {

    lateinit var binding: DatePickerDialogBinding

    private var listener: OnDateSetListener? = null
    fun setListener(listener: OnDateSetListener?): MonthYearPickerDialog {
        this.listener = listener
        return this
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater

        val dialog: View = inflater.inflate(R.layout.date_picker_dialog, null)
        val monthPicker = dialog.findViewById<NumberPicker>(R.id.picker_month)
        val yearPicker = dialog.findViewById<NumberPicker>(R.id.picker_year)

        monthPicker.minValue = 0
        monthPicker.maxValue = 11
        monthPicker.value = month

        yearPicker.minValue = MIN_YEAR
        yearPicker.maxValue = MAX_YEAR
        yearPicker.value = year

        builder.setView(dialog)
            .setPositiveButton(R.string.ok,
                OnClickListener { dialog, id ->
                    listener!!.onDateSet(
                        null,
                        yearPicker.value,
                        monthPicker.value,
                        0
                    )
                })
            .setNegativeButton(R.string.cancel,
                OnClickListener { dialog, id -> this@MonthYearPickerDialog.dialog!!.cancel() })
        return builder.create()
    }

    companion object {
        private const val MAX_YEAR = 2099
        private const val MIN_YEAR = 2000
    }
}