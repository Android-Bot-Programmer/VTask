package ru.vaa.vtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.vaa.vtask.R
import ru.vaa.vtask.components.CustomCardTextComponent
import ru.vaa.vtask.components.CustomFAB
import ru.vaa.vtask.components.CustomHeadingCardTextComponent
import ru.vaa.vtask.components.CustomHeadingTextComponent
import ru.vaa.vtask.components.CustomTextComponent
import ru.vaa.vtask.data.LoaderIntro
import ru.vaa.vtask.data.OnBoardingData
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.navigation.PostVTaskRouter

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoardScreen() {
    val items = ArrayList<OnBoardingData>()

    items.add(
        OnBoardingData(
            R.raw.animation_hello,
            stringResource(R.string.hi_we_are_glad_to_see_you),
            stringResource(R.string.to_continue_using_this_app_you_need_to_log_in_or_create_an_account_if_you_dont_t_haveit_yet)
        )
    )
    items.add(
        OnBoardingData(
            R.raw.animation_log_in,
            stringResource(R.string.choose_th_next_step),
            stringResource(R.string.you_can_choose_only_one)
        )
    )
    val pagerState = rememberPagerState(initialPage = 0)

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.TopStart) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                count = item.size,
                state = pagerState
            ) { page ->
                Column(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item[page].image?.let {
                        LoaderIntro(
                            modifier = Modifier
                                .size(300.dp)
                                .fillMaxWidth()
                                .align(alignment = Alignment.CenterHorizontally),
                            image = it
                        )
                    }
                    CustomHeadingTextComponent(value = item[page].title)
                    CustomTextComponent(value = item[page].desc)
                    SelectSection(pagerState)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SelectSection(pagerState: PagerState) {
    val listRadioOptions = listOf(
        OnBoardingData(
            image = null,
            title = stringResource(R.string.create_a_new_account),
            desc = stringResource(R.string.create_your_account_if_you_dont_have_it)
        ),
        OnBoardingData(
            image = null,
            title = stringResource(R.string.log_in),
            desc = stringResource(R.string.if_you_already_have_an_account_log_in)
        )
    )
    val scope = rememberCoroutineScope()
    var selectedItem by remember {
        mutableStateOf(listRadioOptions[0])
    }
    val logInScreen = stringResource(id = R.string.log_in)
    val signUpScreen = stringResource(id = R.string.create_a_new_account)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .selectableGroup(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (pagerState.currentPage == 1) {
                listRadioOptions.forEach { label ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        elevation = CardDefaults.cardElevation(3.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = selectedItem == label,
                                    onClick = { selectedItem = label },
                                    role = Role.RadioButton
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedItem == label,
                                onClick = { selectedItem = label }
                            )
                            Column {
                                CustomHeadingCardTextComponent(label.title)
                                CustomCardTextComponent(label.desc)
                            }
                        }
                    }
                }
            }

        }
        CustomFAB(
            modifier = Modifier
                .padding(top = 15.dp, bottom = 20.dp)
                .align(Alignment.BottomCenter)
                .clip(CircleShape),
            isEnabled = true
        ) {
            when (pagerState.currentPage) {
                0 -> scope.launch {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                }

                1 -> {
                    when (selectedItem.title) {
                        logInScreen -> PostVTaskRouter.navigateTo(Screen.LogInScreen)
                        signUpScreen -> PostVTaskRouter.navigateTo(Screen.SignUpScreen)
                    }
                }
            }
        }
    }
}
