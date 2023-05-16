package ph29875.fpoly.quanlithuvienDuAnMau;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoiMatKhau#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoiMatKhau extends Fragment {


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
    }
}