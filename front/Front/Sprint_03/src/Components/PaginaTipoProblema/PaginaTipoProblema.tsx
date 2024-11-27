import React from 'react';
import OpcoesGenericas from '../SelecaoOpcoes/SelecaoOpcoes';
import { Espacadores } from '../../StyledComponents/EspacadoresModelo';
import { useNavigate } from 'react-router-dom';
import { Radio } from '../../StyledComponents/EspacadoresMarcas';

const PaginaTipoProblema: React.FC = () => {
  const navigate = useNavigate();
  const opcoesTipoProblema = ['Ruído', 'Falha Intermitente', 'Vazamento', 'Outro'];

  const handleTipoProblemaSelecionado = (tipo: string) => {
    console.log(`Tipo de Problema selecionado: ${tipo}`);
    navigate('/Diagnosticos/EstadoProblema'); // Navega para a página de Estado do Problema
  };

  return (
    <>
      <OpcoesGenericas 
        titulo="Tipo de Problema" 
        opcoes={opcoesTipoProblema} 
        onOpcaoSelecionada={handleTipoProblemaSelecionado} // Passa a função de callback para o componente
      />
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio checked></Radio>
        <Radio disabled></Radio>
      <Espacadores>
        <div className='espacadorEsquerdo'></div>
        <div className='espacadorDireito'></div>
      </Espacadores>
    </>
  );
};

export default PaginaTipoProblema;
