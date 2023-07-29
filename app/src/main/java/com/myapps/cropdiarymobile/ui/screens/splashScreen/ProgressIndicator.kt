import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.theme.color.Error

@Composable
internal fun ProgressIndicator(
    orientation: WindowOrientation,
    size: Dp
) {
    val weight = if (orientation == WindowOrientation.Landscape) 0.5f else 1f
    CircularProgressIndicator(
        modifier = Modifier
            .size(size * weight)
            .layoutId("progressIndicator"),
        color = Error,
        strokeWidth = 4.dp
    )
}