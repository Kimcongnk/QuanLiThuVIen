package ph29875.fpoly.quanlithuvienDuAnMau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ph29875.fpoly.quanlithuvienDuAnMau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemNguoiDung#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemNguoiDung extends Fragment {


    public ThemNguoiDung() {
        // Required empty public constructor
    }

    public static ThemNguoiDung newInstance() {
ThemNguoiDung quanLiPhieuMuon = new ThemNguoiDung();

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
        return inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);
    }
}