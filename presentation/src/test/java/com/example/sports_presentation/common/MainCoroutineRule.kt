package com.example.sports_presentation.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutineRule : BeforeEachCallback, AfterEachCallback {

    private val testDispatcher = StandardTestDispatcher()

    override fun beforeEach(context: ExtensionContext?) = Dispatchers.setMain(testDispatcher)

    override fun afterEach(context: ExtensionContext?) = Dispatchers.resetMain()
}
