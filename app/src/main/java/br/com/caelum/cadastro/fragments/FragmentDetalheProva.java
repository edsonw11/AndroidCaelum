package br.com.caelum.cadastro.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.bean.Prova;

public class FragmentDetalheProva extends Fragment {
    
    private Prova prova;
    private TextView materia;
    private TextView data;
    private ListView topicos;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layoutDetalhe = inflater.inflate(R.layout.fragment_detalhe_prova, container, false);

        if(getArguments() != null){
            this.prova = (Prova) getArguments().getSerializable("prova");
        }
        buscaComponentes(layoutDetalhe);
        populaCamposComDados(prova);

        return layoutDetalhe;
    }

    
    private void buscaComponentes(View view){
        this.materia = (TextView) view.findViewById(R.id.detalhe_prova_materia);
        this.data    = (TextView) view.findViewById(R.id.detalhe_prova_data);
        this.topicos = (ListView) view.findViewById(R.id.detalhe_prova_topicos);
    }

    public void populaCamposComDados(Prova prova){
        if(prova != null){
            this.materia.setText(prova.getMateria());
            this.data.setText(prova.getData());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,
                    prova.getTopicos());
            this.topicos.setAdapter(adapter);
        }
        
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public TextView getMateria() {
        return materia;
    }

    public void setMateria(TextView materia) {
        this.materia = materia;
    }

    public TextView getData() {
        return data;
    }

    public void setData(TextView data) {
        this.data = data;
    }

    public ListView getTopicos() {
        return topicos;
    }

    public void setTopicos(ListView topicos) {
        this.topicos = topicos;
    }
}
