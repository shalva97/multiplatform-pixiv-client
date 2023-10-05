package io.github.shalva97.apps

import io.github.shalva97.PixivAuthClient
import io.github.shalva97.PixivConfig
import io.github.shalva97.SimplePixivClient
import io.github.shalva97.pixiv_client.BuildConfig

internal abstract class AppApiKtTest {

    protected val client: PixivAuthClient = object : SimplePixivClient(
        config = PixivConfig(refreshToken = io.github.shalva97.pixiv_client.BuildConfig.REFRESH_TOKEN)
    ) {
        override val ignore: suspend (Throwable) -> Boolean = { cause ->
            cause.printStackTrace()
            super.ignore(cause)
        }
    }
}