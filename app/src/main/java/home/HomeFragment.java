package home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.primeraapp.R;

import actPrincipales.MainActivity;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final MainActivity ma = (MainActivity) getActivity();
        SharedPreferences sp = ma.getSharedPreferences("SP", ma.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        final Switch swi = root.findViewById(R.id.switchTema);
        int theme = sp.getInt("Theme", 1);
        if(theme==1){
            swi.setChecked(false);
        }else
        {
            swi.setChecked(true);
        }
        swi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swi.isChecked()){
                    editor.putInt("Theme", 0);
                }
                else{
                    editor.putInt("Theme", 1);
                }
                editor.commit();
                ma.setDayNight();
            }
        });

        return root;
    }
}
