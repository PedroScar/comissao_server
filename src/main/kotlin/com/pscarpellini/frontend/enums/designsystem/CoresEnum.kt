package com.pscarpellini.frontend.enums.designsystem

import com.pscarpellini.extensions.formatarNomeTailwind
import com.pscarpellini.frontend.tailwind.PropriedadeTailwind

enum class CoresEnum(
    override val valor: String
): PropriedadeTailwind {
    TRANSPARENT(valor = "#00000000"),
    BRAND_PURE(valor = "#6BD9D5"),
    BRAND_LIGHT(valor = "#EDFCFD"),
    BRAND_MEDIUM(valor = "#B3F7FC"),
    BRAND_DARK(valor = "#007F87"),
    HIGHLIGHT_PURE(valor = "#C9F56A"),
    HIGHLIGHT_LIGHT(valor = "#F1FCD9"),
    HIGHLIGHT_MEDIUM(valor = "#87BF0D"),
    HIGHLIGHT_DARK(valor = "#517308"),
    LOW_PURE(valor = "#1F1F1F"),
    LOW_LIGHT(valor = "#808080"),
    LOW_MEDIUM(valor = "#4D4D4D"),
    LOW_DARK(valor = "#141414"),
    HIGH_PURE(valor = "#FAFAFA"),
    HIGH_LIGHT(valor = "#F0F2F2"),
    HIGH_MEDIUM(valor = "#E0E0E0"),
    HIGH_DARK(valor = "#D1D1D1"),
    ALERT_PURE(valor = "#FF3D00"),
    ALERT_LIGHT(valor = "#FBF1EF"),
    ALERT_MEDIUM(valor = "#FFC0AE"),
    ALERT_DARK(valor = "#D80000"),
    WARNING_PURE(valor = "#FFC107"),
    WARNING_LIGHT(valor = "#FDF4E3"),
    WARNING_MEDIUM(valor = "#FFE291"),
    WARNING_DARK(valor = "#936800"),
    SUCCESS_PURE(valor = "#4CAF50"),
    SUCCESS_LIGHT(valor = "#EFF5EF"),
    SUCCESS_MEDIUM(valor = "#C1E2C0"),
    SUCCESS_DARK(valor = "#1F7827"),
    COLOR_01(valor = "#00A3E0"),
    COLOR_02(valor = "#DC143C"),
    COLOR_03(valor = "#32CD32"),
    COLOR_04(valor = "#FFD700"),
    COLOR_05(valor = "#0759AB"),
    COLOR_06(valor = "#A52A2A"),
    COLOR_07(valor = "#008080"),
    COLOR_08(valor = "#FF5722");

    override val cssProprio: String
        get() = this.name.formatarNomeTailwind()

    val bg: String
        get() = "bg-$cssProprio"
    val border: String
        get() = "border-$cssProprio"
    val text: String
        get() = "text-$cssProprio"

    override fun toString() = this.cssProprio
}