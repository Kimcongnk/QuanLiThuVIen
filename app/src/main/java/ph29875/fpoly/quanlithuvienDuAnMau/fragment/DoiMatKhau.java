package ph29875.fpoly.quanlithuvienDuAnMau.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import ph29875.fpoly.quanlithuvienDuAnMau.Dao.ThuThuDao;
import ph29875.fpoly.quanlithuvienDuAnMau.Model.ThuThu;
import ph29875.fpoly.quanlithuvienDuAnMau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoiMatKhau#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoiMatKhau extends Fragment {
    private EditText hoTen, matKhauCu, matKhauMoi, matKhaunhaplai;
    private Button xacNhan;
    private ThuThuDao thuThuDao;

    private SharedPreferences sharedPreferences;

    public DoiMatKhau() {
        // Required empty public constructor
    }

    public static DoiMatKhau newInstance() {
        DoiMatKhau quanLiPhieuMuon = new DoiMatKhau();

        return quanLiPhieuMuon;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);

        matKhauCu = view.findViewById(R.id.ten);
        matKhauMoi = view.findViewById(R.id.editTextText7);
        matKhaunhaplai = view.findViewById(R.id.editTextText4);
        xacNhan = view.findViewById(R.id.button4);
        thuThuDao = new ThuThuDao(getContext());

        xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = matKhauCu.getText().toString();
                String newPassword = matKhauMoi.getText().toString();
                String confirmPassword = matKhaunhaplai.getText().toString();

                if (newPassword.equals(confirmPassword)) {
                    boolean loginSuccess = thuThuDao.checkLogin("Doe", oldPassword);
                    if (loginSuccess) {
                        int result = thuThuDao.updatePass(newPassword);
                        if (result > 0) {
                            Toast.makeText(getContext(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Incorrect old password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }





}