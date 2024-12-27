package com.pscarpellini.frontend.enums

enum class TiposAvatarEnum(
    val cssProprio: String,
) {
    EXTRA_LARGE_CIRCLE(cssProprio = "${ArredondamentosEnum.PILL} text-5xl size-32"),

    LARGE_CIRCLE(cssProprio = "${ArredondamentosEnum.PILL} text-lg size-10"),
    LARGE_SQUARE(cssProprio = "${ArredondamentosEnum.SM} text-lg size-10"),
    MEDIUM_CIRCLE(cssProprio = "${ArredondamentosEnum.PILL} text-base size-8"),
    MEDIUM_SQUARE(cssProprio = "${ArredondamentosEnum.SM} text-base size-8"),
    SMALL_CIRCLE(cssProprio = "${ArredondamentosEnum.PILL} text-sm size-6"),
    SMALL_SQUARE(cssProprio = "${ArredondamentosEnum.SM} text-sm size-6");

    override fun toString() = this.cssProprio
}