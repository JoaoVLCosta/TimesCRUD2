package br.edu.fateczl.timescrud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.edu.fateczl.timescrud.controller.JogadorController;
import br.edu.fateczl.timescrud.controller.TimeController;
import br.edu.fateczl.timescrud.model.Jogador;
import br.edu.fateczl.timescrud.model.Time;
import br.edu.fateczl.timescrud.persistence.JogadorDao;
import br.edu.fateczl.timescrud.persistence.TimeDao;

public class JogadorFragment extends Fragment {
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private Button btnBuscarJ, btnInserirJ, btnModificarJ, btnExcluirJ, btnListarJ;

    private EditText etIdJ, etNomeJ, etNascimentoJ, etAlturaJ, etPesoJ;

    private Spinner spTimeJ;

    private TextView tvListaJ;

    private JogadorController jCont;
    private TimeController tCont;

    private List<Time> times;

    private View view;

    public JogadorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_jogador, container, false);



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

        btnBuscarJ.setOnClickListener(op -> acaoBuscar());
        btnInserirJ.setOnClickListener(op -> acaoInserir());
        btnModificarJ.setOnClickListener(op -> acaoModificar());
        btnExcluirJ.setOnClickListener(op -> acaoExcluir());
        btnListarJ.setOnClickListener(op -> acaoListar());

        jCont = new JogadorController(new JogadorDao(view.getContext()));
        tCont = new TimeController(new TimeDao(view.getContext()));
        preencheSpinner();

        return view;
    }

    private void acaoInserir() {
        int sPos = spTimeJ.getSelectedItemPosition();
        if(sPos > 0){
            try {
                Jogador jogador = montaJogador();
                Toast.makeText(view.getContext(), "Jogador Inserido com Sucesso",
                        Toast.LENGTH_LONG).show();

                jCont.inserir(jogador);
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um Time",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void acaoBuscar() {
        if(!etIdJ.getText().toString().isEmpty()){
            Jogador jogador = new Jogador();
            jogador.setId(Integer.parseInt(etIdJ.getText().toString()));
            try {
                times = tCont.listar();
                jogador = jCont.buscar(jogador);
                if(jogador.getNome() != null){
                    preencheCampos(jogador);
                } else {
                    Toast.makeText(view.getContext(), "Jogador não Encontrado",
                            Toast.LENGTH_LONG).show();
                    limpaCampos();
                }
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    private void acaoExcluir() {
        if(!etIdJ.getText().toString().isEmpty()){
            Jogador jogador = new Jogador();
            jogador.setId(Integer.parseInt(etIdJ.getText().toString()));
            try {
                jCont.deletar(jogador);
                Toast.makeText(view.getContext(), "Jogador Excluido com Sucesso",
                        Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        }
    }

    private void acaoModificar() {
        int sPos = spTimeJ.getSelectedItemPosition();
        if(sPos > 0){
            Jogador jogador = montaJogador();
            Toast.makeText(view.getContext(), "Jogador Atualizado com Sucesso",
                    Toast.LENGTH_LONG).show();
            try {
                jCont.modificar(jogador);
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um Jogador",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void acaoListar() {
        try {
            List<Jogador> jogadores = jCont.listar();
            StringBuffer buffer = new StringBuffer();
            for (Jogador t : jogadores){
                buffer.append(t.toString()).append("\n");
            }
            tvListaJ.setText(buffer.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Jogador montaJogador(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Jogador j = new Jogador();
        j.setId(Integer.parseInt(etIdJ.getText().toString()));
        j.setNome(etNomeJ.getText().toString());

        j.setDataNasc(LocalDate.parse(etNascimentoJ.getText().toString(), formatter));

        j.setAltura(Float.parseFloat(etAlturaJ.getText().toString()));
        j.setPeso(Float.parseFloat(etPesoJ.getText().toString()));
        j.setTime((Time)spTimeJ.getSelectedItem());

        return j;
    }

    private void preencheCampos(Jogador j){
        etIdJ.setText(String.valueOf(j.getId()));
        etNomeJ.setText(j.getNome());
        etNascimentoJ.setText(String.valueOf(j.getDataNasc()));
        etAlturaJ.setText(String.valueOf(j.getAltura()));
        etPesoJ.setText(String.valueOf(j.getPeso()));
    }

    private void limpaCampos(){
        etIdJ.setText("");
        etNomeJ.setText("");
        etNascimentoJ.setText("");
        etAlturaJ.setText("");
        etPesoJ.setText("");
    }

    private void preencheSpinner() {
        Time t0 = new Time();
        t0.setCodigo(0);
        t0.setNome("Selecione um Time");
        t0.setCidade("");

        try {
            times = tCont.listar();
            times.add(0, t0);

            ArrayAdapter ad = new ArrayAdapter<>(view.getContext(),
                    android.R.layout.simple_spinner_item,
                    times);

            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTimeJ.setAdapter(ad);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}