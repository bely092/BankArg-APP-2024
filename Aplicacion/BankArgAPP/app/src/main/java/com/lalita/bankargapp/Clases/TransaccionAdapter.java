package com.lalita.bankargapp.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lalita.bankargapp.R;

import java.util.List;
import android.widget.TextView;

public class TransaccionAdapter extends RecyclerView.Adapter<TransaccionAdapter.TransaccionViewHolder> {

    private List<Transaccion> transaccionList;

    public TransaccionAdapter(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    @NonNull
    @Override
    public TransaccionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_transaccion, parent, false);
        return new TransaccionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaccionViewHolder holder, int position) {
        Transaccion transaccion = transaccionList.get(position);
        holder.descripcion.setText(transaccion.getDescripcion());
        holder.monto.setText("$" + String.format("%.2f", transaccion.getMonto()));
        holder.fechaTransaccion.setText(transaccion.getFecha_transaccion());
    }

    @Override
    public int getItemCount() {
        return transaccionList.size();
    }

    public class TransaccionViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion, monto, fechaTransaccion;

        public TransaccionViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            monto = itemView.findViewById(R.id.tvMonto);
            fechaTransaccion = itemView.findViewById(R.id.tvFechaTransaccion);
        }
    }
}

