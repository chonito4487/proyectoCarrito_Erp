import CategoriaList from "../components/CategoriaList";

export default function HomePage() {
    return (
        <div>
            <header className="bg-blue-600 text-white p-4 text-center text-lg font-semibold">
                Sistema ERP Ventas - Catálogo
            </header>
            
            <main className="p-6">
                <CategoriaList />
            </main>
        </div>
    );
}