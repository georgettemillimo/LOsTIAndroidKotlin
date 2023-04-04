package com.example.losti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.losti.ui.theme.LOstITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LOstITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login()
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Login() {

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    Box (modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.background),
            contentDescription = "Login Background",
            modifier = Modifier
                .fillMaxSize()
                .blur(8.dp),
            contentScale = ContentScale.Crop
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .alpha(0.5f)
            .clip(
                CutCornerShape(
                    topStart = 48.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 48.dp
                )
            )
            .background(MaterialTheme.colors.surface)

        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(64.dp))
            LoginHeader()

            Spacer(modifier = Modifier.height(48.dp))
            LoginBody(username, password,
                onUsernameChange = {
                    username=it
                },
                onPasswordChange = {
                    password = it
                },
                onForgotPasswordClick={

                })


            LoginFooter(
                onSignInClick = {},
                onSignUpClick = {})
        }
    }


}

@Composable
fun LoginHeader(){
    Text(text = "Welcome User", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
    Text(text = "Sign in to your Account", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
}









@Composable
fun ColumnScope.LoginBody(username: String, password: String,
                          onUsernameChange: (String) -> Unit,
                          onPasswordChange:(String)-> Unit, onForgotPasswordClick:()-> Unit){

    FieldsLayouts(value = username,
        label = "Username" ,
        placeholder = "Enter your Email " ,
        onValueChange = onUsernameChange,
        leadingIcon = {
            Icon(Icons.Default.Person, contentDescription = "Username" )
        }
    )

    Spacer(modifier = Modifier.height(8.dp))
    FieldsLayouts(value = password ,
        label = "Password" ,
        placeholder = "**********",
        onValueChange = onPasswordChange,
        visualTransformation = PasswordVisualTransformation(),
        leadingIcon = {
            Icon(Icons.Default.Lock, contentDescription = "Password")
        }
    )

    TextButton(onClick = { onForgotPasswordClick}, modifier = Modifier.align(Alignment.End)) {
        Text(text = "Forgot Password?")
    }
}




@Composable
fun LoginFooter(
    onSignInClick: ()-> Unit,
    onSignUpClick:()->Unit
){
    Button(onClick = onSignInClick, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Sign In")
    }

    TextButton(onClick = onSignUpClick) {
        Text(text = "Don't Have an Account? Click Here")
    }
}








@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FieldsLayouts(value: String,
                  label: String,
                  placeholder: String,
                  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                  leadingIcon: @Composable (() -> Unit)? = null,
                  trailingIcon: @Composable (() -> Unit)? = null,
                  visualTransformation : VisualTransformation = VisualTransformation.None,
                  onValueChange: (String)-> Unit){

    OutlinedTextField(value = value, onValueChange = onValueChange,
        label= {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
    )

}