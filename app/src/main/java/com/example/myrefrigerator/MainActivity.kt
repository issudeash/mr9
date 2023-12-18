package com.example.myrefrigerator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.GravityCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.example.myrefrigerator.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    /*lateinit var Lefttoggle: ActionBarDrawerToggle
    lateinit var Righttoggle: ActionBarDrawerToggle*/
    lateinit var drawerLayout: DrawerLayout
    lateinit var leftNavigationView: NavigationView
    lateinit var rightNavigationView: NavigationView
    lateinit var rightDrawerbtn: ExtendedFloatingActionButton
    lateinit var leftDrawerbtn: ExtendedFloatingActionButton

    var datas: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        drawerLayout = findViewById(R.id.drawer)

        leftNavigationView = findViewById(R.id.leftDrawer)
        rightNavigationView = findViewById(R.id.rightDrawer)

        leftDrawerbtn = findViewById((R.id.leftfbtn))
        rightDrawerbtn = findViewById((R.id.rightfbtn))

        rightDrawerbtn.isVisible = false
        leftDrawerbtn.isVisible = false



        binding.recipeButton.setOnClickListener{
            val intent1 = Intent(this, RecipeMainActivity::class.java)
            startActivity(intent1)
        }

        binding.listButton.setOnClickListener{
            val intent2 = Intent(this, RecipeMainActivity::class.java) // 이부분 수정 필요
            startActivity(intent2)
        }


        // 왼쪽 툴바 및 토글 초기화
        /*setSupportActionBar(binding.mainToolbar1)
        Lefttoggle = ActionBarDrawerToggle(this, binding.drawer, binding.mainToolbar1, R.string.open_drawer, R.string.close_drawer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.drawer.addDrawerListener(Lefttoggle)
        Lefttoggle.setDrawerIndicatorEnabled(true)
        Lefttoggle.syncState()

        // 오른쪽 툴바 및 토글 초기화
        setSupportActionBar(binding.mainToolbar2)
        Righttoggle = ActionBarDrawerToggle(this, binding.drawer, binding.mainToolbar2, R.string.open_drawer, R.string.close_drawer)
        binding.drawer.addDrawerListener(Righttoggle)
        Righttoggle.setDrawerIndicatorEnabled(true)
        Righttoggle.syncState()


        // 왼쪽 토글 버튼 클릭 시 서랍 열고 닫기
        Lefttoggle.toolbarNavigationClickListener = View.OnClickListener {
            if (binding.drawer.isDrawerOpen(leftNavigationView)) {
                binding.drawer.closeDrawer(leftNavigationView)
                Log.d("mj", "왼닫")
            } else {
                binding.drawer.openDrawer(leftNavigationView)
                Log.d("mj", "왼열")
            }
        }

        // 오른쪽 토글 버튼 클릭 시 서랍 열고 닫기
        Righttoggle.toolbarNavigationClickListener = View.OnClickListener {
            if (binding.drawer.isDrawerOpen(rightNavigationView)) {
                binding.drawer.closeDrawer(rightNavigationView)
                Log.d("mj", "오닫")
            } else {
                binding.drawer.openDrawer(rightNavigationView)
                Log.d("mj", "오열")
            }
        }*/

        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View) {
                // 네비게이션 뷰가 열렸을 때 호출
                when (drawerView) {
                    leftNavigationView -> {
                        rightDrawerbtn.isVisible = true
                        leftDrawerbtn.isVisible = false
                    }

                    rightNavigationView -> {
                        leftDrawerbtn.isVisible = true
                        rightDrawerbtn.isVisible = false
                    }
                }
            }

            override fun onDrawerClosed(drawerView: View) {
                // 네비게이션 뷰가 닫혔을 때 호출
                rightDrawerbtn.isVisible = false
                leftDrawerbtn.isVisible = false
            }

            override fun onDrawerStateChanged(newState: Int) {

            }


        })
    }
}


