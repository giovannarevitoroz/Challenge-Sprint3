import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { BackButton, Button, Container, DivAzulMedio, Divisao, Input, Logo, Title } from '../../StyledComponents/Login';

const Cadastro = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();

  const handleCadastro = () => {
    if (password === confirmPassword) {
      // Lógica de cadastro
      navigate('/BemVindo');
    } else {
      alert('As senhas não coincidem!');
    }
  };

  return (
    <Container>
      <DivAzulMedio>
        <Logo src="./Imagens/Logo.png" alt="Logo" />
        <Title>Cadastro</Title>
        <Input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <Input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <Input
          type="password"
          placeholder="Confirme a senha"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
        />
        <Divisao />
        <Button onClick={handleCadastro}>Cadastrar</Button>
        <BackButton onClick={() => navigate(-1)}>Voltar</BackButton>
      </DivAzulMedio>
    </Container>
  );
};

export default Cadastro;
