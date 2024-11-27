import React from 'react';
import SelecaoOpcoes from '../SelecaoOpcoes/SelecaoOpcoes';
import { Espacadores, Radio } from '../../StyledComponents/EspacadoresMarcas';
import { useNavigate } from 'react-router-dom';

const PaginaMarca: React.FC = () => {
  const navigate = useNavigate();
  const opcoesMarca = ['Chevrolet', 'Fiat', 'Ford', 'Outro'];

  const handleMarcaSelecionada = (marca: string) => {
    console.log(`Marca selecionada: ${marca}`);
    navigate('/Diagnosticos/modelo');
  };

  return (
    <>
      <SelecaoOpcoes titulo="Marca" opcoes={opcoesMarca} onOpcaoSelecionada={handleMarcaSelecionada} />
      <Radio checked></Radio>
      <Radio disabled></Radio>
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

export default PaginaMarca;
