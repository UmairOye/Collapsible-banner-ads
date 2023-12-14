package com.example.collapsiblebannerad

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.collapsiblebannerad.databinding.ActivityMainBinding
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adview: AdView

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadCollapsibleBannerAd()


    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun loadCollapsibleBannerAd() {

        adview = AdView(this)
        adview.adUnitId = getString(R.string.collapsible_id)
        val adSize = getAdSize()

        adview.setAdSize(adSize)
        val extras = Bundle()
        extras.putString("collapsible", "bottom")
        val adRequest = AdRequest.Builder()
            .addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
            .build()

        binding.bannerContainer.addView(adview)
        adview.loadAd(adRequest)

    }


    @RequiresApi(Build.VERSION_CODES.R)
    private fun getAdSize(): AdSize {
        val windowMetrics = windowManager.currentWindowMetrics
        val bounds = windowMetrics.bounds
        var adWidthPixels: Float = binding.bannerContainer.width.toFloat()

        if (adWidthPixels == 0f) {
            adWidthPixels = bounds.width().toFloat()
        }
        val density = resources.displayMetrics.density
        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
    }


}