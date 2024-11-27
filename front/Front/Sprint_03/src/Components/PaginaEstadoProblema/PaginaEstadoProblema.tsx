import React from 'react';
import OpcoesGenericas from '../SelecaoOpcoes/SelecaoOpcoes';
import { Espacadores } from '../../StyledComponents/EspacadoresModelo';
import { useNavigate } from 'react-router-dom';
import { Radio } from '../../StyledComponents/EspacadoresMarcas';

const PaginaEstadoProblema: React.FC = () => {
  const navigate = useNavigate();
  const opcoesEstadoProblema = ['Crítico', 'Substancial', 'Pouco Relevante', 'Outro'];

  const handleEstadoProblemaSelecionado = (estado: string) => {
    console.log(`Estado do Problema selecionado: ${estado}`);
    navigate('/Diagnosticos/DiagnosticoRealizado');
  };

  return (
    <>
      <OpcoesGenericas 
        titulo="Estado do Problema" 
        opcoes={opcoesEstadoProblema} 
        onOpcaoSelecionada={handleEstadoProblemaSelecionado}
      />
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
      <Espacadores>
        <div className='espacadorEsquerdo'></div>
        <div className='espacadorDireito'></div>
      </Espacadores>
    </>
  );
};

export default PaginaEstadoProblema;
