package ru.vaa.vtask.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vaa.vtask.R
import ru.vaa.vtask.ui.theme.Monochrome10
import ru.vaa.vtask.ui.theme.Monochrome20
import ru.vaa.vtask.ui.theme.Monochrome30
import ru.vaa.vtask.ui.theme.Monochrome50
import ru.vaa.vtask.ui.theme.Monochrome60
import ru.vaa.vtask.ui.theme.Monochrome70
import ru.vaa.vtask.ui.theme.Monochrome80
import ru.vaa.vtask.ui.theme.Primary
import ru.vaa.vtask.ui.theme.SystemError


@Composable
fun NormalTextComponent(value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            color = Monochrome60
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun NormalCardTextComponent(value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 3.dp),
        text = value,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            color = Monochrome60
        )
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = value,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            color = Monochrome80
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingCardTextComponent(value: String) {
    Text(
        modifier = Modifier
            .padding(top = 3.dp),
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            color = Monochrome80
        )
    )
}

@ExperimentalMaterial3Api
@Composable
fun CustomTextField(
    label: String,
    painterResource: Painter,
    errorStatus: Boolean = false,
    onTextSelected: (String) -> Unit
) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = Monochrome10,
            errorBorderColor = SystemError,
            errorLeadingIconColor = SystemError
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = if (textValue.value.isNotEmpty()) !errorStatus else errorStatus,
        trailingIcon = {
            if (!errorStatus && textValue.value.isNotEmpty())
                Icon(Icons.Filled.Error, "")
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun CustomPasswordTextField(
    label: String,
    painterResource: Painter,
    errorStatus: Boolean = false,
    onTextSelected: (String) -> Unit
) {
    val localFocusManager = LocalFocusManager.current

    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        label = { Text(text = label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = Monochrome10,
            errorBorderColor = SystemError,
            errorLeadingIconColor = SystemError
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisible.value) {
                stringResource(R.string.hide_password)
            } else {
                stringResource(R.string.show_password)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else
            PasswordVisualTransformation(),
        isError = if (password.value.isNotEmpty()) !errorStatus else errorStatus
    )
}

@Composable
fun UnderLinedTextComponent(value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            color = Monochrome60
        ),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun CustomFABNext(modifier: Modifier, isEnabled: Boolean = false, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { if (isEnabled) onClick() },
        containerColor = if (isEnabled) Primary else Monochrome30
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = stringResource(R.string.next),
            tint = Monochrome10
        )
    }
}

@Composable
fun CustomFABHome(imageVector: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape),
        onClick = { onClick() },
        containerColor = Primary,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun CustomProgressBar() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Monochrome20),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Primary)
    }
}

@Composable
fun BottomShadow(alpha: Float = 0.1f, height: Dp = 8.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Monochrome50.copy(alpha = alpha),
                        Color.Transparent,
                    )
                )
            )
    )
}

@Composable
fun SettingsItems(title: String, isSpacer: Boolean = true, onClick: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .background(Monochrome10)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            androidx.compose.material.Text(
                modifier = Modifier
                    .padding(top = 3.dp),
                text = title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = Monochrome70
                )
            )
            androidx.compose.material.Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = Monochrome70
            )
        }
        if (isSpacer) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
                    .height(2.dp)
                    .background(Monochrome30)
            )
        }
    }
}