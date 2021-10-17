package app.gdsc.lottocomposable

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun LottoTheme(
    content : @Composable () -> Unit
) {

    MaterialTheme(
        content = content
    )

}