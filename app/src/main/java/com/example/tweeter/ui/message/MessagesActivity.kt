package com.example.tweeter.ui.message

import android.os.Bundle
import com.example.tweeter.di.base.HasComponent
import com.example.tweeter.di.component.AppComponent
import com.example.tweeter.ui.base.BaseActivity
import com.example.tweeter.ui.base.BaseFragment

class MessagesActivity : BaseActivity(), HasComponent<AppComponent?> {

    override fun getComponent(): AppComponent? {
        return getApplicationComponent()
    }

    override fun hostFragment(): BaseFragment? {
        return MessagesFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasActionbar(true)
    }

}
