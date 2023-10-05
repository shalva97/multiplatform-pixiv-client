package io.github.shalva97.fanbox

import io.github.shalva97.PixivWebClient
import io.github.shalva97.web.ajax
import io.ktor.client.request.*
import io.ktor.http.*

public class FanBoxNewsLetter(override val client: PixivWebClient) : FanBoxApi() {
    public companion object {
        internal const val COUNT_UNREAD = "https://api.fanbox.cc/newsletter.countUnread"
    }

    public suspend fun countUnread(): Int {
        return client.ajax(api = COUNT_UNREAD) {
            header(HttpHeaders.Origin, "https://www.fanbox.cc")
            header(HttpHeaders.Referrer, "https://www.fanbox.cc/")
        }
    }
}