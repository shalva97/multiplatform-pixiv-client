package io.github.shalva97.apps

import io.github.shalva97.PixivAppClient
import io.ktor.client.call.*
import io.ktor.client.request.*

public suspend fun PixivAppClient.ugoiraMetadata(
    pid: Long,
    url: String = UGOIRA_METADATA,
): UgoiraInfo = useHttpClient { client ->
    client.get(url) {
        parameter("illust_id", pid)
    }.body()
}
