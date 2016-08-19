package br.com.caelum.cadastro.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.bean.Prova;

/**
 * Created by android6384 on 17/08/16.
 */
public class ListaProvaFragment extends Fragment {


    private ListView listViewProvas;


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p/>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View layoutListaProvas = inflater.inflate(R.layout.fragment_lista_prova, container, false);

      this.listViewProvas = (ListView) layoutListaProvas.findViewById(R.id.lista_prova_listview);

      this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(),android.R.layout.simple_list_item_1, getListaDeProvas()));

      this.listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
              Prova provaSelecionada = (Prova) adapterView.getItemAtPosition(posicao);
              Toast.makeText(getActivity(), "Prova Selecionada " + provaSelecionada, Toast.LENGTH_SHORT).show();

          }
      });

      return  layoutListaProvas;
    }

    private List<Prova> getListaDeProvas() {
        Prova p1 = new Prova("20/06/2015", "Matematica");
        p1.setTopicos(Arrays.asList("Algebra linear","Calculo","Estatistica"));

        Prova p2 = new Prova("25/07/2015", "Portugues");
        p2.setTopicos(Arrays.asList("Complemento Nominal","Oracao Subordinadas","Analise Sintatica"));

        List<Prova> provas = Arrays.asList(p1, p2);

        return provas;

    }
}
