package com.fredprojects.helloworld.ui.navigation

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.presentation.features.clients.anime.AQScreen
import com.fredprojects.helloworld.presentation.features.clients.anime.vm.AnimeQuotesVM
import com.fredprojects.helloworld.presentation.features.clients.astronomy.AstronomyInfoScreen
import com.fredprojects.helloworld.presentation.features.clients.astronomy.vm.AstronomyInfoVM
import com.fredprojects.helloworld.presentation.features.clients.math.MathInfoScreen
import com.fredprojects.helloworld.presentation.features.clients.math.vm.MathVM
import com.fredprojects.helloworld.presentation.features.inequality.InequalityScreen
import com.fredprojects.helloworld.presentation.features.inequality.vm.InequalityVM
import com.fredprojects.helloworld.presentation.features.jumps.list.JDListScreen
import com.fredprojects.helloworld.presentation.features.jumps.list.vm.JDEvents
import com.fredprojects.helloworld.presentation.features.jumps.list.vm.JDListVM
import com.fredprojects.helloworld.presentation.features.jumps.rope.JumpingRopeScreen
import com.fredprojects.helloworld.presentation.features.jumps.rope.vm.JREvents
import com.fredprojects.helloworld.presentation.features.jumps.rope.vm.JumpingRopeVM
import com.fredprojects.helloworld.presentation.features.pws.PWListScreen
import com.fredprojects.helloworld.presentation.features.pws.landscape.LandscapeUpsertPWScreen
import com.fredprojects.helloworld.presentation.features.pws.portrait.PortraitUpsertPWScreen
import com.fredprojects.helloworld.presentation.features.pws.vm.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    fibonacciScreen: @Composable () -> Unit,
    onTakePicture: () -> Uri,
    modifier: Modifier = Modifier,
    inequalityVM: InequalityVM = koinViewModel(),
    pwListVM: PWListVM = koinViewModel(),
    upsertPWVM: UpsertPWVM = koinViewModel(),
    jdListVM: JDListVM = koinViewModel(),
    jumpingRopeVM: JumpingRopeVM = koinViewModel(),
    mathVM: MathVM = koinViewModel(),
    aqVM: AnimeQuotesVM = koinViewModel(),
    astronomyInfoVM: AstronomyInfoVM = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val deleteMessage = stringResource(R.string.deletedRecord)
    val cancel = stringResource(R.string.cancel)
    Column(modifier) {
        NavHost(navController = navController, startDestination = Routes.INEQUALITY) {
            composable(Routes.INEQUALITY) {
                InequalityScreen(inequalityVM.solution.collectAsState().value) { a, b ->
                    inequalityVM.solveTheInequality(a, b)
                }
            }
            composable(Routes.FIBONACCI) { fibonacciScreen() }
            composable(Routes.PW_LIST) {
                PWListScreen(pwListVM.pwState.collectAsState().value, { navController.navigate(Routes.UPSERT_PW + "?id=${it}") }) {
                    pwListVM.onEvent(it)
                    if(it is PWEvents.DeletePW) scope.launch {
                        val result = snackbarHostState.showSnackbar(deleteMessage, actionLabel = cancel, duration = SnackbarDuration.Long)
                        if(result == SnackbarResult.ActionPerformed) pwListVM.onEvent(PWEvents.RestorePW)
                    }
                }
            }
            composable(
                Routes.UPSERT_PW + "?id={id}",
                listOf(
                    navArgument("id") {
                        type = NavType.IntType
                        defaultValue = -1
                        nullable = true
                    }
                )
            ) {
                if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    LandscapeUpsertPWScreen(
                        state = upsertPWVM.upsertPWState.collectAsState().value,
                        goBack = { navController.navigateUp() },
                        onTakePicture = onTakePicture
                    ) { upsertPWVM.upsert(it) }
                } else {
                    PortraitUpsertPWScreen(
                        state = upsertPWVM.upsertPWState.collectAsState().value,
                        goBack = { navController.navigateUp() },
                        onTakePicture = onTakePicture
                    ) { upsertPWVM.upsert(it) }
                }
            }
            composable(Routes.JUMPING_ROPE) {
                JumpingRopeScreen(jumpingRopeVM.jrState.collectAsState().value) {
                    jumpingRopeVM.onEvent(it)
                    if(it is JREvents.InsertJD) navController.navigate(Routes.JD_LIST)
                }
            }
            composable(Routes.JD_LIST) {
                JDListScreen(jdListVM.jdState.collectAsState().value) {
                    jdListVM.onEvent(it)
                    if(it is JDEvents.DeleteJD) scope.launch { snackbarHostState.showSnackbar(deleteMessage, duration = SnackbarDuration.Long) }
                }
            }
            composable(Routes.MATH) {
                MathInfoScreen(mathVM.mathInfo.collectAsState().value) { mathVM.getMathInfo(it) }
            }
            composable(Routes.ANIME_QUOTES) {
                AQScreen(aqVM.animeQuotes.collectAsState().value) { aqVM.getAnimeQuotes(it) }
            }
            composable(Routes.ASTRONOMY) {
                AstronomyInfoScreen(astronomyInfoVM.astronomyInfo.collectAsState().value) { ra, dec, radius ->
                    astronomyInfoVM.getAstronomyInfo(ra, dec, radius.toFloatOrNull() ?: 0f)
                }
            }
        }
    }
}