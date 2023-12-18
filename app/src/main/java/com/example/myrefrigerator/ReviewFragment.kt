package com.example.myrefrigerator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myrefrigerator.databinding.ItemRecyclerviewBinding
import com.google.android.material.textfield.TextInputEditText


class ReviewFragment : Fragment() {

    var reviewexist : Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 inflate하여 반환
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Arguments에서 데이터를 받아옴
        val name = arguments?.getString("rcpname")
        val suc = arguments?.getBoolean("success")

        val nameTextView: TextView = view.findViewById(R.id.rfname)
        val itext: TextInputEditText = view.findViewById(R.id.inputreview)
        val myButton: Button = view.findViewById(R.id.addreview)
        val reviewl: TextView = view.findViewById(R.id.lastreview)

        myButton.setOnClickListener {
            val inputValue = itext.text.toString()
            reviewl.text = inputValue
            reviewexist = true
        }

        nameTextView.text = "$name"

        /*val intent = Intent(context, ItemRecyclerviewBinding::class.java)
        if (reviewexist == true || suc == true){
            intent.putExtra("suc", true)
        }

        startActivity(intent)*/
    }


}