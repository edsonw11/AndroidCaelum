package br.com.caelum.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import java.io.File;

import br.com.caelum.cadastro.bean.Aluno;
import br.com.caelum.cadastro.br.com.caelum.dao.AlunoDAO;
import br.com.caelum.cadastro.helper.FormularioHelper;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper formularioHelper;
    private ListaAlunosActivity mainActivityListaAlunos;
    private String filePath;
    private int REQUEST_FOTO = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);


        Intent intent = getIntent();

        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if(aluno != null)
            getFormularioHelper().doPopularAluno(aluno);


        Button btnFoto =  getFormularioHelper().getBtnFoto();

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filePath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Uri localFoto = Uri.fromFile(new File(filePath));
                Intent inTCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                inTCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                startActivityForResult(inTCamera,REQUEST_FOTO);
            }
        });



//        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
//
//        btnSalvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    if(requestCode == REQUEST_FOTO)
        if(resultCode == RESULT_OK)
            getFormularioHelper().loadImagem(filePath);
        else {
            filePath = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.menu_salvar){

            Aluno aluno = getFormularioHelper().buidl();
            AlunoDAO alunoDAO = new AlunoDAO(this);



            if(aluno.getId() == null)
                alunoDAO.insert(aluno);
            else
                alunoDAO.update(aluno);

            alunoDAO.close();

          //  getMainActivityListaAlunos().getListAlunos().add(aluno);

//            Toast.makeText(this,"Aluno : " + aluno.getNome() + " Salvo Com Sucesso!!", Toast.LENGTH_SHORT).show();
          // getMainActivityListaAlunos().recreate();

            finish();
        }
        return true;
    }


    public FormularioHelper getFormularioHelper() {
        if(formularioHelper == null)
            formularioHelper = new FormularioHelper(this);
        return formularioHelper;
    }


    public ListaAlunosActivity getMainActivityListaAlunos() {
        return mainActivityListaAlunos;
    }

    public void setMainActivityListaAlunos(ListaAlunosActivity mainActivityListaAlunos) {
        this.mainActivityListaAlunos = mainActivityListaAlunos;
    }
}
