import { Imagem1, Imagem2, ParagrafoDireito, ParagrafoEsquerdo, ParagrafoRmDireita, ParagrafoRmEsquerdo, Titulo, TituloDireita, TituloEsquerdaBug } from "../../StyledComponents/Equipe";
import HamburgerMenu from "../HamburguerMenu/HamburguerMenu";

const Esquipe = () => {
    return(
        <>
           <HamburgerMenu buttonMarginTop="5vw"></HamburgerMenu>
           <Titulo>Equipe</Titulo>
           <Imagem1 src="./Imagens/Revito.png"></Imagem1>
           <TituloDireita>Giovanna Revito</TituloDireita>
           <ParagrafoRmDireita>Rm 558981</ParagrafoRmDireita>
           <ParagrafoDireito>Uma jovem desenvolvedora que realiza seu trabalho com excelência</ParagrafoDireito>
           <TituloEsquerdaBug>Kaian Gustavo</TituloEsquerdaBug>
           <ParagrafoRmEsquerdo>Rm 558986</ParagrafoRmEsquerdo>
           <ParagrafoEsquerdo>Um jovem desenvolvedor que realiza seu trabalho com excelência</ParagrafoEsquerdo>
           <Imagem2 src="./Imagens/Kaian.png"></Imagem2>
           <Imagem1 src="./Imagens/Kenji.png"></Imagem1>
           <TituloDireita>Lucas Kenji</TituloDireita>
           <ParagrafoRmDireita>Rm 554424</ParagrafoRmDireita>
           <ParagrafoDireito>Um jovem desenvolvedor que realiza seu trabalho com excelência</ParagrafoDireito>
        </>

    )
}

export default Esquipe;