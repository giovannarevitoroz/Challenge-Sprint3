import React from 'react';
import SelecaoOpcoes from '../SelecaoOpcoes/SelecaoOpcoes';
import { Espacadores } from '../../StyledComponents/EspacadoresModelo';
import { useNavigate } from 'react-router-dom';
import { Radio } from '../../StyledComponents/EspacadoresMarcas';

const PaginaAno: React.FC = () => {
  const navigate = useNavigate();
  const opcoesAno = ['2020', '2019', '2018', 'Outro'];

  const handleAnoSelecionado = (ano: string) => {
    console.log(`Ano selecionado: ${ano}`);
    
    navigate('/Diagnosticos/areaProblema');
  };

  return (
    <>
      <SelecaoOpcoes titulo="Ano" opcoes={opcoesAno} onOpcaoSelecionada={handleAnoSelecionado} />
      <Radio checked></Radio>
      <Radio checked></Radio>
      <Radio checked></Radio>
      <Radio disabled></Radio>
      <Radio disabled></Radio>
      <Radio disabled></Radio>
      <Espacadores>
        <div className='espacadorEsquerdo'></div>
        <div className='espacadorDireito'></div>
      </Espacadores>
    </>
  );
};

export default PaginaAno;
