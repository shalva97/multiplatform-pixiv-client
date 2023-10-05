package io.github.shalva97.exception

import io.ktor.http.*

public class ProxyException(proxy: Url) : IllegalArgumentException("Proxy: $proxy Error!")