package com.example.tweeter.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tweeter.R
import com.example.tweeter.presenter.MessagesPresenter
import com.example.tweeter.ui.base.BaseFragment
import javax.inject.Inject

class MessagesFragment : BaseFragment() {

    private val TAG = MessagesFragment::class.simpleName

    @Inject
    lateinit var mMessagesPresenter : MessagesPresenter

    companion object {

        @JvmStatic
        fun newInstance(): MessagesFragment? = MessagesFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }
}
