package io.github.shalva97.fanbox

import io.github.shalva97.PixivWebClient
import io.github.shalva97.web.ajax
import io.ktor.client.request.*
import io.ktor.http.*

public class FanBoxPayment(override val client: PixivWebClient) : FanBoxApi() {
    public companion object {
        internal const val LIST_PAID = "https://api.fanbox.cc/payment.listPaid"

        internal const val LIST_UNPAID = "https://api.fanbox.cc/payment.listUnpaid"
    }

    public suspend fun listPaid(): List<PaidRecord> {
        return client.ajax(api = LIST_PAID) {
            header(HttpHeaders.Origin, "https://www.fanbox.cc")
            header(HttpHeaders.Referrer, "https://www.fanbox.cc/")
        }
    }

    public suspend fun listUnpaid(): List<PaidRecord> {
        return client.ajax(api = LIST_UNPAID) {
            header(HttpHeaders.Origin, "https://www.fanbox.cc")
            header(HttpHeaders.Referrer, "https://www.fanbox.cc/")
        }
    }
}