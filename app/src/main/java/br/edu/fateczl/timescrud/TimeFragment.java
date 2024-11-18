package br.edu.fateczl.timescrud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.timescrud.controller.TimeController;
import br.edu.fateczl.timescrud.model.Time;
import br.edu.fateczl.timescrud.persistence.TimeDao;

public class TimeFragment extends Fragment {
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private Button btnBuscarT, btnInserirT, btnModificarT, btnExcluirT, btnListarT;

    private EditText etCodigoT, etNomeT, etCidadeT;

    private TextView tvListaT;

    private View view;

    private TimeController tCont;

    public TimeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_time, container, false);

        tCont = new TimeController(new TimeDao(view.getContext()));

        btnBuscarT = view.findViewById(R.id.btnBuscarT);
        btnInserirT = view.findViewById(R.id.btnInserirT);
        btnModificarT = view.findViewById(R.id.btnModificarT);
        btnExcluirT = view.findViewById(R.id.btnExcluirT);
        btnListarT = view.findViewById(R.id.btnListarT);

        etCodigoT = view.findViewById(R.id.etCodigoT);
        etNomeT = view.findViewById(R.id.etNomeT);
        etCidadeT = view.findViewById(R.id.etCidadeT);

        tvListaT = view.findViewById(R.id.tvListaT);

        btnInserirT.setOnClickListener(op -> acaoInserir());
        btnBuscarT.setOnClickListener(op -> acaoBuscar());
        btnExcluirT.setOnClickListener(op -> acaoExcluir());
        btnModificarT.setOnClickListener(op -> acaoModificar());
        btnListarT.setOnClickListener(op -> acaoListar());

        return view;
    }

    private void acaoInserir() {
        Time time = montaTime();
        try {
            tCont.inserir(time);
            Toast.makeText(view.getContext(), "Time Inserido com Sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar() {
        if(!etCodigoT.getText().toString().isEmpty()){
            Time time = montaTime();
            try {
                time = tCont.buscar(time);
                if(time.getNome() != null){
                    preencheCampos(time);
                }
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), "Time Não Encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        }
    }

    private void acaoExcluir() {
        if(!etCodigoT.getText().toString().isEmpty()){
            Time time = montaTime();
            try {
                tCont.deletar(time);
                Toast.makeText(view.getContext(), "Time Excluido com Sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        }
    }

    private void acaoModificar() {
        Time time = montaTime();
        try {
            tCont.modificar(time);
            Toast.makeText(view.getContext(), "Time Modificado com Sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoListar() {
        try {
            List<Time> times = tCont.listar();
            StringBuffer buffer = new StringBuffer();
            for (Time t : times){
                buffer.append(t.toString()).append("\n");
            }
            tvListaT.setText(buffer.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Time montaTime(){
        Time t = new Time();
        t.setCodigo(Integer.parseInt(etCodigoT.getText().toString()));
        t.setNome(etNomeT.getText().toString());
        t.setCidade(etCidadeT.getText().toString());

        return t;
    }

    private void preencheCampos(Time t){
        etCodigoT.setText(String.valueOf(t.getCodigo()));
        etNomeT.setText(t.getNome());
        etCidadeT.setText(t.getCidade());
    }

    private void limpaCampos(){
        etCodigoT.setText("");
        etNomeT.setText("");
        etCidadeT.setText("");
    }
}