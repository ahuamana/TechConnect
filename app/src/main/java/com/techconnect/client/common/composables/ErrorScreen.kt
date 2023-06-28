package com.techconnect.client.common.composables


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.techconnect.client.R


//Error General server
@Preview
@Composable
fun CustomErrorScreenSomethingHappens(
    modifier: Modifier = Modifier,
){
    CustomEmptyStateScreen(
        modifier = modifier,
        title = stringResource(id = R.string.empty_screen_title_error_something_went_wrong),
        //Algo pasó, por favor intenta de nuevo
        description = stringResource(id = R.string.empty_screen_description_error_something_went_wrong),
        image = R.drawable.background_something_wrong
    )
}

//no internet
@Preview
@Composable
fun CustomNoInternetConnectionScreen(
    modifier: Modifier = Modifier,
){
    CustomEmptyStateScreen(
        modifier = modifier,
        title = stringResource(id = R.string.empty_screen_title_no_internet),
        //Algo pasó, por favor intenta de nuevo
        description = stringResource(id = R.string.empty_screen_descripcion_no_internet),
        image = R.drawable.background_no_internet_connection
    )
}

//emptysearch
@Preview
@Composable
fun CustomEmptySearchScreen(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.empty_screen_search_title_no_results),
    description: String = stringResource(id = R.string.empty_screen_search_description_no_results, "busqueda")
){
    CustomEmptyStateScreen(
        modifier = modifier,
        title = title,
        description = description,
        image = R.drawable.background_empty_state
    )
}


