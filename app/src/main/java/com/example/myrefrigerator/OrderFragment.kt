package com.example.myrefrigerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 inflate하여 반환
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Arguments에서 데이터를 받아옴
        val manual = arguments?.getStringArrayList("rcpmanual")
        var manualstring :String = ""

        for (i in 1..20){
            val tempstring = manual?.get(i-1)
            manualstring = manualstring + tempstring
            manualstring = manualstring +"\n"
        }

        // 받아온 데이터를 사용
        // 예: TextView에 텍스트 설정
        val manualTextView: TextView = view.findViewById(R.id.ofmanual)

        manualTextView.text = "$manualstring"
    }
}