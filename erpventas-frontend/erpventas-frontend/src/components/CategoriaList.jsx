/* Este componente hace la llamada al backend localhost:8080/api/categorias
y muestra los resultados en la tabla.*/ 

import { useEffect, useState } from "react";
import { getCategorias } from "../api/categoriasApi";

export default function CategoriaList() {
    const [categorias, setCateorias] = useState([]);

    useEffect(() => {
        const cargarCategoria = async () => {
            const data = await getCategorias();
            setCateorias(data);
        };
        cargarCategoria();
    }, []);

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4 text-gray-700">Listado de Categorías</h2>
            {categorias.length === 0 ? (<p className="text-gray-500">No hay categorías registradas.</p>) :
                (
                    <table className="min-w-full border border-gray-300">
                        <thead className="bg-gray-100">
                            <tr>
                                <th className="px-4 py-2 border">ID</th>
                                <th className="px-4 py-2 border">Nombre</th>
                            </tr>
                        </thead>
                        <tbody>
                            {categorias.map((cat) => (
                                <tr key={cat.id}>
                                    <td className="px-4 py-2 border text-center">{cat.id}</td>
                                    <td className="px-4 py-2 border">{cat.nombre}</td>
                                </tr>
                            ))}
                        </tbody>

                    </table>
                )}
        </div>
    );
} 