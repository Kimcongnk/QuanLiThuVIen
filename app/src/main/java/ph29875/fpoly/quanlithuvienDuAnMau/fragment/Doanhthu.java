package ph29875.fpoly.quanlithuvienDuAnMau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ph29875.fpoly.quanlithuvienDuAnMau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Doanhthu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Doanhthu extends Fragment {


    public Doanhthu() {
        // Required empty public constructor
    }

    public static Doanhthu newInstance() {
Doanhthu quanLiPhieuMuon = new Doanhthu();

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
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }
}