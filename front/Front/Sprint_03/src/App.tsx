import { BrowserRouter, Route, Routes } from "react-router-dom";
import LayoutPadrao from "./Components/Layout/LayoutPadrao";
import MainHome from "./Components/MainHome/MainHome";
import Perfil from "./Components/Perfil/Perfil";
import Diagnosticos from "./Components/Diagnosticos/Diagnosticos";
import PaginaMarca from "./Components/PaginaMarca/PaginaMarca";
import PaginaModelo from "./Components/PaginaModelo/PaginaModelo";
import PaginaAno from "./Components/PaginaAno/PaginaAno";
import PaginaAreaProblema from "./Components/PaginaAreaProblema/PaginaAreaProblema";
import PaginaTipoProblema from "./Components/PaginaTipoProblema/PaginaTipoProblema";
import PaginaEstadoProblema from "./Components/PaginaEstadoProblema/PaginaEstadoProblema";
import DiagFeito from "./Components/DiagFeito/DiagFeito";
import Esquipe from "./Components/Equipe/Equipe";
import Veiculos from "./Components/Veiculos/Veiculos";
import Login from "./Components/Login/Login";
import Cadastro from "./Components/Cadastro/Cadastro";
import BemVindo from "./Components/BemVindo/BemVindo";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LayoutPadrao />}>
                    <Route index element={<MainHome />} />
                </Route>

                {/* Diagnosticos com rotas aninhadas */}
                <Route path="Diagnosticos" element={<Diagnosticos />}>
                    <Route index element={<PaginaMarca />} /> {/* Carrega a página "Marca" por padrão */}
                    <Route path="marca" element={<PaginaMarca />} />
                    <Route path="modelo" element={<PaginaModelo />} />
                    <Route path="ano" element={<PaginaAno />}/>
                    <Route path="areaProblema" element={<PaginaAreaProblema />} />
                    <Route path="TipoProblema" element={<PaginaTipoProblema/>}/>
                    <Route path="EstadoProblema" element={<PaginaEstadoProblema></PaginaEstadoProblema>}></Route>
                </Route>

                <Route path="Diagnosticos/DiagnosticoRealizado" element={<DiagFeito/>}/>
                <Route path="Equipe" element={<Esquipe></Esquipe>}></Route>
                <Route path="Equipe" element={<Esquipe></Esquipe>} />
                <Route path="Perfil" element={<Perfil />} />  
                <Route path="Veiculos" element={<Veiculos></Veiculos>}/>
                <Route path="Login" element={<Login></Login>} ></Route>
                <Route path="Cadastro" element={<Cadastro></Cadastro>}></Route>
                <Route path="BemVindo" element={<BemVindo></BemVindo>}></Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
