package pe.edu.idat.appgymv1

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoginPresenter(private val view: LoginActivity) {
    fun login(correo: String, password: String) {
        val url = "http://192.168.1.10:5000/login"  // Reemplaza esto con la dirección de tu servidor Flask

        val jsonObject = JSONObject().apply {
            put("correo", correo)
            put("password", password)
        }

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                // Manejar la respuesta del servidor en la actividad de inicio de sesión
                view.handleLoginResponse(response)
            },
            { error ->
                // Manejar errores de la solicitud
                view.showError("Error de red")
            }
        )

        // Agregar la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(view).add(request)
    }
}