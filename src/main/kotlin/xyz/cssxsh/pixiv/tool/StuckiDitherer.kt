package xyz.cssxsh.pixiv.tool

class StuckiDitherer private constructor() : UniversalDitherer(DISTRIBUTION) {
    companion object {
        @JvmField
        val DISTRIBUTION: List<ErrorComponent> = listOf(
            ErrorComponent(1, 0, 8 / 48.0),
            ErrorComponent(2, 0, 4 / 48.0),

            ErrorComponent(-2, 1, 2 / 48.0),
            ErrorComponent(-1, 1, 4 / 48.0),
            ErrorComponent(0, 1, 8 / 48.0),
            ErrorComponent(1, 1, 4 / 48.0),
            ErrorComponent(2, 1, 2 / 48.0),

            ErrorComponent(-2, 2, 1 / 48.0),
            ErrorComponent(-1, 2, 2 / 48.0),
            ErrorComponent(0, 2, 4 / 48.0),
            ErrorComponent(1, 2, 2 / 48.0),
            ErrorComponent(2, 2, 1 / 48.0),
        )

        @JvmField
        val INSTANCE: StuckiDitherer = StuckiDitherer()
    }
}