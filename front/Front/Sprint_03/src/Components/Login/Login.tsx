import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
// import styled from 'styled-components';
import { BackButton, Button, Container, DivAzulMedio, Divisao, Input, Logo, Title } from '../../StyledComponents/Login';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = () => {
    if (email === 'usuarioteste@.com' && password === '1234') {
      navigate('/perfil');
    } else {
      alert('Login falhou, tente novamente.');
    }
  };

  return ( 
    <>
      <Container>
        <DivAzulMedio>
        <Logo src="./Imagens/Logo.png" alt="Logo"></Logo>
        <Title>Login</Title>
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
        <Divisao></Divisao>
        <Button onClick={handleLogin}>Login</Button>
        <BackButton onClick={() => navigate(-1)}>Voltar</BackButton>
        </DivAzulMedio>
      </Container>
    </>
  );
};

export default Login;