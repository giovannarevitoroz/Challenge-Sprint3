import React from 'react';
import OpcoesGenericas from '../SelecaoOpcoes/SelecaoOpcoes';
import { Espacadores } from '../../StyledComponents/EspacadoresModelo';
import { useNavigate } from 'react-router-dom';
import { Radio } from '../../StyledComponents/EspacadoresMarcas';

const PaginaAreaProblema: React.FC = () => {
  const navigate = useNavigate();
  const opcoesAreaProblema = ['Motor', 'Transmissão', 'Freios', 'Outro'];

  const handleAreaProblemaSelecionada = (area: string) => {
    console.log(`Área do Problema selecionada: ${area}`);
    navigate('/Diagnosticos/TipoProblema');
  };

  return (
    <>
      <OpcoesGenericas 
        titulo="Área do Problema" 
        opcoes={opcoesAreaProblema} 
        onOpcaoSelecionada={handleAreaProblemaSelecionada}
      />
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio disabled></Radio>
        <Radio disabled></Radio>
      <Espacadores>
        <div className='espacadorEsquerdo'></div>
        <div className='espacadorDireito'></div>
      </Espacadores>
    </>
  );
};

export default PaginaAreaProblema;
