package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aplicacionfinanzas.R
import com.example.aplicacionfinanzas.databinding.FragmentGestionarSaldoBinding
import model.Usuario
import model.UsuarioRepositorio

// Clase GestionarSaldoFragment que extiende Fragment
class GestionarSaldoFragment : Fragment() {

    // Declaración de la variable binding para acceder a las vistas
    private lateinit var binding: FragmentGestionarSaldoBinding
    // Obtención del usuario a través del repositorio
    private val usuario = UsuarioRepositorio.obtenerUsuarioPorCorreo("alejandrodev@gmail.com")

    // Método onCreateView que se llama para inflar el layout del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout usando View Binding
        binding = FragmentGestionarSaldoBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Método onViewCreated que se llama después de que la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mostrar el saldo actual del usuario en el TextView
        binding.txtSaldoActual.text = getString(R.string.saldo_actual) + usuario!!.saldo + "€"

        // Configurar el botón para añadir saldo
        binding.btnAddSaldo.setOnClickListener {
            // Obtener el valor del saldo ingresado y convertirlo a Double
            val saldo = binding.edtSaldo.text.toString().toDoubleOrNull()
            if (saldo != null) {
                // Añadir el saldo al saldo actual del usuario
                usuario!!.saldo += saldo
                // Actualizar el TextView con el nuevo saldo
                binding.txtSaldoActual.text = getString(R.string.saldo_actual) + usuario!!.saldo + "€"
            }
        }

        // Configurar el botón para retirar saldo
        binding.btnRetirarSaldo.setOnClickListener {
            // Obtener el valor del saldo ingresado y convertirlo a Double
            val saldo = binding.edtSaldo.text.toString().toDoubleOrNull()
            if (saldo != null) {
                // Restar el saldo del saldo actual del usuario
                usuario!!.saldo -= saldo
                // Actualizar el TextView con el nuevo saldo
                binding.txtSaldoActual.text = getString(R.string.saldo_actual) + usuario!!.saldo + "€"
            }
        }
    }
    // Método onSaveInstanceState que se llama antes de que la actividad sea destruida
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("saldoIngresado", binding.edtSaldo.text.toString())
        outState.putDouble("saldoActual", usuario!!.saldo)
    }
    // Método onViewStateRestored que se llama después de que la actividad haya sido restaurada
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            binding.edtSaldo.setText(savedInstanceState.getString("saldoIngresado"))
            usuario!!.saldo = savedInstanceState.getDouble("saldoActual")
            binding.txtSaldoActual.text = getString(R.string.saldo_actual) + usuario!!.saldo + "€"
        }
    }

}