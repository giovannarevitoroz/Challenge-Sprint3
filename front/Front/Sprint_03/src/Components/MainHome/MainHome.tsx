import { ImagemSlide, Section_four, Section_tree, Section_two } from "../../StyledComponents/MainHome";
import HamburguerMenu from "../HamburguerMenu/HamburguerMenu";

const MainHome = () => {
  return (
    <div className="Content-responsive_01">
      <HamburguerMenu></HamburguerMenu>
      <ImagemSlide>
        <img src="./Imagens/ImagemSlide.png" alt="" />
      </ImagemSlide>
      <Section_two>
        <p>Seu carro novo onde quer que esteja</p>
        <img src="./Imagens/CarroNovo.png" alt="" />
        <a href="#">Confira</a>
      </Section_two>
      <Section_tree>
        <p>Venha conhecer uma de nossas 22 unidades</p>
        <a href="#">IR</a>
      </Section_tree>
      <Section_four>
        <p>já pensou em ter diversos serviços para seu veiculo na palma de suas mãos?</p>
        <img src="./Imagens/DonoMoco.png" alt="" />
      </Section_four>
    </div>
  );
};

export default MainHome;
