import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appcompose.R

@Composable
fun WordPopup(
    onDismiss: () -> Unit,
    englishWord: String,
    frenchWord: String,
    englishSoundIcon: Painter,
    frenchSoundIcon: Painter,
    elephantIcon: Painter,
    onAddWordClick: () -> Unit,
    enClickSound: () -> Unit,
    frClickSound: () -> Unit,
    message: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Close",
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onDismiss() }
                )
                Spacer(modifier = Modifier.height(16.dp))
                WordRow(
                    word = englishWord,
                    soundIcon = englishSoundIcon,
                    popupIcon = R.drawable.en,
                    clickSound = { enClickSound() }
                )
                Spacer(modifier = Modifier.height(16.dp))
                WordRow(
                    word = frenchWord,
                    soundIcon = frenchSoundIcon,
                    popupIcon = R.drawable.fr,
                    clickSound = { frClickSound() }

                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = elephantIcon,
                        contentDescription = "Elephant",
                        modifier = Modifier.size(128.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Button(
                        onClick = onAddWordClick,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.CenterVertically)
                    ) {

                        Text(text = message)

                    }
                }
            }
        }
    }
}

@Composable
fun WordRow(
    word: String,
    soundIcon: Painter,
    popupIcon: Int,
    clickSound: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(popupIcon),
            contentDescription = "Popup Icon",
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = word,
            modifier = Modifier.weight(1f),
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = soundIcon,
            contentDescription = "Sound Icon",
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    clickSound()
                }


        )
    }
}
