package com.example.myfirstcomposeapp.previews

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Nexus 5 Diurno",
    group = "Nexus",
    showSystemUi = true,
    showBackground = true,
    device = Devices.NEXUS_5
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.NEXUS_10
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.FOLDABLE
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.NEXUS_5
)

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.NEXUS_6P
)
@Preview(
    name = "Nexus 5 Nocturno",
    group = "Nexus",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.NEXUS_5
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.NEXUS_10
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.FOLDABLE
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.NEXUS_5
)

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK,
    device = Devices.NEXUS_6P
)
annotation class MySuperPreview
