package com.huda.dicodingcourse

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }

        val btnMoveActivity : Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveData : Button = findViewById(R.id.btn_move_data)
        btnMoveData.setOnClickListener(this)

        val btnDialNumber : Button = findViewById(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener(this)

        val btnBrowser : Button = findViewById(R.id.btn_browser)
        btnBrowser.setOnClickListener(this)

        val btnViewGroup : Button = findViewById(R.id.btn_view_group)
        btnViewGroup.setOnClickListener(this)

        val btnRecyclerView : Button = findViewById(R.id.btn_recycler_view)
        btnRecyclerView.setOnClickListener(this)

        val btnFragmentMainActivity : Button = findViewById(R.id.btn_fragmentmain_view)
        btnFragmentMainActivity.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmptyFields = false

            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }

            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }

        when (v?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Huda Putra Santosa")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 21)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_dial_number ->{
                val numberPhone = "085156890287"
                val dialNumberIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$numberPhone"))
                startActivity(dialNumberIntent)
            }
            R.id.btn_view_group -> {
                val viewGroupIntent = Intent(this@MainActivity, ViewgroupActivity::class.java)
                startActivity(viewGroupIntent)
            }

            R.id.btn_browser -> {
                val url = "https://hudaputrasantosa.github.io/"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$url"))
                startActivity(browserIntent)

            }
            R.id.btn_recycler_view -> {
                val recyclerViewIntent = Intent(this@MainActivity, RecyclerViewActivity::class.java)
                startActivity(recyclerViewIntent)
            }

            R.id.btn_fragmentmain_view -> {
                startActivity(Intent(this@MainActivity, FragmentMainActivity::class.java))
            }
            }
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}