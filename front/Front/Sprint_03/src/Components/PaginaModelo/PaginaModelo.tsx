import React from 'react';
import SelecaoOpcoes from '../SelecaoOpcoes/SelecaoOpcoes';
import { Espacadores } from '../../StyledComponents/EspacadoresModelo';
import { useNavigate } from 'react-router-dom';
import { Radio } from '../../StyledComponents/EspacadoresMarcas';

const PaginaModelo: React.FC = () => {
  const navigate = useNavigate();
  const opcoesModelo = ['Celta', 'Corsa', 'Onix', 'Outro'];

  const handleModeloSelecionado = (modelo: string) => {
    // Aqui você pode salvar a escolha do modelo, se necessário
    console.log(`Modelo selecionado: ${modelo}`);
    
    // Após selecionar o modelo, navegue para a página de ano
    navigate('/Diagnosticos/ano');
  };

  return (
    <>
      <SelecaoOpcoes titulo="Modelo" opcoes={opcoesModelo} onOpcaoSelecionada={handleModeloSelecionado} />
      <Radio checked></Radio>
      <Radio checked></Radio>
      <Radio disabled></Radio>
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

export default PaginaModelo;
