package id.sch.smktelkom_mlg.tugas1.xiirpl5018.aplikasiformlamaran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText etNAMA, etALAMAT;
    Spinner spProvinsi, spKota;
    TextView tvHasil;
    CheckBox cbTL, cbOR, cbGB, cbBC;
    RadioGroup rgPendidikan;
    String[][] arKota = {{"Cirebon", "Bandung", "Purwakarta"}, {"Malang", "Surabaya", "Kediri"}, {"Yogyakarta", "Magelang", "Solo"}};
    ArrayList<String> listKota = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etALAMAT = (EditText) findViewById(R.id.editTextALAMAT);
        etNAMA = (EditText) findViewById(R.id.editTextNAMA);
        spKota = (Spinner) findViewById(R.id.spinnerKOTA);
        spProvinsi = (Spinner) findViewById(R.id.spinnerPROVINSI);
        cbBC = (CheckBox) findViewById(R.id.checkBoxBC);
        cbGB = (CheckBox) findViewById(R.id.checkBoxGB);
        cbOR = (CheckBox) findViewById(R.id.checkBoxOR);
        cbTL = (CheckBox) findViewById(R.id.checkBoxTL);
        rgPendidikan = (RadioGroup) findViewById(R.id.radioGroupPendidikan);
        tvHasil = (TextView) findViewById(R.id.textViewHASIL);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);


        findViewById(R.id.buttonSUBMIT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick();
            }
        });
        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[pos]));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void doClick() {
        if (isValid()) {
            String nama = etNAMA.getText().toString();
            String alamat = etALAMAT.getText().toString();
            String hasil = "\nHobi : \n";
            int startlen = hasil.length();
            if (cbTL.isChecked()) hasil += cbTL.getText() + "\n";
            if (cbOR.isChecked()) hasil += cbOR.getText() + "\n";
            if (cbGB.isChecked()) hasil += cbGB.getText() + "\n";
            if (cbBC.isChecked()) hasil += cbBC.getText() + "\n";

            if (hasil.length() == startlen) hasil += "Anda Belum Memilih!";

            String pendidikan = null;
            if (rgPendidikan.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton) findViewById(rgPendidikan.getCheckedRadioButtonId());
                pendidikan = rb.getText().toString();
            }

            tvHasil.setText("Nama: " + nama + "\nAlamat: " + alamat + "\nPendidikan Terakhir: " + pendidikan + "\nAsal: Provinsi " + spProvinsi.getSelectedItem().toString() + " Kota " + spKota.getSelectedItem().toString() + hasil);
        }
    }

    private boolean isValid() {
        boolean valid = true;
        String nama = etNAMA.getText().toString();
        String alamat = etALAMAT.getText().toString();

        if (nama.isEmpty()) {
            etNAMA.setError("Nama Harus Diisi");
            valid = false;
        } else {
            etNAMA.setError(null);
        }
        if (alamat.isEmpty()) {
            etALAMAT.setError("Alamat Harus Diisi");
            valid = false;
        } else {
            etALAMAT.setError(null);
        }
        return valid;

    }
}
