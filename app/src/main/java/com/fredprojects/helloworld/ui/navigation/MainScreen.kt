package com.fredprojects.helloworld.ui.navigation

import android.app.Activity.BIND_AUTO_CREATE
import android.content.*
import android.os.IBinder
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.fredprojects.helloworld.FibSequenceService
import com.fredprojects.helloworld.MainActivity.Companion.DISPLAY_RESULT
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.di.AppQualifiers
import com.fredprojects.helloworld.presentation.core.FredText
import com.fredprojects.helloworld.presentation.core.displayToast
import com.fredprojects.helloworld.presentation.features.clients.astronomy.*
import com.fredprojects.helloworld.presentation.features.clients.math.*
import com.fredprojects.helloworld.presentation.features.fibonacci.*
import com.fredprojects.helloworld.presentation.features.inequality.*
import com.fredprojects.helloworld.presentation.features.jumps.JDListScreen
import com.fredprojects.helloworld.presentation.features.jumps.vm.JDListVM
import com.fredprojects.helloworld.presentation.features.pws.PWListScreen
import com.fredprojects.helloworld.presentation.features.pws.vm.PWListVM
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named
import java.util.UUID

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(activityContext: ComponentActivity, navigateTo: (String) -> Unit) {
    val inequalityVM: InequalityVM = koinViewModel(named(AppQualifiers.INEQUALITY_VM))
    val pwListVM: PWListVM = koinViewModel(named(AppQualifiers.PW_LIST_VM))
    val jdListVM: JDListVM = koinViewModel(named(AppQualifiers.JD_LIST_VM))
    val mathVM: MathVM = koinViewModel(named(AppQualifiers.MATH_VM))
    val astronomyInfoVM: AstronomyInfoVM = koinViewModel(named(AppQualifiers.ASTRONOMY_INFO_VM))
    val pagerState = rememberPagerState { 6 }
    val scope = rememberCoroutineScope()
    Scaffold(
        Modifier.fillMaxSize(),
        bottomBar = {
            MainScrollableTabRow(pagerState.currentPage) { scope.launch { pagerState.animateScrollToPage(it) } }
        }
    ) { padding ->
        HorizontalPager(pagerState, Modifier.fillMaxSize().padding(padding)) { page ->
            when(page) {
                0 -> {
                    var fibSequences by rememberSaveable { mutableStateOf(emptyList<FibonacciBinder>()) }
                    if(activityContext.intent.action != DISPLAY_RESULT) {
                        FibScreen(fibSequences) {
                            activityContext.apply {
                                val intent = Intent(this, FibSequenceService::class.java)
                                intent.action = UUID.randomUUID().toString()
                                intent.putExtra(FibSequenceService.NUMBER, it)
                                val serviceConnection = object : ServiceConnection {
                                    override fun onServiceConnected(p0: ComponentName?, bind: IBinder?) {
                                        val localBinder = bind as? FibonacciBinder
                                        if (localBinder != null) fibSequences += listOf(localBinder)
                                    }
                                    override fun onServiceDisconnected(p0: ComponentName?) = displayToast(R.string.fibServiceError)
                                }
                                bindService(intent, serviceConnection, BIND_AUTO_CREATE)
                            }
                        }
                    } else navigateTo(Routes.FIBONACCI)
                }
                1 -> InequalityScreen(inequalityVM.solution.collectAsState().value, inequalityVM::solveTheInequality)
                2 -> PWListScreen(pwListVM.pwState.collectAsState().value, pwListVM::onEvent) {
                    if(it != null) {
                        navigateTo(Routes.UPSERT_PW + "?id=${it}")
                    } else navigateTo(Routes.UPSERT_PW)
                }
                3 -> JDListScreen(jdListVM.jdState.collectAsState().value, jdListVM::onEvent) {
                    navigateTo(Routes.JUMPING_ROPE)
                }
                4 -> AstronomyInfoScreen(astronomyInfoVM.astronomyInfo.collectAsState().value) { ra, dec, radius ->
                    astronomyInfoVM.getAstronomyInfo(ra, dec, radius.toFloatOrNull() ?: 2f)
                }
                5 -> MathInfoScreen(mathVM.mathInfo.collectAsState().value, mathVM::getMathInfo)
            }
        }
    }
}
@Composable
private fun MainScrollableTabRow(currentPage: Int, animateScrollToPage: (Int) -> Unit) {
    ScrollableTabRow(
        selectedTabIndex = currentPage,
        modifier = Modifier.fillMaxWidth().border(TabRowDefaults.DividerThickness, MaterialTheme.colors.onBackground),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    ) {
        Routes.mainScreenItems.forEachIndexed { i, screenRoute ->
            Tab(
                selected = i == currentPage,
                onClick = { animateScrollToPage(i) },
                text = { FredText(screenRoute) },
            )
        }
    }
}