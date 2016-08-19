package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastro.bean.Prova;
import br.com.caelum.cadastro.fragments.FragmentDetalheProva;
import br.com.caelum.cadastro.fragments.ListaProvaFragment;

public class ProvasActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(isTablet()) {
            transaction.replace(R.id.principal, new ListaProvaFragment());
            transaction.replace(R.id.secundario, new FragmentDetalheProva());
        }else{
            transaction.replace(R.id.principal, new ListaProvaFragment());

        }
        transaction.commit();
    }

    public void selecionaProva(Prova provaSelecionada) {

        FragmentManager manager = getSupportFragmentManager();

        if(isTablet()){
            FragmentDetalheProva detalheProva = (FragmentDetalheProva) manager.findFragmentById(R.id.secundario);

            detalheProva.populaCamposComDados(provaSelecionada);
        }else{
            Bundle bundle = new Bundle();
            bundle.putSerializable("prova",provaSelecionada);

            FragmentDetalheProva detalheProva = new FragmentDetalheProva();
            detalheProva.setArguments(bundle);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.principal, detalheProva);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }
}
