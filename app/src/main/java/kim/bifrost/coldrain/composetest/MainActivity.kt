package kim.bifrost.coldrain.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kim.bifrost.coldrain.composetest.ui.theme.ComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme() {
                Main()
            }
        }
    }

    @Composable
    fun Main(
        avatar: String = "https://gitee.com/coldrain-moro/images_bed/raw/master/images/chino.jpg",
    ) {
        var selectedItem by remember { mutableStateOf(0) }
        val items = listOf("主页", "通知", "搜索")
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.White,
                    modifier = Modifier.height(50.dp)
                ) {
                    items.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            icon = {
                                when(index){
                                    0 -> Icon(Icons.Filled.Home, contentDescription = null)
                                    1 -> Icon(Icons.Filled.Notifications, contentDescription = null)
                                    else -> Icon(Icons.Filled.Search, contentDescription = null)
                                }
                            },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index },
                        )
                    }
                }
            }
        ) {
            Column(
                Modifier.padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                    ) {
                        Image(
                            painter = rememberImagePainter(data = avatar),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = "主页",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.padding(15.dp)
                    )
                }
                Text(
                    text = "我的工作",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(top = 30.dp, bottom = 20.dp)
                )
                WorkSelection(icon = R.drawable.ic_issue_opened, text = "议题", Color(0xFF25CA25))
                WorkSelection(icon = R.drawable.ic_pull_request, text = "拉取请求", Color(0xFF11ABF1))
                WorkSelection(icon = R.drawable.ic_git_repository_line, text = "仓库", Color(0xFF8811F1))
                WorkSelection(icon = R.drawable.ic_organization, text = "组织", Color(0xFFF18C11))
                Divider(
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 10.dp)
                )
                    Column(
                        Modifier.padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = "收藏夹",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Text(
                            text = "将仓库加入收藏夹以便随时快速访问，而无需搜索",
                            style = TextStyle(
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                shape = RoundedCornerShape(20)
                            ) {
                                Button(
                                    onClick = { /*TODO*/ },
                                    elevation = null,
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(20),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Transparent
                                    )
                                ) {
                                    Text(
                                        text = "添加收藏",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            color = Color(0xFF229DD5)
                                        ),
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }
                        }
                    }
            }
        }
    }

    @Composable
    fun WorkSelection(
        icon: Int,
        text: String,
        color: Color
    ) {
        Row(
            Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 圆角icon
            Surface(
                shape = RoundedCornerShape(10),
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .background(color),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 15.sp
                )
            )
        }
    }

    @Preview
    @Composable
    fun Preview() {
        Main()
    }
}