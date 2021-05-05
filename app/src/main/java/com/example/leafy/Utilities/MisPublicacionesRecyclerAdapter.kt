package com.example.leafy.Utilities

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.leafy.Data.DataManager
import com.example.leafy.Data.Publicaciones
import com.example.leafy.ImageUtilities
import com.example.leafy.R

class MisPublicacionesRecyclerAdapter(val mContext: Context, var publicaciones: List<Publicaciones>):
    RecyclerView.Adapter<MisPublicacionesRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MisPublicacionesRecyclerAdapter.MyViewHolder {

        val v:View= LayoutInflater.from(mContext).inflate(R.layout.item_mis_publicaciones, parent, false)
        val vHolder: MisPublicacionesRecyclerAdapter.MyViewHolder = MyViewHolder(v)

        vHolder.itemView.setOnClickListener(){
            //Toast.makeText(mContext, "Test"+ vHolder.layoutPosition, Toast.LENGTH_SHORT).show()
        }


        return vHolder

    }

    override fun onBindViewHolder(
        holder: MisPublicacionesRecyclerAdapter.MyViewHolder,
        position: Int
    ) {

        holder.av_id?.setText(publicaciones.get(position).id.toString()!!)
        holder.av_titulo.setText(publicaciones.get(position).titulo)

        var idAutor=publicaciones.get(position).idCreador
        var user=DataManager.usuarios.find { it.id == idAutor }

        holder.av_autor.setText(user?.nombre + " " + user?.apellido)

        if(publicaciones.get(position).imgArray == null){
            holder.av_imagen.setImageResource(publicaciones.get(position).idImg1!!)
        }else{
            holder.av_imagen.setImageBitmap(ImageUtilities.getBitMapFromByteArray(publicaciones.get(position).imgArray!!))
        }

        if(publicaciones.get(position).imgArrayPerfil == null){
            holder.av_perfil.setImageResource(publicaciones.get(position).idImgPerfil!!)
        }else{
            holder.av_perfil.setImageBitmap(ImageUtilities.getBitMapFromByteArray(publicaciones.get(position).imgArrayPerfil!!))
        }

        /* cambio de borrador (naranja), a publicado verde
        holder.av_estado.text= mContext.getString(R.string.publicado)
        holder.av_estado.setTextColor(mContext.getColor(R.color.leafy_letras_oscuro))
        holder.av_estado.buttonTintList=(mContext.getColorStateList(R.color.leafy_letras_oscuro));
        */

    }

    override fun getItemCount(): Int {
        return publicaciones.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        public val av_titulo: TextView = itemView.findViewById(R.id.textView19)
        public val av_autor: TextView = itemView.findViewById(R.id.textView20)
        public val av_estado: RadioButton = itemView.findViewById(R.id.radioButton)
        public val av_perfil: ImageView = itemView.findViewById(R.id.imageView4)
        public val av_imagen: ImageView = itemView.findViewById(R.id.imageView9)
        public val av_id: TextView?= itemView.findViewById(R.id.textView21)

        public val av_edit_button: ImageButton?= itemView.findViewById(R.id.imageButton8)
        public val av_delete_button: ImageButton?= itemView.findViewById(R.id.imageButton10)

    }

}