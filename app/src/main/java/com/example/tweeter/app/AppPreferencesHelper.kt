package com.example.tweeter.app

import com.example.tweeter.util.BaseAppPreferencesHelper
import com.example.tweeter.util.security.ObscuredSharedPreferences

class AppPreferencesHelper: BaseAppPreferencesHelper {

   constructor(sharedPreferences: ObscuredSharedPreferences) : super(sharedPreferences)
}