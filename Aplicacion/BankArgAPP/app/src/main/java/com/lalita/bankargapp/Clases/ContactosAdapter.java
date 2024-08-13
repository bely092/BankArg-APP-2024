package com.lalita.bankargapp.Clases;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lalita.bankargapp.R;

import java.util.List;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder> {

    private List<Contacto> contactoList;
    private OnContactoDeleteListener deleteListener;

    public interface OnContactoDeleteListener {
        void onContactoDelete(Contacto contacto);
    }

    public ContactosAdapter(List<Contacto> contactoList, OnContactoDeleteListener deleteListener) {
        this.contactoList = contactoList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contactos, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = contactoList.get(position);
        holder.textViewCBU.setText(contacto.getCbu());
        holder.textViewNombreContacto.setText(contacto.getNombreContacto());

        holder.buttonEliminarContacto.setOnClickListener(v -> {
            deleteListener.onContactoDelete(contacto);
        });
    }

    @Override
    public int getItemCount() {
        return contactoList.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCBU, textViewNombreContacto;
        Button buttonEliminarContacto;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCBU = itemView.findViewById(R.id.textViewCBU);
            textViewNombreContacto = itemView.findViewById(R.id.textViewNombreContacto);
            buttonEliminarContacto = itemView.findViewById(R.id.buttonEliminarContacto);
        }
    }
}
