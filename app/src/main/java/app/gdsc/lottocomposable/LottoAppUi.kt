package app.gdsc.lottocomposable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Help
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LottoAppUi() {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            var totalLottoList by remember {
                mutableStateOf((1..45).shuffled().toList())
            }

            var lottoList by remember {
                mutableStateOf(listOf<Int>())
            }

            if (lottoList.isNotEmpty()) {
                LottoContainerUi(lottoList)
                Spacer(modifier = Modifier.height(20.dp))
            }

            LottoPickButton(
                Modifier.align(Alignment.CenterHorizontally),
                lottoList,
                totalLottoList,
                { lottoList = it },
                { totalLottoList = it }
            )

        }

    }

}

@Composable
fun LottoPickButton(
    modifier : Modifier = Modifier,
    lottoList: List<Int>,
    totalLottoList: List<Int>,
    onLottoListUpdated: (List<Int>) -> Unit,
    onTotalLottoListUpdated: (List<Int>) -> Unit,
) {
    if (lottoList.size == 6) {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                onLottoListUpdated(emptyList())
                onTotalLottoListUpdated((1..45).shuffled().toList())
            }
        ) {
            Text("다시 뽑기")
        }
    } else {
        ExtendedFloatingActionButton(
            modifier = modifier,
            onClick = {
                val item = totalLottoList.random()
                onLottoListUpdated(lottoList + item)
                onTotalLottoListUpdated(totalLottoList - item)
            },
            icon = {
                   Icon(imageVector = Icons.Rounded.Help, contentDescription = "Pick")
            },
        text = { Text("뽑기") }
        )
    }

}

@Composable
fun LottoContainerUi(lottoList: List<Int>) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
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
@Preview(showBackground = true)
fun LottoItemUiPreview() {
    LottoItemUi(45)
}

@Composable
@Preview(showBackground = true)
fun LottoContainerUiPreview() {
    LottoContainerUi(
        listOf(1, 11, 21, 31, 40, 45)
    )
}