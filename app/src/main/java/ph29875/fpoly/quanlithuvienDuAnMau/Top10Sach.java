package ph29875.fpoly.quanlithuvienDuAnMau;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Top10Sach#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Top10Sach extends Fragment {


    public Top10Sach() {
        // Required empty public constructor
    }

    public static Top10Sach newInstance() {
Top10Sach quanLiPhieuMuon = new Top10Sach();

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
        return inflater.inflate(R.layout.fragment_top10_sach, container, false);
    }
}