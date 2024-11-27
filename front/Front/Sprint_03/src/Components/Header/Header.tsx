import { Link, useNavigate } from "react-router-dom";
import '../../../public/css/estilo.css'
import { useState } from "react";

const Header = () => {
   const [mostraConteudo, setMostraConteudo] = useState(true);
   const navega = useNavigate();

   const sairPagina = () => {
    setMostraConteudo(false);
    navega('/');
   }
return (
    <>
      <div className="Content-responsive_01 Header-responsive_01">
        <header>
          {mostraConteudo && (
            <>
              <h1 className="red-hat-display">Bem-vindo Jeferson</h1>
              {/* Usar a URL da imagem diretamente */}
              <Link to="#" onClick={sairPagina}>
                <img src="/Imagens/PortaSair.png" alt="Sair" />
              </Link>
            </>
          )}
        </header>
      </div>
    </>
  );
};

export default Header;
