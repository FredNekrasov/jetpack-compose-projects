package com.fredprojects.helloworld.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.util.fastForEachIndexed
import com.fredprojects.core.ui.*
import com.fredprojects.features.clients.presentation.astronomy.*
import com.fredprojects.features.clients.presentation.math.*
import com.fredprojects.features.math.presentation.inequality.*
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    inequalityVM: InequalityVM,
    mathVM: MathVM,
    astronomyInfoVM: AstronomyInfoVM,
    goBack: Action
) {
    val pagerState = rememberPagerState { 3 }
    val scope = rememberCoroutineScope()
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = { FredTopBar(goBack) },
        bottomBar = {
            MainScrollableTabRow(pagerState.currentPage) { scope.launch { pagerState.animateScrollToPage(it) } }
        }
    ) { padding ->
        HorizontalPager(pagerState, Modifier.fillMaxSize().padding(padding)) { page ->
            when(page) {
                0 -> InequalityScreen(inequalityVM.solution.collectAsState().value, inequalityVM::solveTheInequality)
                1 -> MathInfoScreen(mathVM.mathInfo.collectAsState().value, mathVM::getMathInfo)
                2 -> AstronomyInfoScreen(astronomyInfoVM.astronomyInfo.collectAsState().value) { ra, dec, radius ->
                    astronomyInfoVM.getAstronomyInfo(ra, dec, radius.toFloatOrNull() ?: 2f)
                }
            }
        }
    }
}
@Composable
private fun MainScrollableTabRow(currentPage: Int, scrollToPage: (Int) -> Unit) {
    ScrollableTabRow(
        selectedTabIndex = currentPage,
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        Routes.mainScreenItems.fastForEachIndexed { i, screenRouteId ->
            Tab(
                selected = i == currentPage,
                onClick = { scrollToPage(i) },
                text = { FredText(stringResource(screenRouteId)) },
            )
        }
    }
}