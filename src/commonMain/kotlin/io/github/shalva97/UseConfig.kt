package io.github.shalva97

public interface UseConfig {

    public val config: PixivConfig

    public fun config(block: PixivConfig.() -> Unit): PixivConfig = config.apply(block)
}