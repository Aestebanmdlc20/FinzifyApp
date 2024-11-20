package view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.aplicacionfinanzas.databinding.FragmentOcioBinding
import model.Categoria
import model.GastoRepositorio

class OcioFragment : Fragment() {

    // Declaración de la variable de enlace para el fragmento
    private lateinit var binding: FragmentOcioBinding

    // Método para inflar el diseño del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño usando View Binding
        binding = FragmentOcioBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Método llamado después de que la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Crear una categoría de "Ocio"
        val categoriaOcio = Categoria("Ocio 🎉")
        // Obtener los gastos asociados a la categoría "Ocio"
        val gastosOcio = GastoRepositorio.obtenerGastosPorCategoria(categoriaOcio)

        // Crear un adaptador para mostrar los nombres de los gastos en un ListView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, gastosOcio.map { it.nombre })
        binding.listViewGastos.adapter = adapter

        // Configurar un listener para manejar los clicks en los elementos del ListView
        binding.listViewGastos.setOnItemClickListener { _, _, position, _ ->
            val gasto = gastosOcio[position]
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