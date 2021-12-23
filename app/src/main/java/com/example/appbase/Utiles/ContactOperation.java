package com.example.appbase.Utiles;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import javax.sql.CommonDataSource;

public class ContactOperation {


    public static boolean deleteContact(Context ctx, String phone, String name) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
        Cursor cur = ctx.getContentResolver().query(contactUri, null, null, null, null);
        try {
            if (cur.moveToFirst()) {
                do {
                    if (cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(name)) {
                        String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                        ctx.getContentResolver().delete(uri, null, null);
                        return true;
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            cur.close();
        }
        return false;
    }


    private final static String[] DATA_COLS = {

            ContactsContract.Data.MIMETYPE,
            ContactsContract.Data.DATA1,//phone number
            ContactsContract.Data.CONTACT_ID
    };

    public static boolean updateNameAndNumber(final Context context, String number, String newName, String newNumber,String ID) {

        if (context == null || number == null || number.trim().isEmpty()) return false;

        if (newNumber != null && newNumber.trim().isEmpty()) newNumber = null;

        if (newNumber == null) return false;


       // String contactId = getContactId(context, number);
        //if (contactId == null) return false;
        //selection for name

        String where = String.format(
                "%s = '%s' AND %s = ?",
                DATA_COLS[0], //mimetype
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,
                DATA_COLS[2]/*contactId*/);

        String[] args = {ID};

        ArrayList<ContentProviderOperation> operations = new ArrayList<>();

        operations.add(
                ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                        .withSelection(where, args)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, newName)
                        .build()
        );

        //change selection for number
        where = String.format(
                "%s = '%s' AND %s = ?",
                DATA_COLS[0],//mimetype
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
                DATA_COLS[1]/*number*/);

        //change args for number
        args[0] = number;

        operations.add(
                ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                        .withSelection(where, args)
                        .withValue(DATA_COLS[1]/*number*/, newNumber)
                        .build()
        );

        try {

            ContentProviderResult[] results = context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);

            for (ContentProviderResult result : results) {

                //Log.d("Update Result", result.toString());
                Toast.makeText( context, "Contacto Actualizado", Toast.LENGTH_LONG).show();

            }

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
