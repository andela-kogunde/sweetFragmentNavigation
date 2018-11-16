package com.experiment.codekenn.sweetfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.experiment.codekenn.sweetfragment.base.BaseFragment
import com.experiment.codekenn.sweetfragment.databinding.FragmentSplashBinding
import com.experiment.codekenn.sweetfragment.databinding.FragmentSweetBinding

/**
 * A placeholder fragment containing a simple view.
 */
class SweetSplash : BaseFragment<SweetActivity>() {
    lateinit var binding: FragmentSplashBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        displayBack = false
        baseActivity.binding.toolbar.visibility = View.GONE
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.speakers.setOnClickListener {
            startFragmentForResult(baseActivity.factory.fragment(SweetDevCodeKenn::class.java.simpleName))
        }
        return binding.root
    }

    companion object {
        private var INSTANCE: SweetSplash? = null
        fun instance(): SweetSplash = INSTANCE ?: SweetSplash().also { INSTANCE = it }
    }
}

abstract class SweetFragment : BaseFragment<SweetActivity>() {
    abstract fun bind()
    lateinit var binding: FragmentSweetBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        displayBack = true
        baseActivity.binding.toolbar.visibility = View.VISIBLE
        binding = FragmentSweetBinding.inflate(inflater, container, false)
        bind()
        return binding.root
    }
}

class SweetFragmentFactory {
    fun fragment(value: String): BaseFragment<SweetActivity> {
        when (value) {
            SweetDevCodeKenn::class.java.simpleName -> return SweetDevCodeKenn.instance()
            SweetDev1::class.java.simpleName -> return SweetDev1.instance()
            SweetDev2::class.java.simpleName -> return SweetDev2.instance()
            SweetDev3::class.java.simpleName -> return SweetDev3.instance()
            SweetDev4::class.java.simpleName -> return SweetDev4.instance()
            SweetDev5::class.java.simpleName -> return SweetDev5.instance()
            SweetSplash::class.java.simpleName -> return SweetSplash.instance()
            else -> throw ClassNotFoundException("${value} not found")
        }
    }

    companion object {
        private var INSTANCE: SweetFragmentFactory? = null
        fun instance(): SweetFragmentFactory = INSTANCE
                ?: SweetFragmentFactory().also { INSTANCE = it }
    }
}

/**
 * This Fragments are created for demo purposes.
 */
class SweetDevCodeKenn : SweetFragment() {
    override fun bind() {
        binding.imageView.setImageResource(R.drawable.codekenn)
        binding.fullName.text = "KEHINDE Ogunde"
        binding.profession.text = "Android Dev. @ Homie & Andela"
        binding.root.setOnClickListener {
            startFragmentForResult(baseActivity.factory.fragment(SweetDev1::class.java.simpleName))
        }
    }

    companion object {
        private var INSTANCE: SweetDevCodeKenn? = null
        fun instance(): SweetDevCodeKenn = INSTANCE ?: SweetDevCodeKenn().also { INSTANCE = it }
    }
}

class SweetDev1 : SweetFragment() {
    override fun bind() {
        binding.imageView.setImageResource(R.drawable.jogun)
        binding.fullName.text = "JOGUN Ogedengbe"
        binding.profession.text = "Organizer @ Lagos FullStack Dev. Meetup"
        binding.root.setOnClickListener {
            startFragmentForResult(baseActivity.factory.fragment(SweetDev2::class.java.simpleName))
        }
    }

    companion object {
        private var INSTANCE: SweetDev1? = null
        fun instance(): SweetDev1 = INSTANCE ?: SweetDev1().also { INSTANCE = it }
    }
}

class SweetDev2 : SweetFragment() {
    override fun bind() {
        binding.imageView.setImageResource(R.drawable.samuel)
        binding.fullName.text = "OKEDIJI Samuel"
        binding.profession.text = "Roar Digital"
        binding.root.setOnClickListener {
            startFragmentForResult(baseActivity.factory.fragment(SweetDev3::class.java.simpleName))
        }
    }

    companion object {
        private var INSTANCE: SweetDev2? = null
        fun instance(): SweetDev2 = INSTANCE ?: SweetDev2().also { INSTANCE = it }
    }
}

class SweetDev3 : SweetFragment() {
    override fun bind() {
        binding.imageView.setImageResource(R.drawable.blessing)
        binding.fullName.text = "BLESSING Ajibero"
        binding.profession.text = "Imaginarium Nigeria"
        binding.root.setOnClickListener {
            startFragmentForResult(baseActivity.factory.fragment(SweetDev4::class.java.simpleName))
        }
    }

    companion object {
        private var INSTANCE: SweetDev3? = null
        fun instance(): SweetDev3 = INSTANCE ?: SweetDev3().also { INSTANCE = it }
    }
}

class SweetDev4 : SweetFragment() {
    override fun bind() {
        binding.imageView.setImageResource(R.drawable.emmanuel)
        binding.fullName.text = "EMMANUEL Obute"
        binding.profession.text = "Imaginarium Nigeria"
        binding.root.setOnClickListener {
            startFragmentForResult(baseActivity.factory.fragment(SweetDev5::class.java.simpleName))
        }
    }

    companion object {
        private var INSTANCE: SweetDev4? = null
        fun instance(): SweetDev4 = INSTANCE ?: SweetDev4().also { INSTANCE = it }
    }
}

class SweetDev5 : SweetFragment() {
    override fun bind() {
        binding.imageView.setImageResource(R.drawable.sodiq)
        binding.fullName.text = "AKINJOBI Sodiq"
        binding.profession.text = "AdreSocial"
        binding.root.setOnClickListener {
            val sweetDevCodeKenn = baseActivity.factory.fragment(SweetDevCodeKenn::class.java.simpleName)
            startFragmentForResult(sweetDevCodeKenn, sweetDevCodeKenn.previous())
        }
    }

    companion object {
        private var INSTANCE: SweetDev5? = null
        fun instance(): SweetDev5 = INSTANCE ?: SweetDev5().also { INSTANCE = it }
    }
}