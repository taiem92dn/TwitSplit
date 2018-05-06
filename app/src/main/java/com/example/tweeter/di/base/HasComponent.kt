package com.example.tweeter.di.base

interface HasComponent<C> {
    fun getComponent() :C
}