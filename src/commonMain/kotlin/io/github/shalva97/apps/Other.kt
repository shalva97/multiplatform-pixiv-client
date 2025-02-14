package io.github.shalva97.apps

import io.github.shalva97.FilterType
import io.github.shalva97.UseHttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*

public suspend fun UseHttpClient.emoji(
    url: String = EMOJI,
): EmojiDefinitionData = useHttpClient { client ->
    client.get(url)
        .body()
}

public suspend fun UseHttpClient.stamps(
    url: String = STAMPS,
): StampData = useHttpClient { client ->
    client.get(url)
        .body()
}

public suspend fun UseHttpClient.urls(
    url: String = IDP_URLS,
): Map<String, String> = useHttpClient { client ->
    client.get(url)
        .body()
}

public suspend fun UseHttpClient.application(
    type: FilterType,
): ApplicationData = useHttpClient { client ->
    client.get(
        when (type) {
            FilterType.FOR_ANDROID -> APPLICATION_INFO_ANDROID
            FilterType.FOR_ISO -> APPLICATION_INFO_IOS
        }
    ).body()
}