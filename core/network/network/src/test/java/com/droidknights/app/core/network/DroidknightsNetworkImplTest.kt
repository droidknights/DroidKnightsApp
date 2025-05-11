package com.droidknights.app.core.network

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import retrofit2.Retrofit

internal class DroidknightsNetworkImplTest {

    private val retrofitBuilder = Retrofit.Builder()
    private val network = DroidknightsNetworkImpl(
        retrofit = retrofitBuilder,
    )

    @Test
    fun `test create`() = runTest {
        Assertions.assertNotNull(network.create("https://droidknihghts.app/", MockDroidknightsService::class.java))
    }
}
