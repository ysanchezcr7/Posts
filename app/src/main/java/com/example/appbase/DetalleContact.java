package com.example.appbase;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbase.Utiles.ContactOperation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;


public class DetalleContact extends AppCompatActivity {


    private Context context;
    Toolbar toolbar;
    String id, nombre, phone;
    TextView tvnombre, tvmovil;
    CircleImageView circleImageView;
    Bitmap bitmap = null;
    ContactOperation objOperationContact;
    int onStartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_contact);
        onStartCount = 1;
        if (savedInstanceState == null) // 1st time
        {
            this.overridePendingTransition(R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left);
        } else // already created so reverse animation
        {
            onStartCount = 2;
        }
        toolbar = findViewById(R.id.toolbarDetalle);
        tvmovil = findViewById(R.id.tvNumeroDetalleContact);
        tvnombre = findViewById(R.id.tvNombreDetalleContact);
        circleImageView = findViewById(R.id.imgContact);
        objOperationContact = new ContactOperation();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.back);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   onBackPressed();
                }
            });
        }

        if (getIntent() != null) {
            Intent informacion = getIntent();
            Bundle b = informacion.getExtras();
            if (b != null && b.getString("idContact") != null)
                id = b.getString("idContact");
            if (b.getString("nombre") != null)
                nombre = b.getString("nombre");
            if (b.getString("phone") != null)
                phone = b.getString("phone");

            if (b.getParcelable("imagenContact") != null)
                bitmap = b.getParcelable("imagenContact");

        }

        tvmovil.setText(phone);
        tvnombre.setText(nombre);
        circleImageView.setImageBitmap(bitmap);


    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option_detalles_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delet_contact) {
            deletContacts();
            return true;
        }
        if (id == R.id.action_update_contact) {
            actualizarContacts();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actualizarContacts() {

        final View update_contact = getLayoutInflater().inflate(R.layout.update_contact, null);
        final EditText et_nombre = (EditText) update_contact.findViewById(R.id.et_nombre);
        final EditText et_numero = (EditText) update_contact.findViewById(R.id.et_numero);
        et_nombre.setText(nombre);
        et_numero.setText(phone);
        final AlertDialog dialog_update = new AlertDialog.Builder(DetalleContact.this)
                .setTitle("Actualizar Contacto")
                .setView(update_contact)
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("clik", "Aceptar");
                        String newNombre = et_nombre.getText().toString().trim();
                        String newPhone = et_numero.getText().toString().trim();

                        objOperationContact.updateNameAndNumber(getBaseContext(), phone, newNombre, newPhone, id);
                        Intent i = new Intent(DetalleContact.this, Navegacion.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                // Log.d("clik","Cancelar");
                            }
                        }).create();

        dialog_update.setCanceledOnTouchOutside(false);
        dialog_update.setCancelable(false);

        dialog_update.show();


    }

    private void deletContacts() {

        final AlertDialog dialog_delet = new AlertDialog.Builder(DetalleContact.this)
                .setTitle("Eliminar Contacto")
                .setMessage("Deseas eliminar el contacto " + nombre + " de tu lista")
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        objOperationContact.deleteContact(getBaseContext(), phone, nombre);
                        Intent i = new Intent(DetalleContact.this, Navegacion.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();

        dialog_delet.setCanceledOnTouchOutside(false);
        dialog_delet.setCancelable(false);

        dialog_delet.show();


    }


}
