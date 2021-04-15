package com.example.leafy.Utilities

import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.leafy.Data.DataManager
import com.example.leafy.Data.MisPlantas
import com.example.leafy.Data.Publicaciones
import com.example.leafy.Data.User
import com.example.leafy.ImageUtilities
import com.example.leafy.R
import java.io.ObjectOutput
import java.util.*

public class MisPlantasRecyclerAdapter (val mContext: Context, var mis_plantas: List<MisPlantas>):
        RecyclerView.Adapter<MisPlantasRecyclerAdapter.MyViewHolder>(), View.OnClickListener {



    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        public val av_nombre: TextView = itemView.findViewById(R.id.textView14)
        public val av_imagen: ImageView = itemView.findViewById(R.id.imageView8)
        public val av_bar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val v:View= LayoutInflater.from(mContext).inflate(R.layout.item_mis_plantas, parent, false)
        val vHolder: MisPlantasRecyclerAdapter.MyViewHolder = MyViewHolder(v)

        vHolder.itemView.setOnClickListener(){
            //Toast.makeText(mContext, "Test"+ vHolder.layoutPosition, Toast.LENGTH_SHORT).show()
        }


        return vHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /*
        var user: User? = DataManager.usuarios.find { it.id == mis_plantas[position].idUser }
        //Toast.makeText(this.mContext, user?.nombre, Toast.LENGTH_SHORT).show()
        var userName=user?.nombre
        holder.av_nombre.setText(userName)*/



        var planta: Publicaciones? = DataManager.publicaciones.find { it.id == mis_plantas.get(position).idPlantas }
        var plantaImage=planta?.imgArray

        holder.av_nombre.setText(planta?.titulo)

        if(plantaImage == null){
            holder.av_imagen.setImageResource(planta?.idImg1!!)
        }else{
            holder.av_imagen.setImageBitmap(ImageUtilities.getBitMapFromByteArray(planta?.imgArray!!))
        }
        //holder.av_imagen.setImageBitmap(ImageUtilities.getBitMapFromByteArray(plantaImage!!))
        //holder.av_imagen.setImageResource(publicaciones.get(position).idImgPerfil!!)

        holder.av_bar.max=10
        holder.av_bar.progress= mis_plantas.get(position).progressValue!!

    }

    override fun getItemCount(): Int {
        return mis_plantas.size
    }

    override fun onClick(v: View?) {

    }

}