package com.jyoti.redux.controller

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RepoScreen(searchRepo: () -> Unit = {}) {
    Column {
        TextButton(
            onClick = {
                searchRepo.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Search")
        }
        Text(
            text = "Repo List Name",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.height(70.dp)
        )
        remember { false }
        RepoItemView(repoName = "Repo", repoFullName = "Full Name", stars = "4", forks = "100")
//        if (progressVisible) {
//            CircularProgressIndicator()
//        }
//        LazyColumn {
//            RepoItemView(repoName = "Repo", repoFullName = "Full Name", stars = "4", forks = "100")
//        }
    }
}

@Composable
fun RepoItemView(repoName: String, repoFullName: String, stars: String, forks: String) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = repoName,
            style = TextStyle(fontSize = 16.sp, color = Color.Black),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth()
        )
        Text(
            text = repoFullName,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth()
        )
        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
//            Image(
//                asset = vectorResource(id = R.drawable.ic_grade_black_24dp),
//                modifier = Modifier.preferredSize(20.dp)
//            )
            Text(
                text = stars,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
            )
//            Image(
//                asset = vectorResource(id = R.drawable.ic_if_git_branch_298785),
//                modifier = Modifier.preferredSize(20.dp)
//            )
            Text(
                text = forks,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewRepoScreen(){
    RepoScreen()
}
