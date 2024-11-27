import { Link } from "react-router-dom";
import { Divisao, MeuVeiculo, PrimeiraDiv, SegundaDiv, TerceiraDiv } from "../../StyledComponents/Veiculos";
import HamburgerMenu from "../HamburguerMenu/HamburguerMenu";

const Veiculos = () => {
    return(
        <>
        <HamburgerMenu buttonMarginTop="5vw"></HamburgerMenu>
        <MeuVeiculo src="./Imagens/Corsa.png"></MeuVeiculo>
        <section>
            <PrimeiraDiv>
                 <h2>Manutenções</h2>
                 <h3>Troca de vela 11/10/24</h3>
                 <h3>Troca de vela 11/10/24</h3>
                 <h3>Troca de vela 11/10/24</h3>
                 <Link to="#">Veja mais</Link>
            </PrimeiraDiv>
            <SegundaDiv>
                <h2>Saúde Veiculo</h2>
                <img src="./Imagens/SaudeVeiculo.png" alt="" />
            </SegundaDiv>
            <Divisao></Divisao>
            <TerceiraDiv>
                <h2>veiculo</h2>
                <h3>Placa : ABC123</h3>
                <h3>Renavam : 123456</h3>
                <h3>Ano : 2022</h3>
                <h3>Modelo : Corsa</h3>
            </TerceiraDiv>
        </section>
        </>
    )
}
export default Veiculos;