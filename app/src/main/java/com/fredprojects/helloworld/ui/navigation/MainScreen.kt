package com.fredprojects.helloworld.ui.navigation

import android.app.Activity.BIND_AUTO_CREATE
import android.content.*
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.fredprojects.core.ui.FredText
import com.fredprojects.features.clients.presentation.astronomy.*
import com.fredprojects.features.clients.presentation.math.*
import com.fredprojects.features.fibonacci.impl.*
import com.fredprojects.features.inequality.impl.*
import com.fredprojects.features.jumps.domain.utils.JumpStatus
import com.fredprojects.features.jumps.presentation.*
import com.fredprojects.features.jumps.presentation.vm.JDEvents
import com.fredprojects.features.jumps.presentation.vm.JDListVM
import com.fredprojects.features.pws.presentation.PWListScreen
import com.fredprojects.features.pws.presentation.vm.PWListVM
import com.fredprojects.helloworld.FibSequenceService
import com.fredprojects.helloworld.MainActivity.Companion.DISPLAY_RESULT
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.ui.displayToast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named
import java.util.UUID

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(activityContext: ComponentActivity, navigateTo: (String) -> Unit) {
    val inequalityVM: InequalityVM = koinViewModel(named<InequalityVM>())
    val pwListVM: PWListVM = koinViewModel(named<PWListVM>())
    val mathVM: MathVM = koinViewModel(named<MathVM>())
    val astronomyInfoVM: AstronomyInfoVM = koinViewModel(named<AstronomyInfoVM>())
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
                0 -> FibonacciScreen(activityContext, navigateTo)
                1 -> InequalityScreen(inequalityVM.solution.collectAsState().value, inequalityVM::solveTheInequality)
                2 -> PWListScreen(pwListVM.pwState.collectAsState().value, pwListVM::onEvent) {
                    if(it != null) {
                        navigateTo(Routes.UPSERT_PW + "?id=${it}")
                    } else navigateTo(Routes.UPSERT_PW)
                }
                3 -> JDListScreen(navigateTo)
                4 -> MathInfoScreen(mathVM.mathInfo.collectAsState().value, mathVM::getMathInfo)
                5 -> AstronomyInfoScreen(astronomyInfoVM.astronomyInfo.collectAsState().value) { ra, dec, radius ->
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
@Composable
private fun FibonacciScreen(activityContext: ComponentActivity, navigateTo: (String) -> Unit) {
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
@Composable
private fun JDListScreen(navigateTo: (String) -> Unit) {
    val jdListVM: JDListVM = koinViewModel(named<JDListVM>())
    val jdState = jdListVM.jdState.collectAsState().value
    var isValuesCorrect by remember { mutableStateOf(true) }
    JDListScreen(jdState, jdListVM::onEvent) {
        navigateTo(Routes.JUMPING_ROPE)
    }
    if(jdState.isDialogVisible) JDDialog(jdState, jdListVM::onEvent, isValuesCorrect)
    LaunchedEffect(key1 = true) {
        jdListVM.jdStatus.collectLatest {
            when(it) {
                JumpStatus.SUCCESS -> {
                    isValuesCorrect = true
                    jdListVM.onEvent(JDEvents.SwitchingDialog)
                }
                JumpStatus.NOTHING -> isValuesCorrect = true
                else -> isValuesCorrect = false
            }
        }
    }
}