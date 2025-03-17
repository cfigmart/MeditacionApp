package edu.unicauca.aplimovil.meditacionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.meditacionapp.ui.theme.MeditacionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeditacionAppTheme {
                PantallaPrincipal()
            }
        }
    }
}

@Composable
fun BarraDeBusqueda(
    modifier: Modifier = Modifier
){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(text= stringResource(R.string.search_txt))
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Preview
@Composable
fun PreviewBarraDeBusqueda() {
    BarraDeBusqueda()
}

@Composable
fun CuadroAlineaTuCuerpo(
    @DrawableRes imgId: Int,
    @StringRes descripcionId: Int,
    modifier: Modifier = Modifier,
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(imgId),
            contentDescription = stringResource(descripcionId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(descripcionId),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun PreviewCuadroAlineaTuCuerpo(){
    CuadroAlineaTuCuerpo(
        imgId = R.drawable.ab3_stretching,
        descripcionId = R.string.stretching_txt,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun TarjetaColeccionesFavoritas(
    @DrawableRes imgId: Int,
    @StringRes descripcionId: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(imgId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(descripcionId),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CuadriculaColeccionesFavoritas(
    modifier: Modifier = Modifier
){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp),
    ) {
        items(datosColeccionesFavoritas){
            TarjetaColeccionesFavoritas(
                imgId =  it.imageId,
                descripcionId = it.textId,
                modifier = Modifier.height(80.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCuadriculaColeccionesFavoritas(){
    CuadriculaColeccionesFavoritas()
}


@Composable
fun FilaAlineaTuCuerpo(
    modifier: Modifier = Modifier
){
    LazyRow (
        modifier = modifier
    ) {
        items(datosAlineaTuCuerpo){
            CuadroAlineaTuCuerpo(
                imgId = it.imageId,
                descripcionId = it.textId,
            )
        }
    }
}

@Preview
@Composable
fun PreviewFilaAlineaTuCuerpo(){
    FilaAlineaTuCuerpo()
}




@Preview
@Composable
fun PreviewTarjetaColeccionesFavoritas(){
    TarjetaColeccionesFavoritas(
        imgId = R.drawable.fc1_short_mantras,
        descripcionId = R.string.short_mantras_txt,
        modifier = Modifier.padding(8.dp)

    )
}

@Composable
fun SeccionInicio(
    @StringRes tituloId: Int,
    modifier: Modifier = Modifier,
    contenidoComponible: @Composable () ->Unit
){
    Column {
        Text(
            text = stringResource(tituloId),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        contenidoComponible()
    }
}

@Preview
@Composable
fun PreviewSeccionInicio(){
    SeccionInicio(
        R.string.alinea_tu_cuerpo_txt,
    ) {
        FilaAlineaTuCuerpo()
    }
}


@Preview
@Composable
fun PreviewSeccionInicio2(){
    SeccionInicio(
        R.string.favorite_coleccions_txt,
    ) {
        CuadriculaColeccionesFavoritas()
    }
}

@Composable
fun PantallaPrincipal(){
    Column {
        Spacer(Modifier.height(16.dp))
        BarraDeBusqueda(
            Modifier.padding(horizontal = 16.dp)
        )
        SeccionInicio(
            R.string.alinea_tu_cuerpo_txt,
        ) {
            FilaAlineaTuCuerpo()
        }
        SeccionInicio(
            R.string.favorite_coleccions_txt,
        ) {
            CuadriculaColeccionesFavoritas()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewPantallaPrincipal(){
    MeditacionAppTheme {
        PantallaPrincipal()
    }
}

private val datosAlineaTuCuerpo = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions_txt,
    R.drawable.ab2_quick_yoga to R.string.quick_yoga_txt,
    R.drawable.ab4_tabata to R.string.tabata_txt,
    R.drawable.ab3_stretching to R.string.stretching_txt,
    R.drawable.ab5_hiit to R.string.hiit_txt,
    R.drawable.ab6_pre_natal_yoga to R.string.pre_natal_yoga_txt
).map { ImagenTextoDibujables(it.first, it.second) }

private val datosColeccionesFavoritas = listOf(
    R.drawable.fc1_short_mantras to R.string.short_mantras_txt,
    R.drawable.fc2_nature_meditations to R.string.nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.stress_and_anxiety_txt,
    R.drawable.fc4_self_massage to R.string.self_massage_txt,
    R.drawable.fc5_overwhelmed to R.string.overwhelmed_txt,
    R.drawable.fc6_nightly_wind_down to R.string.nightly_wind_down
).map { ImagenTextoDibujables(it.first, it.second) }


private data class ImagenTextoDibujables(
    @DrawableRes val imageId: Int,
    @StringRes val textId: Int
)