package com.azzam.doadandzikir

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.azzam.doadandzikir.adapter.ArtikelAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var dotslider: Array<ImageView?>

    private val listArtikel: ArrayList<Artikel> = arrayListOf()

    private val slidingCallBack = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            for (i in 0 until listArtikel.size){
                dotslider[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.dot_inactive)
                )
            }

            dotslider[position]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.dot_active)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initView()
        setupViewPager()

    }

    private fun setupViewPager() {
        val llSliderDots: LinearLayout = findViewById(R.id.ll_slider_dots)

        dotslider = arrayOfNulls(listArtikel.size)

        for (i in 0 until listArtikel.size) {
            dotslider[i] = ImageView(this)
            dotslider[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.dot_inactive
                )
            )

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            //mengatur jarak antar indicator
            params.setMargins(9,0,8,0)
            //mengatur posisi indicator
            params.gravity = Gravity.CENTER_VERTICAL
            //menerapkan params sebagai aturan bagaimana indicator ditampilkan
            llSliderDots.addView(dotslider[i], params)
        }

        dotslider[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,R.drawable.dot_active
            )
        )
    }

    private fun initData() {
        val titleArtikel = resources.getStringArray(R.array.arr_title_artikel)
        val descArtikel = resources.getStringArray(R.array.arr_desc_artikel)
        val imgArtikel = resources.obtainTypedArray(R.array.arr_img_artikel)

        for(data in titleArtikel.indices){
            val artikel = Artikel(
                titleArtikel[data],
                descArtikel[data],
                imgArtikel.getResourceId(data, 0)
            )
            listArtikel.add(artikel)
        }
        imgArtikel.recycle()
    }

    private fun initView() {
        val llDzikirDoaSholat: LinearLayout = findViewById(R.id.ll_dzikir_doa_sholat)
        llDzikirDoaSholat.setOnClickListener {
            startActivity(Intent(this, QauliyahShalatActivity::class.java))
        }

        val llDzikirSetiapSaat: LinearLayout =
            findViewById<LinearLayout>(R.id.ll_dzikir_setiap_saat)
        llDzikirSetiapSaat.setOnClickListener {
            startActivity(Intent(this, SetiapSaatDzikirActivity::class.java))
        }
        val llDzikirDoaHarian: LinearLayout = findViewById(R.id.ll_dzikir_doa_harian)
        llDzikirDoaHarian.setOnClickListener {
            startActivity(Intent(this, HarianDzikirDoaActivity::class.java))
        }
        val llDzikirPagiPetang: LinearLayout = findViewById(R.id.ll_dzikir_pagi_petang)
        llDzikirPagiPetang.setOnClickListener {
            startActivity(Intent(this, PagiPetangDzikirActivity::class.java))
        }

        val vpArtikel: ViewPager2 = findViewById(R.id.vp_artikel)
        vpArtikel.adapter = ArtikelAdapter(listArtikel)
        vpArtikel.registerOnPageChangeCallback(slidingCallBack)

    }

}
