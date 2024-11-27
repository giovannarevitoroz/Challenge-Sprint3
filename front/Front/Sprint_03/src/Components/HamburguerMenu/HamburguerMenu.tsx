import { useState, useEffect, useRef } from "react";
import { FormH2, HanburguerBotao, HanburguerMenu, ListaLI, ListaMenu, ListaUL } from "../../StyledComponents/MainHome";
import { useNavigate } from "react-router-dom";

const cliente = "Jeferson";

interface HamburgerMenuProps {
  buttonMarginTop?: string;
}

const HamburgerMenu = ({ buttonMarginTop }: HamburgerMenuProps) => {
  const [menuAberto, setMenuAberto] = useState(false);
  const menuRef = useRef<HTMLDivElement>(null);
  const [ConteudoSair, setConteudoSair] = useState(true);
  const navega = useNavigate();

  const MudaMenu = () => setMenuAberto(prevState => !prevState);

  const sairPagina = () => {
    setConteudoSair(false);
    navega('/');
  };

  useEffect(() => {
    const fecharMenuFora = (event: MouseEvent) => {
      if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
        setMenuAberto(false);
      }
    };
    document.addEventListener("mousedown", fecharMenuFora);
    return () => {
      document.removeEventListener("mousedown", fecharMenuFora);
    };
  }, []);

  return (
    <>
      <HanburguerBotao onClick={MudaMenu} marginTop={buttonMarginTop}></HanburguerBotao>
      <HanburguerBotao onClick={MudaMenu}></HanburguerBotao>
      <HanburguerBotao onClick={MudaMenu}></HanburguerBotao>
      <HanburguerMenu ref={menuRef} menuAberto={menuAberto}>
        <FormH2>Ola, {cliente}</FormH2>
        <nav>
          <ListaUL>
            <ListaLI><ListaMenu to="/">Inicio</ListaMenu></ListaLI>
            <ListaLI><ListaMenu to="/Diagnosticos">Diagnosticos</ListaMenu></ListaLI>
            <ListaLI><ListaMenu to="/BemVindo">Perfil</ListaMenu></ListaLI>
            <ListaLI><ListaMenu to="/Equipe">Equipe</ListaMenu></ListaLI>
            {ConteudoSair && (
              <ListaLI><ListaMenu to="#" onClick={sairPagina}>Sair</ListaMenu></ListaLI>
            )}
          </ListaUL>
        </nav>
      </HanburguerMenu>
    </>
  );
};

export default HamburgerMenu;

