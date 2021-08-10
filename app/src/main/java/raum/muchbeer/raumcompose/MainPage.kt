package raum.muchbeer.raumcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import raum.muchbeer.raumcompose.fragment.MainFragment

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //We don't use this since we have the navGraph setup
       supportFragmentManager.beginTransaction().
                replace(R.id.main_container, MainFragment()).commit()
    }
}