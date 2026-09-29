package nl.giejay.android.tv.immich.api

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ApiClientConfigTest {
    @Test
    fun `diagnostic string never includes API key`() {
        val secret = "unit-test-private-api-key"
        val config = ApiClientConfig("https://example.invalid", secret, false, false)

        assertFalse(config.toString().contains(secret))
        assertTrue(config.toString().contains("redacted"))
    }
}
