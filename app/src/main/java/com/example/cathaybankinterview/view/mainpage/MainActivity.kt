package com.example.cathaybankinterview.view.mainpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cathaybankinterview.R
import com.example.cathaybankinterview.databinding.ActivityMainBinding
import com.example.cathaybankinterview.view.mainpage.MainContract
import com.example.cathaybankinterview.view.mainpage.MainModel
import com.example.cathaybankinterview.view.mainpage.MainPresent

class MainActivity : AppCompatActivity() ,MainContract.ViewContract{

    private lateinit var binding : ActivityMainBinding

    private lateinit var present : MainContract.IMainPresent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        present = MainPresent(this, MainModel())
        present.loadHomePage()
    }

    override fun changePage(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_frame_layout, fragment)
        transaction.commit()
    }

    override fun enableNavigationButton(enable: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enable)
    }

    override fun onSupportNavigateUp(): Boolean {
        present.back()
        return true
    }

}