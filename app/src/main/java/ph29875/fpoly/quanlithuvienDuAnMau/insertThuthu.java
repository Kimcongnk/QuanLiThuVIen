package ph29875.fpoly.quanlithuvienDuAnMau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ph29875.fpoly.quanlithuvienDuAnMau.Dao.ThuThuDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.ThuThu;

public class insertThuthu extends AppCompatActivity {
private EditText hoTen, matKhau;
private Button them;
private ThuThuDao thuThuDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_thuthu);
        hoTen = findViewById(R.id.editTextHoTen);
        matKhau  = findViewById(R.id.editTextMatKhau);
        them = findViewById(R.id.buttonThem);
        thuThuDao = new ThuThuDao(this);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.setHoTen(hoTen.getText().toString());
                thuThu.setMatKhau(matKhau.getText().toString());
                if(thuThuDao.insertThuThu(thuThu) > 0 ){
                    Toast.makeText(insertThuthu.this, "Luu tc", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(insertThuthu.this, "that bai", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}