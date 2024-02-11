package com.example.film_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun getDataFromResponse(response: String, pattern: String): List<String?> {
    val regexNameRu = Regex(pattern)
    val matchResultNameRu = regexNameRu.findAll(response)
    val data = matchResultNameRu.map { it.groups[1]?.value }.toList()
    return data
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fav_button = findViewById<Button>(R.id.button2)
        fav_button.setOnClickListener{
            val intent = Intent(this, FavActivity::class.java)
            startActivity(intent)
        }
        var popular_films_list = ""
        Thread {
            popular_films_list = getPopular()
        }.start()


        val ids = getDataFromResponse(popular_films_list, "filmId\":(.*?)[,\\s]")
        val names = getDataFromResponse(popular_films_list, "nameRu\":\"([^\"]+)")
        val years = getDataFromResponse(popular_films_list, "\"year\\\":\\\"([^\\\"]+)\"")
        val posters = getDataFromResponse(popular_films_list, "posterUrl\":\"([^\"]+)")


        val topFilmsList: RecyclerView = findViewById(R.id.topFilmsList)
        val movies = arrayListOf<MovieCard>()
        for (i in 1..ids.size){
            movies.add(MovieCard(i, names[i] as String, "start", years[i] as String))
        }

        topFilmsList.layoutManager = LinearLayoutManager(this)
        topFilmsList.adapter = MoviesAdapter(movies, this)
    }
}

//android:layout_width="158dp"
//android:layout_height="45dp"
//android:layout_marginStart="15dp"
//android:layout_marginTop="676dp"
//<solid android:color="background: #DEEFFF" />
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Film_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Film_appTheme {
        Greeting("Android")
    }
}
*/