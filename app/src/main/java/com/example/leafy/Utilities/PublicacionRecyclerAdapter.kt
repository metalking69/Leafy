package com.example.leafy.Utilities

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.leafy.Data.Example
import com.example.leafy.Data.DataManager
import com.example.leafy.Data.Publicaciones
import com.example.leafy.R
import com.example.leafy.ImageUtilities


public class PublicacionRecyclerAdapter(val mContext: Context, var publicaciones: List<Publicaciones>, var list: ActionListenerInterface):
    RecyclerView.Adapter<PublicacionRecyclerAdapter.MyViewHolder>(), View.OnClickListener {


        var btnSave1: ImageButton?=null
        var btnSave2: ImageButton?=null

        //private var listener: ActionListenerInterface?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val v:View= LayoutInflater.from(mContext).inflate(R.layout.item_publicacion_main, parent, false)
        val vHolder: MyViewHolder= MyViewHolder(v)

        /*
        btnSave1= v.findViewById<ImageButton>(R.id.imageButton)
        btnSave1?.setOnClickListener(this)
        btnSave2= v.findViewById<ImageButton>(R.id.imageButton2)
        btnSave2?.setOnClickListener(this)*/

        vHolder.itemView.setOnClickListener(){

            //val activityIntent = Intent(mContext, AlbumActivity::class.java)
            //activityIntent.putExtra(ALBUM_POSITION, this.albumPosition)
            //mContext.startActivity(activityIntent)
            //Toast.makeText(mContext, "Test "+ vHolder.layoutPosition, Toast.LENGTH_SHORT).show()


            val idPlanta: Int? = vHolder.av_id?.text.toString().toIntOrNull()
            //Toast.makeText(this.mContext, idPlanta, Toast.LENGTH_SHORT).show()
            list.onClickFragmentButton(R.id.idFrameLayoutCard, idPlanta!!)
        //idFrameLayoutCard
        }
        vHolder.btn_save01.setOnClickListener(){
            vHolder.btn_save01.isEnabled = false
            vHolder.btn_save01.isClickable = false
            vHolder.btn_save01.visibility=INVISIBLE
            vHolder.btn_save02.isEnabled = true
            vHolder.btn_save02.isClickable = true
            vHolder.btn_save02.visibility=VISIBLE
            Toast.makeText(this.mContext, "Agregado a guardados", Toast.LENGTH_SHORT).show()

        }
        vHolder.btn_save02.setOnClickListener(){
            vHolder.btn_save02.isEnabled = false
            vHolder.btn_save02.isClickable = false
            vHolder.btn_save02.visibility=INVISIBLE
            vHolder.btn_save01.isEnabled = true
            vHolder.btn_save01.isClickable = true
            vHolder.btn_save01.visibility=VISIBLE
            Toast.makeText(this.mContext, "Eliminado de guardados", Toast.LENGTH_SHORT).show()

        }

        return vHolder

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        holder.av_id?.setText(publicaciones.get(position).id.toString()!!)
        holder.av_titulo.setText(publicaciones.get(position).titulo)
        holder.av_descripcion.setText(publicaciones.get(position).descripcion)

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
    }

    override fun getItemCount(): Int {
        return publicaciones.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        public val av_titulo: TextView= itemView.findViewById(R.id.textView2)
        public val av_descripcion: TextView= itemView.findViewById(R.id.textView11)
        public val av_imagen: ImageView= itemView.findViewById(R.id.imageView3)
        public val av_perfil: ImageView= itemView.findViewById(R.id.imageView4)
        public val av_id: TextView?= itemView.findViewById(R.id.textView16)

        public val btn_save01: ImageButton= itemView.findViewById(R.id.imageButton)
        public val btn_save02: ImageButton= itemView.findViewById(R.id.imageButton2)
    }

    override fun onClick(v: View?) {
        /*
        when(v!!.id){
            R.id.imageButton->{

                btnSave1?.isEnabled = false
                btnSave1?.isClickable = false
                btnSave1?.visibility=INVISIBLE
                btnSave2?.isEnabled = true
                btnSave2?.isClickable = true
                btnSave2?.visibility=VISIBLE
                Toast.makeText(this.mContext, "Agregado a guardados", Toast.LENGTH_SHORT).show()
            }
            R.id.imageButton2->{

                btnSave2?.isEnabled = false
                btnSave2?.isClickable = false
                btnSave2?.visibility=INVISIBLE
                btnSave1?.isEnabled = true
                btnSave1?.isClickable = true
                btnSave1?.visibility=VISIBLE
                Toast.makeText(this.mContext, "Eliminado de guardados", Toast.LENGTH_SHORT).show()
            }
        }*/
    }



}