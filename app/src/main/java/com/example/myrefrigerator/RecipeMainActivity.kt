package com.example.myrefrigerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrefrigerator.databinding.ActivityRecipeMainBinding
import com.example.myrefrigerator.databinding.ItemRecyclerviewBinding
import org.w3c.dom.Element
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class RecipeMainActivity : AppCompatActivity() {

    private val names = mutableListOf<String>()
    private val mingres = mutableListOf<String>()
    private val images = mutableListOf<String>()
    private val ingres = mutableListOf<String>()
    private val manuallists = mutableListOf<List<String>>()

    private var searchlist = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_main)

        setSupportActionBar(findViewById(R.id.rmtoolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val thread = NetworkThread()
        thread.start()
        thread.join()


        // Inflate the layout for this fragment

        val recyclerview :RecyclerView = findViewById(R.id.recyclerview)

        val layoutManager = GridLayoutManager(this, 1)
        recyclerview.layoutManager = layoutManager
        val adapter = MyAdapter(names, mingres, images, ingres, manuallists)
        recyclerview.adapter = adapter
        recyclerview.addItemDecoration(
            DividerItemDecoration(this, GridLayoutManager.VERTICAL)
        )
        recyclerview.addItemDecoration(
            DividerItemDecoration(this, GridLayoutManager.HORIZONTAL)
        )

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        return super.onCreateOptionsMenu(menu)
    }


    inner class NetworkThread: Thread(){

        override fun run() {
            try {
                // 접속할 페이지의 주소
                var url = URL(resources.getString(R.string.xml))
                var input = url.openConnection().getInputStream()

                var factory = DocumentBuilderFactory.newInstance()
                var builder = factory.newDocumentBuilder()
                // doc: xml문서를 모두 읽어와서 분석을 끝냄
                var doc = builder.parse(input)

                // root: xml 문서의 모든 데이터들을 갖고 있는 객체
                var root = doc.documentElement

                // xml 문서에서 태그 이름이 item인 태그들이 item_node_list에 리스트로 담김
                var item_node_list = root.getElementsByTagName("row")

                // item_node_list에 들어있는 태그 객체 수만큼 반복함
                for(i in 0 until item_node_list.length){
                    // i번째 태그 객체를 item_element에 넣음
                    var item_element = item_node_list.item(i) as Element

                    // item태그 객체에서 원하는 데이터를 태그이름을 이용해서 데이터를 가져옴
                    // xml 문서는 태그 이름으로 데이터를 가져오면 무조건 리스트로 나옴
                    var rcpname_list = item_element.getElementsByTagName("RCP_NM")
                    var rcpingre_list = item_element.getElementsByTagName("RCP_PARTS_DTLS")
                    var rcptag_list = item_element.getElementsByTagName("HASH_TAG")
                    var rcpimage_list = item_element.getElementsByTagName("ATT_FILE_NO_MK")


                    var rcpname_node = rcpname_list.item(0) as Element
                    var rcpingre_node = rcpingre_list.item(0) as Element
                    var rcptag_node = rcptag_list.item(0) as Element
                    var rcpimage_node = rcpimage_list.item(0) as Element


                    // 태그 사이에 있는 문자열을 가지고 오는 작업
                    var rcpname = rcpname_node.textContent
                    var rcpingre = rcpingre_node.textContent
                    var rcptag = rcptag_node.textContent
                    var rcpimage = rcpimage_node.textContent

                    var n: String
                    var manuals = mutableListOf<String>()

                    for (j in 1..20){
                        if (1 <= j && j <= 9){
                            n = "0" + j.toString()
                        }
                        else{
                            n = j.toString()
                        }
                        val rcpmanual_list = item_element.getElementsByTagName("MANUAL"+n)
                        var rcpmanual_node = rcpmanual_list.item(0) as Element
                        var rcpmanual = rcpmanual_node.textContent

                        manuals.add(rcpmanual)
                    }

                    if (rcpingre.contains("대파")){
                        names.add(rcpname)
                        ingres.add(rcpingre)
                        mingres.add(rcptag)
                        images.add(rcpimage)
                        manuallists.add(manuals)
                    }


                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}