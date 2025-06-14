package com.droidknights.app.core.network

import com.droidknights.app.core.network.api.create
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
    fun `서비스 객체를 만들 수 있다`() = runTest {
        Assertions.assertNotNull(network.create<MockDroidknightsService>("https://droidknihghts.app/"))
    }
}
