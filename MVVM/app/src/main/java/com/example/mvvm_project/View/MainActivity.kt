package com.example.mvvm_project.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import com.example.mvvm_project.R
import com.example.mvvm_project.domain.SelectedListener

class MainActivity : AppCompatActivity(), SelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onSelected(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, detailFragment)
            .addToBackStack(null) // agragar al stack para volver con back
            .commit()
    }

}