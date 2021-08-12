package id.handiism.tacticalweapon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private EditText etName, etFirepower, etRateOfFire, etAccuracy, etEvasion;
    private Button btnAdd, btnMyWeapon, btnEnemyWeapon;
    private static Weapon mine, enemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // deklarasai view manual
        tvTitle = findViewById(R.id.tv_title);
        etName = findViewById(R.id.et_name);
        etFirepower = findViewById(R.id.et_firepower);
        etRateOfFire = findViewById(R.id.et_rate_of_fire);
        etAccuracy = findViewById(R.id.et_accuracy);
        etEvasion = findViewById(R.id.et_evasion);
        btnAdd = findViewById(R.id.btn_add);
        btnMyWeapon = findViewById(R.id.btn_my_weapon);
        btnEnemyWeapon = findViewById(R.id.btn_enemy_weapon);

        // input weapon pertama
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checkinput() mengecek apakah input sudah sesuai tipe yang diharapkan
                if (checkInput()) {
                    mine = setWeapon(mine); // setweapon untuk menginisisiasi objeck
                    Toast.makeText(MainActivity.this, "Senjata Ditambahkan", Toast.LENGTH_SHORT).show();
                    tvTitle.setText("Masukkan Senjata Musuh");
                    clearEditText(); // untuk membersihkan editText
                    // input weapon kedua
                    btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // checkinput() mengecek apakah input sudah sesuai tipe yang diharapkan
                            if (checkInput()) {
                                enemy = setWeapon(enemy); // setweapon untuk menginisisiasi objeck
                                clearEditText(); // untuk membersihkan editText
                                Toast.makeText(MainActivity.this, "Senjata Ditambahkan", Toast.LENGTH_SHORT).show();

                                // menampilkan decisionfragment
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                DecisionFragment decisionFragment = new DecisionFragment();
                                fragmentManager.beginTransaction().add(R.id.container, decisionFragment).commit();

                                // menampilkan detailDialogFragment dari weapon ku
                                btnMyWeapon.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        View dialogView = getLayoutInflater().inflate(R.layout.fragment_detail_dialog, null);
                                        // loadfragment() untuk menampilkan fraagment berdarakan objek
                                        loadFragment(changeView(dialogView,mine));

                                    }
                                });

                                // menampilkan detailDialogFragment dari weapon musuh
                                btnEnemyWeapon.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        View dialogView = getLayoutInflater().inflate(R.layout.fragment_detail_dialog, null);
                                        // loadfragment() untuk menampilkan fraagment berdarakan objek
                                        loadFragment(changeView(dialogView, enemy));
                                    }
                                });
                            } else {
                                Toast.makeText(MainActivity.this, "Input Salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Input Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        if (mine == null || enemy == null) {
            btnMyWeapon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this , "Belum diisi", Toast.LENGTH_SHORT).show();
                }
            });
            btnEnemyWeapon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this , "Belum diisi", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void clearEditText() {
        etName.getText().clear();
        etFirepower.getText().clear();
        etRateOfFire.getText().clear();
        etAccuracy.getText().clear();
        etEvasion.getText().clear();
    }

    public boolean checkInput() {
        try {
            String name = etName.getText().toString();
            int firepower = Integer.parseInt(etFirepower.getText().toString());
            int rateOfFire = Integer.parseInt(etRateOfFire.getText().toString());
            int accuracy = Integer.parseInt(etAccuracy.getText().toString());
            int evasion = Integer.parseInt(etEvasion.getText().toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Weapon setWeapon(Weapon weapon) {
        String name = etName.getText().toString();
        int firepower = Integer.parseInt(etFirepower.getText().toString());
        int rateOfFire = Integer.parseInt(etRateOfFire.getText().toString());
        int accuracy = Integer.parseInt(etAccuracy.getText().toString());
        int evasion = Integer.parseInt(etEvasion.getText().toString());
        return new Weapon(name,firepower,rateOfFire,accuracy,evasion);
    }

    public void loadFragment(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.show();
        alertDialog.show();
    }

    public static Weapon getMine() {
        return mine;
    }

    public static Weapon getEnemy() {
        return enemy;
    }

    public View changeView(View view, Weapon weapon) {
        TextView tvName, tvFirepower, tvRateOfFire, tvAccuracy
                , tvEvasion, tvDamagePerSecond, tvCombatEffectiveness;
        tvName = view.findViewById(R.id.tv_name);
        tvFirepower = view.findViewById(R.id.tv_firepower);
        tvRateOfFire = view.findViewById(R.id.tv_rate_of_fire);
        tvAccuracy = view.findViewById(R.id.tv_accuracy);
        tvEvasion = view.findViewById(R.id.tv_evasion);
        tvDamagePerSecond = view.findViewById(R.id.tv_damage_per_second);
        tvCombatEffectiveness = view.findViewById(R.id.tv_combat_effectiveness);

        tvName.setText("Name: " + weapon.getName());
        tvFirepower.setText("Firepower: " + weapon.getFirepower());
        tvRateOfFire.setText("Rate of Fire: " + weapon.getRateOfFire());
        tvAccuracy.setText("Accuracy: " + weapon.getAccuracy());
        tvEvasion.setText("Evasion: " + weapon.getEvasion());
        tvDamagePerSecond.setText("Damage per Second: " + (int) weapon.getDamagePerSecond());
        tvCombatEffectiveness.setText("Combat Effectiveness: " + weapon.getCombatEffectiveness());

        return view;
    }
}