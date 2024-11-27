import React from 'react';

interface SelecaoOpcoesProps {
  titulo: string;
  opcoes: string[];
  onOpcaoSelecionada: (opcao: string) => void;
}

const OpcoesGenericas: React.FC<SelecaoOpcoesProps> = ({ titulo, opcoes, onOpcaoSelecionada }) => {
  return (
    <div className="container">
      <h1 className='tituloPaginaPadraoDiagnosticos'>{titulo}</h1>
      <div className="opcoes">
        {opcoes.map((opcao, index) => (
          <button 
            key={index} 
            className="botao-opcao" 
            onClick={() => onOpcaoSelecionada(opcao)}
          >
            {opcao}
          </button>
        ))}
      </div>
      <div className="botoes-acao">
        <button className="botao-confirmar">Confirmar</button>
        <button className="botao-voltar">Voltar</button>
      </div>
    </div>
  );
};

export default OpcoesGenericas;



