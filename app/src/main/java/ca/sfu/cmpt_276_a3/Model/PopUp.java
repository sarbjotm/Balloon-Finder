package ca.sfu.cmpt_276_a3.Model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import ca.sfu.cmpt_276_a3.Activities.MinesweeperGame;
import ca.sfu.cmpt_276_a3.Activities.WelcomeScreen;

public class PopUp extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("WINNER!")
                .setMessage("Congrats on winning!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = WelcomeScreen.makeIntent(getActivity());
                        startActivity(intent);
                    }
                });
        //Create Dialog
        return builder.create();
    }

}
