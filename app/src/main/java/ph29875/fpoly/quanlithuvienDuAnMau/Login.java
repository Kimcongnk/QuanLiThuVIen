package ph29875.fpoly.quanlithuvienDuAnMau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import ph29875.fpoly.quanlithuvienDuAnMau.Dao.ThanhVienDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Dao.ThuThuDao;
import ph29875.fpoly.quanlithuvienDuAnMau.MainActivity;
import ph29875.fpoly.quanlithuvienDuAnMau.R;


public class Login extends AppCompatActivity {
    Button dangnhap, thoat;
    EditText tendangnhap, matkhau;
    ImageButton showpass;
    CheckBox luutaikhoan;
    ThuThuDao thuThuDAO;
    ThanhVienDao thanhVienDAO;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        dangnhap = findViewById(R.id.slogin_btndangnhap);
        thoat = findViewById(R.id.slogin_btnthoat);
        tendangnhap = findViewById(R.id.slogin_tendangnhap);
        matkhau = findViewById(R.id.slogin_mkdangnhap);
        showpass = findViewById(R.id.slogin_showmk);
        luutaikhoan = findViewById(R.id.slogin_luudp);
        initPreferences();
        SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        tendangnhap.setText(sharedPreferences.getString("DATATEN", ""));
        matkhau.setText(sharedPreferences.getString("DATAMK", ""));
        luutaikhoan.setChecked(sharedPreferences.getBoolean("REMEMBER", false));


        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matkhau.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    matkhau.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showpass.setImageResource(android.R.drawable.ic_menu_view);
                } else {
                    matkhau.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showpass.setImageResource(android.R.drawable.ic_menu_view);
                }
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = tendangnhap.getText().toString();
                String pass = matkhau.getText().toString();
                thanhVienDAO = new ThanhVienDao(Login.this);
                thuThuDAO = new ThuThuDao(Login.this);

                if (tendangnhap.length() == 0) {
                    tendangnhap.requestFocus();
                    tendangnhap.setError("Không bỏ trống tên");
                } else if (matkhau.length() == 0) {
                    matkhau.requestFocus();
                    matkhau.setError("Không bỏ trống mật khẩu");
                } else {
                    if (thuThuDAO.checkLogin(user, pass) == true) {
                        rememberUser(user,pass,luutaikhoan.isChecked());
                        Intent intenttt = new Intent(Login.this, MainActivity.class);

                        Bundle checkdmk =  new Bundle();
                        checkdmk.putBoolean("dmktt", true);

                        intenttt.putExtra("thuthu",true);
                        intenttt.putExtras(checkdmk);


                        startActivity(intenttt);


//                    Doi_Mat_Khau_Fragment fragmentdmk = new Doi_Mat_Khau_Fragment();
//                    fragmentdmk.setArguments(checkdmk);
                        Toast.makeText(Login.this, "Đăng Nhập Bằng Tài Khoản Thủ Thư Thành Công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Login.this, "Tên Hoặc Mật Khẩu Không Đúng", Toast.LENGTH_SHORT).show();
                    }
                }
//                else if(thanhVienDAO.CheckLogintv(user,pass) == true){
//                    rememberUser(user,pass,luutaikhoan.isChecked());
//                    Intent intenttv = new Intent(ScreenLogin.this, MainActivity.class);
//                    intenttv.putExtra("thanhvien",true);
//                    startActivity(intenttv);
//                    Toast.makeText(ScreenLogin.this, "Đăng Nhập Bằng Tài Khoản Thành Viên Thành Công", Toast.LENGTH_SHORT).show();
//                }

            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tendangnhap.setText("");
                matkhau.setText("");

                finish();
            }

        });

    }

    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    public void rememberUser(String nhoten, String nhomk, boolean check) {
        SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!check) {
            editor.clear();
        } else {
            editor.putString("DATATEN", nhoten);
            editor.putString("DATAMK", nhomk);

            editor.putBoolean("REMEMBER", check);
        }
        editor.commit();

    }
}