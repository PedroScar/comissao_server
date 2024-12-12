import com.pscarpellini.style.Colors
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration

fun CssBuilder.baseBody() {
    margin = Margin(0.px)
    padding = Padding(0.px)
    display = Display.flex
    flexDirection = FlexDirection.column
    minHeight = 100.vh
}

fun CssBuilder.baseBotaoArredondado(btnColor: Color) {
    backgroundColor = btnColor
    padding = Padding(12.px, 24.px, 12.px, 24.px)
    borderRadius = 9999.px
    color = Colors.neutral_low_pure
    border = Border.none
    cursor = Cursor.pointer
    fontFamily = "Nunito"
    fontSize = 16.px
    fontWeight = FontWeight.w600
    textAlign = TextAlign.left
    textDecoration = TextDecoration.none
    justifyContent = JustifyContent.center
    display = Display.flex
    gravityCenter()
}

fun CssBuilder.espacarItens() {
    justifyContent = JustifyContent.spaceBetween
    alignItems = Align.center
}

fun CssBuilder.linearLayoutHorizontal(espacarItens: Boolean = false) {
    if (espacarItens) espacarItens()
    display = Display.flex
    flexDirection = FlexDirection.row
}

fun CssBuilder.linearLayoutVertical(espacarItens: Boolean = false, screenWidth: Boolean = false) {
    if (screenWidth) screenWidth()
    if (espacarItens) espacarItens()
    display = Display.flex
    flexDirection = FlexDirection.column
}

fun CssBuilder.baseHeader(backColor: Color) {
    backgroundColor = backColor
    width = LinearDimension("calc(100% - 64px)")
    padding = Padding(32.px)
    position = Position.fixed
    top = 0.px
    zIndex = 100
    linearLayoutHorizontal(true)
}

fun CssBuilder.preencherTelaRestante() {
    flexGrow = 1.0
}

fun StyledElement.screenWidth() {
    width = 100.pct
}

fun StyledElement.gravityStart() {
    alignItems = Align.start
}

fun StyledElement.gravityCenter() {
    alignItems = Align.center
}

fun StyledElement.baseTexto() {
    fontFamily = "Nunito"
    padding = Padding(0.px)
}