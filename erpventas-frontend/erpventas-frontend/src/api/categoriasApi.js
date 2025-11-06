import axios from "axios";

const Api_URL = "http://localhost:8080/api/categorias";

export const getCategorias = async () => {
    try {
        const response = await axios.get(Api_URL);
        return response.data;

    } catch (error) {
        console.log("Error al obtener las categorías:", error);
        return [];
    }
};