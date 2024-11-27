import { useNavigate } from 'react-router-dom';
import { Button, Container, DivAzulMedio, Logo, Title } from '../../StyledComponents/Login';

const BemVindo = () => {
  const navigate = useNavigate();

  return (
    <Container>
      <DivAzulMedio>
        <Logo src="./Imagens/Logo.png" alt="Logo" />
        <Title>Bem-vindo!</Title>
        <Button onClick={() => navigate('/Cadastro')}>Cadastre-se</Button>
        <Button onClick={() => navigate('/Login')}>Login</Button>
      </DivAzulMedio>
    </Container>
  );
};

export default BemVindo;
