package com.alexrosa.noticieroalex
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)

        val fondoNoticias = findViewById<ImageView>(R.id.fondoNoticias)

        Glide.with(this)
            .load(R.drawable.fondito)
            .into(fondoNoticias)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewNoticias)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val noticias = listOf(
            Noticia(
                1,
                "Sin cooperación no hay visas",
                "El presidente electo no ha concretado cómo forzará a otros países a recibir sus ciudadanos de vuelta, pero tiene a su disposición una ley que le permitirá denegar visados a los que se le opongan",
                "2024-11-26",
                "https://imagenes.elpais.com/resizer/v2/NF34AH4R5ZGPJEGKOZPW2MXK6E.jpg?auth=99a29dd585c9faee8f99694cfe7350cf05d382c005f13262b119336e428bd127&width=1200",
                "https://elpais.com/us/2024-11-29/sin-cooperacion-no-hay-visas-asi-puede-trump-obligar-a-cualquier-pais-a-aceptar-a-los-inmigrantes-que-deporte.html"
            ),
            Noticia(
                2,
                "Líderes ultras y populistas de todo el mundo",
                "Líderes ultras y populistas de todo el mundo esperan con ansia una victoria de Trump",
                "2024-11-25",
                "https://imagenes.elpais.com/resizer/v2/Y3SEMXEDQNCO7E7A4TY6A6GB4U.jpg?auth=1a64355d3f15446827798fff34951f89bc8c603a6c95657937a87ff7e768875f&width=1200",
                "https://elpais.com/internacional/elecciones-usa/2024-11-03/lideres-ultras-y-populistas-de-todo-el-mundo-esperan-con-ansia-una-victoria-de-trump.html"
            ),
            Noticia(
                3,
                "El chavismo carga contra Brasil",
                "El chavismo carga contra Brasil: O nos respeta o haremos que nos respete",
                "2024-10-30",
                "https://imagenes.elpais.com/resizer/v2/FVOP222RDMXQQDXJ3X3UBX7QTI.jpg?auth=70218f86750613c19a61ed008a927f691d038774eb315fc009efc75d94a9c7d4&width=1200",
                "https://elpais.com/america/2024-10-30/el-chavismo-carga-contra-brasil-o-nos-respeta-o-haremos-que-nos-respete.html"
            ),
            Noticia(4,
            "Pedro Sánchez, un tercer mandato de vértigo en el PSOE",
            "Pedro Sánchez, un tercer mandato de vértigo en el PSOE",
            "2024-11-24",
            "https://imagenes.elpais.com/resizer/v2/6344ZIH5IRAKVFNC3T7EA4XL6A.jpg?auth=eb35416fce1799be9117f716c9483a5ede8940ab3c2a44e3156766648ef9c9d7&width=980",
            "https://elpais.com/espana/2024-11-29/pedro-sanchez-un-tercer-mandato-de-vertigo-en-el-psoe-para-el-futuro-mas-incierto.html"
        ), Noticia(
            5,
            "Jugada redonda de Vox y rechinar de dientes de FAES",
            "Jugada redonda de Vox y rechinar de dientes de FAES",
            "2024-11-07",
            "https://imagenes.elpais.com/resizer/v2/5TFPBNFGRJCZBAAII3KP537NEY.jpg?auth=36040122ce6aea7db50716474763ad78d1bc34622916da6099a8c4d7ed7e4e18&width=980",
            "https://elpais.com/espana/2024-11-07/jugada-redonda-de-vox-y-chirriar-de-dientes-de-faes.html"
        )
        )

        recyclerView.adapter = NoticiaAdapter(this, noticias)

        val sharedPreferences: SharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val lastNoticia = sharedPreferences.getString("lastNoticia", null)

        if (lastNoticia != null) {
            Toast.makeText(this, "Última noticia leída: $lastNoticia", Toast.LENGTH_SHORT).show()
        }

    }
}
