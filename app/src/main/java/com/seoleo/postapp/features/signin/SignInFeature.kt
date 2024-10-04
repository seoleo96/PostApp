package com.seoleo.postapp.features.signin

import com.seoleo.postapp.features.Feature


interface SignInFeature : Feature {

    /**
    * Navigate to [SignOutFragment]
    */
    fun signOut()

    /**
     * Navigate to [MainFragment]
     */
    fun main()
}