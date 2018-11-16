package com.experiment.codekenn.sweetfragment.extensions

import android.support.transition.CircularPropagation
import android.support.transition.Explode
import android.support.transition.Slide
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.experiment.codekenn.sweetfragment.R

val SWEET_FRAGMENT = "Sweet Fragment"

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, id: Int = R.id.container) {
    supportFragmentManager.transact {
        fragment.setEnterTransition(enterTransition())
        fragment.setExitTransition(exitTransition())
        add(id, fragment, SWEET_FRAGMENT)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, id: Int = R.id.container) {
    supportFragmentManager.transact {
        fragment.setEnterTransition(enterTransition())
        fragment.setExitTransition(exitTransition())
        replace(id, fragment, SWEET_FRAGMENT)
    }
}

interface PreviousFragmentListener {
    fun previous(): Fragment?
}

fun AppCompatActivity.currentFragment(id: Int = R.id.container) = try {
    supportFragmentManager.findFragmentById(id)
} catch (e: Exception) {
    null
}

fun AppCompatActivity.previousFragment(id: Int = R.id.container) = try {
    currentFragment(id)?.let { (it as PreviousFragmentListener).previous() }
} catch (e: Exception) {
    null
}

fun enterTransition(): Slide {
    val slide = Slide()
    slide.setDuration(200)
    slide.setInterpolator(DecelerateInterpolator())
    return slide
}

fun exitTransition(): Explode {
    val explode = Explode()
    explode.setDuration(200)
    explode.setMode(Explode.MODE_OUT)
    explode.setPropagation(CircularPropagation())
    explode.setInterpolator(AccelerateInterpolator())
    return explode
}