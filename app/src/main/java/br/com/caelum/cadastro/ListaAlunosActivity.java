package br.com.caelum.cadastro;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.bean.Aluno;
import br.com.caelum.cadastro.br.com.caelum.cadastro.adapter.custom.ListaAlunosCustom;
import br.com.caelum.cadastro.br.com.caelum.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {

    private int REQUEST_LIGAR = 1;
    private List<Aluno> listAlunos;
    private ListView listView;
    private Aluno alunoSelecionado;


    public void updateListAluno(){

        AlunoDAO alunoDAO = new AlunoDAO(this);
        getListAlunos().clear();
        getListAlunos().addAll(alunoDAO.list());


        ListaAlunosCustom adapter = new ListaAlunosCustom(this,getListAlunos());



       // ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, R.layout.listaalunocustom, getListAlunos());

        listView.setAdapter(adapter);

        alunoDAO.close();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Toast.makeText(ListaAlunosActivity.this, " posicao : " + i, Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Toast.makeText(ListaAlunosActivity.this, " Nome : " + ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
//
//                return true;
//
//            }
//        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listaAlunos);

        registerForContextMenu(listView);

        final Button btnInsert = (Button) findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ListaAlunosActivity.this, " Botao clicado", Toast.LENGTH_SHORT).show();


//                if (btnInsert.getText().toString().equals("+")) {
//                    btnInsert.setText("-");
//                    btnInsert.setBackgroundResource(R.drawable.fundo_azul);
//                } else {
//                    btnInsert.setText("+");
//                    btnInsert.setBackgroundResource(R.drawable.fundo);
//                }


                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);

                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume(){
        super.onResume();
        updateListAluno();

    }



    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info){
        //super.onCreateContextMenu(menu,view,info);
        Log.d("AQUI","TESTE");

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) info;

        alunoSelecionado = (Aluno) getListAlunos().get(menuInfo.position);

        MenuItem menuItemDelete = menu.add("Excluir");
        MenuItem menuItemUpdate = menu.add("Editar");
        MenuItem menuItemCall   = menu.add("Ligar");
        MenuItem menuItemSms   = menu.add("SMS");
        MenuItem menuItemSite  = menu.add("Site");
        MenuItem menuItemMap   = menu.add("Mapa");


        menuItemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AlunoDAO alunoDAO = new AlunoDAO(ListaAlunosActivity.this);
                alunoDAO.delete(alunoSelecionado);
                alunoDAO.close();
                updateListAluno();
                return false;
            }
        });

        menuItemUpdate.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);

                intent.putExtra("aluno",alunoSelecionado);

                startActivity(intent);

                return false;
            }
        });


//        Intent intentCall = new Intent(Intent.ACTION_CALL);
//        intentCall.setData(Uri.parse("tel:"+aluno.getTelefone()));
//        menuItemCall.setIntent(intentCall);


        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:"+alunoSelecionado.getTelefone()));
        intentSms.putExtra("sms_body","Hello Word");
        menuItemSms.setIntent(intentSms);

        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        intentSite.setData(Uri.parse("http://:"+alunoSelecionado.getSite()));
        menuItemSite.setIntent(intentSite);

        Intent intentMap = new Intent(Intent.ACTION_VIEW);
        intentMap.setData(Uri.parse("geo:0,0z=14&q="+ Uri.encode(alunoSelecionado.getEndereco())));
        menuItemMap.setIntent(intentMap);


        menuItemCall.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                setAlunoSelecionado(alunoSelecionado);
                verificaPermissao(android.Manifest.permission.CALL_PHONE);
                return false;
            }
        });

    }

    private void verificaPermissao(String permission){

        if(ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED)
            doExecuteCall();
        else
            ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_LIGAR);

        }


    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == REQUEST_LIGAR){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                doExecuteCall();
            }else{
                Toast.makeText(ListaAlunosActivity.this, "PERDEU MANO!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void doExecuteCall() {
        Intent intentCall = new Intent(Intent.ACTION_CALL);
        intentCall.setData(Uri.parse("tel:"+getAlunoSelecionado().getTelefone()));
        startActivity(intentCall);

    }


    public Aluno getAlunoSelecionado() {
        if(alunoSelecionado == null)
            alunoSelecionado = new Aluno();
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public List<Aluno> getListAlunos() {
        if(listAlunos == null)
            listAlunos = new ArrayList<Aluno>();
        return listAlunos;
    }

    public void setListAlunos(List<Aluno> listAlunos) {
        this.listAlunos = listAlunos;
    }
}
