package com.richmondprojects.jobs

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.richmondprojects.jobs.ui.theme.JOBSTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationSplash()
                }
            }
        }
    }
}

@Composable
fun NavigationSplash() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_app") {
            MainApp()
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val auto = remember { androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(key1 = true) {
        auto.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(0.2F).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("main_app")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "Splash Image",
            modifier = Modifier
                .scale(auto.value)
                .size(500.dp)
        )
    }
}

@Composable
fun MainApp() {
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnBoardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        App()
    }
}

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Top,
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.relax),
            contentDescription = "ObBoarding Image",
            modifier = Modifier.size(500.dp)
        )
        Text(
            text = stringResource(id = R.string.onBaording_text),
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = "Explore over 100000 available jobs and \nboost your career now!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.padding(vertical = 50.dp))
        Button(
            colors = ButtonDefaults.buttonColors(Color(0xfff5fffa)),
            onClick = onContinueClicked,
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
        ) {
            Text(text = "Explore Now")
        }
    }
}

@Composable
fun TopPart() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            tint = Color(0xff18bdc6),
            contentDescription = "Menu Icon",
            modifier = Modifier
                .background(color = Color(0xffe8fafb))
                .size(50.dp)
        )
        Text(
            text = "Hi, Richmond!",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Default.Notifications, tint = Color(0xff18bdc6),
            contentDescription = "Notification Icon",
            modifier = Modifier
                .background(color = Color(0xffe8fafb))
                .size(50.dp)
        )
    }
}

@Composable
fun SearchPart() {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        placeholder = {
            Text(text = "Search Job, Company", color = MaterialTheme.colors.onBackground)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Sort Icon",
                tint = Color(0xff18bdc6)
            )
        }
    )
}

@Composable
fun TipsForYou() {
    Card(
        backgroundColor = Color(0xfff5fffa),
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                Text(
                    text = "How to create a \n perfect CV for you",
                    textAlign = TextAlign.Right,
                )
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                Button(onClick = { }, colors = ButtonDefaults.buttonColors(Color.White)) {
                    Text(text = "Details")
                }
            }
            Image(
                painter = painterResource(id = R.drawable.hello),
                contentDescription = "Bitmoji on Tips Card",
                Modifier
                    .height(150.dp)
                    .width(120.dp)
            )
        }
    }
}

@Composable
fun Categories(
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    Column(horizontalAlignment = CenterHorizontally) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "Dietitian",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RecentJobs(
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "gmail icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
        )
        Spacer(modifier = Modifier.padding(horizontal = 7.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = stringResource(id = text), style = MaterialTheme.typography.h5)
                Row {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon"
                    )
                    Text(text = "USA")
                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Star")
                    Text(text = "Ful Time")
                }

            }
            Spacer(modifier = Modifier.padding(horizontal = 50.dp))
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Fav")
                Text(text = "-2h")
            }
        }

    }
}

@Composable
fun CategoryRow() {
    LazyRow(
        modifier = Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(CategoriesData) { item ->
            Categories(drawable = item.drawable, text = item.text)
        }
    }
}

@Composable
fun RecentColumn() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        items(RecentData) { item ->
            RecentJobs(drawable = item.drawable, text = item.text)
        }
    }
}

@Composable
fun MainScreen() {
    Column(horizontalAlignment = CenterHorizontally) {
        TopPart()
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), elevation = 10.dp
        ) {
            SearchPart()
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    10.dp
                )
        ) {
            Text(text = "Tips for you", fontWeight = FontWeight.Black)
            Text(text = "See all", style = MaterialTheme.typography.subtitle2)
        }
        Surface(modifier = Modifier.padding(15.dp)) {
            TipsForYou()
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    10.dp
                )
        ) {
            Text(text = "Category", fontWeight = FontWeight.Black)
            Text(text = "See all", style = MaterialTheme.typography.subtitle2)
        }
        CategoryRow()
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    10.dp
                )
        ) {
            Text(text = "Recent Jobs", fontWeight = FontWeight.Black)
            Text(text = "See all", style = MaterialTheme.typography.subtitle2)
        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        RecentColumn()
    }
}

@Composable
fun BottomNavigation() {
    BottomNavigation(backgroundColor = Color.White) {
        BottomNavigationItem(selected = true, onClick = { }, icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home Icon"
            )
        })
        BottomNavigationItem(selected = false, onClick = { }, icon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info Icon"
            )
        })
        BottomNavigationItem(selected = false, onClick = { }, icon = {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification Icon"
            )
        })
        BottomNavigationItem(selected = false, onClick = { }, icon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon"
            )
        })
    }
}

@Preview
@Composable
fun BottomNavigationPreview() {
    BottomNavigation()
}

private val CategoriesData = listOf(
    R.drawable.food_tip_im to R.string.Job1,
    R.drawable.design to R.string.Job2,
    R.drawable.data to R.string.Job3,
    R.drawable.finance to R.string.Job4,
    R.drawable.mechanical to R.string.Job5,
    R.drawable.software_engineering to R.string.Job6,
    R.drawable.maketting to R.string.Job7
).map { DrawableStringPair(it.first, it.second) }

private val RecentData = listOf(
    R.drawable.google to R.string.Company1,
    R.drawable.instagram to R.string.Company2,
    R.drawable.tesla to R.string.Company3,
    R.drawable.youtube to R.string.Company4,
    R.drawable.tiktok to R.string.Company5,
    R.drawable.lambo to R.string.Company6,
    R.drawable.twitter to R.string.Company7,
    R.drawable.whatsapp to R.string.Company8
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview
@Composable
fun TopPartPreview() {
    JOBSTheme {
        TopPart()
    }
}

@Preview
@Composable
fun SearchPreview() {
    JOBSTheme {
        SearchPart()
    }
}

@Preview
@Composable
fun TipsPreview() {
    JOBSTheme {
        TipsForYou()
    }
}

@Preview
@Composable
fun CategoriesPreview() {
    JOBSTheme {
        Categories(drawable = R.drawable.food_tip_im, text = R.string.Job1)
    }
}

@Preview
@Composable
fun RecentPreview() {
    JOBSTheme {
        RecentJobs(drawable = R.drawable.google, text = R.string.Company1)
    }
}

@Preview
@Composable
fun CategoriesRowPreview() {
    JOBSTheme {
        CategoryRow()
    }
}

@Preview
@Composable
fun RecentColumnPreview() {
    JOBSTheme {
        RecentColumn()
    }
}

@Composable
@Preview(showBackground = true, name = "navigation")
fun SplashPreview() {
    JOBSTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavigationSplash()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    JOBSTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            OnBoardingScreen(onContinueClicked = {})
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    JOBSTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainApp()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    JOBSTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainScreen()
        }

    }
}

@Composable
fun App() {
    Scaffold(
        bottomBar = { BottomNavigation() }
    ) {
        MainScreen()
    }
}

@Preview
@Composable
fun AppPreview() {
    JOBSTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            App()
        }
    }
}