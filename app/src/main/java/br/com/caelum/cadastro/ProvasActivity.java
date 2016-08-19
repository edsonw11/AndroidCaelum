package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastro.fragments.FragmentDetalheProva;
import br.com.caelum.cadastro.fragments.ListaProvaFragment;

public class ProvasActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_provas);

       boolean ehTablet = getResources().getBoolean(R.bool.isTablet);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(ehTablet) {
            transaction.replace(R.id.principal, new ListaProvaFragment());
            transaction.replace(R.id.secundario, new FragmentDetalheProva());
        }else{
            transaction.replace(R.id.principal, new ListaProvaFragment());

        }
        transaction.commit();
    }

}
