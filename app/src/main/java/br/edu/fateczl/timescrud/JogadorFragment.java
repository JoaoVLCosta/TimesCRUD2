package br.edu.fateczl.timescrud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class JogadorFragment extends Fragment {

    private Button btnBuscarJ, btnInserirJ, btnModificarJ, btnExcluirJ, btnListarJ;

    private EditText etIdJ, etNomeJ, etNascimentoJ, etAlturaJ, etPesoJ;

    private Spinner spTimeJ;

    private TextView tvListaJ;

    public JogadorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_jogador, container, false);

        btnBuscarJ = view.findViewById(R.id.btnBuscarJ);
        btnInserirJ = view.findViewById(R.id.btnInserirJ);
        btnModificarJ = view.findViewById(R.id.btnModificarJ);
        btnExcluirJ = view.findViewById(R.id.btnExcluirJ);
        btnListarJ = view.findViewById(R.id.btnListarJ);

        etIdJ = view.findViewById(R.id.etIdJ);
        etNomeJ = view.findViewById(R.id.etNomeJ);
        etNascimentoJ = view.findViewById(R.id.etNascimentoJ);
        etAlturaJ = view.findViewById(R.id.etAlturaJ);
        etPesoJ = view.findViewById(R.id.etPesoJ);

        spTimeJ = view.findViewById(R.id.spTimeJ);

        tvListaJ = view.findViewById(R.id.tvListaJ);

        btnBuscarJ.setOnClickListener(op -> inserir());
        btnInserirJ.setOnClickListener(op -> buscar());
        btnModificarJ.setOnClickListener(op -> excluir());
        btnExcluirJ.setOnClickListener(op -> modificar());
        btnListarJ.setOnClickListener(op -> listar());

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

        tvListaJ.setText(texto);
    }
}