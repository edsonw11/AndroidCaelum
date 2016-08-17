package br.com.caelum.cadastro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.br.com.caelum.dao.AlunoDAO;

/**
 * Created by android6384 on 16/08/16.
 */
public class SMSRecevier extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[])intent.getSerializableExtra("pdus");

        byte[] pdu = (byte[]) pdus[0];

        SmsMessage smsMessage = SmsMessage.createFromPdu(pdu);

        String phone = smsMessage.getDisplayOriginatingAddress();

        AlunoDAO alunoDAO = new AlunoDAO(context);

        if(alunoDAO.isAluno(phone)){
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
            Toast.makeText(context,"SMS de Aluno " + smsMessage.getMessageBody(), Toast.LENGTH_SHORT).show();
        }


    }
}
