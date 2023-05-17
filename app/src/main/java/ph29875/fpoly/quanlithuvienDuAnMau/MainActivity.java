package ph29875.fpoly.quanlithuvienDuAnMau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ph29875.fpoly.quanlithuvienDuAnMau.fragment.Doanhthu;
import ph29875.fpoly.quanlithuvienDuAnMau.fragment.DoiMatKhau;
import ph29875.fpoly.quanlithuvienDuAnMau.fragment.QuanLiLoaiSach;
import ph29875.fpoly.quanlithuvienDuAnMau.fragment.QuanLiSach;
import ph29875.fpoly.quanlithuvienDuAnMau.fragment.QuanLiThanhVien;
import ph29875.fpoly.quanlithuvienDuAnMau.fragment.ThemNguoiDung;
import ph29875.fpoly.quanlithuvienDuAnMau.fragment.Top10Sach;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();

        navigationView = findViewById(R.id.id_navigation);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.thong_ke) {
            replaceFragment(QuanLiPhieuMuon.newInstance());
            drawerLayout.close();
            return false;
        } else if (id == R.id.quan_li_phieu_muon) {
            replaceFragment(QuanLiLoaiSach.newInstance());
            Toast.makeText(this, "Quản lí phiếu mượn", Toast.LENGTH_SHORT).show();
            drawerLayout.close();
        } else if (id == R.id.quan_li_loai_sach) {
            replaceFragment(QuanLiSach.newInstance());
            Toast.makeText(this, "Quản lí sách", Toast.LENGTH_SHORT).show();

            drawerLayout.close();
        } else if (id == R.id.quan_li_sach) {
            replaceFragment(QuanLiThanhVien.newInstance());
            Toast.makeText(this, "Quản lí sách", Toast.LENGTH_SHORT).show();

            drawerLayout.close();
        } else if (id == R.id.quan_li_thanh_vien) {
            replaceFragment(Top10Sach.newInstance());
            Toast.makeText(this, "Quản lí thành viên", Toast.LENGTH_SHORT).show();

            drawerLayout.close();
        } else if (id == R.id.top10_sach) {
            replaceFragment(Doanhthu.newInstance());
            Toast.makeText(this, "Quản lí top10 sách", Toast.LENGTH_SHORT).show();

            drawerLayout.close();
        } else if (id == R.id.doanh_thu) {
            replaceFragment(ThemNguoiDung.newInstance());

            drawerLayout.close();
        } else if (id == R.id.doi_mat_khau) {
            replaceFragment(DoiMatKhau.newInstance());

            drawerLayout.close();
        }
        return true;
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link QuanLiPhieuMuon#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class QuanLiPhieuMuon extends Fragment {


        public QuanLiPhieuMuon() {
            // Required empty public constructor
        }

        public static QuanLiPhieuMuon newInstance() {
    QuanLiPhieuMuon quanLiPhieuMuon = new QuanLiPhieuMuon();

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
            return inflater.inflate(R.layout.fragment_quan_li_phieu_muon, container, false);
        }
    }
}