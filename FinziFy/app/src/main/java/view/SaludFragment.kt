package view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.aplicacionfinanzas.R
import com.example.aplicacionfinanzas.databinding.FragmentSaludBinding
import model.Categoria
import model.GastoRepositorio

class SaludFragment : Fragment() {

    // Declaración de la variable de enlace para el fragmento
    private lateinit var binding: FragmentSaludBinding

    // Método para inflar el diseño del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño usando View Binding
        binding = FragmentSaludBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Método llamado después de que la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Crear una categoría de "Salud"
        val categoriaSalud = Categoria("Salud 🏥")
        // Obtener los gastos asociados a la categoría "Salud"
        val gastosSalud = GastoRepositorio.obtenerGastosPorCategoria(categoriaSalud)

        // Crear un adaptador para mostrar los nombres de los gastos en un ListView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, gastosSalud.map { it.nombre })
        binding.listViewGastos.adapter = adapter

        // Configurar un listener para manejar los clics en los elementos del ListView
        binding.listViewGastos.setOnItemClickListener { _, _, position, _ ->
            val gasto = gastosSalud[position]
            val intent = Intent(requireContext(), DetalleGasto::class.java)
            val bundle = Bundle()
            bundle.putString("nombre", gasto.nombre)
            bundle.putString("descripcion", gasto.descripcion)
            bundle.putDouble("monto", gasto.monto)
            bundle.putString("categoria", gasto.categoria.nombre)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}