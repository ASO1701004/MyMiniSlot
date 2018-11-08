package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var hcoin = 990
    var kcoin = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        btn_start.setOnClickListener{onStartButtonTapped(it)}
        btn_up.setOnClickListener { onUpButtonTapped(it) }
        btn_down.setOnClickListener { onDownButtonTapped(it) }
        btn_reset.setOnClickListener { onResetButtonTapped(it) }
    }

    fun onStartButtonTapped(view: View?){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        val intent = Intent(this,GameActivity::class.java)
        editor.putInt("havecoin",hcoin).commit()
        editor.putInt("kakecoin",kcoin).commit()

        startActivity(intent)
    }

    fun onResetButtonTapped(view: View?){
        have_coin.setText("990")
        kake_coin.setText("10")
        hcoin = 990
        kcoin = 10
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putInt("havecoin",hcoin).commit()
        editor.putInt("kakecoin",kcoin).commit()
    }

    fun onUpButtonTapped(view: View?){
        if(hcoin > 0) {
            kcoin = kcoin + 10
            hcoin = hcoin - 10
            kake_coin.setText(kcoin.toString())
            have_coin.setText(hcoin.toString())
        }

    }

    fun onDownButtonTapped(view: View?){
        if(kcoin > 10) {
            kcoin = kcoin - 10
            hcoin = hcoin + 10
            kake_coin.setText(kcoin.toString())
            have_coin.setText(hcoin.toString())
        }
    }
}
