package com.example.practicedeliveryheros.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.practicedeliveryheros.R
import com.example.practicedeliveryheros.databinding.ActivityMainBinding
import com.example.practicedeliveryheros.screen.home.HomeFragment
import com.example.practicedeliveryheros.screen.my.MyFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    // MainActivity의 View를 초기화시키는 메소드
    private fun initViews() = with(binding) {
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)

        // 프래그먼트에 대한 전처리
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction()
                .hide(fm)
                .commit()
        }

        findFragment?.let {
            supportFragmentManager.beginTransaction()
                .show(it)
                .commit()
        }?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }

    }

    // 네비게이션 아이템의 선택에 따라 프래그먼트를 전환한다
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_home -> {
                showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }

            R.id.menu_my -> {
                showFragment(MyFragment.newInstance(), MyFragment.TAG)
                true
            }
            else -> false
        }
    }
}