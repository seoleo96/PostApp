package com.seoleo.postapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.seoleo.postapp.databinding.ActivityMainBinding
import com.seoleo.postapp.features.Feature
import com.seoleo.postapp.features.signin.SignInFeature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), Navigation {

    private lateinit var binding: ActivityMainBinding
    private var isKeepOnSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setKeepOnScreenCondition(splashScreen)
        changeKeepOnScreenCondition()
    }

    private fun changeKeepOnScreenCondition() {
        lifecycleScope.launch(Dispatchers.IO) {
            delay(2000)
            isKeepOnSplashScreen = false
        }
    }

    private fun setKeepOnScreenCondition(splashScreen: SplashScreen) {
        splashScreen.setKeepOnScreenCondition {
            isKeepOnSplashScreen
        }
    }

    override fun navigate(feature: Feature) {
        when (feature) {
            is SignInFeature -> findNavController(R.id.nav_host_fragment_content_main)
        }
    }
}

interface Navigation : SignInFeature {

    /**
     * Base navigate method for control
     */
    fun navigate(feature: Feature)

    override fun signOut() {

    }

    override fun main() {

    }

}