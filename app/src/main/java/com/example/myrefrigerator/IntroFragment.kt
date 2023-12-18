package com.example.myrefrigerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 inflate하여 반환
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Arguments에서 데이터를 받아옴
        val name = arguments?.getString("rcpname")
        val ingre = arguments?.getString("rcpingre")

        // 받아온 데이터를 사용
        // 예: TextView에 텍스트 설정
        val nameTextView: TextView = view.findViewById(R.id.ifname)
        val ingreTextView: TextView = view.findViewById(R.id.ifingre)

        nameTextView.text = "$name"
        ingreTextView.text = "$ingre"
    }
}