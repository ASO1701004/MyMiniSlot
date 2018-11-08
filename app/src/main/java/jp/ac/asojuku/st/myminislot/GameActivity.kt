package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    val imageArray = arrayOf(
            R.drawable.banana,
            R.drawable.bar,
            R.drawable.bigwin,
            R.drawable.cherry,
            R.drawable.grape,
            R.drawable.lemon,
            R.drawable.melon,
            R.drawable.orange,
            R.drawable.seven
    )
    var count7 = 0;
    var countbig = 0;
    var countvar = 0;
    var countoth = 0;
    var suica = false;
    var origin1 = true;
    var origin2 = true;
    var islost = false;
    var bet = 1;

    var isButton1 = false
    var isButton2 = false
    var isButton3 = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        var hcoin = pref.getInt("havecoin",0)
        var kcoin = pref.getInt("kakecoin",0)
        have_coin2.setText(hcoin.toString())
        kake_coin2.setText(kcoin.toString())

        btn_back.setOnClickListener { onBackButtonTapped(it) }
        btn_stop1.setOnClickListener { onStop1ButtonTapped(it) }
        btn_stop2.setOnClickListener { onStop2ButtonTapped(it) }
        btn_stop3.setOnClickListener { onStop3ButtonTapped(it) }
    }

    fun onBackButtonTapped(view: View?){
        val intent = Intent(this,MainActivity::class.java)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        var hcoin = pref.getInt("havecoin",0)
        editor.putInt("havecoin",hcoin)
        startActivity(intent)
    }

    fun onStop1ButtonTapped(view: View?){

        if(isButton1 == false){
            val rand = (Math.random()*9).toInt()
            img1.setImageResource(imageArray[rand])
            selectflg(rand)
            if(rand != 3){
                origin1 = false
            }
            if(rand != 0){
                origin2 = false
            }
            isButton1 = true
        }

    }

    fun onStop2ButtonTapped(view: View?){

        if(isButton2 == false){
            val rand = (Math.random()*9).toInt()
            img2.setImageResource(imageArray[rand])
            selectflg(rand)
            if(rand != 7){
                origin1 = false
            }
            if(rand != 6){
                origin2 = false
            }
            isButton2 = true
        }

    }

    fun onStop3ButtonTapped(view: View?){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        var kcoin = pref.getInt("kakecoin",0)
        var hcoin = pref.getInt("havecoin",0)
        if(isButton3 == false){
            val rand = (Math.random()*9).toInt()
            img3.setImageResource(imageArray[rand])
            selectflg(rand)
            if(rand != 5){
                origin1 = false
            }
            if(rand != 4){
                origin2 = false
            }
            isButton3 = true;



            if(count7 == 3){
                islost = true;
                bet = bet * 20

            }else if(count7 == 2) {
                islost = true;
                bet = bet * 3
            }
            if (countbig == 3){
                islost = true;

                bet = bet * 10
            }
            if(countvar == 3){
                islost = true;

                bet = bet * 5
            }
            if(countoth == 3){
                islost = true;
                bet = bet * 2
            }else if (countoth == 2){
                islost = true;
            }
            if (origin1 == true){
                islost = true;
                bet = bet * 30
            }
            if(origin2 == true){
                islost = true;
                bet = bet * 10
            }
            if(suica == true){
                islost = true;
            }
            if(islost == false){
                bet = -1
            }

            var hacoin = hcoin + kcoin * bet ;
            have_coin2.setText(hacoin.toString())
            editor.putInt("havecoin",hacoin).apply()



        }

    }

    fun selectflg(rand : Int?){
        when(rand){
            0 -> (countoth++)
            1 -> (countvar++)
            2 -> (countbig++)
            3 -> (countoth++)
            4 -> (countoth++)
            5 -> (countoth++)
            6 -> (countoth++)//suica
            7 -> (countoth++)
            8 -> (count7++)
        }
    }



}
