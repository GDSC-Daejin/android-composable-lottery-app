package app.gdsc.lottocomposable

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LottoAppUi() {

    var totalLottoList by remember {
        mutableStateOf(
            (1..45).shuffled().toList()
        )
    }

    var lottoList by remember {
        mutableStateOf(listOf<Int>())
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            LottoContainerUi(lottoList)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (lottoList.size >= 6) {
                        lottoList = emptyList()
                        totalLottoList = (1..45).shuffled().toList()
                    } else {
                        val item = totalLottoList.random()
                        totalLottoList = totalLottoList - item
                        lottoList = lottoList + item
                    }
                }
            ) {
                Text("뽑기")
            }

        }

    }

}

@Composable
fun LottoContainerUi(lottoList: List<Int>) {

    Row(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        lottoList.forEach {
            LottoItemUi(it)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }

}

@Composable
fun LottoItemUi(number: Int) {

    val color = when (number) {
        in 0..10 -> Color(0xFFFF5E5E)
        in 11..20 -> Color(0xFFFF9E5E)
        in 21..30 -> Color(0xFFFFE45E)
        in 31..40 -> Color(0xFF8ADB43)
        else -> Color(0xFF5EB4FF)
    }

    Surface(
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        color = color
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = number.toString(),
                textAlign = TextAlign.Center
            )
        }

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LottoAppUiPreview() {
    LottoAppUi()
}