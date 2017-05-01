package com.example.sholehhermawan.mobproasssessment;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sholehhermawan.mobproasssessment.data.AntrianContract.Entry;

public class EditorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_ANTRIAN_LOADER = 0;
    public static int noAntrian = 0;
    public static int noKonfirmasi = 0;

    private Uri mCurrentPetUri;

    private EditText mNamaPasienEditText;
    private EditText mKeluhanEditText;
    private Spinner mKategoriSpinner;
    private Spinner mNamaDokterSpinner;
    private Spinner mRuanganSpinner;

    private int mKategori = Entry.KATEGORI_UNKNOWN;
    private int mDokter = Entry.NAMADOKTER_Unknown;
    private int mRuangan = Entry.RUANGAN_UNKNOWN;

    private boolean mAntrianHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mAntrianHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();

        if (mCurrentPetUri == null) {
            setTitle(getString(R.string.editor_activity_title_new_pet));

            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.editor_activity_title_edit_pet));

            getLoaderManager().initLoader(EXISTING_ANTRIAN_LOADER, null, this);
        }

        mNamaPasienEditText = (EditText) findViewById(R.id.edit_namaPasien);
        mKeluhanEditText = (EditText) findViewById(R.id.edit_keluhan);
        mKategoriSpinner = (Spinner) findViewById(R.id.spinner_kategori);
        mNamaDokterSpinner = (Spinner) findViewById(R.id.spinner_dokter);
        mRuanganSpinner = (Spinner) findViewById(R.id.spinner_ruangan);

        mNamaPasienEditText.setOnTouchListener(mTouchListener);
        mKeluhanEditText.setOnTouchListener(mTouchListener);
        mKategoriSpinner.setOnTouchListener(mTouchListener);
        mNamaDokterSpinner.setOnTouchListener(mTouchListener);
        mRuanganSpinner.setOnTouchListener(mTouchListener);

        setupSpinnerKategori();
        setupSpinnerDokter();
        setupSpinnerRuangan();
    }

    private void setupSpinnerKategori() {
        ArrayAdapter kategoriSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.kategori_dokter, android.R.layout.simple_spinner_item);

        kategoriSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mKategoriSpinner.setAdapter(kategoriSpinnerAdapter);

        mKategoriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.kategori_umum))) {
                        mKategori = Entry.KATEGORI_UMUM;
                    } else if (selection.equals(getString(R.string.kategori_anak))) {
                        mKategori = Entry.KATEGORI_ANAK;
                    } else if (selection.equals(getString(R.string.kategori_kandungan))) {
                        mKategori = Entry.KATEGORI_KANDUNGAN;
                    } else if (selection.equals(getString(R.string.kategori_penyakit_dalam))) {
                        mKategori = Entry.KATEGORI_PENYAKITDALAM;
                    } else if (selection.equals(getString(R.string.kategori_gigi))) {
                        mKategori = Entry.KATEGORI_GIGI;
                    } else {
                        mKategori = Entry.KATEGORI_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mKategori = Entry.KATEGORI_UNKNOWN;
            }
        });
    }

    private void setupSpinnerDokter() {
        ArrayAdapter dokterSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.nama_dokter, android.R.layout.simple_spinner_item);

        dokterSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mNamaDokterSpinner.setAdapter(dokterSpinnerAdapter);

        mNamaDokterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.bambang))) {
                        mDokter = Entry.NAMADOKTER_Bambang;
                    } else if (selection.equals(getString(R.string.jack))) {
                        mDokter = Entry.NAMADOKTER_Jack;
                    } else if (selection.equals(getString(R.string.indah))) {
                        mDokter = Entry.NAMADOKTER_Indah;
                    } else if (selection.equals(getString(R.string.marsini))) {
                        mDokter = Entry.NAMADOKTER_Marsini;
                    } else if (selection.equals(getString(R.string.ely))) {
                        mDokter = Entry.NAMADOKTER_Ely;
                    } else {
                        mDokter = Entry.NAMADOKTER_Unknown;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mDokter = Entry.NAMADOKTER_Unknown;
            }
        });
    }

    private void setupSpinnerRuangan() {
        ArrayAdapter ruanganSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.ruangan, android.R.layout.simple_spinner_item);

        ruanganSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mRuanganSpinner.setAdapter(ruanganSpinnerAdapter);

        mRuanganSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.r01))) {
                        mRuangan = Entry.RUANGAN_UMUM;
                    } else if (selection.equals(getString(R.string.r02))) {
                        mRuangan = Entry.RUANGAN_ANAK;
                    } else if (selection.equals(getString(R.string.r03))) {
                        mRuangan = Entry.RUANGAN_KANDUNGAN;
                    } else if (selection.equals(getString(R.string.r04))) {
                        mRuangan = Entry.RUANGAN_PENYAKITDALAM;
                    } else if (selection.equals(getString(R.string.r05))) {
                        mRuangan = Entry.RUANGAN_GIGI;
                    } else {
                        mRuangan = Entry.RUANGAN_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mRuangan = Entry.RUANGAN_UNKNOWN;
            }
        });
    }

    private void saveAntrian() {
        String nameString = mNamaPasienEditText.getText().toString().trim();
        String keluhanString = mKeluhanEditText.getText().toString().trim();

        if (mCurrentPetUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(keluhanString) &&
                mDokter == Entry.NAMADOKTER_Unknown && mKategori == Entry.KATEGORI_UNKNOWN && mRuangan == Entry.RUANGAN_UNKNOWN) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(Entry.COLUMN_NAMA_USER, nameString);
        values.put(Entry.COLUMN_KELUHAN, keluhanString);
        values.put(Entry.COLUMN_KATEGORI, mKategori);
        values.put(Entry.COLUMN_NAMA_DOKTER, mDokter);
        values.put(Entry.COLUMN_RUANGAN, mRuangan);

        if (mCurrentPetUri == null) {
            Uri newUri = getContentResolver().insert(Entry.CONTENT_URI, values);
            noAntrian++;

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.editor_insert_antrian_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_insert_antrian_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(mCurrentPetUri, values, null, null);

            if (rowsAffected == 0) {
                Toast.makeText(this, getString(R.string.editor_update_antrian_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_update_antrian_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mCurrentPetUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_konfirmasi);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveAntrian();
                finish();
                return true;
            case R.id.action_konfirmasi:
                showDeleteConfirmationDialog();
                return true;
            case android.R.id.home:
                if (!mAntrianHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!mAntrianHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                Entry._ID,
                Entry.COLUMN_NAMA_USER,
                Entry.COLUMN_KELUHAN,
                Entry.COLUMN_KATEGORI,
                Entry.COLUMN_NAMA_DOKTER,
                Entry.COLUMN_RUANGAN };

        return new CursorLoader(this,   // Parent activity context
                mCurrentPetUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(Entry.COLUMN_NAMA_USER);
            int keluhanColumnIndex = cursor.getColumnIndex(Entry.COLUMN_KELUHAN);
            int kategoriColumnIndex = cursor.getColumnIndex(Entry.COLUMN_KATEGORI);
            int dokterColumnIndex = cursor.getColumnIndex(Entry.COLUMN_NAMA_DOKTER);
            int ruanganColumnIndex = cursor.getColumnIndex(Entry.COLUMN_RUANGAN);

            String name = cursor.getString(nameColumnIndex);
            String keluhan = cursor.getString(keluhanColumnIndex);
            int kategori = cursor.getInt(kategoriColumnIndex);
            int dokter = cursor.getInt(dokterColumnIndex);
            int ruangan = cursor.getInt(ruanganColumnIndex);

            mNamaPasienEditText.setText(name);
            mKeluhanEditText.setText(keluhan);

            switch (kategori) {
                case Entry.KATEGORI_UMUM:
                    mKategoriSpinner.setSelection(1);
                    break;
                case Entry.KATEGORI_ANAK:
                    mKategoriSpinner.setSelection(2);
                    break;
                case Entry.KATEGORI_KANDUNGAN:
                    mKategoriSpinner.setSelection(3);
                    break;
                case Entry.KATEGORI_PENYAKITDALAM:
                    mKategoriSpinner.setSelection(4);
                    break;
                case Entry.KATEGORI_GIGI:
                    mKategoriSpinner.setSelection(5);
                    break;
                default:
                    mKategoriSpinner.setSelection(0);
                    break;
            }

            switch (dokter) {
                case Entry.NAMADOKTER_Bambang:
                    mNamaDokterSpinner.setSelection(1);
                    break;
                case Entry.NAMADOKTER_Jack:
                    mNamaDokterSpinner.setSelection(2);
                    break;
                case Entry.NAMADOKTER_Indah:
                    mNamaDokterSpinner.setSelection(3);
                    break;
                case Entry.NAMADOKTER_Marsini:
                    mNamaDokterSpinner.setSelection(4);
                    break;
                case Entry.NAMADOKTER_Ely:
                    mNamaDokterSpinner.setSelection(5);
                    break;
                default:
                    mNamaDokterSpinner.setSelection(0);
                    break;
            }

            switch (ruangan) {
                case Entry.RUANGAN_UMUM:
                    mRuanganSpinner.setSelection(1);
                    break;
                case Entry.RUANGAN_ANAK:
                    mRuanganSpinner.setSelection(2);
                    break;
                case Entry.RUANGAN_KANDUNGAN:
                    mRuanganSpinner.setSelection(3);
                    break;
                case Entry.RUANGAN_PENYAKITDALAM:
                    mRuanganSpinner.setSelection(4);
                    break;
                case Entry.RUANGAN_GIGI:
                    mRuanganSpinner.setSelection(5);
                    break;
                default:
                    mRuanganSpinner.setSelection(0);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNamaPasienEditText.setText("");
        mKeluhanEditText.setText("");
        mKategoriSpinner.setSelection(0);
        mNamaDokterSpinner.setSelection(0);
        mRuanganSpinner.setSelection(0);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                konfirmasiAntrian();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void konfirmasiAntrian() {
        if (mCurrentPetUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentPetUri, null, null);
            noKonfirmasi++;

            if (rowsDeleted == 0) {
                Toast.makeText(this, getString(R.string.editor_delete_antrian_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_delete_antrian_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}