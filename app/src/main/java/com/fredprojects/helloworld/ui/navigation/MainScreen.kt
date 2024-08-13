package com.fredprojects.helloworld.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fredprojects.core.ui.*
import com.fredprojects.features.clients.presentation.astronomy.*
import com.fredprojects.features.clients.presentation.math.*
import com.fredprojects.features.inequality.impl.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
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
private fun MainScrollableTabRow(currentPage: Int, animateScrollToPage: (Int) -> Unit) {
    ScrollableTabRow(
        selectedTabIndex = currentPage,
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    ) {
        Routes.mainScreenItems.forEachIndexed { i, screenRouteId ->
            Tab(
                selected = i == currentPage,
                onClick = { animateScrollToPage(i) },
                text = { FredText(stringResource(screenRouteId)) },
            )
        }
    }
}