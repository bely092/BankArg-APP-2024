package com.lalita.bankargapp.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lalita.bankargapp.R;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

//    Agarra el layout de item_usuario para usarse a posteriori y darle la informacion necesaria con nuestra clase java
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

//    Le coloca TODOS los datos correspondientes (setText) a lo necesario
    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = usuarios.get(position);
        holder.nombreTextView.setText(usuario.getNombre());
        holder.apellidoTextView.setText(usuario.getApellido());
        holder.tipoDocTextView.setText(usuario.getTipoDoc());
        holder.nroDocTextView.setText(usuario.getNroDoc());
        holder.localidadTextView.setText(usuario.getLocalidad());
        holder.nroCalleTextView.setText(String.valueOf(usuario.getNroCalle()));
        holder.calleTextView.setText(usuario.getCalle());
        holder.fechaNacTextView.setText(usuario.getFechaNac());
        holder.tipoSexoTextView.setText(usuario.getTipoSexo());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, apellidoTextView, tipoDocTextView, nroDocTextView, localidadTextView, nroCalleTextView, calleTextView, fechaNacTextView, tipoSexoTextView;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            apellidoTextView = itemView.findViewById(R.id.apellidoTextView);
            tipoDocTextView = itemView.findViewById(R.id.tipoDocTextView);
            nroDocTextView = itemView.findViewById(R.id.nroDocTextView);
            localidadTextView = itemView.findViewById(R.id.localidadTextView);
            nroCalleTextView = itemView.findViewById(R.id.nroCalleTextView);
            calleTextView = itemView.findViewById(R.id.calleTextView);
            fechaNacTextView = itemView.findViewById(R.id.fechaNacTextView);
            tipoSexoTextView = itemView.findViewById(R.id.tipoSexoTextView);
        }
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Usuario usuario = getItem(position);
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario, parent, false);
//        }
//
//        TextView nombreTextView = convertView.findViewById(R.id.nombreTextView);
//        TextView apellidoTextView = convertView.findViewById(R.id.apellidoTextView);
//        TextView tipoDocTextView = convertView.findViewById(R.id.tipoDocTextView);
//        TextView nroDocTextView = convertView.findViewById(R.id.nroDocTextView);
//        TextView localidadTextView = convertView.findViewById(R.id.localidadTextView);
//        TextView nroCalleTextView = convertView.findViewById(R.id.nroCalleTextView);
//        TextView calleTextView = convertView.findViewById(R.id.calleTextView);
//        TextView fechaNacTextView = convertView.findViewById(R.id.fechaNacTextView);
//        TextView tipoSexoTextView = convertView.findViewById(R.id.tipoSexoTextView);
//
//        nombreTextView.setText(usuario.getNombre());
//        apellidoTextView.setText(usuario.getApellido());
//        tipoDocTextView.setText(usuario.getTipoDoc());
//        nroDocTextView.setText(usuario.getNroDoc());
//        localidadTextView.setText(usuario.getLocalidad());
//        nroCalleTextView.setText(String.valueOf(usuario.getNroCalle()));
//        calleTextView.setText(usuario.getCalle());
//        fechaNacTextView.setText(usuario.getFechaNac());
//        tipoSexoTextView.setText(usuario.getTipoSexo());
//
//        return convertView;
//    }
}
