package pe.edu.idat.appgymv1
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import pe.edu.idat.appgymv1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
        private lateinit var binding: ActivityLoginBinding
        private lateinit var presenter: LoginPresenter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            presenter = LoginPresenter(this)

            binding.btnIngresar.setOnClickListener {
                val correo = binding.etCorreo.text.toString()
                val password = binding.etPassword.text.toString()

                presenter.login(correo, password)
            }
        }

        fun handleLoginResponse(response: JSONObject) {
            val mensaje = response.getString("mensaje")
            if (mensaje == "Inicio de sesión exitoso") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Cerrar la actividad de inicio de sesión para evitar volver atrás
            } else {
                showError("Credenciales inválidas")
            }
        }

        fun showError(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
