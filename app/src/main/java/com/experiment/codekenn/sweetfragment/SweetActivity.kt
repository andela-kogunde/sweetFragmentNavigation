package com.experiment.codekenn.sweetfragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.experiment.codekenn.sweetfragment.base.BaseActivity
import com.experiment.codekenn.sweetfragment.databinding.ActivitySweetBinding
import com.experiment.codekenn.sweetfragment.extensions.SWEET_FRAGMENT
import com.experiment.codekenn.sweetfragment.extensions.addFragment
import com.experiment.codekenn.sweetfragment.extensions.currentFragment

class SweetActivity : BaseActivity() {
    lateinit var binding: ActivitySweetBinding
    val factory = SweetFragmentFactory.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sweet)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        savedInstanceState?.getString(SWEET_FRAGMENT)?.let {
            addFragment(factory.fragment(it))
        } ?: addFragment(factory.fragment(SweetSplash::class.java.simpleName))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(SWEET_FRAGMENT, currentFragment()?.javaClass?.simpleName)
    }
}
