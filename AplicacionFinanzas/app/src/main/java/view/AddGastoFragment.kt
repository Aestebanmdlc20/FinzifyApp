package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aplicacionfinanzas.R
import com.example.aplicacionfinanzas.databinding.FragmentAddGastoBinding
import model.Categoria
import model.CategoriaRepositorio
import model.Gasto
import model.GastoRepositorio
import model.UsuarioRepositorio

class AddGastoFragment : Fragment() {

    private lateinit var binding: FragmentAddGastoBinding
    // Como no hay persistencia de datos, se obtiene el usuario por defecto de un repositorio de usuarios
    val usuario = UsuarioRepositorio.obtenerUsuarioPorCorreo("alejandrodev@gmail.com")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGastoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Establezco la barra del menu
        (activity as AppCompatActivity).setSupportActionBar(binding.menuBar)

        // Lista de categorias
        val categorias = CategoriaRepositorio.obtenerCategorias()
        val adapter = object : ArrayAdapter<Categoria>(requireContext(), android.R.layout.simple_spinner_item, categorias) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent) as TextView
                view.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                return view
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView
                view.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                return view
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategorias.adapter = adapter

        // Manejo del botón Añadir Gasto
        binding.btnAddGasto.setOnClickListener {
            val categoriaSeleccionada = binding.spinnerCategorias.selectedItem as Categoria
            val nombre = binding.inputLayoutNombre.editText?.text.toString()
            val descripcion = binding.inputLayoutDescripcion.editText?.text.toString()
            val montoStr = binding.inputLayoutMonto.editText?.text.toString()

            // Validación del campo de nombre, si está vacío establecerá error o si es mayor a 20 caracteres
            if (nombre.isEmpty()) {
                binding.inputLayoutNombre.error = getString(R.string.gasto_name_error)
                return@setOnClickListener
            } else if (nombre.length > 20) {
                binding.inputLayoutNombre.error = getString(R.string.gasto_name_error_max_car)
                return@setOnClickListener
            } else {
                binding.inputLayoutNombre.error = null
            }

            // Validación del campo de monto, si está vacío establecerá error o si es mayor a 6 caracteres
            if (montoStr.isEmpty()) {
                binding.inputLayoutMonto.error = getString(R.string.gasto_monto_error)
                return@setOnClickListener
            } else if (montoStr.length > 6) {
                binding.inputLayoutMonto.error = getString(R.string.gasto_monto_error_max)
                return@setOnClickListener
            } else {
                binding.inputLayoutMonto.error = null
            }

            // Validación del campo de monto, si no es un numero establecerá error
            val monto = try {
                montoStr.toInt()
            } catch (e: NumberFormatException) {
                binding.inputLayoutMonto.error = getString(R.string.gasto_monto_error_num)
                return@setOnClickListener
            }

            // Quitar el error cuando la cantidad/monto sea correcta
            binding.inputLayoutMonto.error = null

            // Crear y agregar el nuevo gasto
            val gasto = Gasto(nombre, descripcion, monto.toDouble(), categoriaSeleccionada)
            GastoRepositorio.agregarGasto(gasto)
            // Restar el monto del gasto al saldo del usuario
            usuario?.saldo = (usuario?.saldo ?: 0.0) - monto.toDouble() // Si es nulo, se establece en 0.0
            Toast.makeText(requireContext(), R.string.gasto_added, Toast.LENGTH_SHORT).show()
        }
    }

    // Para guardar el estado de la vista
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nombre", binding.inputLayoutNombre.editText?.text.toString())
        outState.putString("descripcion", binding.inputLayoutDescripcion.editText?.text.toString())
        outState.putString("monto", binding.inputLayoutMonto.editText?.text.toString())
        outState.putInt("categoria", binding.spinnerCategorias.selectedItemPosition)
    }

    // Para restaurar el estado de la vista
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            binding.inputLayoutNombre.editText?.setText(savedInstanceState.getString("nombre"))
            binding.inputLayoutDescripcion.editText?.setText(savedInstanceState.getString("descripcion"))
            binding.inputLayoutMonto.editText?.setText(savedInstanceState.getString("monto"))
            binding.spinnerCategorias.setSelection(savedInstanceState.getInt("categoria"))
        }
    }
}