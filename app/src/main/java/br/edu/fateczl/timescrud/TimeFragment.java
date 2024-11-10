package br.edu.fateczl.timescrud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimeFragment extends Fragment {
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private Button btnBuscarT, btnInserirT, btnModificarT, btnExcluirT, btnListarT;

    private EditText etCodigoT, etNomeT, etCidadeT;

    private TextView tvListaT;

    public TimeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time, container, false);

        btnBuscarT = view.findViewById(R.id.btnBuscarT);
        btnInserirT = view.findViewById(R.id.btnInserirT);
        btnModificarT = view.findViewById(R.id.btnModificarT);
        btnExcluirT = view.findViewById(R.id.btnExcluirT);
        btnListarT = view.findViewById(R.id.btnListarT);

        etCodigoT = view.findViewById(R.id.etCodigoT);
        etNomeT = view.findViewById(R.id.etNomeT);
        etCidadeT = view.findViewById(R.id.etCidadeT);

        tvListaT = view.findViewById(R.id.tvListaT);

        btnInserirT.setOnClickListener(op -> inserir());
        btnBuscarT.setOnClickListener(op -> buscar());
        btnExcluirT.setOnClickListener(op -> excluir());
        btnModificarT.setOnClickListener(op -> modificar());
        btnListarT.setOnClickListener(op -> listar());

        return view;
    }

    private void inserir() {
        mostrar("INSERIR");
    }

    private void buscar() {
        mostrar("BUSCAR");
    }

    private void excluir() {
        mostrar("EXCLUIR");
    }

    private void modificar() {
        mostrar("MODIFICAR");
    }

    private void listar() {
        mostrar("LISTAR");
    }

    private void mostrar(String texto){
        texto = texto +
                "\nDomínimo PARCIALMENTE implementado (Parte 1). Será expandido na Parte 2";

        tvListaT.setText(texto);
    }
}