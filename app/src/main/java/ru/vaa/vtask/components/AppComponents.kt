package ru.vaa.vtask.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vaa.vtask.R
import ru.vaa.vtask.ui.theme.Monochrome10
import ru.vaa.vtask.ui.theme.Monochrome20
import ru.vaa.vtask.ui.theme.Monochrome60
import ru.vaa.vtask.ui.theme.Monochrome80
import ru.vaa.vtask.ui.theme.Primary


@Composable
fun CustomTextComponent(value: String) {
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
fun CustomCardTextComponent(value: String) {
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
fun CustomHeadingTextComponent(value: String) {
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
fun CustomHeadingCardTextComponent(value: String) {
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
fun CustomTextField(label: String, painterResource: Painter, onTextSelected: (String) -> Unit) {
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
            containerColor = Monochrome10
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun CustomPasswordTextField(
    label: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit
) {
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
            containerColor = Monochrome10
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
            PasswordVisualTransformation()
    )
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = value, annotation = value)
            append(value)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        ClickableText(text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also {
                Log.d("ClickableTextComponent", "{$it}")
                onTextSelected(it.item)
            }
        })
    }
}

@Composable
fun CustomFAB(modifier: Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
        containerColor = Primary
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = stringResource(R.string.next),
            tint = Color.White
        )
    }
}