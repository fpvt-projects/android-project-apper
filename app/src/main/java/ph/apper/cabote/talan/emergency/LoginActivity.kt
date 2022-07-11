package ph.apper.cabote.talan.emergency

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val lgnButton = findViewById<Button>(R.id.loginBtn)
        val passwordInput = findViewById<EditText>(R.id.password)
        val emailInput = findViewById<EditText>(R.id.email)

        //INIT FIREBASE
        auth = FirebaseAuth.getInstance()

        lgnButton.setOnClickListener{
            when {
                TextUtils.isEmpty(emailInput.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please Enter Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(passwordInput.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please Enter Password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else -> {
                    //TODO LOGIN HERE
                    var emailValue =  emailInput.text.toString().trim()
                    var passwordValue = passwordInput.text.toString().trim()

                    auth.signInWithEmailAndPassword(emailValue, passwordValue)
                        .addOnSuccessListener {
                            //LOGIN SUCCESS
                            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                        .addOnFailureListener{
                            //LOGIN FAILED
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }

        fun checkUser() {
            val firebaseUser = auth.currentUser
            if(firebaseUser != null) {
                //USER IS ALREADY LOGGED-IN
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }

    }
}