package br.com.caelum.cadastro.br.com.caelum.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.com.caelum.cadastro.bean.Aluno;

/**
 * Created by android6384 on 11/08/16.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private final String tabelaAluno = "ALUNO";
    private static  final int VERSAO = 2;

    public AlunoDAO(Context context) {

        super(context, "DB_PRD", null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        android.util.Log.i("passando","onCreate");

        String SQL = "CREATE TABLE " + tabelaAluno +
                     " ( ID INTEGER PRIMARY KEY, " +
                     "   NOME TEXT NOT NULL,     " +
                     "   ENDERECO TEXT,          " +
                     "   TELEFONE TEXT,          " +
                     "   SITE TEXT,              " +
                     "   NOTA REAL);";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        android.util.Log.i("passando" + i + " : " +i1, "onUpgrade");
        //String SQL = "DROP TABLE IF EXISTS " + tabelaAluno + ";";
        String SQL = "ALTER TABLE " + tabelaAluno + " ADD COLUMN CAMINHOFOTO TEXT;";
        db.execSQL(SQL);

       // onCreate(db);
    }


    private ContentValues buildAlunoContentValues(Aluno aluno) {
        android.util.Log.i("passando" , "buildAlunoContentValues");
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",aluno.getId());
        contentValues.put("NOME",aluno.getNome());
        contentValues.put("ENDERECO",aluno.getEndereco());
        contentValues.put("TELEFONE",aluno.getTelefone());
        contentValues.put("SITE",aluno.getSite());
        contentValues.put("NOTA",aluno.getNota());
        contentValues.put("CAMINHOFOTO",aluno.getCaminhoFoto());
        return contentValues;

    }

    public List<Aluno> list(){
        android.util.Log.i("passando" , "list");
        List<Aluno> lista = new ArrayList<Aluno>();

        String SQL = "SELECT * FROM " + tabelaAluno + " WHERE 1 = 1" ;

         Cursor c = getReadableDatabase().rawQuery(SQL, null);

        while (c.moveToNext()){

            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("ID")));
            aluno.setNome(c.getString(c.getColumnIndex("NOME")));
            aluno.setEndereco(c.getString(c.getColumnIndex("ENDERECO")));
            aluno.setTelefone(c.getString(c.getColumnIndex("TELEFONE")));
            aluno.setSite(c.getString(c.getColumnIndex("SITE")));
            aluno.setNota(c.getInt(c.getColumnIndex("NOTA")));
            android.util.Log.i("passando" , "NOTA ");
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("CAMINHOFOTO")));
            android.util.Log.i("passando" , "CAMINHOFOTO " + aluno.getCaminhoFoto());
            lista.add(aluno);
            android.util.Log.i("passando" , "lista CAMINHOFOTO >>>>" + aluno.getCaminhoFoto());

        }

        c.close();
        return lista;

    }

    public void findById(SQLiteDatabase db, int id){

        //String SQL = "SELECT COUNT(*) FROM " + tabelaAluno + " WHERE 1 = 1";

        String SQL = "SELECT NOME FROM " + tabelaAluno + " WHERE ID = " + id;

        db.execSQL(SQL);

    }

    public void insert(Aluno aluno){
        android.util.Log.i("passando" , "insert " + aluno.getCaminhoFoto());

        ContentValues contentValues = buildAlunoContentValues(aluno);

        getWritableDatabase().insert(tabelaAluno,null,contentValues);
        getWritableDatabase().close();

    }


    public void delete(Aluno aluno) {

        getWritableDatabase().delete(tabelaAluno,"ID= ?", new String[]{aluno.getId().toString()});


    }



    public void update(Aluno aluno) {

        ContentValues contentValues = buildAlunoContentValues(aluno);

        getWritableDatabase().update(tabelaAluno,contentValues,"ID=?",new String[]{aluno.getId().toString()});

    }

    public boolean isAluno(String phone){
        android.util.Log.i("passando" , "isAluno " + phone);

        String SQL = "SELECT * FROM " + tabelaAluno + " WHERE TELEFONE like %" + phone+"%";

        Cursor c = getReadableDatabase().rawQuery(SQL, null);

        boolean resultado = false;

        if(c.moveToNext())
            resultado =  true;

        c.close();
        return resultado;

    }
}
