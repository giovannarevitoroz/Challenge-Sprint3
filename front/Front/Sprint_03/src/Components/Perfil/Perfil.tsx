import { Agendamentos, AutoPlus, ComponenenteTextual, ComponenenteTextualComplementar, ImagemFundo, ImagemUsuario, ParagrafoBio, SetaVoltar, Veiculos_, } from "../../StyledComponents/Perfil";

const Perfil = () => {
    return(
        <>
        <ImagemFundo src="./Imagens/CarroPerfil.png"></ImagemFundo>
        <ImagemUsuario src="./Imagens/Regis.png"></ImagemUsuario>
        <ParagrafoBio>Olá, sou Régis, trabalho como mecãnico da Porto há 10 anos, sou especialista em cãmbios automáticos, que tal marcar um visita à mInha oficina</ParagrafoBio>
        <div className="FundoAgendamentosPerfil">
            <Agendamentos>Agendamentos</Agendamentos>
            <Veiculos_ to="/Veiculos">Veiculos</Veiculos_>
            <AutoPlus to="#">Auto+</AutoPlus>
            <ComponenenteTextual>Troca de velas</ComponenenteTextual>  
            <ComponenenteTextual>Horario</ComponenenteTextual> 
            <ComponenenteTextual>Centro Porto</ComponenenteTextual> 
            <ComponenenteTextual>Valor</ComponenenteTextual> 
            <ComponenenteTextual>Status</ComponenenteTextual> 
            <ComponenenteTextualComplementar>20/09/2024</ComponenenteTextualComplementar>
            <ComponenenteTextualComplementar>11:20 PM</ComponenenteTextualComplementar>
            <ComponenenteTextualComplementar>Lins de Vas. 111</ComponenenteTextualComplementar>
            <ComponenenteTextualComplementar>557,00 R$</ComponenenteTextualComplementar>
            <ComponenenteTextualComplementar>Pago</ComponenenteTextualComplementar>
            <SetaVoltar to="/"><img src="/Imagens/SetaVolta.png"></img></SetaVoltar>
        </div>
        </>
    )
}

export default Perfil;