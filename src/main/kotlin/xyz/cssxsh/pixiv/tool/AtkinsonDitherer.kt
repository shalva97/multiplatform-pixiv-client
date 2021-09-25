package xyz.cssxsh.pixiv.tool

class AtkinsonDitherer private constructor() : UniversalDitherer(DISTRIBUTION) {
    companion object {
        @JvmStatic
        val DISTRIBUTION: List<ErrorComponent> = listOf(
            ErrorComponent(1, 0, 1 / 8.0),
            ErrorComponent(2, 0, 1 / 8.0),

            ErrorComponent(-1, 1, 1 / 8.0),
            ErrorComponent(0, 1, 1/ 8.0),
            ErrorComponent(1, 1, 1 / 8.0),

            ErrorComponent(0, 2, 1 / 8.0),
        )

        @JvmStatic
        val INSTANCE: AtkinsonDitherer = AtkinsonDitherer()
    }
}