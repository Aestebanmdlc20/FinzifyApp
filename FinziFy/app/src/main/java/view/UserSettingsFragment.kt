package view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionfinanzas.R
import com.example.aplicacionfinanzas.databinding.ActivityAcercaDeBinding
import com.example.aplicacionfinanzas.databinding.FragmentUserSettingsBinding
import model.UsuarioRepositorio

class UserSettingsFragment : Fragment() {
    // Declaración de la variable de enlace de datos
    private lateinit var binding: FragmentUserSettingsBinding
    // Obtención del usuario desde el repositorio
    private val usuario = UsuarioRepositorio.obtenerUsuarioPorCorreo("alejandrodev@gmail.com")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout usando Data Binding
        binding = FragmentUserSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Establecer la barra de menú como la barra de acción
        (activity as AppCompatActivity).setSupportActionBar(binding.menuBar)

        // Mostrar los datos del usuario
        usuario?.let {
            binding.txtNombre.text = usuario.nombre
            binding.txtCorreo.text = usuario.correo
            // Formatear y mostrar el saldo del usuario
            binding.txtSaldo.text = getString(R.string.saldo_format, usuario.saldo.toString())
        }

        // Configurar el botón para volver atrás
        binding.btnAtras.setOnClickListener {
            val intent = Intent(activity, Inicio::class.java)
            startActivity(intent)
        }

    }
}