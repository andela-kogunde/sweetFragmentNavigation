package com.experiment.codekenn.sweetfragment.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.experiment.codekenn.sweetfragment.R
import com.experiment.codekenn.sweetfragment.extensions.PreviousFragmentListener
import com.experiment.codekenn.sweetfragment.extensions.previousFragment
import com.experiment.codekenn.sweetfragment.extensions.replaceFragment

abstract class BaseActivity : AppCompatActivity() {
    override fun onBackPressed() = previousFragment()?.let { replaceFragment(it) }
            ?: super.onBackPressed()

    override fun onOptionsItemSelected(item: MenuItem?) = if (item?.itemId == android.R.id.home) {
        onBackPressed()
        true
    } else super.onOptionsItemSelected(item)
}

@Suppress("UNCHECKED_CAST")
open class BaseFragment<T : BaseActivity> : Fragment(), PreviousFragmentListener {
    val baseActivity: T get() = activity as T
    private var previousFragment: BaseFragment<T>? = null
    override fun previous() = previousFragment
    private var result: Int = 1
    var displayBack = false
        set(value) {
            setHasOptionsMenu(value)
            baseActivity.supportActionBar?.setDisplayHomeAsUpEnabled(value)
        }

    fun startFragmentForResult(fragment: BaseFragment<T>, previousFragment: BaseFragment<T>? = this, id: Int = R.id.container) {
        fragment.previousFragment = previousFragment
        baseActivity.replaceFragment(fragment, id)
    }

    fun result(result: Int, bundle: Bundle?) {
        previousFragment?.result = result
        previousFragment?.arguments = bundle
    }
}
