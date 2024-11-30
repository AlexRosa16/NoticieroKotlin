package com.alexrosa.noticieroalex
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NoticiaAdapter(
    private val context: Context,
    private val noticias: List<Noticia>
) : RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    class NoticiaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewNoticia: ImageView = view.findViewById(R.id.imageViewNoticia)
        val textViewTitulo: TextView = view.findViewById(R.id.textViewTitulo)
        val textViewResumen: TextView = view.findViewById(R.id.textViewResumen)
        val textViewFecha: TextView = view.findViewById(R.id.textViewFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_noticia, parent, false)
        return NoticiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = noticias[position]

        holder.textViewTitulo.text = noticia.titulo
        holder.textViewResumen.text = noticia.resumen
        holder.textViewFecha.text = noticia.fecha

        Glide.with(holder.itemView.context)
            .load(noticia.imagen)
            .centerCrop()
            .into(holder.imageViewNoticia)


        holder.itemView.setOnClickListener {
            val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("lastNoticia", noticia.titulo)
            editor.apply()

            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = android.net.Uri.parse(noticia.enlace)
            }
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = noticias.size
}
