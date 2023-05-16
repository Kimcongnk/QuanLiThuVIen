package ph29875.fpoly.quanlithuvienDuAnMau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

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
            drawerLayout.close();
        } else if (id == R.id.quan_li_loai_sach) {
            replaceFragment(QuanLiSach.newInstance());
            drawerLayout.close();
        } else if (id == R.id.quan_li_sach) {
            replaceFragment(QuanLiThanhVien.newInstance());
            drawerLayout.close();
        } else if (id == R.id.quan_li_thanh_vien) {
            replaceFragment(Top10Sach.newInstance());
            drawerLayout.close();
        } else if (id == R.id.top10_sach) {
            replaceFragment(Doanhthu.newInstance());
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

}